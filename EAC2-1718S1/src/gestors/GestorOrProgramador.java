/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestors;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        try {
            PreparedStatement consulta = con.prepareStatement("INSERT INTO Programador (codi, personal, llenguatges) VALUES (?,(?,?),?)");
            consulta.setInt(1, pr.getCodi());
            consulta.setString(2, pr.getNom());
            consulta.setString(3, pr.getCiutat());
            consulta.setArray(4, obteArrayLlenguatges(pr));
            consulta.executeUpdate();
        } catch (SQLException ex) {
            throw new GestorOrException(ex.getMessage());
        }
    }

    
    /**
     * Actualitza les dades del programador. Si el programador no hi existeix, es provoca una excepcio
     * @param pr programador a actualitzar
     * @throws gestors.GestorOrException si hi ha un error a la base de dades
     * 
     */
    //TODO escriure aquest metode
    public void modificar(Programador pr) throws GestorOrException{
        try {
            PreparedStatement consulta = con.prepareStatement("UPDATE Programador SET personal = (?,?), llenguatges = ? WHERE codi = ?");
            consulta.setInt(4, pr.getCodi());
            consulta.setString(1, pr.getNom());
            consulta.setString(2, pr.getCiutat());
            consulta.setArray(3, obteArrayLlenguatges(pr));
            consulta.executeUpdate();
        } catch (SQLException ex) {
            throw new GestorOrException(ex.getMessage());
        }
    }
    
    
    /**
     * Esborra el programador que te determinat codi
     * @param codiProgramador codi del programador a esborrar
     * @throws gestors.GestorOrException si el codi no correspon a cap programador del context de persistencia
     */
    //TODO escriure aquest metode   
    public void eliminar(int codiProgramador) throws GestorOrException {
        try {
            PreparedStatement consulta = con.prepareStatement("DELETE FROM Programador WHERE codi = ?");
            consulta.setInt(1, codiProgramador);
            consulta.executeUpdate();
        } catch (SQLException ex) {
            throw new GestorOrException(ex.getMessage());
        }
    }

    /**
     * Obte una llista amb tots els programadors del context de persistencia
     * @return llista amb els programadors persistents
     * @throws gestors.GestorOrException en cas d'error a la base de dades
     */
    //TODO escriure aquest metode   
    public List<Programador> obtenirProgramadors() throws GestorOrException  {
        List<Programador> llistaProgramadors = null;
        ResultSet resultatConsulta;
        PreparedStatement consulta;
        Programador programador = new Programador();
        
        try {
            consulta = con.prepareStatement("SELECT * FROM Programador");
            resultatConsulta = consulta.executeQuery();
            
            while (resultatConsulta.next()) {
                programador.setCodi(resultatConsulta.getInt("codi"));
                programador.setNom(resultatConsulta.getString(resultatConsulta.getArray("personal").getResultSet().getString("nom")));
                programador.setCiutat(resultatConsulta.getString(resultatConsulta.getArray("personal").getResultSet().getString("ciutat")));
                programador.setLlenguatges((List<String>)resultatConsulta.getArray("llenguatges"));
                llistaProgramadors.add(programador);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorOrProgramador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return llistaProgramadors;
    }

    
    /**
     * Obte el programador del context de persistencia amb un codi determinat
     * @param codiProgramador codi del programador a obtenir
     * @return programador amb <b>codiProgramador</b> o null si no hi ha cap programador persistent amb aquest codi
     * @throws gestors.GestorOrException en cas d'error a la base de dades
     */
    //TODO escriure aquest metode  
    public Programador obtenirProgramador(int codiProgramador) throws GestorOrException  {
        Programador programador = new Programador();
        ResultSet resultatConsulta;
        PreparedStatement consulta;
        
        try {
            consulta = con.prepareStatement("SELECT * FROM ioc.Programador WHERE codi = ?");
            consulta.setInt(1, codiProgramador);
            resultatConsulta = consulta.executeQuery();
            
            while (resultatConsulta.next()) {
                programador.setCodi(resultatConsulta.getInt("codi"));
                programador.setNom(resultatConsulta.getString(resultatConsulta.getArray("personal").getResultSet().getString("nom")));
                programador.setCiutat(resultatConsulta.getString(resultatConsulta.getArray("personal").getResultSet().getString("ciutat")));
                programador.setLlenguatges((List<String>)resultatConsulta.getArray("llenguatges"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GestorOrProgramador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return programador;
    }

    /**
     * Obte una llista amb tots els programadors del context de persistencia amb un nom determinat
     * @param llenguatge llenguatge que coneixen els programadors a cercar
     * @return llista amb els programadors seleccionats
     * @throws gestors.GestorOrException en cas d'error a la base de dades
     */
    //TODO escriure aquest metode    
    public List<Programador> obtenirProgramadorsPerLlenguatge(String llenguatge) throws GestorOrException  {
        List<Programador> llistaProgramadors = null;
        ResultSet resultatConsulta;
        PreparedStatement consulta;
        Programador programador = new Programador();
        
        try {
            consulta = con.prepareStatement("SELECT * FROM Programador WHERE ? LIKE ANY(llenguatges)");
            consulta.setString(1, llenguatge);
            resultatConsulta = consulta.executeQuery();
            
            while (resultatConsulta.next()) {
                programador.setCodi(resultatConsulta.getInt("codi"));
                programador.setNom(resultatConsulta.getString(resultatConsulta.getArray("personal").getResultSet().getString("nom")));
                programador.setCiutat(resultatConsulta.getString(resultatConsulta.getArray("personal").getResultSet().getString("ciutat")));
                programador.setLlenguatges((List<String>)resultatConsulta.getArray("llenguatges"));
                llistaProgramadors.add(programador);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GestorOrProgramador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return llistaProgramadors;
    }

    private Array obteArrayLlenguatges(Programador pr) throws GestorOrException {
        try {
            List<String> llistaLlenguatges = pr.getLlenguatges();
            String[] vectorLlenguatges = new String[llistaLlenguatges.size()];
            
            vectorLlenguatges = llistaLlenguatges.toArray(vectorLlenguatges);        
            return con.createArrayOf("varchar",vectorLlenguatges);
        } catch (SQLException ex) {
            throw new GestorOrException(ex.getMessage());
        }
    }
}

