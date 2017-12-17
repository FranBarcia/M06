/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.uf4.eac4.aplicacio;

import ioc.dam.m6.persistencia.excepcions.UtilitatPersistenciaException;
import java.util.List;

/**
 *
 * @author josep + professor
 */
public interface AplicacioBD {
    /**
     * Obte una connexio a partir de les dades configurades en invocar el 
     * metode <code>iniciar</code>. Un cop obtinguda la connexio, aquesta 
     * romandrà oberta i es podrà reciclar fins que no s'invoqui el metode 
     * <code>tancar</code>. Abans d'invocar aquest metode, cal haver invocat 
     * el metode <code>iniciar</code>. En cas que s'invoqui obrir sense cap 
     * invocacio previa del metode <code>iniciar</code> produirà un error que es 
     * materialitzarà en el llancament de <code>UtilitatPersistenciaException</code>.
     * El metode iniciar nomes s'ha d'invocar una unica vegada. Un cop invocat, 
     * si les dades de configuracio son correctes i el SGBD es accessible, 
     * no hauria de produir-se cap error.
     * @throws UtilitatPersistenciaException 
     */    
    void obrir() throws UtilitatPersistenciaException;

    /**
     * Tanca la connexio oberta des de la invocacio del metode <i>obrir</i>. 
     * La base de dades ha d'estar aixecada i ha de mantenir oberta la connexio.
     * En cas d'error, aquest sera� enregistrat en un fitxer, pero� no es 
     * llancara� cap excepcio per evitar una excessiva imbrincacio de sentencies 
     * try-catch.
     */    
    void tancar();
    

    /**
     * Prepara el sistema de persistencia per treballar amb ell; 
     * cal invocar-lo nomes un cop i abans de qualsevol 
     * de les altres operacions.
     * @throws UtilitatPersistenciaException si es produeix un error
     */
    void iniciar() throws UtilitatPersistenciaException;
    
    /**
     * Emmagatzema un establiment al sistema de persistencia. Si ja hi existeix
     * un altre amb la mateixa clau, l'actualitza amb les dades del parametre.
     * No actualitza els empleats assignats a l'establiment.
     * @param establiment establiment a fer persistent
     * @throws UtilitatPersistenciaException si es produeix un error 
     */
    void emmagatzemar(final Establiment establiment) throws UtilitatPersistenciaException ;

    /**
     * Emmagatzema un empleat al sistema de persistencia. Si ja hi existeix
     * un altre amb la mateixa clau, l'actualitza amb les dades del parametre.
     * Actualitza l'establiment que te assignat, pero no les dades d'aquest.
     * Si aquest establiment no existis al sistema de persistencia, es produiria
     * un error i no es realitzaria tampoc l'emmagatzematge de l'empleat.
     * @param empleat empleat a fer persistent
     * @throws UtilitatPersistenciaException si es produeix un error 
     */
    void emmagatzemar(final Empleat empleat) throws UtilitatPersistenciaException ;

    /**
     * Elimina un establiment del sistema de persistencia. El localitza per la
     * clau. Si no hi existeix cap amb la mateixa clau, no fa res. Si te empleats
     * assignats, es produeix un error i no es realitza l'eliminacio
     * @param establiment establiment a eliminar del sistema de persistencia
     * @throws UtilitatPersistenciaException si es produeix un error 
     */
    void eliminar(final Establiment establiment) throws UtilitatPersistenciaException ;
    
    /**
     * Elimina un empleat del sistema de persistencia. El localitza per la clau.
     * Si no hi existeix cap amb la mateixa clau, no fa res.
     * @param empleat empleat a eliminar del sistema de persistencia.
     * @throws UtilitatPersistenciaException  si es produeix un error
     */
    void eliminar(final Empleat empleat) throws UtilitatPersistenciaException ;

    /**
     * Actualitza les dades a memoria d'un establiment amb les que te al sistema de
     * persistencia. El localitza per la clau. Si el sistema de persistencia
     * no conte cap establiment amb la mateixa clau, retorna null. Actualitza tambe
     * la llista dels empleats assignats a l'establiment.
     * @param establiment establiment les dades de la qual s'actualitzaran
     * @return referencia a l'empresa actualitzada; null si no existeix al sistema
     * de persistencia.
     * @throws UtilitatPersistenciaException si es produeix un error 
     */
    Establiment refrescar(final Establiment establiment) throws UtilitatPersistenciaException ;
    
    /**
     * Actualitza les dades a memoria d'un empleat amb les que hi ha al sistema de
     * persistencia. El localitza per la clau. Si el sistema de persistencia
     * no conte cap empleat amb la mateixa clau, retorna null. Actualitza tambe
     * les dades de l'establiment assignat.
     * @param empleat empleat les dades del qual s'actualitzaran
     * @return referencia a l'empleat actualitzat; null si no existeix al
     * sistema de persistencia.
     * @throws UtilitatPersistenciaException  si es produeix un error
     */
    Empleat refrescar(final Empleat empleat) throws UtilitatPersistenciaException ;

