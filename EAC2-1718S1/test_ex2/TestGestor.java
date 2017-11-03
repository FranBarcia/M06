/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import gestors.GestorOrException;
import gestors.GestorOrProgramador;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import model.Programador;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author professor
 */
public class TestGestor {
    
    private final String TAULA="Programador";
    
    private final String URL="jdbc:postgresql://localhost:5432/or1718s1";
    private final String USER="ioc";
    private final String PSW="ioc";
    //private final String DRIVER="org.postgresql.Driver";
    private GestorOrProgramador gestor;
    
    
    
    private Connection con;
    
    
    private Programador programadors[]={
            new Programador(1,"Josep","Barcelona",new String[]{"C","Java","Python"}),
            new Programador(2,"Anna","Girona",new String[]{"Java","C#"}),
            new Programador(3,"Jaume","Tarragona",new String[]{"Php","C#"}),
            new Programador(4,"Eva","Lleida",new String[]{"Php","Java","Python"})
    };
    
    
    @Before
    public void setUp() throws SQLException, GestorOrException {
        obrir();
        buidarTaula();
        ferAltes();
        
    }
    
    @After
    public void tearDown() throws SQLException {
        tancar();
    }
    
    
    private void tancarIObrir() throws SQLException{
        tancar();
        obrir();
        
    }
    
    
    private void obrir() throws SQLException{
        con = DriverManager.getConnection(URL, USER, PSW);
        con.setAutoCommit(true);
        gestor = new GestorOrProgramador(con);
     
    }
    
    private void tancar() throws SQLException{
        con.close();
    }
    
    private void buidarTaula() throws SQLException{
        PreparedStatement ps=con.prepareStatement("DELETE FROM "+TAULA);
        
        ps.executeUpdate();
    }
    
    private void ferAltes() throws GestorOrException{
        for(Programador p:programadors){
            gestor.inserir(p);
        }
    }
    
    
    public boolean comparaProgramadors(Programador p1, Programador p2){
        return  p1.getCodi()==p2.getCodi() &&
                p1.getNom().equals(p2.getNom()) &&
                p1.getCiutat().equals(p2.getCiutat()) &&
                comparaLlenguatges(p1.getLlenguatges(),p2.getLlenguatges());
    }
    
    public boolean comparaLlenguatges(List<String> l1, List<String> l2){
        
        if(l1.size()!=l2.size()){
            return false;
        }
        
        for(String s:l1){
            if(!l2.contains(s)){
                return false;
            }
        }
        return true;
    }
    
    
    @Test
    public void provaAltes(){
        
    }
    
    @Test 
    public void provaConsultaPerCodi() throws SQLException, GestorOrException{
        tancarIObrir();
        Programador p=gestor.obtenirProgramador(3);
        assertTrue(comparaProgramadors(p,programadors[2]));
    }
    
    @Test 
    public void provaObtenirProgramadors() throws SQLException, GestorOrException{
        tancarIObrir();
        List<Programador> l=gestor.obtenirProgramadors();
        
        if(l.size()!=programadors.length){
            fail();
        }
        
        for(Programador p:l){
            if(!comparaProgramadors(p,programadors[p.getCodi()-1])){
                fail();
            }
        }
    }
    
    @Test
    public void provaObtenirProgramadorsPerLlenguatge() throws SQLException, GestorOrException{
        tancarIObrir();
        List<Programador> l=gestor.obtenirProgramadorsPerLlenguatge("Python");
        
        assertTrue(l.size()==2 && comprovaProgramador(l,1)&& comprovaProgramador(l,4));
    }
    
    private boolean comprovaProgramador(List<Programador> l, int codi){
        for(Programador p:l){
            if(p.getCodi()==codi && comparaProgramadors(p,programadors[codi-1])){
                return true;
            }
        }
        return false;
    }
    
    @Test
    public void provaEliminar() throws SQLException, GestorOrException {
        gestor.eliminar(3);
        tancarIObrir();
        
        List<Programador> l = gestor.obtenirProgramadors();
        
        assertTrue(l.size()==3 && comprovaProgramador(l,1) && comprovaProgramador(l,2)&& comprovaProgramador(l,4));
    }
    
    @Test
    public void provaModificar() throws GestorOrException, SQLException {
        Programador p=gestor.obtenirProgramador(3);
        String[] llenguatgesNous=new String[]{"Perl","JavaScript"};
        
        p.setNom("Raquel"); p.setCiutat("Sabadell"); p.setLlenguatges(Arrays.asList(llenguatgesNous));
        
        gestor.modificar(p);

        tancarIObrir();
        
        p=gestor.obtenirProgramador(3);
        assertTrue(p.getCodi()==3 && p.getCiutat().equals("Sabadell")&& comparaLlenguatges(p.getLlenguatges(),Arrays.asList(llenguatgesNous)));
      
    }
    
    
}
