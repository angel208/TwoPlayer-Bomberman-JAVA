/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cosas;


import Bomberman.Tablero;
import static java.lang.Thread.sleep;

/**
 * Esta es la llama horizontal, la unica diferencia entre esta y la vertical es la columna de la SpriteSheet que se usara. en este caso la primera
 * @author angelfabricio
 */


public class LlamaHorizontal extends Llama {
    
    public LlamaHorizontal(int x, int y, Tablero mapa){
        super(x,y, mapa);        
    }
    
    @Override
    /*Este hilo dibujara su animacion. Luego se sacara del mapa y morira.*/
    public void run(){
          
    
           /*  ANIMACION   */
          for ( int i=3 ; i>=0 ; i--){
            SpriteActual = spriteS.ObtenerSpriteActual(i, 1 ); //recorrera la SpriteSheet horizontalmente (der->izq) en la columa 1 (esta es la diferencia entre esta y la otra clase de llama)
            
             try { sleep (40);} 
             catch (InterruptedException ex) {}
          }
        
          for ( int i=0 ; i<4 ; i++){
            SpriteActual = spriteS.ObtenerSpriteActual(i, 1 );   //recorrera la SpriteSheet, ahora en direccion contraria (de izq a der)
            
             try { sleep (40);} 
             catch (InterruptedException ex) {}
          }
          /*   FIN DE ANIMACION  */
          
          
        mapaActual.SacarCosa(x, y); //Se elimina la llama de la matriz del mapa
        interrupt(); //se interrume el hilo.    
    }
        
}
