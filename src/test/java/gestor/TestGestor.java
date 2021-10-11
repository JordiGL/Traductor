/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestor;

import java.sql.SQLException;
import java.sql.Timestamp;
import model.Database;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import model.Usuari;
import org.junit.After;


/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class TestGestor {
    
    private GestorUsuari gestor;
    private Database db;

    private final Usuari[] usuaris = {
        new Usuari("gemma@email", "Gemma", "femeni", "12345", "clau"),
        new Usuari("jonatan@email", "Jonatan", "masculi", "54321", "clau")
    };
    
    @Before
    public void setUp() throws SQLException, GestorException {
        
        db = new Database();
        db.obrir();
        gestor = db.getGestorUsuari();
        db.buidarTaula("usuaris");
        ferAltesUsuaris();
    }
    
    @After
    public void tearDown() throws SQLException {
        db.tancar();
    }
    
    public void ferAltesUsuaris() throws GestorException {
        for(Usuari u : usuaris){
            gestor.inserir(u);
        }
    }
    
    @Test
    public void provaObtenir() throws SQLException, GestorException {
        
        assertNull(gestor.obtenirUsuari("asdfsd"));
        
        Usuari a=gestor.obtenirUsuari("gemma@email");
        assertTrue(comparaUsuaris(a,usuaris[0]));
        
        a=gestor.obtenirUsuari("jonatan@email");
        assertTrue(comparaUsuaris(a,usuaris[1]));
       
    }
    
    @Test
    public void provaEliminar()throws SQLException, GestorException{
        
        gestor.eliminar("gemma@email");
        assertNull(gestor.obtenirUsuari("gemma@email"));
    }
    
    @Test
    public void provaInserir() throws SQLException, GestorException{
        
        Usuari jordi = new Usuari("jordi@email", "Jordi", "masculi", "42351", "clau");
        jordi.setUltimLogin(new Timestamp(System.currentTimeMillis()));
        jordi.setAdministrador(true);
        gestor.inserir(jordi);
        
        Usuari a = gestor.obtenirUsuari(jordi.getEmail());
        
        assertTrue(comparaUsuaris(jordi,a));
    }
    
    @Test
    public void provaModificar()throws SQLException, GestorException{      
        
        Usuari gemma=gestor.obtenirUsuari("gemma@email");
        gemma.setAdministrador(true);
        gestor.modificar(gemma.getEmail(), gemma);
        Usuari a = gestor.obtenirUsuari(gemma.getEmail());
        assertTrue(a.getAdministrador());
    }

    public boolean comparaUsuaris(Usuari p1, Usuari p2){
        
        return  p1.getEmail().equals(p2.getEmail()) &&
                p1.getNom().equals(p2.getNom()) &&
                p1.getGenere().equals(p2.getGenere()) &&
                p1.getTelefon().equals(p2.getTelefon()) &&
                p1.getClau().equals(p2.getClau());
    }
    
}
