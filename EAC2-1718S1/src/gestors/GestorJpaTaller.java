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
import model.Taller;

/**
 * Gestor de taller que proporciona operacions relcionades amb la persistencia als tallers per un determinat context de persistencia (EntityManager)
 * @author professor
 */
public class GestorJpaTaller {
    private EntityManager em = null;

    /**
     * Crea un gestor de taller que treballara amb l'EntityManager em
     * @param em context on es fan persistents els tallers
     */
    public GestorJpaTaller(EntityManager em) {
       this.em = em;
    }

    /**
     * Obte una llista amb tots els tallers del context de persistencia
     * @return llista amb els tallers persistents
     */
    
    //TODO implementar el metode
    public List<Taller> obtenirTallers() {
        Query q = em.createNamedQuery("Taller.obtenirTallers", Taller.class);
        
        em.getTransaction().begin();
        em.flush();
        em.getTransaction().commit();
        
        return q.getResultList();
    }

    /**
     * Afegeix una quanitat al nombre de maquines de tots els tallers d'una ciutat determinada
     * @param ciutat nom de la ciutat a que pertanyen els tallers a actualitzar
     * @param increment quantitat a afegir. Pot ser positiva o negativa.
     */
    //TODO implementar el metode
    public void incrementaMaquines(String ciutat, int increment) {
        Query q = em.createNativeQuery("UPDATE Establiment t SET (t.num_maquines  = :num_maquines) "
                                    + "WHERE TYPE(t) LIKE 'taller' AND t.ciutat LIKE :ciutat", Empleat.class);
        q.setParameter("num_maquines", increment);
        q.setParameter("ciutat", ciutat);
        q.executeUpdate();
        
        em.getTransaction().begin();
        em.flush();
        em.getTransaction().commit();
        em.close();
    }


}
