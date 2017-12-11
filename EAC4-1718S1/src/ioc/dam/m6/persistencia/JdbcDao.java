/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.persistencia;

/**
 * Representa una sentència SQL d'accés a dades via JDBC. Es una classe 
 * que complementa la classe UtilitatJdbcPlus. Les classes que implementin 
 * aquesta interfície emmagatzemaran una sentencia SQL. La classe 
 * UtilitatJdbcPlus és capac de treballar amb instancies JdbcDao per executar 
 * les sentencies SQL que continguin.
 * @author josep
 */
public interface JdbcDao {
    /**
     * Obté la sentència SQL emmagatzemada en aquest objecte.
     * @return una cadena amb la sentència SQL
     */
    String getStatement();
}
