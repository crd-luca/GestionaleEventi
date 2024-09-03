package generation.gestionaleEventi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import generation.gestionaleEventi.entities.Evento;
import generation.gestionaleEventi.entities.Gestore;
import generation.gestionaleEventi.entities.Persona;

public interface GestoreRepository extends JpaRepository<Gestore, Long>
{
    Persona findByEmailAndPassword(String username,String password);
/*select l.*
from locale l inner join gestore g on l.id_gestore=g.id */
     
}
