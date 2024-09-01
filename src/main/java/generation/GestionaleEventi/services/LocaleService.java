package generation.gestionaleEventi.services;

import java.util.List;

import org.springframework.stereotype.Service;

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

    public List<Locale> findByGestoreId(Long gestoreId) {
        return getRepository().findByGestoreId(gestoreId);
    }
    
}
