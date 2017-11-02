/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestors;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
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
        em.getTransaction().begin();
        em.persist(empleat);
        em.getTransaction().commit();
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
        em.getTransaction().begin();
        em.persist(empleat);
        em.getTransaction().commit();
    }
    /**
     * Esborra l'empleat que te determinat codi
     * @param codiEmpleat codi de l'empleat a esborrar
     * @throws gestors.GestorJpaException si el codi no correspon a cap empleat del context de persistencia
     */
    //TODO implementar el metode
    public void eliminar(int codiEmpleat) throws GestorJpaException{
        em.getTransaction().begin();
        
        Query q = em.createNamedQuery("Empleat.obtenirEmpleat", Empleat.class);
        q.setParameter("codiEmpleat", codiEmpleat);
        em.remove(q.getSingleResult());
        
        em.getTransaction().commit();
    }

    /**
     * Obte una llista amb tots els empleats del context de persistencia
     * @return llista amb els empleats persistents
     */
    
    //TODO implementar el metode
    public List<Empleat> obtenirEmpleats() {
        Query q = em.createNamedQuery("Empleat.obtenirEmpleats", Empleat.class);
        
        return q.getResultList();
    }

    /**
     * Obte l'empleat del context de persistencia amb un codi determinat
     * @param codiEmpleat codi de l'empleat a obtenir
     * @return empleat amb <b>codiEmpleat</b> o null si no hi ha cap empleat persistent amb aquest codi
     */
    //TODO implementar el metode
    public Empleat obtenirEmpleat(int codiEmpleat) {
        //Query q = em.createNamedQuery("Empleat.obtenirEmpleat", Empleat.class);
        //q.setParameter("codiEmpleat", codiEmpleat);
        
        return em.find(Empleat.class, codiEmpleat);
    }

    /**
     * Obte una llista amb tots els empleats del context de persistencia amb un nom determinat
     * @param nom nom dels empleats a cercar
     * @return llista amb els empleats seleccionats
     */
    //TODO implementar el metode
    public List<Empleat> obtenirEmpleatsPerNom(String nom) {
        Query q = em.createNamedQuery("Empleat.obtenirEmpleatsPerNom", Empleat.class);
        q.setParameter("nom", nom);
        
        return q.getResultList();
    }
    
    /**
     * Obte una llista amb tots els empleats del context de persistencia que viuen en una ciutat determinada
     * @param ciutat nom de la ciutat on viuen els empleats seleccionats
     * @return llista amb els empleats seleccionats
     */
    //TODO implementar el metode
    public List<Empleat> obtenirEmpleatsPerCiutat(String ciutat) {
        Query q = em.createNamedQuery("Empleat.obtenirEmpleatsPerCiutat", Empleat.class);
        q.setParameter("ciutat", ciutat);
        
        return q.getResultList();
    }
    /**
     * Obte una llista amb tots els empleats del context de persistencia que treballen en un establiment amb un codi determinat
     * @param codiEstabliment codi de l'establiment on treballen els empleats seleccionats
     * @return llista amb els empleats seleccionats
     */
    
    //TODO implementar el metode
    public List<Empleat> obtenirEmpleatsDeEstabliment(int codiEstabliment) {
        Query q = em.createNamedQuery("Empleat.obtenirEmpleatsDeEstabliment", Empleat.class);
        q.setParameter("codiEstabliment", codiEstabliment);
        
        return q.getResultList();
    }
    /**
     * Obte una llista amb tots els empleats del context de persistencia que treballen a la mateixa ciutat on viuen, 
     * es a dir, aquells als quals el nom de la seva ciutat i la ciutat del seu establiment coincideixen
     * @return llista amb els empleats seleccionats
     */  
    //TODO implementar el metode
    public List<Empleat> obtenirEmpleatsQueTreballenOnViuen() {
        Query q = em.createNamedQuery("Empleat.obtenirEmpleatsQueTreballenOnViuen", Empleat.class);
        
        return q.getResultList();
    }
}
