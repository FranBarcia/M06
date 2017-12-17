package ioc.dam.m6.uf4.aplicacio.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import ioc.dam.m6.persistencia.excepcions.UtilitatPersistenciaException;
import ioc.dam.m6.uf4.eac4.aplicacio.AplicacioBD;
import ioc.dam.m6.uf4.eac4.aplicacio.Empleat;
import ioc.dam.m6.uf4.eac4.aplicacio.Establiment;
import ioc.dam.m6.uf4.eac4.aplicacio.dao.AplicacioBDImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author professor
 */
public class TestComponentsDAO {
    
    private final  AplicacioBD aplicBD=new AplicacioBDImpl();

    
    private final ArrayList totesAltes=new ArrayList();
    private final ArrayList<Empleat> llistaTotsEmpleats = new ArrayList<>(),
                               llistaJosep = new ArrayList<>(),
                               llistaAnna = new ArrayList<>(),
                               llistaJaume = new ArrayList<>(),
                               llistaEva = new ArrayList<>(),
                               llistaBarcelonins = new ArrayList<>(),
                               llistaHospitalencs = new ArrayList<>(),
                               llistaCastelldefelencs = new ArrayList<>(),
                               llistaBadalonins = new ArrayList<>(),
                               llistaEmpleatsEstabliment1 = new ArrayList<>(),
                               llistaEmpleatsEstabliment2 = new ArrayList<>(),
                               llistaEmpleatsEstabliment3 = new ArrayList<>(),
                               llistaEmpleatsEstabliment5 = new ArrayList<>(),
                               llistaEmpleatsViuenOnTreballen = new ArrayList<>();
    
    private final ArrayList<Establiment> llistaEstabliments1 = new ArrayList<>(),
                                   llistaEstabliments2 = new ArrayList<>(),
                                   llistaEstabliments3  = new ArrayList<>(),
                                   llistaEstablimentsBarcelonins = new ArrayList<>(),
                                   llistaEstablimentsHospitalencs  = new ArrayList<>(),
                                   llistaEstablimentsCastelldefelencs = new ArrayList<>();
        
    private final ArrayList<Establiment> llistaTotsEstabliments = new ArrayList<>();
    
    
    
    
    
    

    /**
     * Abans de cada test neteja la base de dades
     * @throws ioc.dam.m6.persistencia.excepcions.UtilitatPersistenciaException en cas d'error
     */
    @Before
    public void setUp() throws UtilitatPersistenciaException  {
        obre();
        creaDadesPrincipals();
        
        List<Empleat> auxLlistaEmpleats=aplicBD.obtenirEmpleats();

        List<Establiment> auxLlistaEstabliments=aplicBD.obtenirEstabliments();
        
        for(Empleat e:auxLlistaEmpleats){
            aplicBD.eliminar(e);
        }
        for(Establiment e:auxLlistaEstabliments){
            aplicBD.eliminar(e);
        }
        
        fesAltes();
        
    } 
    
    /**
     * En acabar els tests, tanca la connexio amb la base de dades
     */
    
    @After()
    public  void classEnds(){
        tanca();
    }
    
    @Test 
    public void provaAltes(){
        //es fan als tractaments previs
    }
    
    @Test
    public void provaConsultaTotsEstabliments() throws UtilitatPersistenciaException{
        assertTrue(comparaLlistesEstabliments(aplicBD.obtenirEstabliments(),llistaTotsEstabliments));        
    }

    
    @Test
    public void provaConsultaTotsEmpleats() throws UtilitatPersistenciaException{
        assertTrue(comparaLlistesEmpleats(aplicBD.obtenirEmpleats(),llistaTotsEmpleats));        
    }

    @Test
    public void provaConsultaEmpleatsEstabliment() throws UtilitatPersistenciaException{
        assertTrue(comparaLlistesEmpleats(aplicBD.obtenirEmpleatsDeEstabliment(1),this.llistaEmpleatsEstabliment1));   
        assertTrue(comparaLlistesEmpleats(aplicBD.obtenirEmpleatsDeEstabliment(2),this.llistaEmpleatsEstabliment2)); 
        assertTrue(comparaLlistesEmpleats(aplicBD.obtenirEmpleatsDeEstabliment(3),this.llistaEmpleatsEstabliment3)); 
    }
    
