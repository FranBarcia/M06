/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestors;

import java.util.List;
import javax.persistence.EntityManager;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Afegeix una quanitat al nombre de maquines de tots els tallers d'una ciutat determinada
     * @param ciutat nom de la ciutat a que pertanyen els tallers a actualitzar
     * @param increment quantitat a afegir. Pot ser positiva o negativa.
     */
    //TODO implementar el metode
    public void incrementaMaquines(String ciutat, int increment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
