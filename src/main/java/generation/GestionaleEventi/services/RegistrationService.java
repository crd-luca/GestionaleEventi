package generation.gestionaleEventi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import generation.gestionaleEventi.entities.Cliente;
import generation.gestionaleEventi.entities.Gestore;
import generation.gestionaleEventi.entities.Persona;
import generation.gestionaleEventi.repositories.ClienteRepository;
import generation.gestionaleEventi.repositories.GestoreRepository;

@Service
public class RegistrationService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private GestoreRepository gestoreRepository;

    public Persona register(String nome, String cognome, String email, String username, String password, String role){
        Persona p = null;

        if("CLIENTE".equalsIgnoreCase(role)){
            Cliente cliente = new Cliente();
            cliente.setNome(nome);
            cliente.setCognome(cognome);
            cliente.setEmail(email);
            cliente.setUsername(username);
            cliente.setPassword(password);
            p = clienteRepository.save(cliente);
        } else if("GESTORE".equalsIgnoreCase(role)){
            Gestore gestore = new Gestore();
            gestore.setNome(nome);
            gestore.setCognome(cognome);
            gestore.setEmail(email);
            gestore.setUsername(username);
            gestore.setPassword(password);
            p = gestoreRepository.save(gestore);
        }
        return p;
    }
}
