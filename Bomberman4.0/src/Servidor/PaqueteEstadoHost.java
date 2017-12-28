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
public class PaqueteEstadoHost implements Serializable {
    
    MensajeBomberman estadoBomberman;
    MensajeBomba estadoBomba;
    char matrizPowerUp[][];
    boolean partida;

    //*****************Constructor******************************
    public PaqueteEstadoHost( MensajeBomberman bomberman, MensajeBomba bomba, char matriz[][], boolean partida ){
        estadoBomberman = bomberman;
        estadoBomba = bomba;
        matrizPowerUp = matriz;
        this.partida = partida;
    }
    
    //*****************Setter******************************
    public void SetEstadoHost( MensajeBomberman bomberman, MensajeBomba bomba, char matriz[][], boolean partida ){
        estadoBomberman = bomberman;
        estadoBomba = bomba;
        matrizPowerUp = matriz;
        this.partida = partida;        
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

    public boolean isPartidaActive() {
        return partida;
    }
    
}
