package generation.GestionaleEventi.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;




@Entity
@Table(name = "gestore")
@Data
public class Gestore extends Persona {
    @OneToMany(mappedBy = "gestore")
    private List<Locale> locali;
    private List<Evento> eventi;
}
