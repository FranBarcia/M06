/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.uf4.eac4.aplicacio;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa un establiment
 * @author Professor
 */
public class Establiment {
    private int codi;
    private String nom;
    private String ciutat;
    private final List<Empleat> empleats = new ArrayList();

    /**
     * Permet consultar el codi que identifica l'establiment
     * @return codi que identifica l'establiment
     */    

    public int getCodi() {
        return codi;
    }

    /**
     * Permet actualitzar el codi que identifica l'establilment
     * @param codi valor a assignar al codi que identifica l'establiment
     */
    public void setCodi(int codi) {
        this.codi = codi;
    }

    /**
     * Permet consultar el nom de l'establiment
     * @return  nom de l'establiment
     */
    public String getNom() {
        return nom;
    }

    /**
     * Permet actualitzar el nom de l'establiment
     * @param nom nom a donar a l'establiment
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Permet consultar la ciutat on es troba l'establiment
     * @return ciutat on es troba l'establiment
     */
    public String getCiutat() {
        return ciutat;
    }

    /**
     * Permet modificar la ciutat on es troba l'establiment
     * @param ciutat ciutat a assignar a l'establiment
     */
    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }
    
    /**
     * Permet consultar la referencia a la llista amb tots els empleats de 
     * l'establiment.
     * @return llista amb els empleats assignats a l'establiment
     */
    public List<Empleat> getEmpleats() {
        return (List<Empleat>)empleats;
    }
    
}
