package generation.gestionaleEventi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import generation.gestionaleEventi.entities.Evento;
import generation.gestionaleEventi.entities.Locale;
import generation.gestionaleEventi.repositories.LocaleRepository;
@Service
public class LocaleService extends GenericService<Long, Locale,LocaleRepository>
{
    public Locale findByLocaleId(Long idLocale) {
        return getRepository().findById(idLocale).orElse(null);
    }

    public List<Locale> findByFilters(String nome, Long idLocale){
        if(idLocale > 0){
            return getRepository().findByFilters(nome, idLocale);
        }
        else{
            return getRepository().findByNomeContaining(nome);
        }
    }

    public List<Locale> findByGestoreId(Long idGestore) {
        return getRepository().findByGestoreId(idGestore);
    }
    
    
    public List<Evento> findByIdLocale(Long idLocale){
        return getRepository().findByIdGestore(idLocale);

}
}
