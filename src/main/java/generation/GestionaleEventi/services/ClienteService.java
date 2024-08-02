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
