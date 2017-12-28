/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cosas;


import Bomberman.SpriteSheet;
import Bomberman.Tablero;
import Servidor.MensajeBomba;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 * Esta es la clase que se encarga del manejo del bomberman.
 * 
 * * sus tres metodos principales son: obtener sprite, run y colocar bomba.
 *    - obtener sprite calcula cual de las posiciones en la spriteSheet sera utilizada y la extrae.
 *    - run es el metodo principal del bomberma. esta clase hereda de hilos, por lo que corre casi independientemente del bucle del juego y del dibujo de la matriz del mapa
 *      no es completamente independiente, pues aun depende del bucle para leer la tecla que se usara para mover al bomberman.
 *    - colocarBomba crea un nuevo objeto Bomba, la coloca en la matriz del mapa y la activa ejecutando su metodo run.
 * 
 * variables:
 * -contadorMovimiento: esta variable aumenta cada vez que el bomberman se mueve y es usada al momento de calcular la fila de la SpriteSheet
 * -LimiteBombas:       limita la cantidad de bombas que un bomberman puede colocar al mismo tiempo
 * -bombasActivas:      Cada vez que se pone una bomba, este contador aumenta, y se usa para validar la posibilidad del bomberman para poner una bomba
 * -rangoDeBombas:      determina la cantidad de llamas que invocara una bomba al momento de explotar.
 *                      este valor se pasa como parametro a la bomba, lo que le dice cuantas llamas sucesivas crear.
 * -vivo:               este booleano le dice al metodo run, si seguir corriendo o no. Mientras el bomberman este vivo, esta clase seguira corriendo.
 * -vidas:              determina la cantidad actual de vidas que posee el bomberman
 * 
 * -ventana:            es una variable del tipo ImageObserver y se usa al momento de dibujar al bomberman para referirse a la ventana "Partida"
 * -Imagen:             este es el BackBuffer que se le pasa a la clase, para que dibuje al bomberman, y luego sea retornada de nuevo a la clase partida, donde se terminara de dibujar en la pantalla.
 * -mapaActual:         es el tablero de la partida, este tablero sera pasado a las demas clases para que ellas mismas se posicionen dentro de la matriz.
 *
 * @author angelfabricio 
 */
public class Bomberman extends Cosa  {
    
    public int contadorMovimiento=0;
    public int LimiteBombas=1;
    public int BombasActivas=0;
    public int vidas = 7;
    public int RangoDeBombas = 1;
    public boolean vivo = true;
    
        
    ImageObserver ventana;
    Graphics Imagen;  
    Tablero mapaActual;
    char matrizPowerUp[][];

    public Bomberman(int x, int y, SpriteSheet SS, ImageObserver ventana){
        super(x,y);
        spriteS = SS;
        this.ventana=ventana;
        BufferedImage backBuffer = new BufferedImage(30, 30, BufferedImage.TYPE_INT_RGB); //se inicializa el buffer con una imagen cualquiera
        Imagen = backBuffer.getGraphics();
        
    }
       
    public void SetMapaActual ( Tablero mapa, char matriz[][]){
        mapaActual = mapa;
        matrizPowerUp = matriz;
        
    }

    public void setSpriteS(SpriteSheet spriteS) {
        this.spriteS = spriteS;
    }
    
    
    
    @Override
        public BufferedImage ObtenerSprite(){
      
                int aux;
                 
                /* se detemina si la fila siguiente sera diferente a la actual, de ser asi, se aumenta el contador de movimiento */
                if ( (aux = filaSS) != ( filaSS= spriteS.calcularFila(5, contadorMovimiento, filaSS )) ) contadorMovimiento++;
               
               /* se obtiene la imagen actual desde la spriteSheet, para mas informacion visitar la clase SpriteSheet */
               SpriteActual = spriteS.ObtenerSpriteActual(filaSS, columnaSS );

                return SpriteActual; //retorna la imagen actual. 
        } 
        
