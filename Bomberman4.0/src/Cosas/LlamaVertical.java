/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cosas;

import Bomberman.Tablero;
import java.awt.image.BufferedImage;
import static java.lang.Thread.sleep;

/**
 *Esta es la llama vertical, la unica diferencia entre esta y la horizontal es la columna de la SpriteSheet que se usara, en este caso la 2da. 
 *Para mas informacion revisar los comentarios contenidos en la clase LlamaHorizontal
 * 
 * @author angelfabricio
 */


public class LlamaVertical extends Llama{
    

    
    public LlamaVertical(int x, int y, Tablero mapa){
        super(x,y, mapa);        
    }
    
    @Override
    public void run(){

          
         for ( int i=3 ; i>=0 ; i--){
            SpriteActual = spriteS.ObtenerSpriteActual(i, 2 );
            
             try { sleep (40);} 
             catch (InterruptedException ex) {}
          }
        
          for ( int i=0 ; i<4 ; i++){
            SpriteActual = spriteS.ObtenerSpriteActual(i, 2 );
            
             try { sleep (40);} 
             catch (InterruptedException ex) {}
          }
          
        mapaActual.SacarCosa(x, y);
        interrupt();       
    }
    
}
