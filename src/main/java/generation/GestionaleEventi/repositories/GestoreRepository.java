package generation.GestionaleEventi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import generation.GestionaleEventi.entities.Gestore;

public interface GestoreRepository extends JpaRepository<Gestore, Long>
{
    
}
