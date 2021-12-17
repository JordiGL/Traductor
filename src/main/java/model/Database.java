/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;


import gestor.GestorException;
import gestor.GestorUsuari;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 
 * @author Jordi Gómez lozano
 */
public class Database {

    private final String HOST = "localhost";
    private final String DB = "db_escoltam";
    private final int PORT = 5432;
    private final String USER = "postgres";
    private final String PASS = "pass";
    private String url = "jdbc:postgresql://%s:%d/%s";
    
    private Connection connexio;
    
    private GestorUsuari gestorUsuari;

    
    public Database() throws GestorException{

        this.url = String.format(this.url, this.HOST, this.PORT, this.DB);
        try {
            obrir();
        } catch (SQLException ex) {
            throw new GestorException(ex.getMessage());
        }
    }
    
    public GestorUsuari getGestorUsuari() {
        return gestorUsuari;
    }

    public void setGestorUsuari(GestorUsuari gestorUsuari) {
        this.gestorUsuari = gestorUsuari;
    }
     
       
    public void obrir() throws SQLException{
        connexio = DriverManager.getConnection(this.url, USER, PASS);
        connexio.setAutoCommit(true);   
        gestorUsuari = new GestorUsuari(connexio);
    }
    
    public void tancar() throws SQLException{
        connexio.close();
    }
    
    public void buidarTaula(String taulaABuidar) throws GestorException{
        
        try{
            PreparedStatement ps =connexio.prepareStatement("DELETE FROM "+taulaABuidar);     
            ps.executeUpdate();  
        } catch (SQLException ex) {
            
            throw new GestorException(ex.getMessage());     
        }
    }
      
//    public void tancarIObrir() throws SQLException {
//        tancar();
//        obrir();
//
//    }
//
//    public String insertUsuari(Usuari usuari) {
//        String SQL = "INSERT INTO usuaris(nom, genere, telefon, email, password, administrador) "
//                + "VALUES(?,?,?,?,?,?)";
//
//        String id = null;
//
//        try (Connection conn = getExtraConnection();
//                PreparedStatement pstmt = conn.prepareStatement(SQL,
//                Statement.RETURN_GENERATED_KEYS)) {
//
//            pstmt.setString(1, usuari.getNom());
//            pstmt.setString(2, usuari.getGenere());
//            pstmt.setString(3, usuari.getTelefon());
//            pstmt.setString(4, usuari.getEmail());
//            pstmt.setString(5, usuari.getClau());
//            pstmt.setBoolean(6, usuari.getAdministrador());
//            
//            int affectedRows = pstmt.executeUpdate();
//            // check the affected rows 
//            if (affectedRows > 0) {
//                // get the ID back
//                try (ResultSet rs = pstmt.getGeneratedKeys()) {
//                    if (rs.next()) {
//                        id = rs.getString(1);
//                    }
//                } catch (SQLException ex) {
//                    System.out.println(ex.getMessage());
//                }
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return id;
//    }
//    
//    public Connection getExtraConnection(){
//        Connection c = null;
//        try {
//            Class.forName("org.postgresql.Driver");
//            c = DriverManager.getConnection(url, USER, PASS);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return c;
//    }
}
