package generation.gestionaleEventi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import generation.gestionaleEventi.entities.Cliente;
import generation.gestionaleEventi.entities.Gestore;
import generation.gestionaleEventi.entities.Persona;
import generation.gestionaleEventi.services.AppService;
import generation.gestionaleEventi.services.GestoreService;
import generation.gestionaleEventi.services.LocaleService;
import jakarta.servlet.http.HttpSession;
@Controller
public class PersonaController {
   @Autowired
    private LocaleService localeService;
   
    @Autowired
    private ApplicationContext context;
   
    @GetMapping("/area-gestore")
    public String areaGestore(HttpSession session, Model model) {
        Persona p = (Persona) session.getAttribute("persona");
        String role = (String) session.getAttribute("role");

        if (role != null && role.equals("gestore") && p instanceof Gestore) {
            Gestore gestore = (Gestore) p;
            model.addAttribute("persona", gestore);
            model.addAttribute("gestore", gestore);

            // Usa il metodo corretto per ottenere i locali
            model.addAttribute("listalocali", localeService.findByGestoreId(gestore.getId()));

            // Altri attributi e logica

            return "areaGestione"; // Assicurati che il template si chiami correttamente
        }

        session.invalidate();
        return "redirect:/loginpage";
    }

    @GetMapping("/area-cliente")
    public String areaCliente(HttpSession session, Model model){
        Persona p = (Persona)session.getAttribute("persona");
        String role = (String)session.getAttribute("role");

        if(role != null && role.equals("cliente") && p instanceof Cliente){
            model.addAttribute("persona", (Cliente)p);
            model.addAttribute("cliente", (Cliente) p); // Aggiungi questa riga per impostare il gestore

            AppService as = context.getBean(AppService.class);
            if(as.getMessage() != null){
                model.addAttribute("message", as.getMessage());
                as.setMessage(null);
            }

            return "areaCliente.html";
        }
        session.invalidate();
        return "redirect:/loginpage";
    }
}
