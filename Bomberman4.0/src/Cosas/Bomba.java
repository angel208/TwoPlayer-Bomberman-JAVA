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
 * Clase Bomba: es una clase que hereda de hilo, cuya funcion principal es explotar e invocar Llamas.
 * 
 * Atributos:
 * -Rango:            este atributo es pasado a la clase mediante su constructor y determina cuandas llamas generara la bomba.
 * -MapaActual:       es el tablero principal del juego, en el cual la bomba colocara a las Llamas.
 * -SpriteSExplosion: es la segunda SpriteSheet del objeto. La primera spriteSheet (SpriteS) contiene las imagenes de la animacion de la bomba antes de explotar
 *                    mientras que esta contiene la animacion de la explosion que se genera en el mismo cuadro que la bomba.
 * 
 * Metodos:           
 * -Explotar:         Este metodo tiene dos finalidades:
 *                              *Crear Las Llamas, colocarlas en la matriz del mapa y ejecutarlas. Se crearan tantas llamas como lo permita el rango de la bomba
 *                              *Crear la animacion de la explosion interna en la bomba.
 * -run:              es el metodo principal de la bomba. en ella se genera la animacion que ocurre antes de la explosion de la bomba (esta animacion dura 2.7seg)
 *                    Luego de pasado el tiempo, se llama al metodo explotar, se saca la de la matriz del mapa y se mata la ejecucion de la clase. 
 * 
 * @author angelfabricio
 */
public class Bomba extends Cosa {
     
    public boolean isExplotando = false;
    public int Rango;
    Tablero mapaActual;
    public SpriteSheet spriteSExplosion;
    Bomberman b;
    
    public Bomba(Bomberman b, int x, int y, int rango, Tablero mapa){
        super(x,y); 
               Rango = rango;
               mapaActual = mapa;
               this.b = b;
              
              //el primer spriteSheet es de la animacion antes de explotar, y el segundo es el de la explosion.
              URL url = getClass().getResource("/pic/BombaSS.gif");
              spriteS = new SpriteSheet( url,32,31); 
              url = getClass().getResource("/pic/Explosion.gif");
              spriteSExplosion = new SpriteSheet( url,32,31);
    }
    
     public Bomba( int x, int y, int rango, Tablero mapa){
        super(x,y); 
               Rango = rango;
               mapaActual = mapa;
               b = new Bomberman ( 10, 10, null, null);
              
              //el primer spriteSheet es de la animacion antes de explotar, y el segundo es el de la explosion.
              URL url = getClass().getResource("/pic/BombaSS.gif");
              spriteS = new SpriteSheet( url,32,31); 
              url = getClass().getResource("/pic/Explosion.gif");
              spriteSExplosion = new SpriteSheet( url,32,31);
    }

    public void setX(int x) {
        this.x = x;
    }
    
     
     