    @Test
    public void provaConsultaEmpleatsCiutat() throws UtilitatPersistenciaException{
        assertTrue(comparaLlistesEmpleats(aplicBD.obtenirEmpleatsPerCiutat("Barcelona"),this.llistaBarcelonins));   
        assertTrue(comparaLlistesEmpleats(aplicBD.obtenirEmpleatsPerCiutat("Badalona"),this.llistaBadalonins));
        assertTrue(comparaLlistesEmpleats(aplicBD.obtenirEmpleatsPerCiutat("Castelldefels"),this.llistaCastelldefelencs));   
    }
    
    @Test
    public void provaConsultaEmpleatsNom() throws UtilitatPersistenciaException{
        assertTrue(comparaLlistesEmpleats(aplicBD.obtenirEmpleatsPerNom("Anna"),this.llistaAnna));   
        assertTrue(comparaLlistesEmpleats(aplicBD.obtenirEmpleatsPerNom("Eva"),this.llistaEva)); 
        assertTrue(comparaLlistesEmpleats(aplicBD.obtenirEmpleatsPerNom("Jaume"),this.llistaJaume)); 
        assertTrue(comparaLlistesEmpleats(aplicBD.obtenirEmpleatsPerNom("Josep"),this.llistaJosep)); 
    }    
        
    @Test
    public void provaConsultaEmpleatsTreballenOnViuen() throws UtilitatPersistenciaException{
        assertTrue(comparaLlistesEmpleats(aplicBD.obtenirEmpleatsQueTreballenOnViuen(),this.llistaEmpleatsViuenOnTreballen));
    }

    @Test
    public void provaConsultaEstablimentsPerNom() throws UtilitatPersistenciaException{
        assertTrue(comparaLlistesEstabliments(aplicBD.obtenirEstablimentsPerNom("Un"),this.llistaEstabliments1));
        assertTrue(comparaLlistesEstabliments(aplicBD.obtenirEstablimentsPerNom("Dos"),this.llistaEstabliments2));
        assertTrue(comparaLlistesEstabliments(aplicBD.obtenirEstablimentsPerNom("Tres"),this.llistaEstabliments3));

    }
    
    @Test
    public void provaConsultaEstablimentsPerCiutat() throws UtilitatPersistenciaException{
      assertTrue(comparaLlistesEstabliments(aplicBD.obtenirEstablimentsPerCiutat("Barcelona"),this.llistaEstablimentsBarcelonins));
      assertTrue(comparaLlistesEstabliments(aplicBD.obtenirEstablimentsPerCiutat("Castelldefels"),this.llistaEstablimentsCastelldefelencs));      
      assertTrue(comparaLlistesEstabliments(aplicBD.obtenirEstablimentsPerCiutat("Hospitalet"),this.llistaEstablimentsHospitalencs));      
    }

    
    
    @Test
    public void provaConsultaEmpleat3() throws UtilitatPersistenciaException{
        Empleat e1=new Empleat(), e2=llistaTotsEmpleats.get(3);
        e1.setCodi(e2.getCodi());
        
        assertTrue(comparaEmpleats(aplicBD.refrescar(e1),e2));
    }
    
    @Test
    public void provaConsultaEstabliment3() throws UtilitatPersistenciaException{
        Establiment e1=new Establiment(), e2=llistaTotsEstabliments.get(3);

        e1.setCodi(e2.getCodi());
        
        assertTrue(comparaEstabliments(aplicBD.refrescar(e1),e2,true));
    }
    
    @Test
    public void provaEsborrarEmpleat() throws UtilitatPersistenciaException {
        Empleat e=llistaTotsEmpleats.get(3);
        aplicBD.eliminar(e);
        
        assertNull(aplicBD.refrescar(e));
    }

    @Test
    public void provaEsborrarEstabliment() throws UtilitatPersistenciaException {
        Establiment e=llistaTotsEstabliments.get(3);
        
        aplicBD.eliminar(e);
        
        assertNull(aplicBD.refrescar(e));
    }
    
