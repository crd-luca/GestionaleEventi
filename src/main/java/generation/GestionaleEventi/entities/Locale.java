package generation.GestionaleEventi.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "locale")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Locale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "indirizzo")
    private String indirizzo;

    @Column(name = "p_iva")
    private String pIva;

    @ManyToOne
    @JoinColumn(name = "id_gestore")
    private Gestore gestore;
    
    @OneToMany(mappedBy = "locale")
    private List<Evento> eventi;

}
