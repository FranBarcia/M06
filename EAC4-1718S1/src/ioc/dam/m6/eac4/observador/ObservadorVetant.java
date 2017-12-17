/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.eac4.observador;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

/**
 * VetoableChangeListener que evita que es realitzin mes canvis dels permesos
 * @author professor 
 */
public class ObservadorVetant implements VetoableChangeListener{

    private int canvisPermesos;  //nombre de canvis possibles sense superar el total permes

    /**
     * Constructor que inicialitza el nombre de canvis possibles amb el maxim que es permetra
     * @param maxCanvis maxim nombre de canvis que es permetra
     */
    
    public ObservadorVetant(int maxCanvis) {
        canvisPermesos=maxCanvis;
    }
    
    /**
     * Llenca una excepcio si el canvi observat supera el nombre de canvis permes
     * @param evt event que informa sobre el canvi que es vol fer
     * @throws PropertyVetoException exepcio que es llenca amb la finalitat d'evitar
     * el canvi si supera el maxim nombre permes 
     */
    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        String novaTarifa;
        
        if (canvisPermesos == 0) {
            throw new PropertyVetoException("El canvi supera el nombre de canvis permés", evt);
        } else {
            novaTarifa = (String)evt.getNewValue();
            canvisPermesos--;
        }
    }
}

