/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.persistencia;

import ioc.dam.m6.persistencia.excepcions.UtilitatPersistenciaException;
import java.sql.Connection;

/**
 *
 * @author josep
 */
public abstract class AbstractJdbcDaoSimplificat<T> 
                                                implements DaoSimplificat<T>{
    protected Connection con;

    /**
     * Constructor que rep una connexió per paràmetre.
     * @param connection és la connexió amb la que aquest objecte treballarà
     */
    public AbstractJdbcDaoSimplificat(Connection connection) {
        this.con = connection;
    }
    
    @Override
    public void emmagatzemar(T entitat) throws UtilitatPersistenciaException {
        if(esPersistent(entitat)){
            modificar(entitat);
        }else{
            inserir(entitat);
        }
    }
        
    /**
     * Obté la connexió oberta que es fa servir per executar les sentències SQL
     * contra el SGDB on s'emmagatzemaran les entitats controlades per aquest 
     * DAO.
     * @return 
     */
    public Connection getConnexio(){
        return con;
    }

    /**
     * Indica si l'objecte passat per paràmetre és 
     * persistent.  La comprovació de l'existència es basa 
     * exclusivament amb el valor dels atributs 
     * identificadors de la instància.
     * @param entitat a comprovar.
     * @return cert si l'entitat a comprovar és persistent. 
     * Fals en cas contrari.
     * @throws UtilitatPersistenciaException Es llanca per 
     * qualsevol error provinent del sistema de persistència.
     */
    
    
    
    abstract protected boolean esPersistent(T entitat) 
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
    abstract protected void modificar(T entitat) 
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
    abstract protected void inserir(T entitat) 
				throws UtilitatPersistenciaException;
}
