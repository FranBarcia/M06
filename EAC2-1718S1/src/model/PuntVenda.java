/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 * Punt de venda de l'empresa
 * @author professor
 */
//TODO posar les anotacions necessaries per fer la classe persistent
@Entity
public class PuntVenda extends Establiment implements Serializable{
    
    private float facturacioObjectiu;
    private Taller tallerAssignat;
    
    
    /**
     * Construeix un taller amb els valors per defecte de Java
    */
    public PuntVenda() {
    }

    /**
     * Construeix un punt de venda amb els parametres especificats
     * @param codi codi que identifica al punt de venda entre tots els establiments
     * @param nom nom del punt de venda
     * @param ciutat ciutat del punt de venda
     * @param facturacioObjectiu facturacio mensual objectiu
     * @param tallerAssignat taller que te associat per atendre el servei postvenda
     */
    public PuntVenda(int codi, String nom, String ciutat, float facturacioObjectiu, Taller tallerAssignat) {
        super(codi, nom, ciutat);
        this.facturacioObjectiu=facturacioObjectiu;
        this.tallerAssignat = tallerAssignat;
    }

    /**
     * Obte la facturacio mensual objectiu
     * @return facturacio mensual objectiu
     */
    public float getFacturacioObjectiu() {
        return facturacioObjectiu;
    }

    /**
     * Actualitza la facturacio objectiu mensual
     * @param facturacioObjectiu nou valor per la facturacio objectiu mensual
     */
    public void setFacturacioObjectiu(float facturacioObjectiu) {
        this.facturacioObjectiu = facturacioObjectiu;
    }

    /**
     * Obte el taller assignat al servei postvenda
     * @return taller assignat al servei postvenda
     */
    public Taller getTallerAssignat() {
        return tallerAssignat;
    }

    /**
     * Actualitza el taller assignat al servei postvenda
     * @param tallerAssignat nou valor pel taller assignat
     */
    public void setTallerAssignat(Taller tallerAssignat) {
        this.tallerAssignat = tallerAssignat;
    }
    
}
