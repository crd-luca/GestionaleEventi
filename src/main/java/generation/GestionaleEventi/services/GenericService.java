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
}