    @Test 
    public void provaModificaEmpleat() throws UtilitatPersistenciaException {
        final String  NOU_VALOR="La seva";
        Empleat nou=new Empleat(),e=llistaTotsEmpleats.get(0);
        
        int codiEmpleat=e.getCodi();
        
        nou.setCodi(codiEmpleat);
        
        e.setCiutat(NOU_VALOR);
        
        aplicBD.emmagatzemar(e);
        
        nou=aplicBD.refrescar(nou);
        
        assertEquals(nou.getCiutat(),NOU_VALOR);
    }
    
    @Test 
    public void provaModificaEstabliment() throws UtilitatPersistenciaException {
        final String  NOU_VALOR="La seva";
        Establiment nou=new Establiment(),e=llistaTotsEstabliments.get(0);
        
        int codiEstabliment=e.getCodi();
        
        nou.setCodi(codiEstabliment);
        
        e.setCiutat(NOU_VALOR);
        
        aplicBD.emmagatzemar(e);
        
        nou=aplicBD.refrescar(nou);
        
        assertEquals(nou.getCiutat(),NOU_VALOR);
    }
    
    /**
     * Dona d'alta tots els objectes del vector <code>totesAltes</code>
     * @throws UtilitatPersistenciaException en cas d'error
     */

    private void fesAltes() throws UtilitatPersistenciaException{
        for(Object o:totesAltes){
            if(o instanceof Establiment){
                aplicBD.emmagatzemar((Establiment) o);
            }
            if(o instanceof Empleat){
                aplicBD.emmagatzemar((Empleat) o);
            }
        }
    }

    /**
     * Crea un empleat amb les dades indicades pels parametres
     * @param codi codi que identifica al nou empleat
     * @param nom nom del nou empleat
     * @param ciutat ciutat del nou empleat
     * @param establiment establiment on es assignat el nou empleat
     * @return l'empleat creat
     */
    
    private Empleat creaEmpleat(int codi, String nom, String ciutat, Establiment establiment){
        Empleat emp=new Empleat();
        
        emp.setCodi(codi);
        emp.setNom(nom);
        emp.setCiutat(ciutat);
        emp.setEstabliment(establiment);
        
        return emp;
    }
    
    /**
     * Crea un establiment amb les dades indicades pels parametres i sense empleats
     * assignats
     * @param codi codi que identifica al nou establiment
     * @param nom nom del nou establiment
     * @param ciutat ciutat del nou establiment
     * @return l'establiment creat
     */
    
    private  Establiment creaEstabliment(int codi, String nom, String ciutat){
        Establiment est=new Establiment();
        
        est.setCodi(codi);
        est.setNom(nom);
        est.setCiutat(ciutat);
        
        return est;
    }
    
    /**
     * crea establiments i empleats i els emmagatzema a les llistes on aniran els
     * resultats esperats de les diferents proves
     */
    
