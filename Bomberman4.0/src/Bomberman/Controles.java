/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bomberman;

import Cosas.Bomba;
import Cosas.Pared;
import Cosas.Cosa;
import Cosas.Bloque;
import Cosas.Bomberman;
import Servidor.MensajeBomba;
import Ventanas.Pausa;
import java.awt.event.KeyEvent;

/**
 * Esta clase controles permite el movimiento del personaje.
 * esta destinada a cualquier movimiento en el juego, pero hasta ahora ha sido util solo para el movimineto de los bomberman,
 * es mas, muchos de sus metodos estan hechos exclusivamente para este fin.
 * 
 * todos sus parametros enteros contienen informacion acerca de los limites de la ventana de la partida los cuales se usaran para validar el movimiento
 * @author angelfabricio
 */
public class Controles {
    ManejoDeEntradas input;
    GUI partida;
    Tablero mapa;
    
    
    /*Constructor para controles del Pj*/
    public Controles (  GUI partida, ManejoDeEntradas input, Tablero m){
         this.partida = partida;
         this.input = input;     
         mapa = m;
    }
    
    /*controles para los menus*/
    public Controles ( ManejoDeEntradas input){
         this.input = input;     
    }
    
    //===================VALIDACIONES HORIZONTALES=========================================
    private boolean validarMovimientoDerecha(  Bomberman bomberman ){
        
        int x =(bomberman.x+32)/30; //x del torzo del bomberman en la matriz
        int y= (bomberman.y+32)/30; //y del torzo del bomberman en la matriz
        
        if (  mapa.GetObjeto( x , y )instanceof Bloque || mapa.GetObjeto( x , y )instanceof Pared ){ //verifica si en la posicion del bomberman en la matriz existe un bloque o una pared
               Cosa Blq =  mapa.GetObjeto(x, y); //de ser asi, obtiene ese objeto para poder usar sus coordenadas en la pantalla.

               
             if (  (( bomberman.y+64 > Blq.yPantalla ) || ( bomberman.y < Blq.yPantalla )) ){ //evalua si las coordenadas en la pantalla del bomberman y el objeto son igales
                 return false;} //de serlo no deja q se mueva
             
             return true;
        }
        if (  mapa.GetObjeto( x , y )instanceof Bomba ){ //verifica si en la posicion del bomberman en la matriz existe una bomba
               Cosa Blq =  mapa.GetObjeto(x, y); //de ser asi, obtiene ese objeto para poder usar sus coordenadas en la pantalla.

               
             if (  ((( bomberman.y+64 > Blq.yPantalla ) || ( bomberman.y < Blq.yPantalla )) && !(mapa.GetObjeto(bomberman.x/30, bomberman.y/30) instanceof Bomba)) ){ //evalua si estamos parados en una bomba
                 return false;} //de serlo no deja q se mueva
             
             return true;
        }
        return true;
    }
    private boolean validarMovimientoIzquierda(  Bomberman bomberman ){
        
        int x =(bomberman.x-5)/30; //x del torzo del bomberman en la matriz
        int y= (bomberman.y+32)/30; //y del torzo del bomberman en la matriz
        
        if (  mapa.GetObjeto( x , y )instanceof Bloque || mapa.GetObjeto( x , y )instanceof Pared ){
               Cosa Blq = mapa.GetObjeto(x, y);
             
             if (  (( bomberman.y+64 > Blq.yPantalla ) || ( bomberman.y < Blq.yPantalla )) ){
                 return false;}
             
             return true;
        }
        if (  mapa.GetObjeto( x , y )instanceof Bomba ){ //verifica si en la posicion del bomberman en la matriz existe una bomba
               Cosa Blq =  mapa.GetObjeto(x, y); //de ser asi, obtiene ese objeto para poder usar sus coordenadas en la pantalla.
   
             if (  (  (( bomberman.y+64 > Blq.yPantalla ) || ( bomberman.y < Blq.yPantalla )) && !(mapa.GetObjeto(bomberman.x/30, bomberman.y/30) instanceof Bomba)) ){ //evalua si estamos parados en una bomba
                 return false;} //de serlo no deja q se mueva
             
             return true;
        }
        
        return true;
    }
    
