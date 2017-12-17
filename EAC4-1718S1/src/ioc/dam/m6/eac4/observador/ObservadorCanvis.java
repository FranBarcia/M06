/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.eac4.observador;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * PropertyChangeListener que, cada cop que observa un canvi, incrementa un
 * comptador; inicialment aquest comptador val 0
 * @author professor
 */
public class ObservadorCanvis implements PropertyChangeListener{

    private int canvis=0; //comptador dels canvis observats
    
    /**
     * incrementa el comptador
     * @param evt event que informa sobre el canvi
     */    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() != evt.getOldValue()) {
            canvis++;
        }
    }
    
    /**
     * Permet consulta el comptador dels canvi
     * @return valor del comptador dels canvis
     */
    public int getCanvis() {
        return canvis;
    }
    
}
