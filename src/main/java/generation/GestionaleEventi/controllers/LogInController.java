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

    @GetMapping("/login")
    public String loginpage(Model model) {
        if(appService.getMessage() != null){
            model.addAttribute("message", appService.getMessage());
            appService.setMessage(null);
        }
        return "login.html";
    }

    // Metodo appService ha un campo message di tipo String.
    // Questo campo viene utilizzato per passare un messaggio da visualizzare all'utente.
    // Ad esempio, se l'accesso non è riuscito, viene settato il messaggio di errore.
    // Quando viene chiamato il metodo loginpage, viene controllato il valore di appService.getMessage().
    // Se appService.getMessage() è diverso da null, viene aggiunto il messaggio al modello e viene settato appService.getMessage(null).
    // In questo modo, quando viene chiamato il metodo loginpage, viene mostrato il messaggio all'utente se è presente.
    

    @PostMapping("/login-form")
    public String login(@RequestParam Map<String,String> params, HttpSession session ) {
        Persona p = logInService.login(params.get("email"), params.get("password"));
        if(p != null){
            if(p instanceof Cliente){
                session.setAttribute("role", "CLIENTE");
                session.setAttribute("role", p);
                return "redirect:/";
            }
            else if(p instanceof Gestore){
                session.setAttribute("role", "GESTORE");
                session.setAttribute("role", p);
                return "redirect:/";
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

/**
 * La classe LogInController è un controller di Spring.
 * Spring utilizza l'annotazione @Controller per identificare una classe come un controller di Spring.
 * In questo caso, la classe LogInController è un controller che fornisce le operazioni di login e logout dell'applicazione GestionaleEventi.
 * La classe LogInController ha un campo appService di tipo AppService, che è un servizio di Spring che fornisce un'interfaccia per gestire l'applicazione.
 * La classe LogInController ha un campo logInService di tipo LogInService, che è un servizio di Spring che fornisce un'interfaccia per gestire l'autenticazione dell'utente.
 * La classe LogInController ha un metodo login(@RequestParam Map<String,String> params, HttpSession session) che viene richiamato quando l'utente fa click sul bottone "login" della pagina di login.
 * Questo metodo prende in input il nome utente e la password dell'utente e controlla se esistono un utente con queste credenziali.
 * Se esiste un utente con queste credenziali, il metodo imposta due attributi nella sessione http: "role" e l'utente stesso.
 * La sessione http è un oggetto che viene utilizzato per gestire lo stato dell'utente tra le richieste http.
 * Ogni richiesta http ha una sessione http associata.
 * Le sessioni http vengono utilizzate per gestire l'autenticazione dell'utente, il caricamento dei dati tra le richieste http ed il caricamento dei dati sul client.
 * In questo caso, la sessione http viene utilizzata per gestire l'autenticazione dell'utente.
 * Infatti, se l'utente ha effettuato il login, la sessione http ha un attributo "role" che contiene il ruolo dell'utente (CLIENTE o GESTORE).
 * Inoltre, la sessione http ha un attributo "user" che contiene l'utente stesso.
 * Questi attributi vengono utilizzati per gestire l'accesso alle risorse dell'applicazione.
 * Infatti, se l'utente ha effettuato il login, l'applicazione gli permette di accedere alle risorse riservate per il suo ruolo.
 * Inoltre, se l'utente fa click sul bottone "logout", il metodo logout(HttpSession session) viene richiamato.
 * Questo metodo invalidate la sessione http, cioè cancella tutti gli attributi della sessione http.
 * In questo modo, l'utente viene disconnesso dall'applicazione.
 */
