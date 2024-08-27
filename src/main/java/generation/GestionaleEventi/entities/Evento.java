package generation.gestionaleEventi.entities;

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
@Entity
@Table(name = "evento")
@Data
public class Evento implements GenericEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome_evento")
    private String nome;

    @Column(name = "capienza")
    private int capienza;

    @Column(name = "eta_minima")
    private int etaMinima;

    @Column(name = "orario")
    private String orario;

    @Column(name = "giorno")
    private String giorno;

    @Column(name = "prezzo")
    private double prezzo;
    
    @ManyToOne
    @JoinColumn(name = "id_locale")
    private Locale locale;
      
    @ManyToMany(mappedBy = "eventi")
    private List<Persona> persone;
}
