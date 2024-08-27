package generation.gestionaleEventi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import generation.gestionaleEventi.entities.Gestore;
import generation.gestionaleEventi.entities.Persona;

public interface GestoreRepository extends JpaRepository<Gestore, Long>
{
    Persona findByEmailAndPassword(String username,String password);
}
