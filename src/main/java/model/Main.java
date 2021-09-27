/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * 
 * @author Jordi Gómez Lozano
 */
public class Main {


    public static void main(String[] args) {
        //Variables generals
        String ubicacio = "westeurope";
        String aTraduir = "Estic traduint aquesta frase";  
        String idiomaFinal = "en"; 
        //Variables de configuració del reproductor.
        String reproductorKey = "2a6863e2e9ec43a6b8a3018e6d8f3bb9";
        String toDeVeu = "femeni";
        //Variables de configuració del traductor.
        String traductorKey = "c1cb65b7f7f94f9eb9f12a983997cb65";
        String idiomaOrigen = "ca"; 

        
        try { 
            //Configuració del Text to Speech.
            Reproductor textToSpeech = new Reproductor(
                    reproductorKey, 
                    ubicacio, 
                    idiomaFinal,
                    toDeVeu
            );
            
            //Configuració del traductor.
            Traductor translateRequest = new Traductor(
                    traductorKey,
                    ubicacio,
                    idiomaOrigen,
                    idiomaFinal
            );
            
            //Traducció de l' String passat per paràmetre.
            String traduit = translateRequest.Traduir(aTraduir);
            System.out.println("String a traduir: "+ aTraduir);
            System.out.println("String traduit: "+ traduit);
            
            //Reproductor de l' String traduit.
            textToSpeech.reproduir(traduit);
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
