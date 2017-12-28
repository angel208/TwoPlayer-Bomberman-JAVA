package Cosas;

import Bomberman.SpriteSheet;
import Bomberman.Tablero;


  /**  Esta clase es el objeto llama, cuyo unico metodo es aparecer y desaparecer. esta es una clase abstracta
    *  la cual sera padre de LlamaHorizontal y LlamaVertical. estas dos clases estan separadas, por que el tratamiento
     * de la SpriteSheet de cada una es diferente, debido a q las imagenes son distintas.
  */
public abstract class Llama extends Cosa {

    Tablero mapaActual; //el mapa en el cual estara contenida la llama
    
    public Llama(int x, int y, Tablero mapa){
        super(x,y); 
              mapaActual = mapa;
              //URL url = getClass().getResource("/pic/llama.gif");
              spriteS = new SpriteSheet(getClass().getClassLoader().getResource("pic/llama.gif"),32,31);
                /* todas las imagenes tanto de las llamas horizontales como de las verticales estan contenidas en la misma SpriteSheet */
    }
    
}
