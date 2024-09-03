package generation.gestionaleEventi.controllers;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import generation.gestionaleEventi.entities.Evento;
import generation.gestionaleEventi.entities.Gestore;
import generation.gestionaleEventi.entities.Locale;
import generation.gestionaleEventi.services.GestoreService;
import generation.gestionaleEventi.services.LocaleService;
import jakarta.servlet.http.HttpSession;
@Controller
public class LocaleController {
    @Autowired
    private GestoreService gestoreService;
    @Autowired
    private LocaleService localeService;

     @GetMapping("/createLocale-form")
    public String creaLocaleForm( HttpSession session, Model model) {
       
        String userRole = (String) session.getAttribute("role");
        if (!"gestore".equals(userRole)) {
            model.addAttribute("message", "Non hai i permessi per creare un evento.");
            return "errorPage";
        }
        return "creaLocale";   
    }
       @PostMapping("/createLocale")
    public String createEvent(
            @RequestParam String nome,
            @RequestParam String indirizzo,
            @RequestParam String pIva,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,

         
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
       
     
    
        // Crea l'oggetto Evento
        Locale locale = new Locale();
        locale.setNome(nome);
       locale.setIndirizzo(indirizzo);
       locale.setPIva(pIva);
       locale.setImmagineUrl(fileName != null ? "/images/" + fileName : "");  // Imposta URL immagine se presente
       // locale.setGestore(gestore);
    
        // Salva l'evento nel database
        localeService.creatOrUpdate(locale);
    
        model.addAttribute("message", "Evento creato con successo!");
        return "redirect:/area-gestore/eventi";
    }
}
