package generation.gestionaleEventi.controllers;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import generation.gestionaleEventi.entities.Evento;
import generation.gestionaleEventi.services.EventoService;


@Controller
public class AppController {
    
      @Autowired
    private EventoService eventoService;

    @GetMapping("/")
    public String root(Model model) {
        // Recupera tutti gli eventi dal database
        List<Evento> eventi = eventoService.findAll();
         // Ordinare per data
         eventi.sort(Comparator.comparing(Evento::getGiorno));
        // Aggiungi gli eventi al modello
        model.addAttribute("eventi", eventi);
        // Restituisci il nome della vista (index.html) senza estensione
        return "index";
    }
    
}