    @Override
    public void run(){
        
        //animacion que ocurre antes de explotar (dura 2.7seg)
        for ( int k=0 ; k<9 ; k++ ){
            SpriteActual = spriteS.ObtenerSpriteActual(k%3);            
            try { sleep (330); } catch (InterruptedException ex) {   }
        }
        
        //Se explota la bomba
        Explotar();
        mapaActual.SacarCosa(x, y);
        
        b.BombasActivas -= 1;
        interrupt();
                
    }
    
    
    public void Explotar(){
        
        isExplotando = true;
        
        //CREACION DE LLAMAS: esto lo hara tantas veces el rango lo permita
        //las llamas se colocaran solo y solo si en ese espacio de la matriz no hay nada, hay un bomberman o un powerUp.
        for ( int i=1 ; i<=Rango ; i++){
                  if ( (mapaActual.GetObjeto( x , y-i ) instanceof Pared) || mapaActual.GetObjeto( x , y-i ) instanceof Bomba  ) break;      //evalua si se encuentra con una pared, de ser asi, no dibuja mas llamas en esa direccion
                
                  else if ( mapaActual.GetObjeto( x , y-i ) instanceof Bloque  ){          //aca se toma en cuenta si en la posicion en la que se debe generar la llama existe un bloque
                      Bloque blq =(Bloque) mapaActual.GetObjeto( x , y-i );           //de ser asi, se crea una variable auxiliar bloque que contiene dicho bloque
                      if(!blq.isAlive()) blq.start();                                 //evalua si ese bloque no se esta ejecutando (si no se esta destruyendo), de no estar corriendo                                                       
                      break;                                                          //se ejecuta su hilo lo cual lo destruye, y se sale del ciclo que genera las llamas en esta direccion.
                  }
                  
                  Llama llamaArriba = new LlamaVertical( x , y-i, mapaActual  );      //se crea una nueva llama con coordenadas siguientes a la bomba
                  mapaActual.AgregarCosa(llamaArriba, llamaArriba.x, llamaArriba.y);  //se agrega la llama a la matriz del mapa
                  llamaArriba.start();                                                //se ejecuta el hilo de la llama            
        }
        //lo mismo que lo anterior pero en direccion hacia abajo
        for ( int i=1 ; i<=Rango ; i++){
                  if ( (mapaActual.GetObjeto( x , y+i ) instanceof Pared) ||( mapaActual.GetObjeto( x , y+i ) instanceof Bomba)  ) break;
                  else if ( mapaActual.GetObjeto( x , y+i ) instanceof Bloque  ){
                      Bloque blq =(Bloque) mapaActual.GetObjeto( x , y+i ); 
                      if(!blq.isAlive()) blq.start();
                      break;
                  }
                 
                  Llama llamaAbajo = new LlamaVertical( x , y+i, mapaActual  );
                  mapaActual.AgregarCosa(llamaAbajo, llamaAbajo.x, llamaAbajo.y);
                  llamaAbajo.start();
        }
        for ( int i=1 ; i<=Rango ; i++){
                  if ( (mapaActual.GetObjeto( x+i , y ) instanceof Pared) || (mapaActual.GetObjeto( x+i , y ) instanceof Bomba)  ) break;
                  else if ( mapaActual.GetObjeto( x+i , y ) instanceof Bloque  ){
                      Bloque blq =(Bloque) mapaActual.GetObjeto( x+i , y ); 
                      if(!blq.isAlive()) blq.start();
                      break;
                  }
                 
                  Llama llamaDer = new LlamaHorizontal( x+i , y, mapaActual  );
                  mapaActual.AgregarCosa(llamaDer, llamaDer.x, llamaDer.y);
                  llamaDer.start();
        }
        for ( int i=1 ; i<=Rango ; i++){
                  if ( (mapaActual.GetObjeto( x-i , y ) instanceof Pared) || (mapaActual.GetObjeto( x-i , y ) instanceof Bomba)  ) break;
                  else if ( mapaActual.GetObjeto( x-i , y ) instanceof Bloque  ){
                      Bloque blq =(Bloque) mapaActual.GetObjeto( x-i , y ); 
                      if(!blq.isAlive()) blq.start();
                      break;
                  }
                  
                  Llama llamaIzq = new LlamaHorizontal( x-i , y, mapaActual  );
                  mapaActual.AgregarCosa(llamaIzq, llamaIzq.x, llamaIzq.y);
                  llamaIzq.start(); 
        }
        
  
        
        //ANIMACION INTERNA DE EXPLOSION.
         for ( int i=7 ; i>=4 ; i--){
             
            SpriteActual = spriteSExplosion.ObtenerSpriteActual(i);            
             try { sleep (40);} catch (InterruptedException ex) {}
             
        }
        for ( int i=4 ; i<8 ; i++){
            
            SpriteActual = spriteSExplosion.ObtenerSpriteActual(i);            
            try { sleep (40);} catch (InterruptedException ex) {}
        }    
        

    }
    
 

}
