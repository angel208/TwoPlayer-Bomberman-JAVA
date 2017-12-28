/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cosas;

import Bomberman.SpriteSheet;
import java.awt.image.BufferedImage;

/**
 *  esta es la Clase padre de todos los objetos del juego. Hereda de la clase Thread.
 *  (x,y) son las coordenadas del objeto dentro de la matriz del mapa
 *  (xPantalla, Ypantalla) son las coordenadas del objeto en la pantalla, estan dadas por los pixeles de la pantalla
 *  columnaSS y filaSS son el numero e la columna y fila a usar en la spriteSheet
 *  SpriteActual es la imagen en el momento del objeto, generalmente es una parte de su spriteSheet
 *  SpriteS es la spritSheet del objeto.
 * @author angelfabricio
 */
public class Cosa extends Thread{
    
    public int x,y;
    public int xPantalla;
    public int yPantalla;
    public int columnaSS=1, filaSS=0; //posicion de fila y columna en la SpriteSheet
    
    public BufferedImage SpriteActual;
    public SpriteSheet spriteS;
    
    public Cosa(int x, int y){
        this.x = x;
        this.y = y;
        xPantalla = (x * 30); //pasa (x,y) de la matriz al (x,y) de la pantalla
        yPantalla = (y * 30)+5;
    }
    
   /* Retorna la imagen Actual del objeto  */
   public BufferedImage ObtenerSprite(){
         return SpriteActual; 
    }

    
    
   

}
