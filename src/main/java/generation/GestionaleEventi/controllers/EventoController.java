package generation.gestionaleEventi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import generation.gestionaleEventi.entities.Evento;
import generation.gestionaleEventi.entities.Gestore;
import generation.gestionaleEventi.entities.Locale;
import generation.gestionaleEventi.entities.Persona;
import generation.gestionaleEventi.repositories.EventoRepository;
import generation.gestionaleEventi.services.AppService;
import generation.gestionaleEventi.services.EventoService;
import generation.gestionaleEventi.services.LocaleService;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpSession;

@Controller
public class EventoController {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private LocaleService localeService;
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private EventoService eventoService;
    @Autowired
    private AppService appService;

    @GetMapping("/creaEvento-form")
    public String creaEventoForm( @RequestParam ("idLocale") Long idLocale,HttpSession session, Model model) {
       model.addAttribute("idLocale", idLocale);
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
            @RequestParam (value = "idlocale", required = false) Long idlocale,
         
            HttpSession session,
            Model model) {
    
        // Verifica se l'utente è un gestore
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
    
        // Converti e valida il formato della data
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date;
        try {
            date = sdf.parse(giorno);
        } catch (ParseException e) {
            model.addAttribute("message", "Formato della data non valido. Usa il formato gg/mm/aaaa.");
            return "errorPage";
        }
        Locale locale=localeService.findById(idLocale);
     
    
        // Crea l'oggetto Evento
        Evento evento = new Evento();
        evento.setNome(nome);
        evento.setCapienza(capienza != null ? capienza : 0);  // Imposta valore predefinito se non fornito
        evento.setOrario(orario != null ? orario : "");       // Imposta valore predefinito se non fornito
        evento.setGiorno(new Date(date.getTime()));  // Converti java.util.Date a java.sql.Date
        evento.setPrezzo(prezzo != null ? prezzo : 0.0);      // Imposta valore predefinito se non fornito
        evento.setImmagineUrl(fileName != null ? "/images/" + fileName : "");  // Imposta URL immagine se presente
       evento.setLocale(locale);
    
        // Salva l'evento nel database
        eventoService.creatOrUpdate(evento);
    
        model.addAttribute("message", "Evento creato con successo!");
        return "redirect:/area-gestore/eventi";
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
        return "ricercaEventi"; 
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
            model.addAttribute("eventi", eventoService.findByIdGestore(gestore.getId()));

            return "areaEventi"; // Nome del template
        }

        session.invalidate();
        return "redirect:/loginpage";
    }

    @GetMapping("/evento/delete/{id}")
    public String delete(@PathVariable("id") Long idEvento, HttpSession session) {
        Persona p = (Persona) session.getAttribute("persona");
        String role = (String) session.getAttribute("role");
    
        if (role != null && role.equals("gestore") && p instanceof Gestore) {
            if (idEvento <= 0) {
                appService.setMessage("Errore: ID evento non valido");
                return "redirect:/area-gestore";
            } else {
                eventoService.delete(idEvento);
                appService.setMessage("Eliminazione avvenuta con successo");
                return "redirect:/area-gestore";
            }
        }
        appService.setMessage("Errore: richiesta non autorizzata");
        return "redirect:/loginpage";
    }

    @PostMapping("/evento/update")
    public String update(
            @RequestParam("id") Long id,
            @RequestParam("nome") String nome,
            @RequestParam("orario") String orario,
            @RequestParam("giorno") String giorno,
            @RequestParam("prezzo") Double prezzo,
            HttpSession session) {
        
        Persona p = (Persona)session.getAttribute("persona");
        String role = (String)session.getAttribute("role");
        AppService as = context.getBean(AppService.class);
        
        if(role != null && role.equals("gestore") && p instanceof Gestore) {
            try {
                Evento e = new Evento();
                e.setId(id);
                e.setNome(nome);
                e.setOrario(orario);
                e.setGiorno(Date.valueOf(giorno)); // Assicurati che la data sia nel formato 'yyyy-MM-dd'
                e.setPrezzo(prezzo);
    
                eventoService.creatOrUpdate(e);
                as.setMessage("Modifica avvenuta con successo");
                return "redirect:/area-gestore/eventi";
            } catch (IllegalArgumentException e) {
                as.setMessage("Errore nel formato dei dati forniti");
                return "redirect:/area-gestore/eventi";
            }
        }
        
        as.setMessage("Errore richiesta non autorizzata");
        return "redirect:/loginpage";
    }
}    