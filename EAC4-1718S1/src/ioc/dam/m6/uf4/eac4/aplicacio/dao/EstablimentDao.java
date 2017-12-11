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

/**
 *
 * @author Professor
 */
public class EstablimentDao extends AbstractJdbcDaoSimplificat<Establiment> {

    /**
     * Constructor que rep una connexio per parametre.
     * @param connection la connexio amb la que aquest objecte treballara 
     */ 
    
    public EstablimentDao(Connection connection) {
        super(connection);
    }
    
    //TODO completar aquest metode
    @Override
    protected boolean esPersistent(final Establiment entitat) throws UtilitatPersistenciaException {
        return false;  //instruccio que cal canviar; nomes hi es perque es pugui compilar

    }
    
    //TODO completar aquest metode
    @Override
    protected void modificar(final Establiment entitat) throws UtilitatPersistenciaException {
    }
    
    //TODO completar aquest metode
    @Override
    protected void inserir(final Establiment entitat) throws UtilitatPersistenciaException {

    }
    
    //TODO completar aquest metode
    @Override
    public Establiment refrescar(final Establiment entitat) throws UtilitatPersistenciaException {
        Establiment ret;
        JdbcPreparedQueryDao jdbcDao = new JdbcPreparedQueryDao() {

            @Override
            public Object writeObject(ResultSet rs) throws SQLException {

                // aqui cal:
                //      - omplir les dades atomiques de l'establiment (nom i ciutat)
                //      - utilitzant un bucle que avanci pel ResultSet, omplir la llista dels empleats
                return entitat;
            }

            @Override
            public String getStatement() {
                return "";  // cal posar la instruccio SQL necessaria per obtenir un recordset amb les dades
                            // de l'establiment i dels seus empleats
            }

            @Override
            public void setParameter(PreparedStatement pstm) throws SQLException {
                // tambe cal omplir aixo
            }
        };
        ret = (Establiment) UtilitatJdbcPlus.obtenirObjecte(con, jdbcDao);
        
        return ret;
    }

    //TODO completar aquest metode
    @Override
    public void eliminar(final Establiment entitat) throws UtilitatPersistenciaException {

    }

    //TODO completar aquest metode    
    @Override
    public List<Establiment> obtenirTot() throws UtilitatPersistenciaException {   
        return null;   //instruccio que cal canviar; nomes hi es perque es pugui compilar     
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
    //TODO completar aquest metode   
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
