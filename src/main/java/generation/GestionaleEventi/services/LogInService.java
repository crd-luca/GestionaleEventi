package generation.GestionaleEventi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import generation.GestionaleEventi.entities.Persona;
import generation.GestionaleEventi.repositories.ClienteRepository;
import generation.GestionaleEventi.repositories.GestoreRepository;

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

        p = clienteRepository.findByEmailAndPassword(email, password);
        if(p != null){
            return p;
        }

        return p;
    }
}
