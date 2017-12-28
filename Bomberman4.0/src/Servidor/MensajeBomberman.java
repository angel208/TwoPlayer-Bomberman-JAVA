/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.Serializable;

/**
 *
 * @author angelfabricio
 */
public class MensajeBomberman implements Serializable {
    int coordenadaX;
    int coordenadaY;    
    int Columna, Fila;
    boolean vivo;
    
    public MensajeBomberman ( int x, int y, int Columna, int Fila, boolean vivo){
        coordenadaX = x;
        coordenadaY = y;
        this.Columna = Columna;
        this.Fila = Fila;
        this.vivo = vivo;
        
    }
    
    public void SetPacket( int x, int y, int Columna, int Fila,  boolean vivo){
        coordenadaX = x;
        coordenadaY = y;
        this.Columna = Columna;
        this.Fila = Fila;
        this.vivo = vivo;
    }

   //------------------Geters--------------------------

    public int getCoordenadaX() {
        return coordenadaX;
    }

    public int getCoordenadaY() {
        return coordenadaY;
    }

    public int getColumna() {
        return Columna;
    }

    public int getFila() {
        return Fila;
    }
    
    public boolean getVivo() {
        return vivo;
    }
    
    
}
