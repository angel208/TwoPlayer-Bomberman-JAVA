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
public class MensajeBomba implements Serializable {
    int posicionX;
    int posicionY;
    int Estado;
    int rango;
    
    //**********************Constructores****************************
    public MensajeBomba( int px,int py, int Estado, int rango ){
        
        posicionX = px;
        posicionY = py;
        this.Estado = Estado;
        this.rango = rango;
        
    }
    
    //**********************Seters****************************
    public void SetMensaje( int x, int y, int e, int r){
        Estado = e;
        posicionX = x;
        posicionY = y;
        rango = r;
    }
    
    public void SetMensaje( int x, int y, int r){
        posicionX = x;
        posicionY = y;
        rango = r;
    }
    
    public void SetToZero(){
        Estado = 0;
        posicionX = 0;
        posicionY = 0;
        rango = 0;
    }
    
    //*******************Getters***********************************
    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public int getEstado() {
        return Estado;
    }

    public int getRango() {
        return rango;
    }
    
    
    
    
}
