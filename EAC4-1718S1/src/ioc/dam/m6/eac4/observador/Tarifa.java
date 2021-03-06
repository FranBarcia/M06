/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.eac4.observador;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;

/**
 * Classe que conte el nom d'una tarifa. Admet observadors dels tipus
 * VetoableChangeListener i PropertyChangeListener.
 * @author professor
 */
public class Tarifa {

    private String nom="";  //nom de la tarifa
    private VetoableChangeSupport vcs = new VetoableChangeSupport(this);
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
     * Permet consultar el nom de la tarifa
     * @return nom de la tarifa
     */
    public String getNom() {
        return nom;
    }

    /**
     * actualitza el nom de la tarifa; informa als observadors registrats tant del tipus
     * PropertyVetoListener com PropertyChangeListener
     * @param nom nou nom de la tarifa
     * @throws PropertyVetoException en cas que el canvi no sigui possible
     */
    
    public void setNom(String nom) throws PropertyVetoException {
        this.vcs.fireVetoableChange("tarifa.nom", this.nom, nom); 
        this.pcs.firePropertyChange("tarifa.nom", this.nom, nom);
        this.nom = nom;
    }

    public void addVetoableChangeListener(VetoableChangeListener listener) {
         this.vcs.addVetoableChangeListener(listener);
     }

     public void removeVetoableChangeListener(VetoableChangeListener listener) {
         this.vcs.removeVetoableChangeListener(listener);
     }
     
    public void addPropertyChangeListener(PropertyChangeListener listener) {
         this.pcs.addPropertyChangeListener(listener);
     }

     public void removePropertyChangeListener(PropertyChangeListener listener) {
         this.pcs.removePropertyChangeListener(listener);
     }

}
