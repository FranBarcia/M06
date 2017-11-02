/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Establiment de l'empresa
 * @author professor
 */
//TODO posar les anotacions necessaries per fer la classe persistent
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipus", discriminatorType= DiscriminatorType.STRING, length=2)
@DiscriminatorValue(value="E")
@NamedQueries({
    @NamedQuery(name="Establiment.eliminar", query="DELETE FROM Establiment e WHERE e.codi = :codiEstabliment"),
    @NamedQuery(name="Establiment.obtenirEstabliment", query="SELECT e FROM Establiment e WHERE e.codi = :codiEstabliment"),
    @NamedQuery(name="Establiment.obtenirEstabliments", query="SELECT e FROM Establiment e"),
    @NamedQuery(name="Establiment.obtenirEmpleatsPerCiutat", query="SELECT e FROM Establiment e WHERE e.ciutat = :ciutat")
})
@NamedNativeQuery(name="Establiment.inserir", query="INSERT INTO Establiment e (codi, nom, ciutat)"
                                                  + " VALUES (:codi, :nom, :ciutat)")
public class Establiment implements Serializable {
   @Id
   @Column (name="codi", nullable=false)
   private int codi;
   @Column (name="nom", nullable=false)
   private String nom;
   @Column (name="ciutat", nullable=false)
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
