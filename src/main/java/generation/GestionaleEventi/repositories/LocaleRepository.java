package generation.GestionaleEventi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import generation.GestionaleEventi.entities.Locale;

public interface LocaleRepository extends JpaRepository<Locale, Long>
{
     @Query("SELECT l FROM Locale l WHERE l.id = :idLocale")
    List<Locale> findByIdLocale(@Param("idLocale") Long idLocale);

    List<Locale> findByNome(String nome);

    @Query("SELECT l FROM Locale l WHERE l.nome LIKE(CONCAT('%', :nome, '%')) AND l.id = :idLocale")
    List<Locale> findByFilters(@Param("nome") String nome, @Param("idLocale") Long idLocale);

    List<Locale> findByNomeContaining(String nome);
}
