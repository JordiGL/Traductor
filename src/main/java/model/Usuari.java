/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.Timestamp;

/**
 * 
 * @author Jordi Gómez Lozano
 */
public class Usuari {
    //private final String IDIOMA = "catala";
    //private String token;
    
    private int id;
    
    private String cognoms;
    
    private String nom;
    
    private String username;

    private int phone;

    private String email;

    private String password;

    private String gender;
    
    private boolean enabled;
    
    //private Timestamp ultimLogin;

    public Usuari(int id, String cognoms, String email, String genere, String nom, String clau, int telefon, String sobrenom){
        this.id = id;
        this.cognoms = cognoms;        
        this.email = email;
        this.enabled = false;
        this.gender = genere;
        this.nom = nom;
        this.password = clau;
        this.phone= telefon;
        this.username = sobrenom;
        
        
       
//        this.ultimLogin = null;
    }
    
    public Usuari(int id, String cognoms, String email, boolean administrador, String genere, String nom, String clau, int telefon, String sobrenom){
        this.id = id;
        this.cognoms = cognoms;        
        this.email = email;
        this.enabled = administrador;
        this.gender = genere;
        this.nom = nom;
        this.password = clau;
        this.phone= telefon;
        this.username = sobrenom;
//        this.ultimLogin = ultimLogin;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
//    public Timestamp getUltimLogin() {
//        return this.ultimLogin;
//    }
//
//    public void setUltimLogin(Timestamp value) {
//        this.ultimLogin = value;
//    }
}