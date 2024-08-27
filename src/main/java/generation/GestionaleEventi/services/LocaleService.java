package generation.GestionaleEventi.services;

import java.util.List;

import generation.GestionaleEventi.entities.Locale;
import generation.GestionaleEventi.repositories.LocaleRepository;

public class LocaleService extends GenericService<Long, Locale,LocaleRepository>
{
     public List<Locale> findByLocaleId(Long idLocale){
        return getRepository().findByIdLocale(idLocale);
    }

    public List<Locale> findByFilters(String nome, Long idLocale){
        if(idLocale > 0){
            return getRepository().findByFilters(nome, idLocale);
        }
        else{
            return getRepository().findByNomeContaining(nome);
        }
    }
}
