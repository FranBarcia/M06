/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.persistencia;


import ioc.dam.m6.eac4.aplicacio.controlsgbd.EstructuraAplicacioBD;
import ioc.dam.m6.persistencia.excepcions.UtilitatPersistenciaException;
import ioc.dam.m6.persistencia.excepcions.UtilitatJdbcRollbackException;
import ioc.dam.m6.persistencia.excepcions.UtilitatJdbcSQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Conjunt d'utilitats per facilitar el treball amb connectors JDBC.
 * @author josep
 */
public class UtilitatJdbc {

    /**
     * Crer i retorna una connexió configurara amb les dades passades per 
     * paràmetre.
     * @param driver és el nom complert de la classe Driver que usarem per 
     * connectar via JDBC amb el SGBD que fem servir com a magatzem de dades.
     * @param url és l'adreca que permet connectar amb la base de dades
     * @param user és el nom de l'usuari amb els permisos adequats per 
     * gestionar la persistència de les nostrres entitats.
     * @param password és la contrasenya de l'usuari indicat al paràmetre 
     * anterior.
     * @return Connection amb la connexió instanciada.
     * @throws UtilitatPersistenciaException 
     */
    public static Connection obrir(String driver, String url, String user, 
                                 String password) throws UtilitatPersistenciaException{
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            onError(ex);
        } catch (ClassNotFoundException ex) {
            onError(ex);
        }        
        return con;
    }

    /**
     * Executa un conjunt de sentenciaes SQL en una única transacció.
     * @param con és la connexió contra la que excutar les sentències SQL
     * @param sentenciesSql és una array amb totes els sentències SQL a 
     * executar. Les sentències es troben expressades com a cadenes de 
     * caràcters.
     * @throws UtilitatJdbcSQLException 
     */
    public static void executar(Connection con, String[] sentenciesSql) 
                                                throws UtilitatJdbcSQLException{
        boolean autocommit=true;
        Statement stm = null;
        try {
            autocommit = con.getAutoCommit();
            con.setAutoCommit(false);
            stm = con.createStatement();

            for(String sent: sentenciesSql){
                stm.executeUpdate(sent);
            }
            con.commit();
            con.setAutoCommit(autocommit);
        } catch (SQLException ex) {
            desfer(con, ex);
            onError(ex);            
        }finally{
            tancaStatement(stm);
        }
    }    

    /**
     * Executa una sentència SQL.
     * @param con és la connexió contra la que excutar la sentència SQL
     * @param sentenciaSql és una cadena de caracters representant una
     * sentència SQL.
     * @throws UtilitatJdbcSQLException 
     */
    public static void executar(Connection con, String sentenciaSql) 
                                                throws UtilitatJdbcSQLException{
        boolean autocommit=true;
        Statement stm = null;
        try {
            autocommit=con.getAutoCommit();
            con.setAutoCommit(true);
            stm = con.createStatement();
            stm.executeUpdate(sentenciaSql);
            con.setAutoCommit(autocommit);
        } catch (SQLException ex) {
            onError(ex);            
        }finally{
            tancaStatement(stm);
        }
    }    
    
    /**
     * Obté un valor numèric, el qual és el resultat de demanar el seguent 
     * valor d'una seqüencia anomeanda com indica el paràmetre nomSequencia, al
     * SGBD connectat per mitjà de la connexió con. 
     * @param con és la connexió utilitzada per demanar el seguent valor de la 
     * seqüència
     * @param nomSequencia és el nom de la sequencia de la que es demanarà el
     * següent valor.
     * @return el valor numèric obtingut.
     * @throws SQLException 
     */
    public static Long getNextValueId(Connection con, String nomSequencia) 
                                                throws SQLException{
        Long ret = null;
        boolean autocommit=true;
        Statement stm = null;
        ResultSet rs = null;
        try {
            autocommit=con.getAutoCommit();
            con.setAutoCommit(true);
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT nextval('" + nomSequencia + "')");
            if(rs.next()){
                ret = rs.getLong(1);
            }

            con.setAutoCommit(autocommit);
        }finally{
            tancaStatement(stm, rs);
        }
        return ret;
    }
    

    /**
     * Desfar les accions fetes en la transacció activa mitjancant una c
     * sentència rollback. El metode pot rebre una intància no nul·la d'una 
     * excepció en cas que la invocació d'aquest metode sigui degut a un error
     * anterior que requerrexi desfer les accions de la transacció activa.
     * @param con és la connexió on es troba activa la transacció a desfer.
     * @param sqlEx és l'exepció que provoca la invocació d'aquest mètode per 
     * tal de desfer les accions de la transacció.
     * @throws UtilitatJdbcRollbackException 
     */
    public static void desfer(Connection con, SQLException sqlEx) 
                                           throws UtilitatJdbcRollbackException{
        try {
            con.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(EstructuraAplicacioBD.class.getName())
                    .log(Level.SEVERE, null, ex);
            throw new UtilitatJdbcRollbackException(ex, sqlEx);
        }
    }
    
    /**
     * Tanca la connexió passada per paràmetre. Un com tancada la connexió 
     * restarà inactiva.
     * @param con és la connexió a tancar.
     */
    public static void tancarConnexio(Connection con){
        try {
            if(con!=null && !con.isClosed()){
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilitatJdbc.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Tanca l'Statement i el ResultSet passats per paràmetre.
     * @param stm és l'Statement a tancar
     * @param rs és el ResultSet a tancar.
     */
    public static void tancaStatement(Statement stm, ResultSet rs){
        try {
            if(rs!=null && !rs.isClosed()){
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstructuraAplicacioBD.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        try {
            if(stm!=null && !stm.isClosed()){
                stm.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstructuraAplicacioBD.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    public static void tancaStatement(Statement stm){
        tancaStatement(stm, null);
    }

    public static void tancaResultSet(ResultSet rs){
        tancaStatement(null, rs);
    }

    public static void onError(Exception ex) throws UtilitatPersistenciaException{
        Logger.getLogger(EstructuraAplicacioBD.class.getName()).log(
                Level.SEVERE, null, ex);
        throw new UtilitatPersistenciaException(ex.getMessage(), ex);
    }

    public static void onError(SQLException ex) throws UtilitatJdbcSQLException{
        Logger.getLogger(EstructuraAplicacioBD.class.getName()).log(
                Level.SEVERE, null, ex);
        throw new UtilitatJdbcSQLException(ex.getMessage(), ex);
    }
}
