/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.persistencia;

import ioc.dam.m6.persistencia.excepcions.UtilitatPersistenciaException;
import java.util.List;

/**
 * Representa la funcionalitat minima 
 * d'accés a les dades persistents de qualsevol entitat del model de dades 
 * d'una aplicació. Es tracta d'una classe paramètrica on el primer paràmetre
 * representa el tipus d'entitat de la que es farà el manteniment i 
 * sincronització amb la font de persistència.
 * El segón paràmetre representa el tipus de dada que l'entitat utilitza com a
 * identificador (clau primària) de les seves instancies. 
 * @author josep
 */
public interface Dao<T, I> {    
    /**
     * Crea una instancia nova de l'entitat referenciada per 
     * aquest objecte DAO, la qual no és emmagatzemada de 
     * forma persistent sinó que es crea amb l'objectiu de 
     * realitzar alguna gestió temporal.
     * @return instància de l'entitat referenciada per 
     * aquest objecte DAO.
     */
    public T novaInstanciaTemporal();
    
    /**
     * Crea una instancia nova de l'entitat referenciada per 
     * aquest objecte DAO, la qual s'emmagatzemarà de forma 
     * persistent abans de ser retornada. Aquest mètode 
     * només és adequat en cas que l'entitat tingui un
     * identificador generat de forma automàtica, doncs en 
     * cas contrari, la inserció generarà un error.
     * @return instància de l'entitat referenciada per aquest 
     * objecte DAO.
     * @throws UtilitatPersistenciaException Es llança per 
     * qualsevol error provinent del sistema de persistència.
     */
    public T novaInstancia() 
				throws UtilitatPersistenciaException;
    
    /**
     * Crea una instancia nova de l'entitat referenciada per 
     * aquest objecte DAO i s'inicialitza amb l'identificador
     * passat per paràmetre. La instància no s'emmagatzemarà,
     * per tant s'hauria de destinar a realitzar alguna 
     * gestió temporal.
     * @param id de la instància (clau primària).
     * @return instància de l'entitat referenciada per aquest 
     * objecte DAO.
     */
    public T novaInstanciaTemporal(I id);
    
    /**
     * Crea una instancia nova de l'entitat referenciada per 
     * aquest objecte DAO i s'inicialitza amb l'identificador
     * passat per paràmetre. La instància s'emmagatzemarà de 
     * forma persistent abans de ser retornada. En cas que ja 
     * existís una entitat persistent amb el mateix 
     * identificador es llancaria una excepció indicant que 
     * l'entitat que s'ha intentat crear no es pot inserir 
     * perquè el seu identificador ja està utilitzat.
     * @param id de la instància (clau primària).
     * @return instància de l'entitat referenciada per aquest 
     * objecte DAO.
     * @throws UtilitatPersistenciaException Es llança per 
     * qualsevol error provinent del sistema de persistència.
     */
    public T novaInstancia(final I id) 
				throws UtilitatPersistenciaException;
    
    /**
     * Refresca els atributs de l'entitat passada per  
     * paràmetre amb les dades emmagatzemades.
     * @param entitat a refrescar
     * @return L'entitat refrescada.
     * @throws UtilitatPersistenciaException Es llança per 
     * qualsevol error provinent del sistema de persistència.
     */
    public T refrescar(final T entitat)  
				throws UtilitatPersistenciaException;
    
    /**
     * Emmagatzema les dades contingudes als atributs de 
     * l'entitat passada per paràmetre. Si l'entitat no 
     * fos persistent en el moment de la invocació, es 
     * farà una inserció. En canvi si l'entitat ja fos 
     * persistent es realitzarà una actualització de les 
     * dades emmagatzemades.
     * @param entitat a emmagatzemar
     * @throws UtilitatPersistenciaException Es llança per 
     * qualsevol error provinent del sistema de persistència.
     */
    public void emmagatzemarDades(final T entitat)  
				throws UtilitatPersistenciaException;
    
