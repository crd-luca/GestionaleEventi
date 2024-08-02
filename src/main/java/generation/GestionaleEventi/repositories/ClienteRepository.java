package generation.GestionaleEventi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import generation.GestionaleEventi.entities.Cliente;
import generation.GestionaleEventi.entities.Persona;

public interface ClienteRepository extends JpaRepository<Cliente,Long>
{
    Persona findByEmailAndPassword(String username,String password);
}
