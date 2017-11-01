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
import model.Establiment;

/**
 * Gestor d'establiment que proporciona operacions relcionades amb la persistencia als establiments per un determinat context de persistencia (EntityManager)
 * @author professor
 */
public class GestorJpaEstabliment {
    private EntityManager em = null;

    /**
     * Crea un gestor d'establiment que treballara amb l'EntityManager em
     * @param em context on es fan persistents els establiments
     */
    public GestorJpaEstabliment(EntityManager em) {
       this.em = em;
    }
    /**
     * Inserta un establiment <b>nou</b> en el context de persistencia. Si l'establiment ja hi existeix, es pot provocar una RuntimeException.
     * @param establiment establiment a inserir
     */
    //TODO implementar el metode
    public void inserir(Establiment establiment) {
        Query q = em.createNativeQuery("INSERT INTO Establiment e VALUES (:codi, :nom, :ciutat)", Establiment.class);
        q.setParameter("codi", establiment.getCodi());
        q.setParameter("nom", establiment.getNom());
        q.setParameter("ciutat", establiment.getCiutat());
        q.executeUpdate();
        
        em.getTransaction().begin();
        em.flush();
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Actualitza les dades de l'establiment. Si l'establiment no hi existeix, es provoca una excepcio
     * @param establiment establiment a actualitzar
     * @throws gestors.GestorJpaException si l'establiment no existeix en el context
     * de persistencia.
     * 
     */
    
    //TODO implementar el metode
    
    public void modificar(Establiment establiment) throws GestorJpaException{
        Query q = em.createNativeQuery("UPDATE Establiment e SET (e.nom = :nom, e.ciutat = :ciutat) WHERE e.codi = :codi", Establiment.class);
        q.setParameter("codi", establiment.getCodi());
        q.setParameter("nom", establiment.getNom());
        q.setParameter("ciutat", establiment.getCiutat());
        q.executeUpdate();
        
        em.getTransaction().begin();
        em.flush();
        em.getTransaction().commit();
        em.close();
    }
    /**
     * Esborra l'establiment que te determinat codi
     * @param codiEstabliment codi de l'establiment a esborrar
     * @throws gestors.GestorJpaException si el codi no correspon a cap establiment del context de persistencia
     */
    //TODO implementar el metode
    public void eliminar(int codiEstabliment) throws GestorJpaException{
        Query q = em.createNamedQuery("Establiment.eliminar", Establiment.class);
        q.setParameter("codiEstabliment", codiEstabliment);
        
        em.getTransaction().begin();
        em.flush();
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Obte una llista amb tots els establiments del context de persistencia
     * @return llista amb els establiments persistents
     */
    
    //TODO implementar el metode
    public List<Establiment> obtenirEstabliments() {
        Query q = em.createNamedQuery("Establiment.obtenirEstablments", Establiment.class);
        
        em.getTransaction().begin();
        em.flush();
        em.getTransaction().commit();
        
        return q.getResultList();
    }

    /**
     * Obte l'establiment del context de persistencia amb un codi determinat
     * @param codiEstabliment codi de l'establiment a obtenir
     * @return establiment amb <b>codiEstabliment</b> o null si no hi ha cap establiment persistent amb aquest codi
     */
    //TODO implementar el metode
    public Establiment obtenirEstabliment(int codiEstabliment) {
        Query q = em.createNamedQuery("Establiment.obtenirEstablment", Establiment.class);
        q.setParameter("codi", codiEstabliment);
        
        em.getTransaction().begin();
        em.flush();
        em.getTransaction().commit();
        
        return (Establiment)q.getSingleResult();
    }

    /**
     * Obte una llista amb tots els establiments del context de persistencia amb un nom determinat i que pertanyen a una classe determinada.
     * Pot haver mes d'un establiment amb el mateix nom (per exemple, essent un taller i l'altre punt de venda).
     * @param nom nom dels establiments a cercar
     * @param classe classe a que pertany l'establiment. S'espera que sigui Establiment, PuntVenda o Taller, pero no cal controlar-ho. 
     * @return llista amb els establiments seleccionats
     */
    //TODO implementar el metode
    public List<Establiment> obtenirEstablimentsPerNom(String nom, Class classe) {
        Query q = em.createNamedQuery("Establiment.obtenirEmpleatsPerCiutat", Establiment.class);
        q.setParameter("ciutat", nom);
        
        em.getTransaction().begin();
        em.flush();
        em.getTransaction().commit();
        
        return q.getResultList();
    }
    
    /**
     * Obte una llista amb tots els establiments del context de persistencia d'una ciutat determinada
     * @param ciutat nom de la ciutat dels establiments seleccionats
     * @return llista amb els establiments seleccionats
     */
    //TODO implementar el metode
    public List<Establiment> obtenirEstablimentsPerCiutat(String ciutat) {
        Query q = em.createNamedQuery("Establiment.obtenirEmpleatsPerCiutat", Establiment.class);
        q.setParameter("ciutat", ciutat);
        
        em.getTransaction().begin();
        em.flush();
        em.getTransaction().commit();
        
        return q.getResultList();
    }

}
