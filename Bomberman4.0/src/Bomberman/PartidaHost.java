/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bomberman;

import Cosas.Bomba;
import Cosas.Bomberman;
import Cosas.Cosa;
import Cosas.Pu_1Up;
import Cosas.Pu_BombUp;
import Cosas.Pu_FireUp;
import Servidor.MensajeBomba;
import Servidor.MensajeBomberman;
import Servidor.PaqueteEstadoCliente;
import Servidor.PaqueteEstadoHost;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.System.exit;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import javax.imageio.ImageIO;


/**
 * Clase Partida: esta es la clase principal del juego y probablemente la mas coplicada.
 * 
 * Atributos: 
 * -Ejecutando: este es el booleano que le indica al bucle principal si debe seguir corriendo.
 * -fps:        este atributo limita la cantida de framas del juego que se mostraran en un segundo.
 * -controlPj:  este es un objeto de la clase controles, que se usara para mover al bomberman (mas informacion en la clase misma)
 * -mapa:       esta es la matriz que contiene a todos los objetos en el juego, y es la matriz que se pasara como parametro a muchos de los
 *              objetos del juego.
 * 
 * -backBuffer: la muestra de imagenes en la ventana del juego funciona de la siguiente manera:
 *              todo lo que se tiene que pintar en la pantalla, es pintado primero en un Buffer.
 *              luego de que todo ha sido pintado en este buffer, se pinta el buffer en la pantalla como tal. 
 *              esto evita que los objetos titile entre si, ya que siempre estara presente la misma cantidad de objetos en la imagen.
 * -b1:         es el bomberman de la partida. por ahora el juego solo maneja la posibilidad de usar un solo personaje.
 * 
 * Metodos:
 * -inicializar: inicializa la ventana, los controles y el bomberman.
 * -ejecutar:    El bucle principal del juego hace unicamente tres cosas: limitar el frame rate, dibujar el juego, y mover al bomberman.
 *               el frame rate se limita calculando cuando tarda el bucle actual, y si tardo menos de lo deseado, se pone a dormir la ventana por el tiempo que resta.
 * 
 * -dibujar:     algoritmo:
                 *crea la imagen que se bibujara en pantalla y el buffer del que se hablo anteriormente.
 *               *Dibuja la imagen de fondo en el buffer.
 *               *pasa el buffer como parametro a la funcion "ImprimirCampo" que forma parte de la clase Tablero, la clase dibuja el mapa dentro del buffer de una forma independiente del bucle.
 *               *pasa el buffer como parametro a la funcion "setGraficos" de la clase "bomberman", la cual internamente va dibujando en el buffer el personaje de forma independiente al bucle.
 *               *luego de que el buffer esta completo y tiene todo lo que tiene que tener, se dibuja este buffer en la ventana que vera el usuario.
 *             
 * 
 * @author angelfabricio
 */
public class PartidaHost extends GUI implements Runnable {

    
    //0000000000000000000000==Declaracion de Atributos==0000000000000000000000000000000000000000000000000000000000000000000000
           public boolean Partida= true, Ganador = false;
           public int LastFrames;
           public int fps = 30; 
           public Controles controlPj;
           
           char colorMapa;
           char colorPj; 
           String NickName;
           
           char matrizPowerUp[][] = new char[20][15];
           Tablero mapa = new Tablero(this, matrizPowerUp);
           
           BufferedImage backBuffer = new BufferedImage(windowWidth, 450, BufferedImage.TYPE_INT_RGB); 
           BufferedImage Fondo = null;
           Bomberman b1;
           URL url;
           
           //****************Vaiables de estado de los Bomberman Enemigos y paquetes**************
           
            char matrizEnemiga[][];
            int xEnemigo, yEnemigo, fEnemigo, cEnemigo;
            SpriteSheet spriteSEnemigo ; char colorBombermanEnemigo ;
            boolean vivoEnemigo=true;
            
