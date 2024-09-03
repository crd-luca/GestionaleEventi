package generation.gestionaleEventi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import generation.gestionaleEventi.entities.Evento;
import generation.gestionaleEventi.entities.Locale;

public interface LocaleRepository extends JpaRepository<Locale, Long> {

    @Query("SELECT l FROM Locale l WHERE l.gestore.id = :gestoreId")
    List<Locale> findByGestoreId(@Param("gestoreId") Long gestoreId);

    List<Locale> findByNome(String nome);

    @Query("SELECT l FROM Locale l WHERE l.nome LIKE(CONCAT('%', :nome, '%')) AND l.id = :idLocale")
    List<Locale> findByFilters(@Param("nome") String nome, @Param("idLocale") Long idLocale);

    List<Locale> findByNomeContaining(String nome);

       @Query("SELECT l FROM Locale l INNER JOIN Gestore g ON l.gestore.id = g.id WHERE g.id = :idGestore")
    public List<Evento> findByIdGestore(Long idGestore);

}
