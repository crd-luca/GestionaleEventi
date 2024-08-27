package generation.GestionaleEventi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import generation.GestionaleEventi.entities.GenericEntity;
import lombok.Data;

@Data
public abstract class GenericService <ID, E extends GenericEntity, J extends JpaRepository<E, ID>>
{
     @Autowired
    private J repository;

    public List<E> findAll(){
        return repository.findAll();
    }


    public E findById(ID id){
        
        // if(repository.existsById(id)){
        //     return repository.findById(id).get();
        // }
    
        return repository.findById(id).orElse(null);
    }

   
 public E creatOrUpdate(E e){
        return repository.save(e);
    }

    public void delete(ID id){
        repository.deleteById(id);
    }

    public J getRepository(){
        return repository;
    }
}

/**
 * La classe GenericService è una classe astratta che definisce un servizio generico per le entità di tipo E.
 * Questa classe viene utilizzata come base per la creazione di servizi specifici per ogni entità.
 * La classe GenericService estende l'interfaccia JpaRepository, che è una specifica di Spring Data JPA.
 * Spring Data JPA fornisce un'API per l'accesso ai dati JPA (Java Persistence API) tramite Spring Data.
 * 
 * La classe GenericService ha due generici:
 * - ID: rappresenta il tipo di dati dell'identificativo dell'entità (in questo caso Long)
 * - E: rappresenta l'entità che si vuole gestire (in questo caso Cliente, Gestore, Organizzatore, Utente)
 * - J: rappresenta il repository dell'entità (in questo caso ClienteRepository, GestoreRepository, OrganizzatoreRepository, UtenteRepository)
 * 
 * La classe GenericService ha un campo repository di tipo J, che rappresenta il repository dell'entità.
 * Il campo repository viene inizializzato tramite l'iniezione di dipendenze di Spring.
 * 
 * La classe GenericService fornisce metodi generici per le operazioni di base CRUD (CREATE, READ, UPDATE, DELETE).
 * 
 * Ad esempio:
 * - findAll(): restituisce tutti gli elementi dell'entità.
 * - findById(ID id): restituisce l'elemento dell'entità con l'identificativo specificato.
 * - creatOrUpdate(E e): crea o aggiorna l'elemento dell'entità con i dati specificati.
 * - delete(ID id): cancella l'elemento dell'entità con l'identificativo specificato.
 * - getRepository(): restituisce il repository dell'entità.
 * 
 * La classe GenericService è stata creata per riutilizzare il codice comune tra i servizi specifici delle varie entità
 * e per semplificare la programmazione.
 * 
 */
