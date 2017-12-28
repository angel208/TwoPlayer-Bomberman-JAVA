
package Bomberman;

import java.awt.Insets;
import static javax.swing.JFrame.EXIT_ON_CLOSE;


/**
 *Clase base de todas las clases que sean ventanas. es una clase simple que contiene un objeto de manejo de entradas y salidas, un ancho un largo y unos insets
 * Inset: son la parte de la ventana que se encuentra debajo del marco de la misma. esta es una clase que contiene las dimensiones de los insets de la ventana.
 * @author angelfabricio
 */
public class GUI extends javax.swing.JFrame {
    
    int windowWidth, windowHeight;
    Insets inset;
    ManejoDeEntradas input;
    
    
    public GUI ( int width, int height ){
        windowWidth=width;
        windowHeight=height;
    }
    


    
    public void inicializar(){ //este metodo debe abrir la ventana
                
                //Inicializa la ventana
                setTitle("Bomberman"); 
                setSize(windowWidth, windowHeight); 
                setResizable(false); 
                setDefaultCloseOperation(EXIT_ON_CLOSE); 
                setVisible(true); 
 
                /* los insets son las partes de la imagen que estan justo detras de la barra de titulo de la ventana.
                cuando se va a trabajar en la ventana, la posicion (0,0) esta dentro del inset superior por lo
                que si dibujamo algo ahi, se dibujara justo detras de la barra de titulo. por eso creamos una 
                vaiable e tipo inset que guarda el tama;o de todos los insets de la ventana (superior, inferior,
                derecho e izquierdo), estos valores se obtienen mediante getInsets(). pero el que da los valores
                del inset en primer lugar es @setVisible, por lo que si se usa @getInsets antes del serVisible,
                todos los valores seran cero y el resize no tendria efecto. Entonces, se ponen los tama;os
                predeterminados a la ventana, se pone en true el @SetVisible, y LUEGO es que se llama al @getInset 
                para obtener los valores de los insets, para asi sumarselos a los limites del tama;o de la ventana.
                */
                inset = getInsets(); //obtiene los insets de la ventana
                setSize(windowWidth+inset.left+inset.right, windowHeight+inset.bottom+inset.top); //acomoda el tama;o de la ventana
                
                input = new ManejoDeEntradas ( this ); 
     
           } 
    
    
    
    
    
}
