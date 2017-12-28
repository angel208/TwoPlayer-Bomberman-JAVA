/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bomberman;



import java.io.InputStream;
import java.io.InputStreamReader;



/**
 *
 * @author angelfabricio
 */
public class ManejoDeArchivos {
    
    
     public char[][] GenerarMatriz( InputStream stream ) {
         
       char [][] matriz=new char[20][15];
       int i = 0;
       int j;
       char caracter= ' ';  
        
      try
      {
             
       InputStreamReader ArchivoInput=new InputStreamReader( stream );  //reader del archivo

        
       for (j=0 ; j<15 ; j++ ){
           for ( i=0; i<=20 ; i++ ){
               caracter = (char)ArchivoInput.read();
                
               if( (char)caracter == '\n' ) i--;

                if( (char)caracter == 'x' )
                     matriz[i][j] = 'x'; 
                if( (char)caracter == 'b' )
                     matriz[i][j] = 'b';
                else if( (char)caracter == '*' )
                     matriz[i][j] = '*';  
                
           } 
           
                  
       }   
        
   
            
       ArchivoInput.close();
     
       }catch(Exception e){}
      
      return matriz;
}
    
}
