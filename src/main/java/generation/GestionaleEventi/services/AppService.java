package generation.GestionaleEventi.services;
import org.springframework.stereotype.Service;

import lombok.Data;

@Service
@Data
public class AppService {
    private String message;
}

/**
 * Questa classe è un servizio di Spring.
 * Spring utilizza l'annotazione @Service per identificare una classe come un servizio che può essere utilizzato da altre classi.
 * In questo caso, la classe AppService è un servizio che fornisce un'interfaccia per gestire l'applicazione GestionaleEventi.
 * La classe AppService ha un campo message di tipo String che rappresenta un messaggio da visualizzare all'utente.
 * La classe AppService è annotata con l'annotazione @Data che genera automaticamente i metodi getter e setter per il campo message.
 * La classe AppService è registrata nel contesto di Spring tramite l'annotazione @Service.
 * In questo modo, l'applicazione può utilizzare il servizio AppService per accedere al messaggio da visualizzare all'utente.
 */
