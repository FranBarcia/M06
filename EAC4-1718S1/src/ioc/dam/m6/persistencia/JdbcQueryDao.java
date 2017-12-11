/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Representa una sentència SQL de tipus consulta via JDBC. Es una classe 
 * que complementa la classe UtilitatJdbcPlus. Les classes que implementin 
 * aquesta interfície emmagatzemaran una sentencia SQL de tipus consula. La 
 * classe UtilitatJdbcPlus és capac de treballar amb instancies JdbcQueryDao
 * per executar les sentencies SQL que continguin, obtenir els resultats i 
 * instanciar entitats amb les dades recollides 
 * @author josep
 */
public interface JdbcQueryDao extends JdbcDao{

//    /**
//     * Obté la sentència SQL emmagatzemada en aquest objecte.
//     * @return una cadena amb la sentència SQL
//     */
//    String getStatement();
    
    /**
     * Instancia un objecte amb les dades extretes del ResultSet passat per 
     * paràmetre, el qual conté els resultats de l'execució de la sentencia 
     * SQL que aquest objecte JdbcQueryDao representa.
     * @param rs és el ResultSet que conté els resultats de l'execució de la 
     * sentencia SQL que aquest objecte JdbcQueryDao representa.
     * @return La instància construïda.
     * @throws SQLException 
     */
    Object writeObject(ResultSet rs) throws SQLException;    
}
