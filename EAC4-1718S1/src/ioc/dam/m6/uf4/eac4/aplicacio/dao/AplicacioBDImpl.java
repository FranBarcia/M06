/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.uf4.eac4.aplicacio.dao;

import ioc.dam.m6.uf4.eac4.aplicacio.Establiment;
import ioc.dam.m6.uf4.eac4.aplicacio.Empleat;
import ioc.dam.m6.persistencia.excepcions.UtilitatPersistenciaException;
import java.util.List;
import ioc.dam.m6.uf4.eac4.aplicacio.AplicacioBD;
import java.util.ArrayList;

/**
 *
 * @author josep
 */
public class AplicacioBDImpl extends AplicacioBDJdbc
                                                    implements AplicacioBD{
    EstablimentDao establimentDao;
    
    EmpleatDao empleatDao;
 
    
    @Override
    public void obrir() throws UtilitatPersistenciaException {
        super.obrir();
        establimentDao = new EstablimentDao(con);
 
        empleatDao = new EmpleatDao(con);
     
    }
    
    @Override
    public void emmagatzemar(Establiment establiment) 
                        throws UtilitatPersistenciaException {
        establimentDao.emmagatzemar(establiment);
    }

    @Override
    public void emmagatzemar(Empleat empleat) throws UtilitatPersistenciaException {
        empleatDao.emmagatzemar(empleat);
    }


    @Override
    public void eliminar(Establiment establiment) throws UtilitatPersistenciaException {
        establimentDao.eliminar(establiment);
    }

    @Override
    public void eliminar(Empleat empleat) throws UtilitatPersistenciaException {
        empleatDao.eliminar(empleat);
    }


    @Override
    public Establiment refrescar(Establiment establiment) throws UtilitatPersistenciaException {
        return establimentDao.refrescar(establiment);
    }

    @Override
    public Empleat refrescar(Empleat empleat) throws UtilitatPersistenciaException {
        return empleatDao.refrescar(empleat);
    }


    @Override
    public List<Establiment> obtenirEstabliments()  
                                throws UtilitatPersistenciaException {
        return establimentDao.obtenirTot();
    }

    @Override
    public List<Empleat> obtenirEmpleats()  
                                throws UtilitatPersistenciaException {
        return empleatDao.obtenirTot();
    }


    @Override
    public List<Empleat> obtenirEmpleatsDeEstabliment(final int codiEstabliment) 
                                throws UtilitatPersistenciaException {
        
        Establiment aux=new Establiment();
        List<Empleat> resultat=new ArrayList<>();
        
        aux.setCodi(codiEstabliment);
        establimentDao.refrescar(aux);
        resultat.addAll(aux.getEmpleats());
        
        return resultat;
    }

    @Override
    public List<Empleat> obtenirEmpleatsPerNom(String nom) throws UtilitatPersistenciaException {
        return empleatDao.obtenirEmpleatsPerNom(nom);
    }

    @Override
    public List<Empleat> obtenirEmpleatsPerCiutat(String ciutat) throws UtilitatPersistenciaException {
        return empleatDao.obtenirEmpleatsPerCiutat(ciutat);
    }

    @Override
    public List<Empleat> obtenirEmpleatsQueTreballenOnViuen() throws UtilitatPersistenciaException {
        return empleatDao.obtenirEmpleatsQueTreballenOnViuen();
    }

    @Override
    public List<Establiment> obtenirEstablimentsPerNom(String nom) throws UtilitatPersistenciaException {
        return establimentDao.obtenirEstablimentsPerNom(nom);
    }

    @Override
    public List<Establiment> obtenirEstablimentsPerCiutat(String ciutat) throws UtilitatPersistenciaException {
        return establimentDao.obtenirEstablimentsPerCiutat(ciutat);
    }




}
