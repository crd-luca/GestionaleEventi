package generation.gestionaleEventi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import generation.gestionaleEventi.entities.Persona;
import generation.gestionaleEventi.repositories.ClienteRepository;
import generation.gestionaleEventi.repositories.GestoreRepository;

@Service
public class LogInService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private GestoreRepository gestoreRepository;

    public Persona login(String email, String password){
        Persona p = null;
        
        p = clienteRepository.findByEmailAndPassword(email, password);
        if(p !=null){
            return p;
        }

        p = gestoreRepository.findByEmailAndPassword(email, password);
        if(p != null){
            return p;
        }

        return p;
    }
}
