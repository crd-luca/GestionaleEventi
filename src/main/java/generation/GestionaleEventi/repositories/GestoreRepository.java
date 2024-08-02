package generation.GestionaleEventi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import generation.GestionaleEventi.entities.Gestore;
import generation.GestionaleEventi.entities.Persona;

public interface GestoreRepository extends JpaRepository<Gestore, Long>
{
    Persona findByEmailAndPassword(String username,String password);
}
