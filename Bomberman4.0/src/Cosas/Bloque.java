/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cosas;

import Bomberman.SpriteSheet;
import Bomberman.Tablero;
import java.io.File;
import java.net.URL;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 * clase bloque: contiene su constructor y su metodo "run" el cual hereda de los hilos;
 * su imagen actual sera la del bloque normal, hasta que sea destruido.
 * al ser destruido, se llama a su metodo Destruir(), el cual hace la animacion de destruccion, lo saca de la matriz del mapa e interrumpe su propa ejecucion
 * el metodo Destruir se llama en la clase Bomba. se valida si la proxima llama va a tocar un bloque, de ser asi, se drestruye dicho bloque y se paran las llamas.
 * @author angelfabricio
 */
public class Bloque extends Cosa {
    Tablero mapa;
    char matriz[][];
    Random random = new Random();
    
   public Bloque(int x, int y, Tablero mapa, char matriz[][]){
        
        super(x,y);  
               this.mapa = mapa;
               this.matriz=matriz;
               
               //imagen antes de ser destruido
               URL url = getClass().getResource("/pic/BloqueDestruccion.gif");
               spriteS = new SpriteSheet (url, 31, 31 );
               //spriteSheet que contiene la animacion de la destruccion
               url = getClass().getResource("/pic/bloque.gif");
               try {SpriteActual= ImageIO.read(url);}
               catch ( Exception e ){}
               
    }
    
   
    @Override
    public void run(){
             for ( int i=0 ; i<8 ; i++){
            
              SpriteActual = spriteS.ObtenerSpriteActual(i);            
              try { sleep (40);} catch (InterruptedException ex) {} //se varia entre las imagenes de la spriteSheet
              
             }  
             mapa.SacarCosa(x, y); //se elimina del mapa.
             
             //=======GENERAR POWERUP==============
             int r = random.nextInt(3); //si este numero es 0, se genera un PowerUp, sino no.
             
             if ( r == 0 ){ //de ser 0 el random, se genera un powerUp aleatorio
                 
                 int CualPowerUp = random.nextInt(7); // este numero aleatorio indica cual powerUp se creara.
                 
                 switch(CualPowerUp){
                     case 0: case 1: case 5: { matriz[x][y]='3'; break; }
                     case 2: case 3: case 6: { matriz[x][y]='1';  break; }
                     case 4:                 { matriz[x][y]='2'; break; }
                 }
                 
             }     
             //======================================
             
             
             interrupt(); //se interrumpe la ejecucion.
             
             /*================POR ACA SE DEBERIAN GENERAR LOS POWERUps=============*/
    }
    
    
}
