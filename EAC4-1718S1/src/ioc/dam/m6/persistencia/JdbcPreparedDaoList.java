package ioc.dam.m6.persistencia;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Representa una sentència SQL paramètrica d'accés a dades via JDBC. Es una 
 * classe que complementa la classe UtilitatJdbcPlus. El format de la sentencia 
 * SQL seguirà la sintaxi JDBC en referència a la seva parametrització. 
 * Les classes que implementin aquesta interfície emmagatzemaran la sentencia 
 * SQL. EL mètode setParameter, és capac de treballar amb dades indexades de 
 * manera que la mateixa sentencia s'executi per cada index en una única 
 * transacció.
 * La classe UtilitatJdbcPlus és capac de treballar amb instancies 
 * JdbcPreparedDaoList creant la intancia PreparedStatement, incoporant-hi 
 * les dades dels paràmetres d'acord amb l'index corresponent i executant-les. 
 * @author josep
 */
public interface JdbcPreparedDaoList extends JdbcDao{
    
//    String getStatement();

    /**
     * Aquest mètode té per objectiu fer l'assignació de valors als parapemters 
     * des d'una llista de dades indexade. El paràmetre pstm s'ha creat 
     * a partir de la cadena SQL i el valor id indica l'index de la llista 
     * de dades al que toca executar. Totes les execucions es realitzaran com
     * una única transacció.
     * @param id és l'index corresponent a un conjunt de dades amb les que 
     * assignar el paràmetres d'una de les execucions de la sentencia SQL 
     * retornada per getStatemet().
     * @param pstm és el PreparedStatement corresponent a la cadena SQL.
     * @throws SQLException 
     */
    void setParameter(int id, PreparedStatement pstm) throws SQLException;
    
    /**
     * Obté el nombre de dades per a les que caldrà executar la sentència SQL
     * @return enter nombre de dades per a les que caldrà executar la sentència 
     * SQL
     */
    int sizeList();
}
