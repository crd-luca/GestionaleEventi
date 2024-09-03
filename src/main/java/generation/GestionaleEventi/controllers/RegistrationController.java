package generation.gestionaleEventi.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import generation.gestionaleEventi.entities.Persona;
import generation.gestionaleEventi.services.AppService;
import generation.gestionaleEventi.services.RegistrationService;
import jakarta.servlet.http.HttpSession;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private AppService appService;

    @GetMapping("/register")
    public String paginaRegistrazione(Model model) {
        if (appService.getMessage() != null && !appService.getMessage().isEmpty()) {
            model.addAttribute("message", appService.getMessage());
            appService.setMessage(""); // Pulisce il messaggio dopo averlo usato
        }
        return "register.html";
    }

    @PostMapping("/register-form")
    public String registra(@RequestParam Map<String, String> params, HttpSession session) {
        String nome = params.get("nome");
        String cognome = params.get("cognome");
        String email = params.get("email");
        String username = params.get("username");
        String password = params.get("password");
        String confermaPassword = params.get("confirm-password");
        String ruolo = params.get("role");

        // Controllo se la password e la conferma della password coincidono
        if (!password.equals(confermaPassword)) {
            appService.setMessage("Le password non corrispondono.");
            return "redirect:/register";
        }

        Persona persona = registrationService.register(nome, cognome, email, username, password, ruolo);
        if (persona != null) {
            session.setAttribute("user", persona);
            if ("CLIENTE".equalsIgnoreCase(ruolo)) {
                session.setAttribute("role", "CLIENTE");
                return "redirect:/area-cliente";
            } else if ("GESTORE".equalsIgnoreCase(ruolo)) {
                session.setAttribute("role", "GESTORE");
                return "redirect:/area-gestore";
            }
        }
        appService.setMessage("Errore nella registrazione.");
        return "redirect:/register";
    }
}