    /**
     * Permet obtenir una llista de tots els establiments del sistema de
     * persistencia. No inclou les dades dels empleats que aquests tenen assignats.
     * Per obtenir-les, cal refrescar cada establiment.
     * @return llista amb tots els establiments del sistema de persistencia
     * @throws UtilitatPersistenciaException si es produeix un error 
     */
    List<Establiment> obtenirEstabliments() throws UtilitatPersistenciaException ;
    
    /**
     * Permet obtenir una llista de tots els empleats del sistema de persistencia.
     * Inclou les dades de l'establiment que te assignat.
     * @return llista amb tots els empleats del sistema de persistencia
     * @throws UtilitatPersistenciaException si es produeix un error 
     */
    List<Empleat> obtenirEmpleats() throws UtilitatPersistenciaException ;
    
    /**
     * Permet obtenir una llista de tots els empleats del sistema de persistencia
     * assignats a un determinat establiment; aquest s'identifica pel seu codi.
     * Inclou les dades de l'establiment que te assignat.
     * @param codiEstabliment codi que identifica l'empresa on pertanyen els 
     * empleats de la llista
     * @return llista amb tots els empleats del sistema de persistencia assignats
     * a l'establiment que es passa com a parametre
     * @throws UtilitatPersistenciaException si es produeix un error 
     */
    
    List<Empleat> obtenirEmpleatsDeEstabliment(final int codiEstabliment) 
                        throws UtilitatPersistenciaException ;

    /**
     * Permet obtenir una llista de tots els empleats del sistema de persistencia
     * amb un determinat nom. Inclou les dades de l'establiment que te assignat.
     * @param nom nom que comparteixen els empleats que formaran part del resultat
     * @return llista amb tots els empleats del sistema de persistencia que tenen
     * com a nom l'indicat al parametre.
     * @throws UtilitatPersistenciaException si es produeix un error 
     */    
    List<Empleat> obtenirEmpleatsPerNom(final String nom) 
                        throws UtilitatPersistenciaException ;
    
    /**
     * Permet obtenir una llista de tots els empleats del sistema de persistencia
     * amb d'una determinada ciutat. Inclou les dades de l'establiment que cada 
     * empleatte assignat.
     * @param ciutat ciutat dels empleats que formaran part de la llista
     * @return llista amb tots els empleats del sistema de persistencia de la ciutat
     * indicada pel parametre.
     * @throws UtilitatPersistenciaException si es produeix un error 
     */    
    List<Empleat> obtenirEmpleatsPerCiutat(final String ciutat) 
                        throws UtilitatPersistenciaException ;  

    /**
     * Permet obtenir una llista de tots els empleats del sistema de persistencia
     * la ciutat dels quals coincideix amb la de l'establilment que tenen assignat. 
     * Inclou les dades de l'establiment que cada empleat te assignat.
     * @return llista amb tots els empleats del sistema de persistencia 
     * la ciutat dels quals coincideix amb la de l'establilment que tenen assignat.
     * @throws UtilitatPersistenciaException si es produeix un error 
     */      
    List<Empleat> obtenirEmpleatsQueTreballenOnViuen() 
                        throws UtilitatPersistenciaException ;

    /**
     * Permet obtenir una llista de tots els establiments del sistema de
     * persistencia amb el nom indicat pel parametre.
     * No inclou les dades dels empleats que aquests tenen assignats.
     * Per obtenir-les, cal refrescar cada establiment.
     * @param nom nom dels establiments que formen part del resultat.
     * @return llista amb tots els establiments del sistema de persistencia amb
     * el nom indicat pel parametre
     * @throws UtilitatPersistenciaException si es produeix un error 
     */    
    List<Establiment> obtenirEstablimentsPerNom(final String nom) 
                        throws UtilitatPersistenciaException ;

    /**
     * Permet obtenir una llista de tots els establiments del sistema de
     * persistencia de la ciutat indicada pel parametre.
     * No inclou les dades dels empleats que aquests tenen assignats.
     * Per obtenir-les, cal refrescar cada establiment.
     * @param ciutat ciutat dels establiments que formen part del resultat.
     * @return llista amb tots els establiments del sistema de persistencia de
     * la ciutat indicada pel parametre
     * @throws UtilitatPersistenciaException si es produeix un error 
     */   
    
    List<Establiment> obtenirEstablimentsPerCiutat(final String ciutat) 
                        throws UtilitatPersistenciaException ;
    
    

}
