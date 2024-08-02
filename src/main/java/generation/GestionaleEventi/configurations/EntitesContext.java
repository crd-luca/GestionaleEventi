package generation.GestionaleEventi.configurations;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import generation.GestionaleEventi.entities.Cliente;

@Configuration
public class EntitesContext {
    
    @Bean
    @Scope("prototype")
    public Cliente newCliente(Map<String,String> params){
        Cliente c = new Cliente();
        c.fromMap(params);

        return c;

    }
}
