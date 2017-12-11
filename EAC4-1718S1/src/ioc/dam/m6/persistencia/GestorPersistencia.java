/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.persistencia;

import ioc.dam.m6.persistencia.excepcions.UtilitatPersistenciaException;

/**
 *
 * @author josep
 */
public interface GestorPersistencia {
    /**
     * Assigna els valors de configuració de la connexió a partir d'un sistema
     * d'inicialització concretat a cada instancia d'aquesta interfície. 
     * @throws UtilitatPersistenciaException 
     */
    void iniciar() throws UtilitatPersistenciaException;

    /**
     * Obté una connexió a partir de les dades configurades en invocar el 
     * mètode <code>iniciar</code>. Una cop obtinguda la connexió aquesta 
     * romandrà oberta i es podrà reciclar fins que no s'invoqui el mètode 
     * <code>tancar</code>. Abans d'invocar aquest mètode, cal haver invocat 
     * el mètode <code>iniciar</code>. En cas que s'invoqui obrir sense cap 
     * invocació prèvia del mètode iniciar produirà un error que es 
     * materialitzarà en el llancament de <code>UtilitatPersistenciaException</code>.
     * El mètode iniciar només s'ha d'invocar una única vegada. Un cop invocat, 
     * si les dades de configuració són correctes i el SGBD és accessible, 
     * no hauria de produir-se cap error.
     * @throws UtilitatPersistenciaException 
     */
    void obrir() throws UtilitatPersistenciaException;

    /**
     * Tanca la connexió oberta des de la invocació del mètode <i>obrir</i>. 
     * La base de dades ha d'estar aixecada i ha de mantenir oberta la connexió.
     * En cas d'error, aquest serà enregistrat en un fitxer, però no es 
     * llancarà cap excepció per evitar una excessiva imbrincació de sentencies 
     * try-catch.
     */
    void tancar();    
}