        /* este es uno de los metodos que se llamara constantemente en el bucle principal de la partida. 
            este actualiza el BackBuffer que se esta usando actualmente.*/
        public void SetGraficos( Graphics I ){
           Imagen = I;
        }
        
    @Override
        public void run(){
        
            while(vivo){
                            SpriteActual = ObtenerSprite();
                            Imagen.drawImage(SpriteActual, x , y-10 , 32 , 62 , ventana);//dibuja el bomberman en el backbuffer
                            
                            //==========================================MUERTE=================================================
                            if (( mapaActual.GetObjeto( x/30 , (y+30)/30  ) instanceof Llama ) || (( mapaActual.GetObjeto( x/30 , (y+30)/30  ) instanceof Bomba )  && (((Bomba)mapaActual.GetObjeto( x/30 , (y+30)/30)).isExplotando)    ) ){
                            //si se encuentra al bomberman en la misma casilla de una llama, este muere, saliendose del bucle que lo dibuja
                            //si se encuentra al bomberman en la misma casilla de na bomba explotando ( la llama de la bomba ) muere.   
                                   
                                    Bomba bomba;
                                    if ( mapaActual.GetObjeto( x/30 , (y+30)/30  ) instanceof Bomba ) { //si de hecho esta encima de una bomba..
                                           bomba = (Bomba)mapaActual.GetObjeto( x/30 , (y+30)/30 );  
                                           bomba.isExplotando =false; //evita que la bomba le vuelva a hacer efecto.
                                    }
                                
                                vidas--;     //resta una vida al bomberman
                                System.out.println("vidas actuales: " + vidas);
                                
                                    if ( vidas>0 ){ //si el bomberman aun tiene vidas, se mueve hasta su posicion inicial.
                                         x=30;
                                         y=10;
                                         RangoDeBombas = 1;
                                         LimiteBombas = 1;
                                    }
                                    else{ //si no le quedan vidas, el bomberman muere.
                                        System.out.println("muerto!");
                                        Morir();

                                    }
                            }
                            //==========================================MUERTE=================================================
                            
                            validarPowerUp();    
            }
 
        }
        public void Morir(){
        
           vivo = false;
           interrupt();
           
        }
        public void validarPowerUp(){
                           if ( mapaActual.GetObjeto( x/30 , (y+30)/30  ) instanceof Pu_FireUp ){                    
                             if (RangoDeBombas < 6 ) RangoDeBombas++;
                               matrizPowerUp[x/30][(y+30)/30]='x';
                               mapaActual.SacarCosa(x/30, (y+30)/30);
                           }
                             if ( mapaActual.GetObjeto( x/30 , (y+30)/30  ) instanceof Pu_1Up ){                    
                                if (vidas < 10 ) vidas++;
                                matrizPowerUp[x/30][(y+30)/30]='x';
                                mapaActual.SacarCosa(x/30, (y+30)/30);
                            }
                              if ( mapaActual.GetObjeto( x/30 , (y+30)/30  ) instanceof Pu_BombUp ){                    
                                if (LimiteBombas < 6 ) LimiteBombas++;
                                matrizPowerUp[x/30][(y+30)/30]='x';
                                mapaActual.SacarCosa(x/30, (y+30)/30);
                            }
        }
        
        
        public void ColocarBomba( MensajeBomba m ){
                        
            //Coloca la bomba en la matriz
            //se activa esa bomba
            
            BombasActivas++;
            Bomba bomba;
             
            bomba = new Bomba( this, (x)/30, (y+30)/30, RangoDeBombas, mapaActual);
            
            m.SetMensaje( bomba.x, bomba.y , 1, RangoDeBombas);
            mapaActual.AgregarCosa(bomba, bomba.x, bomba.y);                            //agrega la bomba creada a la matriz del mapa
            bomba.start();                                                             //ejecuta el hilo de la bomba.

            
        }
        
        //===========================

  
}
