package generation.gestionaleEventi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import generation.gestionaleEventi.entities.Evento;


@Repository
public interface EventoRepository extends JpaRepository<Evento,Long>
{
    /**
     * Questa interfaccia rappresenta un repository JPA per l'entità Evento.
     * Un repository JPA è una specifica di Spring Data JPA.
     * Spring Data JPA fornisce un'API per l'accesso ai dati JPA (Java Persistence API) tramite Spring Data.
     * 
     * In questa interfaccia, viene dichiarato che la classe EventoRepository estende l'interfaccia JpaRepository,
     * che è una specifica di Spring Data JPA.
     * Questa interfaccia fornisce metodi generici per le operazioni di base CRUD (CREATE, READ, UPDATE, DELETE).
     * 
     * Inoltre, viene dichiarato un metodo personalizzato findByIdEvento, che restituisce un elenco di Eventi che ha un id specifico.
     * Questo metodo viene utilizzato per cercare un evento tramite il suo id.
     * 
     * Un repository JPA è una classe che fornisce un'interfaccia per l'accesso ai dati della tabella corrispondente all'entità.
     * Questo repository è utilizzato dai servizi per inserire, modificare e cancellare dati da e verso la tabella.
     * 
     * Inoltre, viene dichiarato un metodo personalizzato findByNome, che restituisce un elenco di Eventi che ha un nome specifico.
     * Questo metodo viene utilizzato per cercare un evento tramite il suo nome.
     * 
     * Infine, viene dicharato un metodo personalizzato findByFilters, che restituisce un elenco di Eventi che ha un nome specifico e un id specifico.
     * Questo metodo viene utilizzato per cercare un evento tramite il suo nome e il suo id.
     * 
     * Questa interfaccia è registrata nel contesto di Spring tramite l'annotazione @Repository.
     * In questo modo, l'applicazione può utilizzare il repository per accedere ai dati dell'entità Evento.
     */
    @Query("SELECT e FROM Evento e WHERE e.id = :idEvento")
    List<Evento> findByIdEvento(@Param("idEvento") Long idEvento);

    List<Evento> findByNome(String nome);

    @Query("SELECT e FROM Evento e WHERE e.nome LIKE(CONCAT('%', :nome, '%')) AND e.id = :idEvento")
    List<Evento> findByFilters(@Param("nome") String nome, @Param("idEvento") Long idEvento);

    List<Evento> findByNomeContaining(String nome);
}
