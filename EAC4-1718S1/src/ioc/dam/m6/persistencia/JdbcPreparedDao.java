/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.persistencia;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Representa una sentència SQL d'accés a dades via JDBC. Es una classe 
 * que complementa la classe UtilitatJdbcPlus. El format de la sentencia SQL 
 * seguirà la sintaxi JDBC en referència a la seva parametrització. Les classes 
 * que implementin aquesta interfície emmagatzemaran la sentencia SQL. 
 * La classe UtilitatJdbcPlus és capac de treballar amb instancies 
 * JdbcPreparedDao per crear les intancies PreparedStatement, incoportarari 
 * les dades desl paràmetres i executar-les. 
 * @author josep
 */
public interface JdbcPreparedDao extends JdbcDao{
    
//    String getStatement();
    
    /**
     * Aquest mètode té per objectiu fer l'assignació de valors als parapemters 
     * de l'objecte PreparedStatement. El paràmetre s'ha creat amb la cadena SQL
     * que encapsula aquest objecte JdbcPreparedDao. 
     * @param pstm. Aquest paràmetre és un objecte de tipus PreparedStatement 
     * i s'ha creat amb la cadena SQL que encapsula aquest objecte JdbcPreparedDao. 
     * @throws SQLException 
     */
    void setParameter(PreparedStatement pstm) throws SQLException;
}
