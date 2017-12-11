/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.uf4.eac4.aplicacio.dao;

import ioc.dam.m6.uf4.eac4.aplicacio.Establiment;
import ioc.dam.m6.uf4.eac4.aplicacio.Empleat;
import ioc.dam.m6.persistencia.AbstractJdbcDaoSimplificat;
import ioc.dam.m6.persistencia.JdbcPreparedDao;
import ioc.dam.m6.persistencia.JdbcPreparedQueryDao;
import ioc.dam.m6.persistencia.JdbcQueryDao;
import ioc.dam.m6.persistencia.UtilitatJdbcPlus;
import ioc.dam.m6.persistencia.excepcions.UtilitatJdbcSQLException;
import ioc.dam.m6.persistencia.excepcions.UtilitatPersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Professor
 */
public class EmpleatDao extends AbstractJdbcDaoSimplificat<Empleat> {

   /**
     * Constructor que rep una connexio per parametre.
     * @param connection la connexio amb la que aquest objecte treballara 
     */
    
    public EmpleatDao(Connection connection) {
        super(connection);

    }

    @Override
    protected boolean esPersistent(final Empleat entitat) throws UtilitatPersistenciaException {
        boolean ret= false;
        if(entitat.getCiutat()==null){
            return ret;
        }
        
        JdbcPreparedQueryDao jdbcDao = new JdbcPreparedQueryDao() {

            @Override
            public Object writeObject(ResultSet rs) throws SQLException {
                return rs.getInt(1);
            }

            @Override
            public String getStatement() {
                return "select count(codi) from empleat where codi=?";
            }

            @Override
            public void setParameter(PreparedStatement pstm) throws SQLException {
                int field=0;
                pstm.setInt(++field, entitat.getCodi());
            }
        };
        ret = ((Integer)UtilitatJdbcPlus.obtenirObjecte(con, jdbcDao))>=1;
        
        return ret;
    }

    @Override
    protected void modificar(final Empleat entitat) throws UtilitatPersistenciaException {
        JdbcPreparedDao jdbcPreparedDao = new JdbcPreparedDao() {

            @Override
            public void setParameter(PreparedStatement pstm) throws SQLException {
                int field=0;
                pstm.setString(++field, entitat.getNom());
                pstm.setString(++field,entitat.getCiutat());
                pstm.setInt(++field, entitat.getEstabliment().getCodi());
                pstm.setInt(++field,entitat.getCodi());

            }

            @Override
            public String getStatement() {
                return "update empleat set nom=?, ciutat=?, establiment=? where codi=?";
            }
        };
        UtilitatJdbcPlus.executar(con, jdbcPreparedDao);
    }

    @Override
    protected void inserir(final Empleat entitat) throws UtilitatPersistenciaException {
        JdbcPreparedDao jdbcPreparedDao = new JdbcPreparedDao() {

            @Override
            public void setParameter(PreparedStatement pstm) throws SQLException {
                int field=0;
                Establiment co=entitat.getEstabliment();
                
                pstm.setDouble(++field,entitat.getCodi());                
                pstm.setString(++field, entitat.getNom());                
                pstm.setString(++field, entitat.getCiutat());
            
                if(co==null){
                    pstm.setNull(++field,java.sql.Types.INTEGER);
                }else{
                    pstm.setInt(++field, co.getCodi());
                }
          
            }

            @Override
            public String getStatement() {
                return "insert into Empleat(codi, nom, ciutat, establiment) values(?,?, ?, ?)";
            }
        };
        UtilitatJdbcPlus.executar(con, jdbcPreparedDao);
    }

