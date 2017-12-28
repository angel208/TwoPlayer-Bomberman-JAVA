
package Bomberman;

import Cosas.Bloque;
import Cosas.Pared;
import Cosas.Cosa;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.io.InputStream;
import java.net.URL;


/**
 *Clase que contiene el tablero.
 * el tablero es la matriz que contiene  cada uno de los objetos presentes actualmente en el juego 
 * (Excepto a los bomberman, estos esta fuera de la matriz, y solo la usan para verificar si estan parados sobre una llama o una pared).
 * 
 * 
 * @author angelfabricio
 */
public final class Tablero extends Thread{
    public Cosa[][] tablero= new Cosa[30][15];
    private final ManejoDeArchivos FileManager = new ManejoDeArchivos();
    char matrizPowerUp[][];
    
    ImageObserver ventana; //este es el observador de la ventana de partida.
    
    public Tablero( ImageObserver o , char matrizPU[][]){
        
        for ( int j = 0 ; j<15 ; j++ ) 
            for ( int i=0 ; i<30 ; i++){
                tablero[i][j]= null; // Inicializa todas las posiciones en null
            }
          
          matrizPowerUp = matrizPU;
          System.out.println("seteada matriz");
          ventana = o;
          CargarMatriz();
            
    }
    
    //agrega una cosa en la posicion (x,y)
    public void AgregarCosa ( Cosa cosa, int x, int y ){ 
        tablero[x][y] = cosa;
    }
    
    //elimina la cosa en la posicion (x,y)
    public void SacarCosa ( int x, int y ){
        tablero[x][y] = null;
    }
    
    //obtiene la cosa en la posicion (x,y)
    public Cosa GetObjeto ( int x, int y){
        return tablero[x][y];
    }

    public void SetMatrizPowerUp( char m[][]){
        matrizPowerUp = m;
    }
    public void CargarMatriz(){
         char [][] matriz=new char[20][15];
         System.out.println("cargando objetos");
         
         InputStream stream = getClass().getResourceAsStream("/mapas/Mapa2.map");
         matriz = FileManager.GenerarMatriz( stream );
         
         for (int j=0 ; j<15 ; j++ )
           for ( int i=0; i<20 ; i++ ){

                if( matriz[i][j] == 'x' )
                     tablero[i][j] = new Pared(i , j); 
                else if( matriz[i][j] == 'b' )
                     tablero[i][j] = new Bloque(i , j, this, matrizPowerUp);
 
           }    
    }
    
    
    /* Este proceso se llama en el bucle principal del juego.
      en cada iteracion del juego, se imprimen todas las imagenes de todos los objetos en el tablero
      en la posicion de esa casilla en la matriz.*/
    public void ImprimirCampo( Graphics Imagen ){

        for ( int j = 0 ; j<15 ; j++ )
            for ( int i= 0 ; i<30 ; i++){
                
                if ( tablero[i][j] != null ) //para evitar las violaciones de segmento                  
                     
                 Imagen.drawImage(tablero[i][j].ObtenerSprite(), tablero[i][j].xPantalla, tablero[i][j].yPantalla ,30, 30, ventana);

            }
    }
    
}
