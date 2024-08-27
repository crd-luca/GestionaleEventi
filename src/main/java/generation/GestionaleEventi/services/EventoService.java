package generation.gestionaleEventi.services;

import java.util.List;

import generation.gestionaleEventi.entities.Evento;
import generation.gestionaleEventi.repositories.EventoRepository;

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

}
