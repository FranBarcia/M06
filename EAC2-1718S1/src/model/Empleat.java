/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Empleat de l'empresa
 * @author professor
 */
//TODO posar les anotacions necessaries per fer la classe persistent
@Entity
public class Empleat implements Serializable {
   @Id
   private int codi;
   private String nom;
   private String ciutat;
   private Establiment establiment;
   
   /**
    * Construeix un nou empleat amb els valors per defecte de Java
   */
   
   public Empleat() {
   } 
   
   
   /**
    * Construeix un nou empleat amb els parametres especificats
    * @param codi codi que identifica l'empleat
    * @param nom nom de l'empleat
    * @param ciutat ciutat on viu l'empleat
    * @param establiment establiment on treballa l'empleat
    */
   
   public Empleat(int codi, String nom, String ciutat, Establiment establiment) {
        this.codi = codi;
        this.nom = nom;
        this.ciutat = ciutat;
        this.establiment = establiment;
   }

    /**
    * Obte el codi de que identifica l'empleat
    * @return codi que identifica l'empleat
    */
   public int getCodi() {
        return codi;
   }

   /** Actualitza el codi que identifica l'empleat
    * @param codi nou valor per al codi que identifica l'empleat
    */
   public void setCodi(int codi) {
        this.codi = codi;
   }
   
   
   /**
    * Obte la ciutat de l'empleat
    * @return nom de la ciutat de l'empleat
    */
   public String getCiutat() {
        return ciutat;
   }

   /**
    * Actualitza la ciutat de l'empleat
    * @param ciutat nou valor per la ciutat de l'empleat
    */
   public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
   }


   /**
    * Obte el nom de l'empleat
    * @return nom de l'empleat
    */
   public String getNom() {
        return nom;
   }

   /**
    * Actualitza el nom de l'empleat
    * @param nom nou valor per al nom de l'empleat
    */
   public void setNom(String nom) {
        this.nom = nom;
   }

   /**
    * Obte l'establiment on treballa l'empleat
    * @return establiment on treballa l'empleat
    */
    public Establiment getEstabliment() {
        return establiment;
    }

    /**
     * Actualitza l'establiment on treballa l'empleat
     * @param establiment nou valor per l'establiment on treballa l'empleat
     */
    public void setEstabliment(Establiment establiment) {
        this.establiment = establiment;
    }
}
