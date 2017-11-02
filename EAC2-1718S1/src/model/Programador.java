/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * classe que representa un programador
 * @author professor
 */
public class Programador {
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    private int codi;
    private String nom;
    private String ciutat;
    private final List<String> llenguatges = new ArrayList<String>();

    
    public Programador() {
    }

    public Programador(int codi, String nom, String ciutat, String [] llenguatges) {
        this.codi = codi;
        this.nom = nom;
        this.ciutat = ciutat;
        this.llenguatges.addAll(Arrays.asList(llenguatges));
    }

    
    /**
     * Obte el valor de la propietat codi. El codi identifica al programador dins del sistema de persistencia
     *
     * @return el valor de la propietat codi
     */
    public int getCodi() {
        return codi;
    }

    /**
     * Actualitza el valor de la propietat codi. El codi identifica al programador dins del sistema de persistencia
     *
     * @param codi nou valor de la propietat codi
     */
    public void setCodi(int codi) {
        this.codi = codi;
    }    
    
    
    
    
    /**
     * Obte el valor de la propietat nom
     *
     * @return el valor de la propietat nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Actualitza el valor de la propietat nom
     *
     * @param nom nou valor de la propietat nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obte el valor de la propietat ciutat
     *
     * @return el valor de la propietat ciutat
     */
    public String getCiutat() {
        return ciutat;
    }

    /**
     * Actualitza el valor de la propietat ciutat
     *
     * @param ciutat nou valor de la propietat ciutat
     */
    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }
    /**
     * Obte el valor de la propietat llenguatges
     *
     * @return el valor de la propietat llenguatges
     */
    public List<String> getLlenguatges() {
        return llenguatges;
    }

    /**
     * Actualitza el valor de la propietat llenguatges
     *
     * @param llenguatges nou valor de la propietat llenguatges
     */
    public void setLlenguatges(List<String> llenguatges) {
        this.llenguatges.clear();
        this.llenguatges.addAll(llenguatges);
    
    }






}
