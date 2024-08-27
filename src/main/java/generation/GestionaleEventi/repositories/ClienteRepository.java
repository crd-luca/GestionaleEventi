package generation.gestionaleEventi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import generation.gestionaleEventi.entities.Cliente;
import generation.gestionaleEventi.entities.Persona;

public interface ClienteRepository extends JpaRepository<Cliente,Long>
{
    Persona findByEmailAndPassword(String username,String password);
}

/**
 * Questa interfaccia estende l'interfaccia JpaRepository, che è una specifica di Spring Data JPA.
 * Spring Data JPA fornisce un'API per l'accesso ai dati JPA (Java Persistence API) tramite Spring Data.
 * 
 * La classe ClienteRepository ha due generici:
 * - Cliente: rappresenta l'entità che si vuole gestire (in questo caso Cliente)
 * - Long: rappresenta il tipo di dati dell'identificativo dell'entità (in questo caso Long)
 * 
 * L'interfaccia estende JpaRepository, che è una specifica di Spring Data JPA.
 * Questa interfaccia fornisce metodi generici per le operazioni di base CRUD (CREATE, READ, UPDATE, DELETE).
 * 
 * Nel caso specifico di questa interfaccia, viene definito un metodo personalizzato:
 * - findByEmailAndPassword: restituisce un'istanza di Persona (che è padre di Cliente) che ha una email e una password specifiche.
 * 
 * È possibile utilizzare questa interfaccia in un Service che ha l'annotazione @Autowired per iniettare questa interfaccia.
 * 
 * Ad esempio:
 * 
 * @Autowired
 * private ClienteRepository clienteRepository;
 * 
 * e poi utilizzare il metodo findByEmailAndPassword:
 * 
 * Persona persona = clienteRepository.findByEmailAndPassword("email", "password");
 * 
 * Se l'email e la password non corrispondono ad alcuna persona, viene restituito null.
 * 
 * Se l'email e la password corrispondono ad una persona, viene restituito l'istanza di persona.
 */
