package generation.GestionaleEventi.entities;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "persone")
@Inheritance(strategy = InheritanceType.JOINED) // Chi estenderà la classe Persona sarà, lato DB, una tabella che usa
                                                // l'id della tabella persone come chiave primaria e come chiave esterna
                                                // allo stesso tempo
@Data

public class Persona implements GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "data_nascita")
    private Date dataNascita;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

      
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "partecipanti",
        joinColumns = {@JoinColumn(name = "id_utente")},
        inverseJoinColumns = {@JoinColumn(name = "id_evento")}
    )
    private List<Evento> eventi;
}
