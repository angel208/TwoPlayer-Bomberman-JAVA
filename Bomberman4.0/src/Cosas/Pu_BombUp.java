/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cosas;

import Bomberman.Tablero;

/**
 *
 * @author angelfabricio
 */
public class Pu_BombUp extends PowerUp{
    
        public Pu_BombUp( int x, int y, Tablero mapa ){
        
        super (x,y,mapa);
        SpriteActual = spriteS.ObtenerSpriteActual(0);
    }
}
