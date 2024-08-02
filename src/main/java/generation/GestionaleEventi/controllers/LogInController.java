package generation.GestionaleEventi.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import generation.GestionaleEventi.entities.Cliente;
import generation.GestionaleEventi.entities.Gestore;
import generation.GestionaleEventi.entities.Persona;
import generation.GestionaleEventi.services.AppService;
import generation.GestionaleEventi.services.LogInService;
import jakarta.servlet.http.HttpSession;



@Controller
public class LogInController {
    
    @Autowired
    private LogInService logInService;

    @Autowired
    private AppService appService;

    @GetMapping("/loginpage")
    public String loginpage(Model model) {
        if(appService.getMessage() != null){
            model.addAttribute("message", appService.getMessage());
            appService.setMessage(null);
        }
        return "loginpage.html";
    }

    @PostMapping("/login")
    public String login(@RequestParam Map<String,String> params, HttpSession session ) {
        Persona p = logInService.login(params.get("username"), params.get("password"));
        if(p != null){
            if(p instanceof Cliente){
                session.setAttribute("role", "CLIENTE");
                session.setAttribute("role", p);
                return "redirect:/area-cliente";
            }
            else if(p instanceof Gestore){
                session.setAttribute("role", "GESTORE");
                session.setAttribute("role", p);
                return "redirect:/area-gestore";
            }
        }
        appService.setMessage("Errore credenziali errate");
        return "redirect:/loginpage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/loginpage";
    }
    
    
    
}
