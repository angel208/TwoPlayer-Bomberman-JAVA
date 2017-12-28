
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cosas;

import Bomberman.SpriteSheet;
import Bomberman.Tablero;
import java.net.URL;

/**
 *
 * @author angelfabricio
 */
public class PowerUp extends Cosa{
    
    Tablero mapaActual; //el mapa en el cual estara contenida la llama
    
    public PowerUp(int x, int y, Tablero mapa){
        super(x,y); 
              mapaActual = mapa;
              URL url = getClass().getResource("/pic/PowerUps.gif");
              spriteS = new SpriteSheet(url,32,31);
                /* todas las imagenes tanto de las llamas horizontales como de las verticales estan contenidas en la misma SpriteSheet */
    }
    
}