    @Override
    public Empleat refrescar(final Empleat entitat) throws UtilitatPersistenciaException {
        Empleat ret=null;
        JdbcPreparedQueryDao jdbcDao = new JdbcPreparedQueryDao() {

            @Override
            public Object writeObject(ResultSet rs) throws SQLException {
                int field=0;

                
                
                entitat.setNom(rs.getString(++field));
                entitat.setCiutat(rs.getString(++field));
                
                Establiment est=new Establiment();
                est.setCodi(rs.getInt(++field));
                if(!rs.wasNull()){
                    est.setNom(rs.getString(++field));
                    est.setCiutat(rs.getString(++field));
                }else{
                    est=null;
                }
                entitat.setEstabliment(est);
                

                return entitat;
            }

            @Override
            public String getStatement() {
                return "SELECT em.nom, em.ciutat, em.establiment, es.nom, es.ciutat"
                        + " FROM Empleat em LEFT JOIN Establiment es ON em.establiment=es.codi WHERE em.codi=?";
            }

            @Override
            public void setParameter(PreparedStatement pstm) throws SQLException {
                int field=0;
                pstm.setInt(++field, entitat.getCodi());
            }
        };
        ret = (Empleat) UtilitatJdbcPlus.obtenirObjecte(con, jdbcDao);
        
        return ret;
    }

    //TODO completar aquest metode
    @Override
    public void eliminar(final Empleat entitat) throws UtilitatPersistenciaException {
        
    }

    @Override
    public List<Empleat> obtenirTot() throws UtilitatPersistenciaException {
        
        JdbcQueryDao jdbcDao = new JdbcQueryDao() {

            @Override
            public Object writeObject(ResultSet rs) throws SQLException {
                int field=0;
                Empleat empleat=new Empleat();
                
                empleat.setCodi(rs.getInt(++field));
                empleat.setNom(rs.getString(++field));
                empleat.setCiutat(rs.getString(++field));
                
                Establiment est=new Establiment();
                est.setCodi(rs.getInt(++field));
                if(!rs.wasNull()){
                    est.setNom(rs.getString(++field));
                    est.setCiutat(rs.getString(++field));
                }else{
                    est=null;
                }
                empleat.setEstabliment(est);
                

                return empleat;
            }

            @Override
            public String getStatement() {
                return "SELECT em.codi, em.nom, em.ciutat, em.establiment, es.nom, es.ciutat"
                        + " FROM Empleat em LEFT JOIN Establiment es ON em.establiment=es.codi";
            }

        };
        List<Empleat> ret = UtilitatJdbcPlus.obtenirLlista(con, jdbcDao);        
        return ret;  
    }  
    
    /**
     * Permet obtenir una llista de tots els empleats del sistema de persistencia
     * amb un determinat nom. Inclou les dades de l'establiment que te assignat.
     * @param nom nom que comparteixen els empleats que formaran part del resultat
     * @return llista amb tots els empleats del sistema de persistencia que tenen
     * com a nom l'indicat al parametre.
     * @throws UtilitatPersistenciaException si es produeix un error 
     */  
    
    //TODO completar aquest metode
    public List<Empleat> obtenirEmpleatsPerNom(String nom) throws UtilitatPersistenciaException{
        return null;  //instruccio que cal canviar; nomes hi es perque es pugui compilar
    }

    /**
     * Permet obtenir una llista de tots els empleats del sistema de persistencia
     * amb d'una determinada ciutat. Inclou les dades de l'establiment que cada 
     * empleatte assignat.
     * @param ciutat ciutat dels empleats que formaran part de la llista
     * @return llista amb tots els empleats del sistema de persistencia de la ciutat
     * indicada pel parametre.
     * @throws UtilitatPersistenciaException si es produeix un error 
     */   
    
    //TODO completar aquest metode
    public List<Empleat> obtenirEmpleatsPerCiutat(String ciutat) throws UtilitatPersistenciaException{
 
        return null;  //instruccio que cal canviar; nomes hi es perque es pugui compilar
    }
    
    /**
     * Permet obtenir una llista de tots els empleats del sistema de persistencia
     * la ciutat dels quals coincideix amb la de l'establilment que tenen assignat. 
     * Inclou les dades de l'establiment que cada empleat te assignat.
     * @return llista amb tots els empleats del sistema de persistencia 
     * la ciutat dels quals coincideix amb la de l'establilment que tenen assignat.
     * @throws UtilitatJdbcSQLException si es produeix un error 
     */   
    //TODO completar aquest metode
    public List<Empleat> obtenirEmpleatsQueTreballenOnViuen() throws UtilitatJdbcSQLException {
        return null;  //instruccio que cal canviar; nomes hi es perque es pugui compilar

    }

}
