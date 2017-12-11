/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.persistencia;

import ioc.dam.m6.persistencia.excepcions.UtilitatPersistenciaException;
import java.util.List;

/**
 *
 * @author josep
 */

/**
 * Representa la funcionalitat minima 
 * d'accs a les dades persistents de qualsevol entitat del model de dades 
 * d'una aplicaci�. Es tracta d'una classe param?trica, en la que el par?metre
 * representa el tipus d'entitat de la que es far? el manteniment i 
 * sincronitzaci� amb la font de persist?ncia.
 * @author josep
 */
public interface DaoSimplificat<T> {

    /**
     * Refresca els atributs de l'entitat passada per  
     * par?metre amb les dades emmagatzemades.
     * @param entitat a refrescar
     * @return L'entitat refrescada.
     * @throws UtilitatPersistenciaException Es llan�a per 
     * qualsevol error provinent del sistema de persist?ncia.
     */
    public T refrescar(final T entitat)  
				throws UtilitatPersistenciaException;
    
    /**
     * Emmagatzema les dades contingudes als atributs de 
     * l'entitat passada per par?metre. Si l'entitat no 
     * fos persistent en el moment de la invocaci�, es 
     * far? una inserci�. En canvi si l'entitat ja fos 
     * persistent es realitzar? una actualitzaci� de les 
     * dades emmagatzemades.
     * @param entitat a emmagatzemar
     * @throws UtilitatPersistenciaException Es llan�a per 
     * qualsevol error provinent del sistema de persist?ncia.
     */
    public void emmagatzemar(final T entitat)  
				throws UtilitatPersistenciaException;
    
    /**
     * Fa que l'entitat passada per par?metre deixi de ser 
     * persistent. Si no existeix cap entitat persistent 
     * identificada amb el mateix valor que la instancia 
     * passada per par?metre, es produir? un error i es 
     * llancar? una excepci� de tipus 
     * UtilitatPersistenciaException.
     * @param entitat �s l'entitat candidata a deixar de ser 
     * persistent.
     * @throws UtilitatPersistenciaException Es llan�a per 
     * qualsevol error provinent del sistema de persist?ncia.
     */
    public void eliminar(final T entitat) 
				throws UtilitatPersistenciaException;
    
    /**
     * Obt� una llista de totes les entitats emmagatzemades 
     * del tipus referenciat per aquest DAO.
     * @return llista d'entitats persistents del tipus 
     * referenciat per aquest DAO.
     * @throws UtilitatPersistenciaException Es llan�a per 
     * qualsevol error provinent del sistema de persist?ncia.
     */
    public List<T> obtenirTot() 
				throws UtilitatPersistenciaException;   
}
