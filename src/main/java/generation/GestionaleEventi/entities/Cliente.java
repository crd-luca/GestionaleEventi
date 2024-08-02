package generation.GestionaleEventi.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "cliente")
@Data
// @ToString(exclude = {"classe"}, callSuper = true)

public class Cliente extends Persona{
    
    List<Evento> eventi;
}
