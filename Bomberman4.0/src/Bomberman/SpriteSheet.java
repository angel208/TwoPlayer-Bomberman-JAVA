/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bomberman;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;


/**
 * Esta clase permite el manejo de las SpriteSheets.
 * 
 * toda SpriteSheet en el juego no se maneja como una simple imagen, sino como una clase completa. es decir, cada vez que se va a usar una spriteSheet
 * se agarra la imagen .GIF y se crea un nuevo objeto de tipo SpriteSheet en base a esta imagen. 
 * 
 * src es la direccion de la imagen original
 * framesPerRow contiene la cantidad de sprites en una fila de la SpriteSheet
 * fHeight y fWidth contienen el tama;o de cada una de las framas (sprites), este tama;o es constante en toda la spriteSheet.
 * @author angelfabricio
 */
public class SpriteSheet {
    
    BufferedImage imagen = null;
    URL src;
    int framesPerRow;
    int fHeight, fWidth;
    

    /* Constructor de la clase  */
    public SpriteSheet( URL url, int frameWidth, int frameHeight ){
         
        fHeight = frameHeight;
        fWidth = frameWidth;
        
         
               try {imagen = ImageIO.read( url );} //Crea una nueva BufferedImage con la imagen  contenida en la ruta Path
               catch ( Exception e){};
                       
           src = url ;
           framesPerRow = ((int) Math.floor ( imagen.getWidth() / frameWidth )); //se calcula la cantidad de framas por cada fila de la hoja
    }
   
    
    /*  Este metodo calcula la fila actual dependiendo del movimiento del bomberma.
    es solo usada con este fin. dependiendo si se toca la tecla (w,a,s,d) la fila de la SpriteSheet cambia
    cambiando la imagen, creando la ainmacion de movimiento.*/
    public int calcularFila( int velocidad,  int contador, int filaActual ){ //solo para personajes

           if (contador%velocidad == 0){ //evita que exista un cambio en cada bucle del juego.
             filaActual = ((filaActual+1) % (framesPerRow-1) ) ;
           }
         
           return filaActual;
    }
    
    
    /*  Metodo simple que dada la columna y la fila indicada, extrae la imagen de la spriteSheet en esa posicion
       y la retorna*/
    public BufferedImage ObtenerSpriteActual( int Fila, int Columna ){ //cuando la columna es dinamica(fuego, bomberman)
        
        BufferedImage img = null;
        
        img = imagen.getSubimage( Fila*fWidth , (Columna*fHeight)-fHeight , fWidth, fHeight );
        
        return img;
    }
    
    
    /*  Metodo simple que dada la fila indicada, extrae la imagen de la spriteSheet en esa posicion y la retorna*/
    public BufferedImage ObtenerSpriteActual( int Fila ){ //cuando la spritesheet tiene una sola columna(bomba,bloque)
        
        BufferedImage img = null;
        
        img = imagen.getSubimage( Fila*fWidth , 0 , fWidth,   fHeight );
        
        return img;
    }
    
    
    
    
    
}
