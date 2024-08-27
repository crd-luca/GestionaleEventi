package generation.gestionaleEventi.entities;

public interface GenericEntity extends IMappable{
    
}

/**
 * Questa classe estende l'interfaccia IMappable perchè questa interfaccia 
 * definisce due metodi che possono essere utilizzati per valutare se una classe
 * estende IMappable e perchè estende IMappable. 
 * 
 * Il primo metodo prende in input una mappa e la analizza per verificare se le chiavi corrispondono
 * ai nomi delle proprietà della classe che estende IMappable. Se c'è corrispondenza, allora
 * viene assegnato il valore associato alla proprietà.
 * 
 * Il secondo metodo trasforma i valori delle proprietà della classe che estende IMappable
 * in una mappa dove le chiavi sono i nomi delle proprietà e i valori i valori ad esse associati.
 */
