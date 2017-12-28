/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bomberman;

import Ventanas.MenuGrafico;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;



    /**
     * Clase principal del juego. solo posee el metodo static main, el cual se encarga unicamente de inicializar y correr el menu.
     * el juego funciona de la siguiente manera. el main abre un menu, la cual es una ventana que contiene opciones como "jugar""opciones"salir"
     * cuando se presione la opcion "jugar" el menu abrira otra ventana que es la ventana principal de la partida. En esa ventana es en donde la mayoria del juego se llevara a cabo.
     * otras ventanas se abriran al momento de presionar "opciones" o salir".
     * 
     */

public class Jbomber {

    public static void main(String[] args) {
              
       MenuGrafico Menu = new MenuGrafico(422,560);
       Menu.inicializar();
                               
        
    }
    
}