            MensajeBomba BombaAliada = new MensajeBomba(0 , 0 , 0, 0); 
            MensajeBomberman mensajeBomberman = new MensajeBomberman ( 0 , 0, 1, 1 , true );
            PaqueteEstadoHost packetHost = new PaqueteEstadoHost( mensajeBomberman, BombaAliada,  matrizPowerUp, true);
            
            char matrizAEnviar[][];
            PaqueteEstadoCliente packetClient = null;
            MensajeBomba BombaEnemiga = null;
            
           //*****************************************************************************  
            
            //sockets e input
            int Puerto;
            private ServerSocket serverSocket = null;
            private Socket socket = null;
            private ObjectInputStream inputStream = null;
            private ObjectOutputStream outputStream = null;
            
            //Labels de las estadisticas
            private javax.swing.JLabel JugadorLabel;
            private javax.swing.JLabel VidasLabel;
            private javax.swing.JLabel BombasLabel;
            private javax.swing.JLabel RangoLabel;
            private javax.swing.JPanel jPanel1;
            
           
     //0000000000000000000000==Declaracion de Atributos==0000000000000000000000000000000000000000000000000000000000000000000000
            
     //--------metodos---------
            
           
         public PartidaHost ( int puerto, char ColorMapa, char ColorPj, String NickName){
             super(600, 485);
             
             Puerto = puerto;
             colorMapa = ColorMapa;
             colorPj = ColorPj;
             this.NickName = NickName;
             
             initComponents();
         }

