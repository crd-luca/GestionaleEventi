package generation.GestionaleEventi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import generation.GestionaleEventi.entities.Locale;

public interface LocaleRepository extends JpaRepository<Locale, Long>
{
    
}
