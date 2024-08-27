package generation.GestionaleEventi.services;

import generation.GestionaleEventi.entities.Cliente;
import generation.GestionaleEventi.repositories.ClienteRepository;

public class ClienteService extends GenericService<Long,Cliente, ClienteRepository>
{
    public void createOrUpdateUser(Long id, String username, String password){
        if(getRepository().existsById(id)){
            Cliente c = getRepository().findById(id).orElse(null);
            c.setUsername(username);
            c.setPassword(password);
            getRepository().save(c);
        }   
        else{
            //Feedback 
        }
    }
}

    /**
     * La classe ClienteService estende la classe GenericService, che a sua volta estende JpaRepository.
     * Questa classe permette di effettuare operazioni CRUD su un'entità Cliente, tramite il suo repository ClienteRepository.
     * L'entità Cliente è un tipo di Persona che può accedere al sistema tramite username e password.
     * Il metodo createOrUpdateUser permette di creare o aggiornare un utente Cliente.
     * Se l'utente esiste già, viene sovrascritto il suo username e password. Se l'utente non esiste, viene creato un nuovo utente con
     * il dato username e password.
     */
