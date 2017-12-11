/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.persistencia;

/**
 * Representa una sentència SQL de tipus consulta via JDBC. Es una classe 
 * que complementa la classe UtilitatJdbcPlus. El format de la sentencia SQL 
 * seguirà la sintaxi JDBC en referència a la seva parametrització. Les classes 
 * que implementin aquesta interfície emmagatzemaran una sentencia SQL de tipus 
 * consula. La classe UtilitatJdbcPlus és capac de treballar amb instancies 
 * JdbcPreparedQueryDao per crear les intancies PreparedStatement 
 * corresponents, incoporar-hi les dades dels paràmetres, executar-les,
 * obtenir els resultats i instanciar entitats amb les dades recollides 
 * @author josep
 */
public interface JdbcPreparedQueryDao extends JdbcPreparedDao, JdbcQueryDao {

//    String getStatement();
//    void setParameter(PreparedStatement pstm) throws SQLException;  
//    Object writeObject(ResultSet rs) throws SQLException;        
}
