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
import generation.gestionaleEventi.services.EventoService;
import generation.gestionaleEventi.services.LogInService;
import jakarta.servlet.http.HttpSession;

@Controller
public class LogInController {
    @Autowired
    private EventoService eventoService;
    @Autowired
    private LogInService loginService;

    @Autowired
    private AppService appService;

    @GetMapping("/loginpage")
    public String loginpage(Model model) {
        if(appService.getMessage() != null){
            model.addAttribute("message", appService.getMessage());
            appService.setMessage(null);
        }
        return "login.html";
    }

    @PostMapping("/login")
    public String login(@RequestParam Map<String, String> params, HttpSession session, Model model) {
        String email = params.get("email");
        String password = params.get("password");
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
    
        Persona p = loginService.login(email, password);
        if (p != null) {
            System.out.println("Persona trovata: " + p.getClass().getName());
            if (p instanceof Gestore) {
                Gestore gestore = (Gestore) p;
                System.out.println("Nome Gestore: " + gestore.getNome()); // Assicurati che getNome() esista e ritorni il nome
                session.setAttribute("role", "gestore");
                session.setAttribute("persona", p);
                model.addAttribute("gestore", p);
                return "redirect:/area-gestore";
            } else if (p instanceof Cliente) {
                Cliente cliente = (Cliente) p;
                System.out.println("Nome Cliente: " + cliente.getNome());
                session.setAttribute("role", "cliente");  // Usa "CLIENTE" per mantenere la consistenza
                session.setAttribute("persona", p);
                model.addAttribute("cliente", p);
                return "redirect:/area-cliente";
            }
            
        }
        System.out.println("Login fallito o tipo di persona non gestore");
        model.addAttribute("message", "Le credenziali sono errate");
        return "errorPage";
    }
    
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