    /**
     * Indica si l'objecte passat per paràmetre és 
     * persistent.  La comprovació de l'existència es basa 
     * exclusivament amb el valor dels atributs 
     * identificadors de la instància.
     * @param entitat a comprovar.
     * @return cert si l'entitat a comprovar és persistent. 
     * Fals en cas contrari.
     * @throws UtilitatPersistenciaException Es llança per 
     * qualsevol error provinent del sistema de persistència.
     */
    public boolean esPersistent(final T entitat) 
				throws UtilitatPersistenciaException;
    
    /**
     * Actualitza les dades emmagatzemades amb les 
     * contingudes a l'estat de la instancia passada per 
     * paràmetre. L'entitat ha d'haver estat emmagatzemada 
     * amb anterioritat. Contràriament es produirà un error.
     * @param entitat des d'on fer la actualització.
     * @throws UtilitatPersistenciaException Es llança per 
     * qualsevol error provinent del sistema de persistència.
     */
    public void modificar(final T entitat) 
				throws UtilitatPersistenciaException;
    
    /**
     * S'emmagatzema per primer cop l'entitat passada per 
     * paràmetre. És a dir que per poder-la inserir amb èxit, 
     * cal que no existeixi a la base de dades cap altra 
     * entitat emmagatzemada amb el mateix identificador. 
     * Quan això passi es produirà una error i es llancarà 
     * una excepció de tipus UtilitatPersistenciaException.
     * @param entitat a inserir
     * @throws UtilitatPersistenciaException Es llança per 
     * qualsevol error provinent del sistema de persistència.
     */
    public void inserir(final T entitat) 
				throws UtilitatPersistenciaException;
    
    /**
     * Fa que la entitat identificada amb la clau que es 
     * passa per paràmetre deixi de ser persistent. Si no 
     * existeix cap entitat persistent identificada amb la 
     * clau, es produirà es llancarà una excepció de tipus 
     * UtilitatPersistenciaException.
     * @param clau que identifica l'entitat candidata a 
     * deixar de ser persistent.
     * @throws UtilitatPersistenciaException Es llança per 
     * qualsevol error provinent del sistema de persistència.
     */
    public void eliminarPerClau(final I clau) 
				throws UtilitatPersistenciaException;   
    
    /**
     * Fa que l'entitat passada per paràmetre deixi de ser 
     * persistent. Si no existeix cap entitat persistent 
     * identificada amb el mateix valor que la instancia 
     * passada per paràmetre, es produirà un error i es 
     * llancarà una excepció de tipus 
     * UtilitatPersistenciaException.
     * @param entitat és l'entitat candidata a deixar de ser 
     * persistent.
     * @throws UtilitatPersistenciaException Es llança per 
     * qualsevol error provinent del sistema de persistència.
     */
    public void eliminar(final T entitat) 
				throws UtilitatPersistenciaException;   
    
    /**
     * Obté una instancia persistent (emmagatzemada 
     * prèviament) identificada amb la clau que es passa per 
     * paràmetre. 
     * @param clau que identifica la entitat que es desitja 
     * recuperar.
     * @return Instancia de l'entitat recuperada a partir de 
     * les dades emmagatzemades.
     * @throws UtilitatPersistenciaException Es llança per 
     * qualsevol error provinent del sistema de persistència.
     */
    public T obtenirInstancia(final I clau) 
				throws UtilitatPersistenciaException;
    
    /**
     * Obté una llista de totes les entitats emmagatzemades 
     * del tipus referenciat per aquest DAO.
     * @return llista d'entitats persistents del tipus 
     * referenciat per aquest DAO.
     * @throws UtilitatPersistenciaException Es llança per 
     * qualsevol error provinent del sistema de persistència.
     */
    public List<T> obtenirTot() 
				throws UtilitatPersistenciaException;   
}
