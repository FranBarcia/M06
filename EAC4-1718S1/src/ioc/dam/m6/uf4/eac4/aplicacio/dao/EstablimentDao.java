/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.uf4.eac4.aplicacio.dao;

import ioc.dam.m6.uf4.eac4.aplicacio.Establiment;
import ioc.dam.m6.persistencia.AbstractJdbcDaoSimplificat;
import ioc.dam.m6.persistencia.JdbcPreparedDao;
import ioc.dam.m6.persistencia.JdbcPreparedQueryDao;
import ioc.dam.m6.persistencia.JdbcQueryDao;
import ioc.dam.m6.persistencia.UtilitatJdbcPlus;
import ioc.dam.m6.persistencia.excepcions.UtilitatJdbcSQLException;
import ioc.dam.m6.persistencia.excepcions.UtilitatPersistenciaException;
import ioc.dam.m6.uf4.eac4.aplicacio.Empleat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Professor
 */
public class EstablimentDao extends AbstractJdbcDaoSimplificat<Establiment> {

    /**
     * Constructor que rep una connexio per parametre.
     * @param connection la connexio amb la que aquest objecte treballarà
     */ 
    
    public EstablimentDao(Connection connection) {
        super(connection);
    }
    
    @Override
    protected boolean esPersistent(final Establiment entitat) throws UtilitatPersistenciaException {
        boolean empleat= false;
        JdbcPreparedQueryDao jdbcDao = new JdbcPreparedQueryDao() {

            @Override
            public Object writeObject(ResultSet rs) throws SQLException {
                return rs.getInt(1);
            }

            @Override
            public String getStatement() {
                return "select count(codi) from Establiment where codi = ?";
            }

            @Override
            public void setParameter(PreparedStatement pstm) throws SQLException {
                int field=0;
                pstm.setInt(++field, entitat.getCodi());
            }
        };
        empleat = ((Integer)UtilitatJdbcPlus.obtenirObjecte(con, jdbcDao))>=1;
        
        return empleat;
    }
    
    @Override
    protected void modificar(final Establiment entitat) throws UtilitatPersistenciaException {
        JdbcPreparedDao jdbcPreparedDao = new JdbcPreparedDao() {

            @Override
            public void setParameter(PreparedStatement pstm) throws SQLException {
                int field=0;
                
                pstm.setString(++field, entitat.getNom());
                pstm.setString(++field, entitat.getCiutat());
                pstm.setInt(++field, entitat.getCodi());
            }

            @Override
            public String getStatement() {
                return "update Establiment set nom=?, ciutat=? where codi=?";
            }
        };
        UtilitatJdbcPlus.executar(con, jdbcPreparedDao);
    }
    
    @Override
    protected void inserir(final Establiment entitat) throws UtilitatPersistenciaException {
        JdbcPreparedDao jdbcPreparedDao = new JdbcPreparedDao() {

            @Override
            public void setParameter(PreparedStatement pstm) throws SQLException {
                int field=0;
                pstm.setInt(++field, entitat.getCodi());
                pstm.setString(++field, entitat.getNom());
                pstm.setString(++field, entitat.getCiutat());
            }

            @Override
            public String getStatement() {
                return "insert into Establiment(codi, nom, ciutat) values(?,?,?)";
            }
        };
        UtilitatJdbcPlus.executar(con, jdbcPreparedDao);
    }
    
    @Override
    public Establiment refrescar(final Establiment entitat) throws UtilitatPersistenciaException {
        Establiment ret= null;
        JdbcPreparedQueryDao jdbcDao = new JdbcPreparedQueryDao() {

            @Override
            public Object writeObject(ResultSet rs) throws SQLException {
                
                int field=0;
                Establiment establiment = new Establiment();
                establiment.setCodi(entitat.getCodi());
                establiment.setNom(rs.getString(++field));
                establiment.setCiutat(rs.getString(++field));
                
                establiment.getEmpleats().clear();
                
                try {
                    EmpleatDao ed = new EmpleatDao(con);
                    establiment.getEmpleats().addAll(ed.obtenirEmpleatsQueTreballenOnViuen());
                } catch (UtilitatPersistenciaException ex) {
                    Logger.getLogger(EstablimentDao.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                return establiment;
            }

            @Override
            public String getStatement() {
                return "select nom, ciutat from Establiment where codi=?";
            }

            @Override
            public void setParameter(PreparedStatement pstm) throws SQLException {
                int field=0;
                pstm.setInt(++field, entitat.getCodi());
            }
        };
        ret = (Establiment) UtilitatJdbcPlus.obtenirObjecte(con, jdbcDao);
        
        return ret;
    }

    @Override
    public void eliminar(final Establiment entitat) throws UtilitatPersistenciaException {
        JdbcPreparedDao jdbcDao = new JdbcPreparedDao() {

            @Override
            public void setParameter(PreparedStatement pstm) throws SQLException {
                int field=0;
                pstm.setInt(++field, entitat.getCodi());
            }

            @Override
            public String getStatement() {
                return "delete from Establiment where codi = ?";
            }
        };
        UtilitatJdbcPlus.executar(con, jdbcDao);
    }
 
    @Override
    public List<Establiment> obtenirTot() throws UtilitatPersistenciaException {   
        JdbcQueryDao jdbcDao = new JdbcQueryDao() {

            @Override
            public Object writeObject(ResultSet rs) throws SQLException {
                int field=0;
                Establiment establiment = new Establiment();
                establiment.setCodi(rs.getInt(++field));
                establiment.setNom(rs.getString(++field));
                establiment.setCiutat(rs.getString(++field));
                
                establiment.getEmpleats().clear();
                
                try {
                    EmpleatDao ed = new EmpleatDao(con);
                    establiment.getEmpleats().addAll(ed.obtenirEmpleatsQueTreballenOnViuen());
                    
                } catch (UtilitatPersistenciaException ex) {
                    Logger.getLogger(EstablimentDao.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                return establiment;
            }

            @Override
            public String getStatement() {
                return "select codi, nom, ciutat from Establiment";
            }

        };
        List<Establiment> ret = UtilitatJdbcPlus.obtenirLlista(con, jdbcDao);        
        return ret;
    }
    
    /**
     * Permet obtenir una llista de tots els establiments del sistema de
     * persistencia amb el nom indicat pel parametre.
     * No inclou les dades dels empleats que aquests tenen assignats.
     * Per obtenir-les, cal refrescar cada establiment.
     * @param nom nom dels establiments que formen part del resultat.
     * @return llista amb tots els establiments del sistema de persistencia amb
     * el nom indicat pel parametre
     * @throws UtilitatJdbcSQLException si es produeix un error 
     */     

    public List<Establiment> obtenirEstablimentsPerNom(String nom) throws UtilitatJdbcSQLException {
        return null;   //instruccio que cal canviar; nomes hi es perque es pugui compilar     
    }

    /**
     * Permet obtenir una llista de tots els establiments del sistema de
     * persistencia de la ciutat indicada pel parametre.
     * No inclou les dades dels empleats que aquests tenen assignats.
     * Per obtenir-les, cal refrescar cada establiment.
     * @param ciutat ciutat dels establiments que formen part del resultat.
     * @return llista amb tots els establiments del sistema de persistencia de
     * la ciutat indicada pel parametre
     * @throws UtilitatJdbcSQLException si es produeix un error 
     */  
    
    //TODO completar aquest metode   
    public List<Establiment> obtenirEstablimentsPerCiutat(String ciutat) throws UtilitatJdbcSQLException {
        return null;   //instruccio que cal canviar; nomes hi es perque es pugui compilar     
    }
}