         private void initComponents() {

        
        jPanel1 = new javax.swing.JPanel();
        JugadorLabel = new javax.swing.JLabel();
        VidasLabel = new javax.swing.JLabel();
        BombasLabel = new javax.swing.JLabel();
        RangoLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 35));

        JugadorLabel.setText("Esperando por los rivales...");
        JugadorLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        VidasLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        VidasLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/1Up.gif"))); // NOI18N
        VidasLabel.setText(":3");

        BombasLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        BombasLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/bombUp.gif"))); // NOI18N
        BombasLabel.setText(": 2");

        RangoLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        RangoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/fireUp.gif"))); // NOI18N
        RangoLabel.setText(":2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(JugadorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(VidasLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BombasLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                .addGap(27, 27, 27)
                .addComponent(RangoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(VidasLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addComponent(BombasLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(RangoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(JugadorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 460, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    } 
        
           @Override
         public void run(){

               try{
                  serverSocket = new ServerSocket(Puerto);
                  System.out.println("Host Conectado ");
                  System.out.println("Esperando Conexion de clientes");
                  
                  socket = serverSocket.accept();           //crea un nuevo socket, el cual espera a q un socket (el cliente) se conecte a el
                  System.out.println("Se ha conectado un Rival");
                  
                   //--------------INICIALIZACIONES PREVIAS-----------------------
                            JugadorLabel.setText("Jugador: "+NickName);
                            
                            url = getClass().getResource("/pic/bomberman"+colorPj+".gif");
                            b1.setSpriteS( new SpriteSheet( url , 32, 62 ) );
                            
                            Fondo= ImageIO.read( getClass().getResource("/pic/fondo"+colorMapa+".gif") );
                            outputStream = new ObjectOutputStream(socket.getOutputStream()); 
                            outputStream.writeObject(colorMapa);

                            inputStream = new ObjectInputStream(socket.getInputStream());
                            colorBombermanEnemigo = (char) inputStream.readObject();
                            
                            url = getClass().getResource("/pic/bomberman"+colorBombermanEnemigo+".gif");
                            spriteSEnemigo = new SpriteSheet( url, 32, 62 );

                            outputStream = new ObjectOutputStream(socket.getOutputStream()); 
                            outputStream.writeObject(colorPj);
                   //--------------INICIALIZACIONES PREVIAS-----------------------
                            
               } catch (Exception e){}
               
               
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!BUCLE PRINCIPAL DEL JUEGO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
               
               while (Partida || (LastFrames < 30)){ //loop principal del juego
                   
                   long TiempoActual = System.currentTimeMillis(); //captura el tiempo actual del bucle
                   
                   BombaAliada.SetToZero();                                  //inicializa el mensaje de la bomba
                   if (b1.isAlive()) controlPj.MoverPj(b1, BombaAliada);     //mueve el bomberman si esta vivo
                   
                   if ( !Partida ) LastFrames++;                             //cuando termina la partida, el juego corre 40 framas mas para que las animaciones puedan culminar
                   
                   //******************************interaccion con el cliente****************************
                   try{
                   inputStream = new ObjectInputStream(socket.getInputStream());

                   packetClient= (PaqueteEstadoCliente) inputStream.readObject();
                   
                   //se descomprime el paquete Entrante
                   BombaEnemiga = packetClient.getEstadoBomba();
                   matrizEnemiga = packetClient.getMatrizPowerUp();
                   xEnemigo = packetClient.getEstadoBomberman().getCoordenadaX();
                   yEnemigo = packetClient.getEstadoBomberman().getCoordenadaY();
                   fEnemigo = packetClient.getEstadoBomberman().getFila();
                   cEnemigo = packetClient.getEstadoBomberman().getColumna();
                   vivoEnemigo = packetClient.getEstadoBomberman().getVivo();
                   //Fin de la descompresion.
                   
                   //Coloca la Bomba
                    if ( (BombaEnemiga.getEstado() == 1) && !(mapa.GetObjeto(BombaEnemiga.getPosicionX(), BombaEnemiga.getPosicionY()) instanceof Bomba)){
                          Bomba bomba = new Bomba( BombaEnemiga.getPosicionX(), BombaEnemiga.getPosicionY(), BombaEnemiga.getRango(), mapa);  //crea una nueva bomba en la posicion actual del personaje
                          mapa.AgregarCosa(bomba, bomba.x, bomba.y);                            //agrega la bomba creada a la matriz del mapa
                          bomba.start();   
                      }  
                   //Fin de colocacion de bomba
                   
                   
                   //maneja los power ups y crea la matriz a ser enviada
                     GenerarTablero();
                     matrizAEnviar = new char[20][15];
                     GenerarMatrizAEnviar();
                   //fin del manejo de los powerUps
                     
                     
                   //Verificar Partida ganada
                     if ( vivoEnemigo == false || b1.vivo == false ){
                         if ( b1.vivo )
                              Ganador = true;
                         Partida = false;
                     }
                   //fin de la verificacion
                     
                     
                   //Comprime el paquete que sera enviado
                     mensajeBomberman = new MensajeBomberman ( b1.x , b1.y, b1.columnaSS, b1.filaSS , b1.vivo );
                     packetHost = new PaqueteEstadoHost( mensajeBomberman, BombaAliada,  matrizAEnviar, Partida);        
                   //fin de compresion de paquete
                     
                     
                   //manda el paquete
                      outputStream = new ObjectOutputStream(socket.getOutputStream()); 
                      outputStream.writeObject(packetHost);
                   //enviado
                   
                   } catch (Exception e){}   
                   
                   
                   //************************************************************************************
                   
                   Dibujar();
                   
                   
                   TiempoActual = (1000/fps)-(System.currentTimeMillis() - TiempoActual);//devuelve los ms que faltan para llegar al tiempo de refresh
                
                   //-------------------------------delay------------
                   if (TiempoActual > 0){ 
                       try{
                          Thread.sleep(TiempoActual);
                       }catch(Exception e){}    
                   }
                   //------------------------------------------------                  
               }
               
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!FIN DEL BUCLE PRINCIPAL DEL JUEGO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
               if ( Ganador == true ){ 
                   JugadorLabel.setFont(new java.awt.Font("Tahoma", 1, 18));
                   JugadorLabel.setText("Ganaste la Partida!");
               }
               else{
                   JugadorLabel.setFont(new java.awt.Font("Tahoma", 1, 18));
                   JugadorLabel.setText("Perdiste, mejor suerte la proxima!");
               }
               
               try{ Thread.sleep(3000);}catch (Exception e){}
               
               setVisible(false);
               exit(0); 
           }


           @Override
           public void inicializar(){ //este metodo debe abrir la ventana
                super.inicializar(); 
                
                controlPj = new Controles ( this, input, mapa ); 
                this.setTitle("(HOST)Bomberman ");
              
              //=================================================personaje 
                url = getClass().getResource("/pic/bomberman2.gif");
                SpriteSheet SpriteS = new SpriteSheet (url, 32, 62 );
                b1 = new Bomberman(30, 30, SpriteS, this);
                b1.SetMapaActual(mapa, matrizPowerUp);
             //==========================================================     
                
                
                b1.start();
           }
            
           public void GenerarTablero(){
               
               for ( int i=0; i<20 ; i++)
                       for ( int j=0; j<15; j++){
                           if      (matrizPowerUp[i][j]=='1') { mapa.AgregarCosa(new Pu_FireUp(i,j,mapa), i, j); matrizPowerUp[i][j]='0';   }
                           else if (matrizPowerUp[i][j]=='2') { mapa.AgregarCosa(new Pu_1Up(i,j,mapa), i, j);   matrizPowerUp[i][j]='0';    }
                           else if (matrizPowerUp[i][j]=='3') { mapa.AgregarCosa(new Pu_BombUp(i,j,mapa), i, j);  matrizPowerUp[i][j]='0';  }
                           else if (matrizPowerUp[i][j]=='x') { mapa.SacarCosa(i, j); matrizPowerUp[i][j]='0';  }    
                           else if (matrizEnemiga[i][j]=='x') { mapa.SacarCosa(i, j);  }
                       }
               
           }
           public void GenerarMatrizAEnviar(){
               for ( int i=0; i<20 ; i++)
                       for ( int j=0; j<15; j++){
                               matrizAEnviar[i][j] = toNumber(mapa.tablero[i][j]);
                       }
           }
           
           public char toNumber ( Cosa P ){
               if      ( P instanceof Pu_FireUp ) return '1';
               else if ( P instanceof Pu_1Up )    return '2';
               else if ( P instanceof Pu_BombUp ) return '3';
               else return '0';
           }
           
           public void Dibujar(){
               Graphics g = getGraphics(); //grafico de la ventana
               Graphics bbg = backBuffer.getGraphics(); //buffer que contiene la imagen a dibujar ahora
               
            //------------------fondo
               bbg.drawImage(Fondo, 0, 3,600, 450, this);

            //-----------------fondo 
               
            //-------Estadisticas---------
               VidasLabel.setText  (Integer.toString(b1.vidas));
               BombasLabel.setText (Integer.toString(b1.LimiteBombas));
               RangoLabel.setText  (Integer.toString(b1.RangoDeBombas));
            //-------Estadisticas---------
               
            //---------------Campo   
               mapa.ImprimirCampo(bbg);
            //--------------campo   
              
            //--------------Imprimir Enemigos---------
              if (vivoEnemigo) bbg.drawImage ( spriteSEnemigo.ObtenerSpriteActual(fEnemigo, cEnemigo), xEnemigo ,yEnemigo-10, 32, 64 , this);
            //---------------------------------------
               
            //----------------pj              
               b1.SetGraficos(bbg);
            //--------------pj              

               
               
               g.drawImage (backBuffer, inset.left ,inset.top , this); //esto pinta en g (la ventana) una imagen (el buffer creado) en la posicion 0,0 (del inset).
               //aca se usan como limites los insets (top y left), para dibujar la imagen justo dentro de los insets.
           }
    }