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
    
    private String nom;

    private String telefon;

    private String email;

    private String clau;

    private String genere;
    
    private boolean administrador;
    
    private Timestamp ultimLogin;

    public Usuari(String email, String nom, String genere, String telefon, String clau){
        this.nom = nom;
        this.telefon= telefon;
        this.email = email;
        this.clau = clau;
        this.genere = genere;
        this.administrador = false;
        this.ultimLogin = null;
    }
    
    public Usuari(String email, String nom, String genere, String telefon, String clau, boolean administrador, Timestamp ultimLogin){
        this.nom = nom;
        this.telefon= telefon;
        this.email = email;
        this.clau = clau;
        this.genere = genere;
        this.administrador = administrador;
        this.ultimLogin = ultimLogin;
    }
    
    public String getNom() {
        return this.nom;
    }

    public void setNom(String value) {
        this.nom = value;
    }
    
    public boolean getAdministrador() {
        return this.administrador;
    }

    public void setAdministrador(boolean value) {
        this.administrador = value;
    }
    public String getTelefon() {
        return this.telefon;
    }

    public void setTelefon(String value) {
        this.telefon = value;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public String getClau() {
        return this.clau;
    }

    public void setClau(String value) {
        this.clau = value;
    }

    public String getGenere() {
        return this.genere;
    }

    public void setGenere(String value) {
        this.genere = value;
    }

    public Timestamp getUltimLogin() {
        return this.ultimLogin;
    }

    public void setUltimLogin(Timestamp value) {
        this.ultimLogin = value;
    }
}