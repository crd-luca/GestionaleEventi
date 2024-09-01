package generation.gestionaleEventi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import generation.gestionaleEventi.entities.Evento;
import generation.gestionaleEventi.entities.Gestore;
import generation.gestionaleEventi.entities.Persona;
import generation.gestionaleEventi.repositories.EventoRepository;
import generation.gestionaleEventi.services.EventoService;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import jakarta.servlet.http.HttpSession;

@Controller
public class EventoController {
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private EventoService eventoService;

    @GetMapping("/creaEvento-form")
    public String creaEventoForm(HttpSession session, Model model) {
        String userRole = (String) session.getAttribute("role");
        if (!"gestore".equals(userRole)) {
            model.addAttribute("message", "Non hai i permessi per creare un evento.");
            return "errorPage";
        }
        return "creaEvento";   
    }
    @PostMapping("/createEvent")
    public String createEvent(
            @RequestParam("nome") String nome,
            @RequestParam(value = "capienza", required = false) Integer capienza,
            @RequestParam(value = "orario", required = false) String orario,
            @RequestParam("giorno") String giorno,
            @RequestParam(value = "prezzo", required = false) Double prezzo,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
            HttpSession session,
            Model model) {

        // Controlla se l'utente è un gestore
        String userRole = (String) session.getAttribute("role");
        if (!"gestore".equalsIgnoreCase(userRole)) {
            model.addAttribute("message", "Non hai i permessi per creare un evento.");
            return "errorPage";
        }

        // Percorso dove salvare le immagini
        String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images/";

        String fileName = null;

        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                // Salva il file sul server
                fileName = imageFile.getOriginalFilename();
                File file = new File(uploadDir + fileName);
                imageFile.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("message", "Errore durante il salvataggio dell'immagine.");
                return "errorPage";
            }
        }

        // Crea l'oggetto Evento
        Evento evento = new Evento();
        evento.setNome(nome);
        evento.setCapienza(capienza != null ? capienza : 0);  // Imposta valore predefinito se non fornito
        evento.setOrario(orario != null ? orario : "");       // Imposta valore predefinito se non fornito
        evento.setGiorno(Date.valueOf(giorno));
        evento.setPrezzo(prezzo != null ? prezzo : 0.0);      // Imposta valore predefinito se non fornito
        evento.setImmagineUrl(fileName != null ? "/images/" + fileName : "");  // Imposta URL immagine se presente

        // Salva l'evento nel database
        eventoService.creatOrUpdate(evento);

        model.addAttribute("message", "Evento creato con successo!");
        return "redirect:/areaGestione";
    }

    

   
  
    @GetMapping("/eventi/search")
    public String searchEventi(@RequestParam(value = "giorno", required = false) String giorno, Model model) {
        if (giorno== null) {
            model.addAttribute("error", "Data non valida");
            return "index"; // Assicurati di utilizzare lo stesso nome del template della tua home page
        }
        else{
        List<Evento> eventi = eventoRepository.findByGiorno(Date.valueOf(giorno));
        model.addAttribute("eventi", eventi);
        System.out.println("evento: " + eventi);
        model.addAttribute("giorno", Date.valueOf(giorno)); // Aggiungi un attributo per indicare che una ricerca è stata effettuata
        return "index"; // Assicurati di utilizzare lo stesso nome del template della tua home page
        }
    }
    
     @GetMapping("/area-gestore/eventi")
    public String areaEventi(HttpSession session, Model model) {
        Persona p = (Persona) session.getAttribute("persona");
        String role = (String) session.getAttribute("role");

        if (role != null && role.equals("gestore") && p instanceof Gestore) {
            Gestore gestore = (Gestore) p;
            model.addAttribute("persona", gestore);
            model.addAttribute("gestore", gestore);

            // Ottieni la lista degli eventi
            model.addAttribute("eventi", eventoService.findByEventoId(gestore.getId()));

            return "areaEventi"; // Nome del template
        }

        session.invalidate();
        return "redirect:/loginpage";
    }
}


