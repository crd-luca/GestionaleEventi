package generation.gestionaleEventi.entities;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;
@Entity
@Table(name = "evento")
@Data
 @ToString(exclude = {"locale","persone"})
public class Evento implements GenericEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome_evento")
    private String nome;

    @Column(name = "capienza")
    private int capienza;

  

    @Column(name = "orario")
    private String orario;

    @Column(name = "giorno")
    private Date giorno;

    @Column(name = "prezzo")
    private double prezzo;

    @Column(name = "immagine_url")  // Nuova colonna per l'URL dell'immagine
    private String immagineUrl;
    
    @ManyToOne
    @JoinColumn(name = "id_locale")
    private Locale locale;
      
    @ManyToMany(mappedBy = "eventi")
    private List<Persona> persone;
}
