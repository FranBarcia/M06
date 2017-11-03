/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestors;

import java.sql.Connection;
import java.util.List;
import model.Programador;

/**
 * Programador que coneix diferents llenguatges de programacio
 * @author professor
 */
public class GestorOrProgramador {
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


    private Connection con = null;

    /**
     * Crea un gestor de programador que treballara amb la connexio con
     * @param con connexio a traves de la qual es fan persistents els programadors
     */
    public GestorOrProgramador(Connection con) {
       this.con = con;
    }
    /**
     * Inserta un programador <b>nou</b> en el context de persistencia. Si el programador ja hi existeix, es pot provocar una RuntimeException.
     * @param pr
     * @throws gestors.GestorOrException en cas d'error a la base de dades
     */
    
    //TODO escriure aquest metode
    public void inserir(Programador pr) throws GestorOrException  {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    /**
     * Actualitza les dades del programador. Si el programador no hi existeix, es provoca una excepcio
     * @param pr programador a actualitzar
     * @throws gestors.GestorOrException si hi ha un error a la base de dades
     * 
     */
    //TODO escriure aquest metode
    public void modificar(Programador pr) throws GestorOrException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    /**
     * Esborra el programador que te determinat codi
     * @param codiProgramador codi del programador a esborrar
     * @throws gestors.GestorOrException si el codi no correspon a cap programador del context de persistencia
     */
    //TODO escriure aquest metode   
    public void eliminar(int codiProgramador) throws GestorOrException {
        
    }

    /**
     * Obte una llista amb tots els programadors del context de persistencia
     * @return llista amb els programadors persistents
     * @throws gestors.GestorOrException en cas d'error a la base de dades
     */
    //TODO escriure aquest metode   
    public List<Programador> obtenirProgramadors() throws GestorOrException  {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    /**
     * Obte el programador del context de persistencia amb un codi determinat
     * @param codiProgramador codi del programador a obtenir
     * @return programador amb <b>codiProgramador</b> o null si no hi ha cap programador persistent amb aquest codi
     * @throws gestors.GestorOrException en cas d'error a la base de dades
     */
    //TODO escriure aquest metode  
    public Programador obtenirProgramador(int codiProgramador) throws GestorOrException  {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Obte una llista amb tots els programadors del context de persistencia amb un nom determinat
     * @param llenguatge llenguatge que coneixen els programadors a cercar
     * @return llista amb els programadors seleccionats
     * @throws gestors.GestorOrException en cas d'error a la base de dades
     */
    //TODO escriure aquest metode    
    public List<Programador> obtenirProgramadorsPerLlenguatge(String llenguatge) throws GestorOrException  {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}