    private  void creaDadesPrincipals(){
        
        
        Establiment[] establiments = new Establiment[6];

        Empleat[] empleats = new Empleat[10];


        
        int i=0;
        
        establiments[0]=creaEstabliment(++i,"Un","Barcelona");
        establiments[1]=creaEstabliment(++i,"Dos","Barcelona");
        establiments[2]=creaEstabliment(++i,"Tres","Castelldefels");
        establiments[3]=creaEstabliment(++i,"Quatre","Badalona");
        establiments[4]=creaEstabliment(++i,"Un","Hospitalet");
        establiments[5]=creaEstabliment(++i,"Dos","Hospitalet");
        
        llistaEstabliments1.add(establiments[0]);
        llistaEstabliments1.add(establiments[4]);
 
        llistaEstabliments2.add(establiments[1]);
        llistaEstabliments2.add(establiments[5]);
        
        llistaEstabliments3.add(establiments[2]);
        
        llistaEstablimentsCastelldefelencs.add(establiments[2]);
        llistaEstablimentsHospitalencs.add(establiments[4]);
        llistaEstablimentsHospitalencs.add(establiments[5]);
        
        empleats[0]=creaEmpleat(++i,"Josep","Barcelona",establiments[0]); 
        empleats[1]=creaEmpleat(++i,"Anna","Barcelona",establiments[0]);  
        empleats[2]=creaEmpleat(++i,"Jaume","Barcelona",establiments[2]); 
        empleats[3]=creaEmpleat(++i,"Eva","Barcelona",establiments[2]);
        empleats[4]=creaEmpleat(++i,"Josep","Barcelona",establiments[0]); 
        empleats[5]=creaEmpleat(++i,"Anna","Hospitalet",establiments[0]);
        empleats[6]=creaEmpleat(++i,"Jaume","Badalona",establiments[1]);  
        empleats[7]=creaEmpleat(++i,"Eva","Hospitalet",establiments[1]);  
        empleats[8]=creaEmpleat(++i,"Josep","Hospitalet",establiments[4]);  
        empleats[9]=creaEmpleat(++i,"Anna","Castelldefels",establiments[2]);
        
        
        totesAltes.addAll(Arrays.asList(establiments));
        totesAltes.addAll(Arrays.asList(empleats));
        
        llistaTotsEmpleats.addAll(Arrays.asList(empleats));
        llistaTotsEstabliments.addAll(Arrays.asList(establiments));
        
        llistaJosep.add(empleats[0]);
        llistaJosep.add(empleats[4]);
        llistaJosep.add(empleats[8]);
        
        llistaAnna.add(empleats[1]);
        llistaAnna.add(empleats[5]);
        llistaAnna.add(empleats[9]);
        
        llistaJaume.add(empleats[2]);
        llistaJaume.add(empleats[6]);
        
        llistaEva.add(empleats[3]);
        llistaEva.add(empleats[7]);
        
        llistaBarcelonins.addAll(Arrays.asList(empleats).subList(0, 5));
        llistaBadalonins.add(empleats[6]);
                
        llistaHospitalencs.add(empleats[5]);
        llistaHospitalencs.add(empleats[7]);
        llistaHospitalencs.add(empleats[8]);
        
        llistaCastelldefelencs.add(empleats[9]);
        
        llistaEmpleatsEstabliment1.addAll(Arrays.asList(empleats).subList(0,2));
        llistaEmpleatsEstabliment1.addAll(Arrays.asList(empleats).subList(4,6));
        
        llistaEmpleatsEstabliment2.addAll(Arrays.asList(empleats).subList(6,8));
        
        llistaEmpleatsEstabliment3.addAll(Arrays.asList(empleats).subList(2,4));
        llistaEmpleatsEstabliment3.add(empleats[9]);
        
        llistaEmpleatsEstabliment5.add(empleats[8]);
        
        
        llistaEmpleatsViuenOnTreballen.add(empleats[0]);
        llistaEmpleatsViuenOnTreballen.add(empleats[1]);
        llistaEmpleatsViuenOnTreballen.add(empleats[4]);

        llistaEmpleatsViuenOnTreballen.add(empleats[8]);
        
        llistaEmpleatsViuenOnTreballen.add(empleats[9]);
        
        
        llistaEstablimentsBarcelonins.add(establiments[0]);
        llistaEstablimentsBarcelonins.add(establiments[1]);

        
       

    }
    
    /**
     * Compara llistes dues llistes d'empleats sense tenir en compte l'ordre
     * @param a primera llista a comparar
     * @param b segona llista a comparar
     * @return cert si totes dues llistes son iguals i fals en cas contrari
     */
    private boolean comparaLlistesEmpleats(List <Empleat> a, List<Empleat> b){
        if(a.size()!=b.size()){
            return false;
        }
        for(Empleat e:a){
            if(!conteEmpleat(b,e)){
                return false;
            }
        }
        return true;
    }

