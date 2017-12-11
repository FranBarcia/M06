/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.persistencia;

import ioc.dam.m6.persistencia.excepcions.UtilitatJdbcSQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author josep
 */
public class UtilitatJdbcPlus extends UtilitatJdbc{
        public static void executar(Connection con, JdbcDao[] jdbcDaos) 
                                                throws UtilitatJdbcSQLException{       
        boolean autocommit=true;
        Statement stm = null;
        try {
            autocommit=con.getAutoCommit();
            con.setAutoCommit(false);
            for(JdbcDao dao: jdbcDaos){
                if(dao instanceof JdbcPreparedDao){
                    stm = con.prepareStatement(dao.getStatement());
                    ((JdbcPreparedDao)dao).setParameter((PreparedStatement) stm);
                    ((PreparedStatement)stm).executeUpdate();
                }else if(dao instanceof JdbcPreparedDaoList){
                    stm = con.prepareStatement(dao.getStatement());
                    for(int n=0; n<((JdbcPreparedDaoList)dao).sizeList(); n++){
                        ((JdbcPreparedDaoList)dao).setParameter(n, (PreparedStatement) stm);
                        ((PreparedStatement)stm).executeUpdate();
                    }
                }else{
                    stm = con.createStatement();
                    stm.executeUpdate(dao.getStatement());
                }
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
    
    public static void executar(Connection con, 
                                JdbcPreparedDao jdbcDao) 
                                        throws UtilitatJdbcSQLException{
        boolean autocommit=true;
        PreparedStatement stm = null;
        try {
            autocommit=con.getAutoCommit();
            con.setAutoCommit(true);
            stm = con.prepareStatement(jdbcDao.getStatement());
            jdbcDao.setParameter(stm);
            stm.executeUpdate();
            con.setAutoCommit(autocommit);
        } catch (SQLException ex) {
            onError(ex);            
        }finally{
            tancaStatement(stm);
        }
    }
    
    public static void executar(Connection con, 
                                JdbcPreparedDaoList jdbcPreparedDaoList) 
                                                throws UtilitatJdbcSQLException{
        boolean autocommit=true;
        PreparedStatement stm = null;
        try {
            autocommit=con.getAutoCommit();
            con.setAutoCommit(false);
            stm = con.prepareStatement(jdbcPreparedDaoList.getStatement());
            for(int n=0; n<jdbcPreparedDaoList.sizeList(); n++){
                jdbcPreparedDaoList.setParameter(n, stm);
                stm.executeUpdate();
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
    
    public static List obtenirLlista(Connection con, 
                                JdbcPreparedQueryDao jdbcDao) 
                                        throws UtilitatJdbcSQLException{
        List ret = new ArrayList();
        boolean autocommit=true;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            autocommit=con.getAutoCommit();
            con.setAutoCommit(true);
            stm = con.prepareStatement(jdbcDao.getStatement());
            jdbcDao.setParameter(stm);
            rs = stm.executeQuery();
            while(rs.next()){
                ret.add(jdbcDao.writeObject(rs));
            }

            con.setAutoCommit(autocommit);
        } catch (SQLException ex) {
            onError(ex);            
        }finally{
            tancaStatement(stm, rs);
        }
        return ret;
    }
    
    public static List obtenirLlista(Connection con, 
                                JdbcQueryDao jdbcDao) 
                                        throws UtilitatJdbcSQLException{
        List ret = new ArrayList();
        boolean autocommit=true;
        Statement stm = null;
        ResultSet rs = null;
        try {
            autocommit=con.getAutoCommit();
            con.setAutoCommit(true);
            stm = con.createStatement();
            rs = stm.executeQuery(jdbcDao.getStatement());
            while(rs.next()){
                ret.add(jdbcDao.writeObject(rs));
            }
            
            con.setAutoCommit(autocommit);
        } catch (SQLException ex) {
            onError(ex);            
        }finally{
            tancaStatement(stm, rs);
        }
        return ret;
    }
    
    public static Object obtenirObjecte(Connection con, 
                                JdbcPreparedQueryDao jdbcDao) 
                                        throws UtilitatJdbcSQLException{
        Object ret= null;
        boolean autocommit=true;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            autocommit=con.getAutoCommit();
            con.setAutoCommit(true);
            stm = con.prepareStatement(jdbcDao.getStatement());
            jdbcDao.setParameter(stm);
            rs = stm.executeQuery();
            if(rs.next()){
                ret=jdbcDao.writeObject(rs);
            }

            con.setAutoCommit(autocommit);
        } catch (SQLException ex) {
            onError(ex);            
        }finally{
            tancaStatement(stm, rs);
        }
        return ret;
    }
    
    public static Object obtenirObjecte(Connection con, 
                                JdbcQueryDao jdbcDao) 
                                        throws UtilitatJdbcSQLException{
        Object ret= null;
        boolean autocommit=true;
        Statement stm = null;
        ResultSet rs = null;
        try {
            autocommit=con.getAutoCommit();
            con.setAutoCommit(true);
            stm = con.createStatement();
            rs = stm.executeQuery(jdbcDao.getStatement());
            if(rs.next()){
                ret=jdbcDao.writeObject(rs);
            }
            
            con.setAutoCommit(autocommit);
        } catch (SQLException ex) {
            onError(ex);            
        }finally{
            tancaStatement(stm, rs);
        }
        return ret;
    }
    
}
