package generation.gestionaleEventi.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import generation.gestionaleEventi.entities.Cliente;
import generation.gestionaleEventi.entities.Gestore;
import generation.gestionaleEventi.entities.Persona;
import generation.gestionaleEventi.services.AppService;
import generation.gestionaleEventi.services.LogInService;
import jakarta.servlet.http.HttpSession;

@Controller
public class LogInController {
    
    @Autowired
    private LogInService logInService;

    @Autowired
    private AppService appService;

    @GetMapping("/login")
    public String loginpage(Model model) {
        if(appService.getMessage() != null){
            model.addAttribute("message", appService.getMessage());
            appService.setMessage(null);
        }
        return "login.html";
    }

    @PostMapping("/login-form")
    public String login(@RequestParam Map<String,String> params, HttpSession session ) {
        Persona p = logInService.login(params.get("email"), params.get("password"));
        if(p != null){
            if(p instanceof Cliente){
                session.setAttribute("role", "CLIENTE");
                session.setAttribute("user", p);
                return "areaCliente.html";
            } else if(p instanceof Gestore){
                session.setAttribute("role", "GESTORE");
                session.setAttribute("user", p);
                return "areaGestione.html";
            }
        }
        appService.setMessage("Errore credenziali errate");
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/loginpage";
    }
}
