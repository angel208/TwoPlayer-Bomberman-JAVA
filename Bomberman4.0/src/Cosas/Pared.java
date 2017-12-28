/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cosas;


import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * Objeto Pared. lo Unico importante que contiene son sus coordenadas y su imagen que nunca va a variar
 * @author angelfabricio
 */
public class Pared extends Cosa{

    public Pared(int x, int y){
        super(x,y);  
               URL url = getClass().getResource("/pic/pared.gif");
               try {SpriteActual= ImageIO.read(url);}
               catch ( Exception e){}
    }
    
}
