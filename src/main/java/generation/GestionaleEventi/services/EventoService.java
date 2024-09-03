package generation.gestionaleEventi.services;


import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import generation.gestionaleEventi.entities.Evento;
import generation.gestionaleEventi.repositories.EventoRepository;

@Service
public class EventoService extends GenericService<Long,Evento,EventoRepository>
{
    public List<Evento> findByEventoId(Long idEvento){
        return getRepository().findByIdEvento(idEvento);
    }

    public List<Evento> findByFilters(String nome, Long idEvento){
        if(idEvento > 0){
            return getRepository().findByFilters(nome, idEvento);
        }
        else{
            return getRepository().findByNomeContaining(nome);
        }
    }

    public List<Evento> findByGiorno(Date giorno){

        return getRepository().findByGiorno(giorno);
    }

    public List<Evento> findByIdGestore(Long idGestore){
        return getRepository().findByIdGestore(idGestore);

}
public void delete(Long id){
    getRepository().deleteById(id);
}
public void createOrUpdateUser(Long id, String nome, String orario, double prezzo, Date giorno){
    if(getRepository().existsById(id)){
        Evento e = getRepository().findById(id).orElse(null);
       e.setNome(nome);
       e.setOrario(orario);
       e.setPrezzo(prezzo);
       e.setGiorno(giorno);
        getRepository().save(e);
    }   
    else{
        //Feedback 
    }
}


}