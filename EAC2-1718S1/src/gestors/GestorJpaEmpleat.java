/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestors;

import java.util.List;
import javax.persistence.EntityManager;
import model.Empleat;

/**
 * Gestor d'empleat que proporciona operacions relcionades amb la persistencia als empleats per un determinat context de persistencia (EntityManager)
 * @author professor
 */
public class GestorJpaEmpleat {
    private EntityManager em = null;

    /**
     * Crea un gestor d'empleat que treballara amb l'EntityManager em
     * @param em context on es fan persistents els empleats
     */
    public GestorJpaEmpleat(EntityManager em) {
       this.em = em;
    }
    /**
     * Inserta un empleat <b>nou</b> en el context de persistencia. Si l'empleat ja hi existeix, es pot provocar una RuntimeException.
     * @param empleat empleat a inserir
     */
    //TODO implementar el metode
    public void inserir(Empleat empleat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Actualitza les dades de l'empleat. Si l'empleat no hi existeix, es provoca una excepcio
     * @param empleat empleat a actualitzar
     * @throws gestors.GestorJpaException si l'empleat no existeix en el context
     * de persistencia.
     * 
     */
    
    //TODO implementar el metode
    
    public void modificar(Empleat empleat) throws GestorJpaException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * Esborra l'empleat que te determinat codi
     * @param codiEmpleat codi de l'empleat a esborrar
     * @throws gestors.GestorJpaException si el codi no correspon a cap empleat del context de persistencia
     */
    //TODO implementar el metode
    public void eliminar(int codiEmpleat) throws GestorJpaException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Obte una llista amb tots els empleats del context de persistencia
     * @return llista amb els empleats persistents
     */
    
    //TODO implementar el metode
       public List<Empleat> obtenirEmpleats() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Obte l'empleat del context de persistencia amb un codi determinat
     * @param codiEmpleat codi de l'empleat a obtenir
     * @return empleat amb <b>codiEmpleat</b> o null si no hi ha cap empleat persistent amb aquest codi
     */
    //TODO implementar el metode
    public Empleat obtenirEmpleat(int codiEmpleat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Obte una llista amb tots els empleats del context de persistencia amb un nom determinat
     * @param nom nom dels empleats a cercar
     * @return llista amb els empleats seleccionats
     */
    //TODO implementar el metode
    public List<Empleat> obtenirEmpleatsPerNom(String nom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Obte una llista amb tots els empleats del context de persistencia que viuen en una ciutat determinada
     * @param ciutat nom de la ciutat on viuen els empleats seleccionats
     * @return llista amb els empleats seleccionats
     */
    //TODO implementar el metode
    public List<Empleat> obtenirEmpleatsPerCiutat(String ciutat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * Obte una llista amb tots els empleats del context de persistencia que treballen en un establiment amb un codi determinat
     * @param codiEstabliment codi de l'establiment on treballen els empleats seleccionats
     * @return llista amb els empleats seleccionats
     */
    
    //TODO implementar el metode
    public List<Empleat> obtenirEmpleatsDeEstabliment(int codiEstabliment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * Obte una llista amb tots els empleats del context de persistencia que treballen a la mateixa ciutat on viuen, es a dir, aquells als quals el nom de la seva ciutat i la ciutat del seu establiment coincideixen
     * @return llista amb els empleats seleccionats
     */  
    //TODO implementar el metode
    public List<Empleat> obtenirEmpleatsQueTreballenOnViuen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
