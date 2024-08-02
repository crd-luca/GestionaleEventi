package generation.GestionaleEventi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import generation.GestionaleEventi.entities.Evento;



public interface EventoRepository extends JpaRepository<Evento,Long>
 
{
    @Query("SELECT e FROM Evento e WHERE e.id = :idEvento")
    List<Evento> findByIdEvento(@Param("idEvento") Long idEvento);

    List<Evento> findByNome(String nome);

    @Query("SELECT e FROM Evento e WHERE e.nome LIKE(CONCAT('%', :nome, '%')) AND e.id = :idEvento")
    List<Evento> findByFilters(@Param("nome") String nome, @Param("idEvento") Long idEvento);

    List<Evento> findByNomeContaining(String nome);
}
