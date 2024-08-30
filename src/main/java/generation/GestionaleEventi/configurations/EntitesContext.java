package generation.gestionaleEventi.configurations;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import generation.gestionaleEventi.entities.Cliente;
import generation.gestionaleEventi.entities.Gestore;

@Configuration
public class EntitesContext {
    
    @Bean
    /**
     * Il metodo @Bean annotato con @Scope("prototype") indica a Spring di creare una nuova istanza di Cliente ogni volta che viene richiesto tramite @Autowired o tramite @Qualifier.
     * Il metodo @Scope("prototype") è molto utile per creare oggetti che devono avere dati diversi all'interno di un'istanza.
     * In questo caso, ogni volta che viene chiamato il metodo newCliente, viene creata una nuova istanza di Cliente con i dati passati come parametro.
     * L'utilizzo di @Scope("prototype") è particolarmente utile quando si lavora con oggetti che devono avere dati diversi all'interno di un'istanza.
     * Un esempio di utilizzo di @Scope("prototype") è quello di creare oggetti che rappresentano entità che devono avere un ID univoco. In questo caso, ogni volta che viene creato un nuovo oggetto, l'ID viene generato automaticamente.
     */
    @Scope("prototype")
    public Cliente newCliente(Map<String,String> params){
        Cliente c = new Cliente();
        c.fromMap(params);

        return c;

    }

    @Bean
    @Scope("prototype")
    public Gestore newStudente(Map<String, String> params){
        Gestore s = new Gestore();
        s.fromMap(params);

        return s;
    }
}
