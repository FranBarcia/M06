/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Taller de l'empresa
 * @author professor
 */
//TODO posar les anotacions necessaries per fer la classe persistent
public class Taller extends Establiment implements Serializable{
    
    private int nMaquines;
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
