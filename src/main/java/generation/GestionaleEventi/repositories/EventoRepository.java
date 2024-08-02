package generation.GestionaleEventi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import generation.GestionaleEventi.entities.Evento;

public interface EventoRepository extends JpaRepository<Evento,Long>
 
{
    
}
