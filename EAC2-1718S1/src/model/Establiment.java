/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 * Establiment de l'empresa
 * @author professor
 */
//TODO posar les anotacions necessaries per fer la classe persistent
public class Establiment implements Serializable {
   private int codi;
   private String nom;
   private String ciutat;

   /**
    * Construeix un nou establiment amb els valors per defecte de Java
    */
   
   public Establiment() {
   }   
   
   
   /**
    * Construeix un nou establiment amb els paramtres especificats
    * @param codi identificador de l'establiment
    * @param nom nom de l'establiment
    * @param ciutat ciutat on s'ubica l'establiment
    */
   public Establiment(int codi, String nom, String ciutat) {
        this.codi = codi;
        this.nom = nom;
        this.ciutat = ciutat;
   }

   
   /**
    * Obte el codi que identifica l'establiment
    * @return codi que identifica l'establiment
    */
   public int getCodi() {
        return codi;
   }

   /**
    * Actualitza el codi que identifica l'establiment
    * @param codi nou valor per al codi que identifica l'establiment
    */
   public void setCodi(int codi) {
        this.codi = codi;
   }   
   
     
   /**
    * Obte la ciutat on s'ubica l'establiment
    * @return ciutat on s'ubica l'establiment
    */
   public String getCiutat() {
        return ciutat;
   }

   /**
    * Actualitza la ciutat on s'ubica l'establiment
    * @param ciutat nou valor per la ciutat on s'ubica l'establiment
    */
   public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
   }

   /**
    * Obte el nom de l'establiment
    * @return nom de l'establiment
    */
   public String getNom() {
        return nom;
   }

   /**
    * Actualitza el nom de l'establiment
    * @param nom nou valor per al nom de l'establiment
    */
   public void setNom(String nom) {
        this.nom = nom;
   }
    
}