    //===================VALIDACIONES VERTICALES=========================================
    private boolean validarMovimientoArriba(  Bomberman bomberman ){
               
        int xIzq =(bomberman.x+15)/30; //x del extremo izq del torzo del bomberman en la matriz       
        int y= (bomberman.y+15)/30; //y del torzo del bomberman en la matriz
        int xDer =(bomberman.x+4)/30; //x del extremo derecjo del torzo del bomberman en la matriz
        
        
        //las validaciones del movimiento horizontal son un poco diferentes. se tienen dos coordenadas el (x,y) del exremo derecho del bomberman y el (x,y) de su extremo izq.
        //calcula si en la posicion de la matriz en la q esta uno de esos extremos hay una pared o bloque. de ser asi, carga en la variable blq, ese objeto para poder usar sus coordenadas en la pantalla.
        if (  mapa.GetObjeto( xIzq , y )instanceof Bloque || mapa.GetObjeto( xIzq , y )instanceof Pared ||  mapa.GetObjeto( xDer , y )instanceof Bloque || mapa.GetObjeto( xDer , y )instanceof Pared ){
               Cosa Blq = (mapa.GetObjeto(xIzq, y)) == null ? (mapa.GetObjeto(xDer, y))  : (mapa.GetObjeto(xIzq, y)) ;
             
               
             if (  (( bomberman.x+32 > Blq.xPantalla ) || ( bomberman.x+10 < Blq.xPantalla )) ){ //evalua las coordenadas en pantalla del bomberman y el objeto. de ser iguales no permite el movimiento.
                 return false;}
             
             return true;
        }
         if (  mapa.GetObjeto( xIzq , y )instanceof  Bomba || mapa.GetObjeto( xDer , y )instanceof Bomba  ){
               Cosa Blq = (mapa.GetObjeto(xIzq, y)) == null ? (mapa.GetObjeto(xDer, y))  : (mapa.GetObjeto(xIzq, y)) ;
             
               
             if (  (( bomberman.x+32 > Blq.xPantalla ) || ( bomberman.x+10 < Blq.xPantalla ))  && !(mapa.GetObjeto(bomberman.x/30, bomberman.y/30) instanceof Bomba) ){ //evalua las coordenadas en pantalla del bomberman y el objeto. de ser iguales no permite el movimiento.
                 return false;}
             
             return true;
        }
        return true;

    }
    private boolean validarMovimientoAbajo(  Bomberman bomberman ){

        int xIzq =(bomberman.x+15)/30; //x del extremo izq del torzo del bomberman en la matriz       
        int y= (bomberman.y+42)/30; //y del torzo del bomberman en la matriz
        int xDer =(bomberman.x+4)/30; //x del extremo derecjo del torzo del bomberman en la matriz
        
        if (  mapa.GetObjeto( xIzq , y )instanceof Bloque || mapa.GetObjeto( xIzq , y )instanceof Pared ||  mapa.GetObjeto( xDer , y )instanceof Bloque || mapa.GetObjeto( xDer , y )instanceof Pared ){
               Cosa Blq = (mapa.GetObjeto(xIzq, y)) == null ? (mapa.GetObjeto(xDer, y))  : (mapa.GetObjeto(xIzq, y)) ;
             
             if (  (( bomberman.x+32 > Blq.xPantalla ) || ( bomberman.x+10 < Blq.xPantalla )) ){
                 return false;}
             
             return true;
        }
         if (  mapa.GetObjeto( xIzq , y )instanceof  Bomba || mapa.GetObjeto( xDer , y )instanceof Bomba  ){
               Cosa Blq = (mapa.GetObjeto(xIzq, y)) == null ? (mapa.GetObjeto(xDer, y))  : (mapa.GetObjeto(xIzq, y)) ;
             
               
             if (  (( bomberman.x+32 > Blq.xPantalla ) || ( bomberman.x+10 < Blq.xPantalla ))  && !(mapa.GetObjeto(bomberman.x/30, bomberman.y/30) instanceof Bomba) ){ //evalua las coordenadas en pantalla del bomberman y el objeto. de ser iguales no permite el movimiento.
                 return false;}
             
             return true;
        }
         
        return true;
    }
    //===================VALIDACION DE BOMBAS=========================================
    private boolean validarColocacionBombas(  Bomberman bomberman ){
     
       if (( mapa.GetObjeto(bomberman.x/30, (bomberman.y+30)/30) instanceof Bomba ))
           return false;
       
       if ( bomberman.LimiteBombas - bomberman.BombasActivas <= 0 )  
           return false;
        
       return true;
    }

    /* =========== se encarga de leer las teclas, mover al personaje, validar esos movimientos y poner bombas =============*/
    public void MoverPj ( Bomberman b, MensajeBomba mensaje){
         if (input.isKeyDown(KeyEvent.VK_D)  && validarMovimientoDerecha( b )  ) 
                { 
                        b.x += 5;                                             //suma 5 a la cordenada x del bomberman 
                        b.columnaSS = 2;                                     //cambia la columa del spriteShet (viendo hacia la derecha)
                        b.contadorMovimiento=(b.contadorMovimiento+1)%231;  //pone un limite al contador
                        
                } 
                if (input.isKeyDown(KeyEvent.VK_A)&& validarMovimientoIzquierda(  b ) ) 
                { 
                        b.x -= 5;
                        b.columnaSS = 1;
                        b.contadorMovimiento=(b.contadorMovimiento+1)%231;
                }  
                if (input.isKeyDown(KeyEvent.VK_W) &&  validarMovimientoArriba(  b )) 
                { 
                        b.y -= 5; 
                        b.columnaSS = 3;
                        b.contadorMovimiento=(b.contadorMovimiento+1)%231;
                } 
                if (  (input.isKeyDown(KeyEvent.VK_S)) && validarMovimientoAbajo( b )  ) 
                { 
                        b.y += 5; 
                        b.columnaSS = 4;
                        b.contadorMovimiento=(b.contadorMovimiento+1)%231;
                }
                if (  (input.isKeyDown(KeyEvent.VK_SPACE)) && validarColocacionBombas( b ) ) 
                { 
                    b.ColocarBomba( mensaje );
                }
                if (  (input.isKeyDown(KeyEvent.VK_ESCAPE ) ) )
                { 
                    Pausa pausa;
                    try { partida.wait();} catch (Exception ex) { }
                    
                    if ( partida instanceof PartidaHost ) pausa = new Pausa((PartidaHost) partida);
                    else                                  pausa = new Pausa((PartidaClient) partida);
                    
                    
                    
                    
                }
                
               
    }
    
}
