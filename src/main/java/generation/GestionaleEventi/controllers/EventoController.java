package generation.gestionaleEventi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import generation.gestionaleEventi.entities.Evento;
import generation.gestionaleEventi.services.EventoService;

import java.io.File;
import java.io.IOException;
import jakarta.servlet.http.HttpSession;

@Controller
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping("/createEvent")
    public String createEvent(Evento evento, MultipartFile imageFile, HttpSession session, Model model) {
        // Controlla se l'utente è un gestore
        String userRole = (String) session.getAttribute("role");
        if (!"GESTORE".equals(userRole)) {
            model.addAttribute("message", "Non hai i permessi per creare un evento.");
            return "errorPage";
        }

        // Percorso dove salvare le immagini (aggiorna questo percorso in base alla tua configurazione)
        String uploadDir = "/path/to/images/";

        try {
            // Salva il file sul server
            String fileName = imageFile.getOriginalFilename();
            File file = new File(uploadDir + fileName);
            imageFile.transferTo(file);

            // Imposta il percorso dell'immagine nell'evento
            evento.setImmagineUrl("/images/" + fileName);

            // Salva l'evento nel database
            eventoService.creatOrUpdate(evento);

            model.addAttribute("message", "Evento creato con successo!");
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Errore durante la creazione dell'evento.");
        }

        return "redirect:/areaGestione";
    }
}
