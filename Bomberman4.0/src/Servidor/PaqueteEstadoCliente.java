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
public class PaqueteEstadoCliente implements Serializable {
   
    MensajeBomberman estadoBomberman;
    MensajeBomba estadoBomba;
    char matrizPowerUp[][];
    
     //*****************Constructor******************************
    public PaqueteEstadoCliente( MensajeBomberman bomberman, MensajeBomba bomba, char matriz[][] ){
        estadoBomberman = bomberman;
        estadoBomba = bomba;
        matrizPowerUp = matriz;
    }
    
    //*****************Setter******************************
    public void SetEstadoCliente( MensajeBomberman bomberman, MensajeBomba bomba, char matriz[][] ){
        estadoBomberman = bomberman;
        estadoBomba = bomba;
        matrizPowerUp = matriz;      
    }
    
    //*****************Getters******************************

    public MensajeBomberman getEstadoBomberman() {
        return estadoBomberman;
    }

    public MensajeBomba getEstadoBomba() {
        return estadoBomba;
    }

    public char[][] getMatrizPowerUp() {
        return matrizPowerUp;
    }

    
}
