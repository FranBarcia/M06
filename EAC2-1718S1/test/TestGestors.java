/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import gestors.GestorJpaEmpleat;
import gestors.GestorJpaEstabliment;
import gestors.GestorJpaException;
import gestors.GestorJpaPuntVenda;
import gestors.GestorJpaTaller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Empleat;
import model.Establiment;
import model.PuntVenda;
import model.Taller;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 *
 * @author professor
 */
public class TestGestors {
    
    private  EntityManagerFactory emf;
    private  EntityManager em;
    private  final String PU = "jpa1718s1";
    private  GestorJpaEmpleat gestorJpaEmpleat;
    private  GestorJpaEstabliment gestorJpaEstabliment;
    private  GestorJpaPuntVenda gestorJpaPuntVenda;
    private  GestorJpaTaller gestorJpaTaller;
    
    private ArrayList totesAltes=new ArrayList();
    private ArrayList<Empleat> llistaTotsEmpleats = new ArrayList<>(),
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
                               llistaEmpleatsViuenOnTreballen = new ArrayList<>();
    
    private ArrayList<Establiment> llistaEstabliments1 = new ArrayList<>(),
                                   llistaEstabliments2 = new ArrayList<>(),
                                   llistaEstabliments3  = new ArrayList<>(),
                                   llistaEstablimentsBarcelonins = new ArrayList<>(),
                                   llistaEstablimentsHospitalencs  = new ArrayList<>(),
                                   llistaEstablimentsCastelldefelencs = new ArrayList<>();
    
    private ArrayList<Taller> llistaTallers1 = new ArrayList<>(),
                                   llistaTallers2 = new ArrayList<>(),
                                   llistaTallers3  = new ArrayList<>();
    
    private ArrayList<PuntVenda> llistaPuntsVenda1 = new ArrayList<>(),
                                   llistaPuntsVenda2 = new ArrayList<>(),
                                   llistaPuntsVenda3  = new ArrayList<>();
    
    private ArrayList<Taller> llistaTotsTallers = new ArrayList<>();
    private ArrayList<PuntVenda> llistaTotsPuntsVenda = new ArrayList<>();
    private ArrayList<Establiment> llistaTotsEstabliments = new ArrayList<>();
    
    
    
    
    
    

    /**
     * Abans de cada test neteja la base de dades
     * @throws GestorJpaException 
     */
    @Before
    public void setUp() throws GestorJpaException {
        obre();
        creaDadesPrincipals();
        
        List<Empleat> auxLlistaEmpleats=gestorJpaEmpleat.obtenirEmpleats();
        List<PuntVenda> auxLlistaPuntsVenda=gestorJpaPuntVenda.obtenirPuntsVenda();
        List<Establiment> auxLlistaEstabliments;
        
        for(Empleat e:auxLlistaEmpleats){
            gestorJpaEmpleat.eliminar(e.getCodi());
        }
        for(Establiment e:auxLlistaPuntsVenda){
            gestorJpaEstabliment.eliminar(e.getCodi());
        }
        
        auxLlistaEstabliments=gestorJpaEstabliment.obtenirEstabliments();
        
        for(Establiment e:auxLlistaEstabliments){
            gestorJpaEstabliment.eliminar(e.getCodi());
        }
        
        fesAltes();
        
        actualitzaLlistesTallers();
        
    } 
    
