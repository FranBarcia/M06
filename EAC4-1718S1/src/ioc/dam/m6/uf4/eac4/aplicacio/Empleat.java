/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.uf4.eac4.aplicacio;


/**
 * Classe que representa un empleat
 * @author Professor
 */
public class Empleat  {

    private int codi;
    private String ciutat;  
    private String nom;
    private Establiment establiment;

    /**
     * Permet consultar el codi que identifica l'empleat
     * @return codi que identifica l'empleat
     */    
    public int getCodi() {
        return codi;
    }

    /**
     * Permet actualitzar el codi que identifica l'empleat
     * @param codi salari a assignar a l'empleat
     */
    public void setCodi(int codi) {
        this.codi = codi;
    }    
    
    
    /**
     * Permet obtenir la ciutat de l'empleat
     * @return ciutat de l'empleat
     */
    public String getCiutat() {
        return ciutat;
    }

    /**
     * Permet actualitzar la ciutat de l'empleat
     * @param ciutat ciutat de l'empleat
     */
    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    /**
     * Permet consultar el nom d'un empleat
     * @return nom de l'empleat
     */
    public String getNom() {
        return nom;
    }
    
    /**
     * Permet actualitzar el nom d'un empleat
     * @param nom nom a assignar a l'empleat
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    /**
     * Permet consultar l'establiment de l'empleat
     * @return establiment de l'empleat
     */
    public Establiment getEstabliment() {
        return establiment;
    }

    /**
     * Permet actualitzar l'establiment de l'empleat
     * @param establiment establiment a assignar a l'empleat
     */
    public void setEstabliment(Establiment establiment) {
        this.establiment = (Establiment)establiment;
    }
    
    
}
