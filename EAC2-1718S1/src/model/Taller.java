/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Taller de l'empresa
 * @author professor
 */
//TODO posar les anotacions necessaries per fer la classe persistent
@Entity
@DiscriminatorValue(value="T")
@NamedQueries({
    @NamedQuery(name="Taller.obtenirTaller", query="SELECT t FROM Establiment t "
                                                + "WHERE TYPE(t) = Taller AND t.ciutat = :ciutat"),
    @NamedQuery(name="Taller.obtenirTallers", query="SELECT t FROM Establiment t WHERE TYPE(t) = Taller")
})
public class Taller extends Establiment implements Serializable{
    @Column (name="MAQUINES", nullable=false)
    private int nMaquines;
    @OneToMany
    @JoinColumn(name="punt_venda")
    private final List<PuntVenda> puntsVendaAssociats=new ArrayList<>();


    /**
     * Construeix un taller amb els valors per defecte de Java
     */
    public Taller() {
    }
   
    
    /**
     * Construeix un taller amb els parametres especificats
     * @param codi codi que identifica el taller entre tots els establiments
     * @param nom nom del taller
     * @param ciutat ciutat on s'ubica el taller
     * @param nMaquines nombre de maquines del taller
     */
    
    public Taller(int codi, String nom, String ciutat, int nMaquines) {
        super(codi, nom, ciutat);
        this.nMaquines = nMaquines;
    }

    /**
     * Actualitza el nombre de maquines
     * @param nMaquines nou valor per al nombre de maquines del taller
     */
    public void setNMaquines(int nMaquines) {
        this.nMaquines = nMaquines;
    }

    /**
     * Obte el nombre de maquines del taller
     * @return nombre de maquines que te el taller
     */
    public int getNMaquines() {
        return nMaquines;
    }
    
    /**
     * Obte la llista dels punts de venda que tenen associat el taller per a que els proporcioni servei postvenda
     * @return llista dels punts de venda associats al taller
     */
    public List<PuntVenda> getPuntsVendaAssociats() {
        return puntsVendaAssociats;
    }
    
    /**
     * Actualitza la llista dels punts de venda associats amb els valors d'una llista determinada
     * @param l llista amb els nous valors de la llista dels punts de venda associats
     */
    public void setPuntsVendaAssociats(List<PuntVenda> l) {
        puntsVendaAssociats.clear();
        puntsVendaAssociats.addAll(l);
    }    
    
}
