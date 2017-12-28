/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import java.io.*;
import java.net.URL;
/**
 *
 * @author Xavier Beckles
 */
public class InputS{
    String texto = "";
        
    public String leerTxt(InputStream is){
        try{
              
            InputStreamReader ISR=new InputStreamReader (is);
            BufferedReader BffR = new BufferedReader(ISR);
            String cadena;
            String temp="";
            while((cadena = BffR.readLine())!=null) {
                //lee mientras halla texto
                temp = temp + "\n" + cadena;//guarda texto       
                
            }
            texto=temp;
        }
        catch(Exception e){
            System.err.println("No se encontro Archivo");
        }
        return texto;
    }
    
    public void escribirTxt (String direccion, String datos){
        try{
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(direccion));
            bw.write(datos+"\n");
            bw.close();
        }
        catch(Exception e){
            
        }       
        
    }
    public void sobrescribirTxt (String direccion, String datos){
        try{
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(direccion));
            bw.write(datos+"\n");
            bw.close();
        }
        catch(Exception e){
            
        }       
        
    }
    
}