    /**
     * Comprova si l'empleat <code>em</code> es a la llista <code>l</code>
     * @param l llista d'empleats on mirem si es troba l'empleat
     * @param em empleat que cerquem a la llista
     * @return cert si l'empleat es a la llista, fals en cas contrari
     */
    private boolean conteEmpleat(List<Empleat> l, Empleat em){
        for(Empleat e:l){
            if(comparaEmpleats(em,e)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Compara llistes dues llistes d'establiments sense tenir en compte l'ordre
     * @param a primera llista a comparar
     * @param b segona llista a comparar
     * @return cert si totes dues llistes son iguals i fals en cas contrari
     */
    private boolean comparaLlistesEstabliments (List a, List b){
        if(a.size()!=b.size()){
            return false;
        }
        for(Object o:a){
            Establiment e=(Establiment)o;
            if(!conteEstabliment(b,e)){
                return false;
            }
        }
        return true;
    }

    /**
     * Comprova si l'establiment <code>et</code> es a la llista <code>l</code>
     * @param l  llista on cerquem l'establiment
     * @param et establiment que cerquem a la llista
     * @return cert si es troba un establiment igual a <code>et</code> a la llista
     * <code>l</code>. Fals en cas contrari.
     */
    
    private boolean conteEstabliment(List<Establiment> l, Establiment et){
        for(Establiment e:l){
            if(comparaEstabliments(et,e)){
                return true;
            }
        }
        return false;
    }    
    
    /**
     * obre la connexio amb la base de dades
     */
    private void obre(){
        try{
        
        aplicBD.iniciar();
        aplicBD.obrir();

        }catch(Exception e){
            System.out.println("@@@@@@@@@");
            System.out.println(e.getMessage());
            System.out.println("@@@@@@@@@");
        }
    }
    
    /**
     * tanca la connexio amb la base de dades
     */
    private void tanca(){
        aplicBD.tancar();
    }
    
    /**
     * compara dues cadenes tenint en compte que poden ser <code>null</code>
     * @param s1 primera cadena a comparar
     * @param s2 segona cadena a comparar
     * @return cert si totes dues cadenes son iguals; fals en cas contrari
     */
    private boolean comparaStrings(String s1, String s2){
        if(s1==s2){
            return true;
        }
        
        if(s1==null){
            return false; //si s2 valgues null ja no hauriem arribat aqui
        }
        
        return s1.equals(s2);
    }

    /**
     * compara totes les dades de dos empleats 
     * @param e1 primera cadena a comparar
     * @param e2 segona cadena a comparar
     * @return cert si totes dos empleats tenen les mateixes dades
     */
    private boolean comparaEmpleats(Empleat e1, Empleat e2){
        if(e1==e2){
            return true;
        }
        
        if(e1==null || e2==null){
            return false; //un es null i l'altre no
        }    
        
        return e1.getCodi()==e2.getCodi() && comparaStrings(e1.getNom(),e2.getNom()) && comparaStrings(e1.getCiutat(),e2.getCiutat()) && comparaEstabliments(e1.getEstabliment(), e2.getEstabliment());
           
    }

    /**
     * compara totes les dades de dos establiments, excepte la llista dels empleats assignats
     * @param e1 primer establiment a comparar
     * @param e2 segon establiemnt a comparar
     * @return cert si tenen totes les dades iguals, fals en cas contrari
     */
    private boolean comparaEstabliments(Establiment e1, Establiment e2){
        return comparaEstabliments(e1,e2,false);
    }

    /**
     * compara totes les dades de dos establiments
     * @param e1 primer establiment a comparar
     * @param e2 segon establiemnt a comparar
     * @param llistaEmpleats indica si cal comparar tambe les llistes dels 
     * empleats dels dos establiments;  nomes es fara aquesta comparacio si 
     * <code>llistaEmpleats</code> val cert
     * @return cert si tenen totes les dades iguals, fals en cas contrari
     */    
    private boolean comparaEstabliments(Establiment e1, Establiment e2, boolean llistaEmpleats){
        if(e1==e2){
            return true;
        }
        
        if(e1==null || e2==null){
            return false; //un es null i l'altre no
        }        
        
        if(e1.getCodi()!=e2.getCodi()|| !comparaStrings(e1.getNom(),e2.getNom())||!comparaStrings(e1.getCiutat(),e2.getCiutat()) ){
            return false;
        }
     
        
        return !llistaEmpleats || this.comparaLlistesEmpleats(e1.getEmpleats(), e2.getEmpleats());
        
    }
    

}