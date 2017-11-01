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
import model.PuntVenda;

/**
 * Gestor de punt de venda que proporciona operacions relcionades amb la persistencia als punts de venda per un determinat context de persistencia (EntityManager)
 * @author professor
 */
public class GestorJpaPuntVenda {
    private EntityManager em = null;

    /**
     * Crea un gestor de punt venda que treballara amb l'EntityManager em
     * @param em context on es fan persistents els tallers
     */
    public GestorJpaPuntVenda(EntityManager em) {
       this.em = em;
    }

    /**
     * Obte una llista amb tots els punts de venda del context de persistencia
     * @return llista amb els punts de venda persistents
     */
    
    //TODO implementar el metode
    public List<PuntVenda> obtenirPuntsVenda() {
        Query q = em.createNamedQuery("PuntVenda.obtenirPuntsVenda", PuntVenda.class);
        
        em.getTransaction().begin();
        em.flush();
        em.getTransaction().commit();
        
        return q.getResultList();
    }

    /**
     * Esborra tots els punts de venda d'una ciutat determinada
     * @param ciutat nom de la ciutat a que pertanyen els punts de venda a esborrar
     */
    //TODO implementar el metode
    public void esborraPuntsVenda(String ciutat) {
        Query q = em.createNamedQuery("PuntVenda.esborraPuntsVenda", PuntVenda.class);
        q.setParameter("ciutat", ciutat);
        
        em.getTransaction().begin();
        em.flush();
        em.getTransaction().commit();
    }
}
