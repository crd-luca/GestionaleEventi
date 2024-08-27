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
    public String registrationPage(Model model) {
        if (appService.getMessage() != null) {
            model.addAttribute("message", appService.getMessage());
            appService.setMessage(null);
        }
        return "register.html";
    }

    @PostMapping("/register-form")
    public String register(@RequestParam Map<String, String> params, HttpSession session) {
        String nome = params.get("nome");
        String cognome = params.get("cognome");
        String email = params.get("email");
        String username = params.get("username");
        String password = params.get("password");
        String role = params.get("role");

        Persona p = registrationService.register(nome, cognome, email, username, password, role);
        if (p != null) {
            session.setAttribute("user", p);
            if ("CLIENTE".equalsIgnoreCase(role)) {
                session.setAttribute("role", "CLIENTE");
                return "redirect:/area-cliente";
            } else if ("GESTORE".equalsIgnoreCase(role)) {
                session.setAttribute("role", "GESTORE");
                return "redirect:/area-gestore";
            }
        }
        appService.setMessage("Errore nella registrazione");
        return "redirect:/registrationpage";
    }
}