    /**
     * En acabar els tests, tanca l'entityManager i la factoria d'entities manager
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
    public void provaConsultaTotsEstabliments(){
        assertTrue(comparaLlistesEstabliments(gestorJpaEstabliment.obtenirEstabliments(),llistaTotsEstabliments));        
    }

    @Test
    public void provaConsultaTotsTallers(){
        assertTrue(comparaLlistesEstabliments(gestorJpaTaller.obtenirTallers(),llistaTotsTallers));        
    }

    @Test
    public void provaConsultaTotsPuntsVenda(){
        assertTrue(comparaLlistesEstabliments(gestorJpaPuntVenda.obtenirPuntsVenda(),llistaTotsPuntsVenda));        
    }    
       
    @Test
    public void provaIncrementaMaquinesCiutat(){
        final String CIUTAT="Barcelona";
        final int INCREMENT=3;
        
        
        
        List<Taller> consulta = gestorJpaTaller.obtenirTallers();
        HashMap<Integer,Integer> valorsAntics=new HashMap<>();
        
        //emmagatzememem el nombre de maquines abans del canvi
        for(int i=0;i<consulta.size();i++){
            Taller aux=consulta.get(i);
            if(aux.getCiutat().equals(CIUTAT)){
                valorsAntics.put(aux.getCodi(),aux.getNMaquines());
            }
        }
        
        gestorJpaTaller.incrementaMaquines(CIUTAT, INCREMENT);
        
        tancaIObre();
        
        
        consulta = gestorJpaTaller.obtenirTallers();

        //comprovem que s'han realitzat els canvis
        for(int i=0;i<consulta.size();i++){
            Taller aux=consulta.get(i);
            if(aux.getCiutat().equals(CIUTAT)){                    
                if((valorsAntics.get(aux.getCodi())+INCREMENT)!=aux.getNMaquines()){

                    fail();
                }
            }
        }
 
    }
    
    
    @Test
    public void provaEsborraPuntsVendaCiutat() throws GestorJpaException{
        final String CIUTAT="Barcelona";
        List <PuntVenda> totsPuntsVenda;
        int nPvBarcelona=0;
        PuntVenda foraBarcelona=null;
        List <Empleat> totsEmpleats;
        
        totsPuntsVenda=gestorJpaPuntVenda.obtenirPuntsVenda();
        
        for(PuntVenda pv:totsPuntsVenda){
            if(pv.getCiutat().equals(CIUTAT)){
                nPvBarcelona++;
            } else{
                foraBarcelona=pv;
            }
        }
        
        totsEmpleats=gestorJpaEmpleat.obtenirEmpleats();
        
        for(Empleat e:totsEmpleats){
            if(e.getEstabliment().getCiutat().equals(CIUTAT)){
                e.setEstabliment(foraBarcelona);
                gestorJpaEmpleat.modificar(e);
            }
        }
        
        gestorJpaPuntVenda.esborraPuntsVenda(CIUTAT);
        tancaIObre();
        totsPuntsVenda=gestorJpaPuntVenda.obtenirPuntsVenda();
        
        int compt=0;
        
        for(PuntVenda pv:totsPuntsVenda){
            if(pv.getCiutat().equals(CIUTAT)){
                compt++;                
            }
        }
        
        assertTrue(compt==0 && totsPuntsVenda.size()+ nPvBarcelona == llistaTotsPuntsVenda.size());
    }
    
    
    
    
        
    
    
    
    
    @Test
    public void provaConsultaTotsEmpleats(){
        assertTrue(comparaLlistesEmpleats(gestorJpaEmpleat.obtenirEmpleats(),llistaTotsEmpleats));        
    }

    @Test
    public void provaConsultaEmpleatsEstabliment(){
        assertTrue(comparaLlistesEmpleats(gestorJpaEmpleat.obtenirEmpleatsDeEstabliment(1),this.llistaEmpleatsEstabliment1));   
        assertTrue(comparaLlistesEmpleats(gestorJpaEmpleat.obtenirEmpleatsDeEstabliment(2),this.llistaEmpleatsEstabliment2)); 
        assertTrue(comparaLlistesEmpleats(gestorJpaEmpleat.obtenirEmpleatsDeEstabliment(3),this.llistaEmpleatsEstabliment3)); 
    }
    
    @Test
    public void provaConsultaEmpleatsCiutat(){
        assertTrue(comparaLlistesEmpleats(gestorJpaEmpleat.obtenirEmpleatsPerCiutat("Barcelona"),this.llistaBarcelonins));   
        assertTrue(comparaLlistesEmpleats(gestorJpaEmpleat.obtenirEmpleatsPerCiutat("Badalona"),this.llistaBadalonins));
        assertTrue(comparaLlistesEmpleats(gestorJpaEmpleat.obtenirEmpleatsPerCiutat("Castelldefels"),this.llistaCastelldefelencs));   
    }
    
    @Test
    public void provaConsultaEmpleatsNom(){
        assertTrue(comparaLlistesEmpleats(gestorJpaEmpleat.obtenirEmpleatsPerNom("Anna"),this.llistaAnna));   
        assertTrue(comparaLlistesEmpleats(gestorJpaEmpleat.obtenirEmpleatsPerNom("Eva"),this.llistaEva)); 
        assertTrue(comparaLlistesEmpleats(gestorJpaEmpleat.obtenirEmpleatsPerNom("Jaume"),this.llistaJaume)); 
        assertTrue(comparaLlistesEmpleats(gestorJpaEmpleat.obtenirEmpleatsPerNom("Josep"),this.llistaJosep)); 
    }    
        
    @Test
    public void provaConsultaEmpleatsTreballenOnViuen(){
        assertTrue(comparaLlistesEmpleats(gestorJpaEmpleat.obtenirEmpleatsQueTreballenOnViuen(),this.llistaEmpleatsViuenOnTreballen));
    }

    @Test
    public void provaConsultaDelegacionsPerNom(){
        assertTrue(comparaLlistesEstabliments(gestorJpaEstabliment.obtenirEstablimentsPerNom("Un", Establiment.class),this.llistaEstabliments1));
        assertTrue(comparaLlistesEstabliments(gestorJpaEstabliment.obtenirEstablimentsPerNom("Dos", Establiment.class),this.llistaEstabliments2));
        assertTrue(comparaLlistesEstabliments(gestorJpaEstabliment.obtenirEstablimentsPerNom("Tres", Establiment.class),this.llistaEstabliments3));

        assertTrue(comparaLlistesEstabliments(gestorJpaEstabliment.obtenirEstablimentsPerNom("Un", Taller.class),this.llistaTallers1));
        assertTrue(comparaLlistesEstabliments(gestorJpaEstabliment.obtenirEstablimentsPerNom("Dos", Taller.class),this.llistaTallers2));
        assertTrue(comparaLlistesEstabliments(gestorJpaEstabliment.obtenirEstablimentsPerNom("Tres", Taller.class),this.llistaTallers3));

        assertTrue(comparaLlistesEstabliments(gestorJpaEstabliment.obtenirEstablimentsPerNom("Un", PuntVenda.class),this.llistaPuntsVenda1));
        assertTrue(comparaLlistesEstabliments(gestorJpaEstabliment.obtenirEstablimentsPerNom("Dos", PuntVenda.class),this.llistaPuntsVenda2));
        assertTrue(comparaLlistesEstabliments(gestorJpaEstabliment.obtenirEstablimentsPerNom("Tres", PuntVenda.class),this.llistaPuntsVenda3));

    }
    
    @Test
    public void provaConsultaDelegacionsPerCiutat(){
      assertTrue(comparaLlistesEstabliments(gestorJpaEstabliment.obtenirEstablimentsPerCiutat("Barcelona"),this.llistaEstablimentsBarcelonins));
      assertTrue(comparaLlistesEstabliments(gestorJpaEstabliment.obtenirEstablimentsPerCiutat("Castelldefels"),this.llistaEstablimentsCastelldefelencs));      
      assertTrue(comparaLlistesEstabliments(gestorJpaEstabliment.obtenirEstablimentsPerCiutat("Hospitalet"),this.llistaEstablimentsHospitalencs));      
    }

    
    
    @Test
    public void provaConsultaEmpleat3(){
        Empleat e=llistaTotsEmpleats.get(3);
        assertEquals(gestorJpaEmpleat.obtenirEmpleat(e.getCodi()),e);
    }
    
    @Test
    public void provaConsultaEstabliment3(){
        Establiment e=llistaTotsEstabliments.get(3);
        assertEquals(gestorJpaEstabliment.obtenirEstabliment(e.getCodi()),e);
    }
    
    @Test
    public void provaEsborrarEmpleat() throws GestorJpaException{
        int e=llistaTotsEmpleats.get(3).getCodi();
        gestorJpaEmpleat.eliminar(e);
        
        assertNull(gestorJpaEmpleat.obtenirEmpleat(e));
    }

    @Test
    public void provaEsborrarEstabliment() throws GestorJpaException{
        int e=llistaTotsEstabliments.get(3).getCodi();
        gestorJpaEstabliment.eliminar(e);
        
        assertNull(gestorJpaEstabliment.obtenirEstabliment(e));
    }
    
    @Test 
    public void provaModificaEmpleat() throws GestorJpaException{
        final String  NOU_VALOR="La seva";
        Empleat nou,e=llistaTotsEmpleats.get(0);
        int codiEmpleat=e.getCodi();
        e.setCiutat(NOU_VALOR);
        gestorJpaEmpleat.modificar(e);
        
        nou=gestorJpaEmpleat.obtenirEmpleat(codiEmpleat);
        assertEquals(nou.getCiutat(),NOU_VALOR);
    }
    
    @Test 
    public void provaModificaEstabliment() throws GestorJpaException{
        final String  NOU_VALOR="La seva";
        Establiment nou,e=llistaTotsEstabliments.get(0);
        int codiEstabliment=e.getCodi();
        e.setCiutat(NOU_VALOR);
        gestorJpaEstabliment.modificar(e);
        
        nou=gestorJpaEstabliment.obtenirEstabliment(codiEstabliment);
        assertEquals(nou.getCiutat(),NOU_VALOR);
    }
    
    private void actualitzaLlistesTallers(){
        for(PuntVenda pv:this.llistaTotsPuntsVenda){
            pv.getTallerAssignat().getPuntsVendaAssociats().add(pv);
        }
    }
    private void fesAltes(){
        for(Object o:totesAltes){
            if(o instanceof Establiment){
               gestorJpaEstabliment.inserir((Establiment)o);
            }
            if(o instanceof Empleat){
               gestorJpaEmpleat.inserir((Empleat)o);
            }
        };
    }
    
    private Empleat creaEmpleat(int codi, String nom, String ciutat, Establiment establiment){
        return new Empleat(codi, nom, ciutat, establiment);
    }
    
    private  Taller creaTaller(int codi, String nom, String ciutat, int nMaquines){
        return new Taller(codi, nom, ciutat, nMaquines);
    }
    
    private  PuntVenda creaPuntVenda(int codi, String nom, String ciutat, int facturacioObjectiu, Taller taller){
        return new PuntVenda(codi, nom, ciutat, facturacioObjectiu, taller);
    }

    
    private  void creaDadesPrincipals(){
        
        
        Taller[] tallers = new Taller[3];
        PuntVenda[] puntsVenda = new PuntVenda[6];
        Empleat[] empleats = new Empleat[10];
        int i=0;
        
        
        tallers[0]=creaTaller(++i,"Un","Barcelona",2);
        tallers[1]=creaTaller(++i,"Dos","Barcelona",3);
        tallers[2]=creaTaller(++i,"Un","Hospitalet",4);
        
        puntsVenda[0]=creaPuntVenda(++i,"Un","Barcelona",800, tallers[0]);
        puntsVenda[1]=creaPuntVenda(++i,"Dos","Barcelona",700, tallers[0]);
        puntsVenda[2]=creaPuntVenda(++i,"Tres","Barcelona",500,tallers[1]);
        puntsVenda[3]=creaPuntVenda(++i,"Quatre","Badalona",800,tallers[1]);
        puntsVenda[4]=creaPuntVenda(++i,"Un","Hospitalet",500,tallers[2]);
        puntsVenda[5]=creaPuntVenda(++i,"Dos","Hospitalet",800,tallers[2]);
        
        i=0;
        empleats[0]=creaEmpleat(++i,"Josep","Barcelona",tallers[0]); 
        empleats[1]=creaEmpleat(++i,"Anna","Barcelona",tallers[0]);  
        empleats[2]=creaEmpleat(++i,"Jaume","Barcelona",tallers[2]); 
        empleats[3]=creaEmpleat(++i,"Eva","Barcelona",tallers[2]);
        empleats[4]=creaEmpleat(++i,"Josep","Barcelona",puntsVenda[0]); 
        empleats[5]=creaEmpleat(++i,"Anna","Hospitalet",puntsVenda[0]);
        empleats[6]=creaEmpleat(++i,"Jaume","Badalona",puntsVenda[1]);  
        empleats[7]=creaEmpleat(++i,"Eva","Hospitalet",puntsVenda[1]);  
        empleats[8]=creaEmpleat(++i,"Josep","Hospitalet",puntsVenda[4]);  
        empleats[9]=creaEmpleat(++i,"Anna","Castelldefels",puntsVenda[2]);
        
        
        totesAltes.addAll(Arrays.asList(tallers));
        totesAltes.addAll(Arrays.asList(puntsVenda));
        totesAltes.addAll(Arrays.asList(empleats));
        
        llistaTotsEmpleats.addAll(Arrays.asList(empleats));
        llistaTotsTallers.addAll(Arrays.asList(tallers));
        llistaTotsPuntsVenda.addAll(Arrays.asList(puntsVenda));
        
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
        
        llistaEmpleatsEstabliment3.addAll(Arrays.asList(empleats).subList(2,4));
        
        llistaEmpleatsViuenOnTreballen.add(empleats[0]);
        llistaEmpleatsViuenOnTreballen.add(empleats[1]);
        llistaEmpleatsViuenOnTreballen.add(empleats[4]);
        llistaEmpleatsViuenOnTreballen.add(empleats[8]);
        
        llistaEstabliments1.add(tallers[0]);llistaTallers1.add(tallers[0]);
        llistaEstabliments1.add(tallers[2]);llistaTallers1.add(tallers[2]);
        llistaEstabliments1.add(puntsVenda[0]); llistaPuntsVenda1.add(puntsVenda[0]);       
        llistaEstabliments1.add(puntsVenda[4]); llistaPuntsVenda1.add(puntsVenda[4]);
        
        llistaEstabliments2.add(tallers[1]);llistaTallers2.add(tallers[1]);
        llistaEstabliments2.add(puntsVenda[1]);  llistaPuntsVenda2.add(puntsVenda[1]);      
        llistaEstabliments2.add(puntsVenda[5]);  llistaPuntsVenda2.add(puntsVenda[5]);
        
        llistaEstabliments3.add(puntsVenda[2]); llistaPuntsVenda3.add(puntsVenda[2]);
        
        llistaEstablimentsBarcelonins.add(tallers[0]);
        llistaEstablimentsBarcelonins.add(tallers[1]);
        llistaEstablimentsBarcelonins.add(puntsVenda[0]);
        llistaEstablimentsBarcelonins.add(puntsVenda[1]);
        llistaEstablimentsBarcelonins.add(puntsVenda[2]);

        llistaEstablimentsHospitalencs.add(tallers[2]);        
        llistaEstablimentsHospitalencs.add(puntsVenda[4]);
        llistaEstablimentsHospitalencs.add(puntsVenda[5]);
        
        llistaTotsEstabliments.addAll(llistaTotsPuntsVenda);
        llistaTotsEstabliments.addAll(llistaTotsTallers);
        
    

    }
    
    
    
    
    
    
    
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

    
    private boolean conteEmpleat(List<Empleat> l, Empleat em){
        for(Empleat e:l){
            if(comparaEmpleats(em,e)){
                return true;
            }
        }
        return false;
    }


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

    
    private boolean conteEstabliment(List<Establiment> l, Establiment et){
        for(Establiment e:l){
            if(comparaEstabliments(et,e)){
                return true;
            }
        }
        return false;
    }    
    
    private void obre(){
        try{
        emf = Persistence.createEntityManagerFactory(PU);
        em=emf.createEntityManager();

        gestorJpaEmpleat= new GestorJpaEmpleat(em);
        gestorJpaEstabliment= new GestorJpaEstabliment(em);
        gestorJpaPuntVenda= new GestorJpaPuntVenda(em);
        gestorJpaTaller= new GestorJpaTaller(em);
        }catch(Exception e){
            System.out.println("@@@@@@@@@");
            System.out.println(e.getMessage());
            System.out.println("@@@@@@@@@");
        }
    }
    private void tanca(){
        em.close();
        emf.close();
    }
    
    private void tancaIObre() {
        tanca();
        obre();
    }
    
    
// ESBORRAR A PARTIR D'AQUI ?????
    
    private boolean comparaStrings(String s1, String s2){
        if(s1==s2){
            return true;
        }
        
        if(s1==null){
            return false; //si s2 valgues null ja no hauriem arribat aqui
        }
        
        return s1.equals(s2);
    }
 
    private boolean comparaEmpleats(Empleat e1, Empleat e2){
        if(e1==e2){
            return true;
        }
        
        if(e1==null || e2==null){
            return false; //un es null i l'altre no
        }    
        
        return e1.getCodi()==e2.getCodi() && comparaStrings(e1.getNom(),e2.getNom()) && comparaStrings(e1.getCiutat(),e2.getCiutat()) && comparaEstabliments(e1.getEstabliment(), e2.getEstabliment());
           
    }
    
    private boolean comparaEstabliments(Establiment e1, Establiment e2){
        if(e1==e2){
            return true;
        }
        
        if(e1==null || e2==null){
            return false; //un es null i l'altre no
        }        
        
        if(e1.getCodi()!=e2.getCodi()|| !comparaStrings(e1.getNom(),e2.getNom())||!comparaStrings(e1.getCiutat(),e2.getCiutat()) || e1.getClass()!=e2.getClass()){
            return false;
        }
        
        // tots dos son de la mateixa classe
        
        
        if(e1 instanceof Taller){
            return ((Taller)e1).getNMaquines()==((Taller)e2).getNMaquines() && comparaLlistesPuntsVenda(((Taller)e1).getPuntsVendaAssociats(),((Taller)e2).getPuntsVendaAssociats());
        }else if(e1 instanceof PuntVenda){
            return ((PuntVenda)e1).getFacturacioObjectiu()==((PuntVenda)e2).getFacturacioObjectiu() && comparaEstabliments(((PuntVenda)e1).getTallerAssignat(),((PuntVenda)e2).getTallerAssignat());
        }
        
        return true;  //aqui no hauriem d'arribar
        
    }
    
    //nomes comparem els codis dels punts de venda per evitar recursivitat infinita
    
    private boolean comparaLlistesPuntsVenda(List<PuntVenda> pv1, List<PuntVenda> pv2){
        int longitud=pv1.size();
        
        if(longitud!=pv2.size()){
            return false;
        }
        
        ArrayList<Integer> aux= new ArrayList<>();
        
        for(PuntVenda pv:pv1){
            aux.add(pv.getCodi());
        }
        
        for(PuntVenda pv:pv2){
            if(!aux.remove((Integer)pv.getCodi())){
                return false;
            }
        }        
        
        return true;
        
    }

}
