/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bomberman;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author angelfabricio
 */
public class ManejoDeEntradas implements KeyListener  {
      boolean keys[] = new boolean[256];
        /** 
         * Assigns the newly created InputHandler to a Component 
         * @param c Component to get input from 
         */ 
        public ManejoDeEntradas(Component c) 
        { 
                c.addKeyListener(this); 
           
        } 
        
        /** 
         * Checks whether a specific key is down 
         * @param keyCode The key to check 
         * @return Whether the key is pressed or not 
         */ 
        public boolean isKeyDown(int keyCode) 
        { 
                if (keyCode > 0 && keyCode < 256) 
                { 
                        return keys[keyCode]; 
                } 
                
                return false; 
        } 
        
        /** 
         * Called when a key is pressed while the component is focused 
         * @param e KeyEvent sent by the component 
         */ 
        public void keyPressed(KeyEvent e) 
        { 
                if (e.getKeyCode() > 0 && e.getKeyCode() < 256) 
                { 
                        keys[e.getKeyCode()] = true; 
                } 
        } 

        /** 
         * Called when a key is released while the component is focused 
         * @param e KeyEvent sent by the component 
         */ 
        public void keyReleased(KeyEvent e) 
        { 
                if (e.getKeyCode() > 0 && e.getKeyCode() < 256) 
                { 
                        keys[e.getKeyCode()] = false; 
                } 
        } 

        /** 
         * Not used 
         */ 
        public void keyTyped(KeyEvent e){
        
        } 
    
}
