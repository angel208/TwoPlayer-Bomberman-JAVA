/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Bomberman.GUI;
import Bomberman.PartidaHost;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.exit;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author angelfabricio
 */
public class MenuGrafico extends GUI {

    /**
     * Creates new form menu2
     */
          PartidaHost Host;
          
          

      public MenuGrafico( int w, int h){
        super(w,h);
        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        
           //inicializa todos los listener
            BotonAcercaDe.addActionListener ( new AcercaDeButtonAction());
            BotonConectarConHost.addActionListener ( new ConectarConHostButtonAction());
            BotonIniciarHost.addActionListener ( new IniciarHostButtonAction());
            BotonSalirMenu.addActionListener ( new SalirMenuButtonAction());
      }
    
          @Override
    public void inicializar() {
         super.inicializar();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BotonIniciarHost = new javax.swing.JButton();
        BotonConectarConHost = new javax.swing.JButton();
        BotonAcercaDe = new javax.swing.JButton();
        BotonSalirMenu = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(425, 560));
        setResizable(false);
        getContentPane().setLayout(null);

        BotonIniciarHost.setText("Host");
        BotonIniciarHost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonIniciarHostActionPerformed(evt);
            }
        });
        getContentPane().add(BotonIniciarHost);
        BotonIniciarHost.setBounds(10, 360, 140, 35);

        BotonConectarConHost.setText("Conectarse a Host");
        getContentPane().add(BotonConectarConHost);
        BotonConectarConHost.setBounds(10, 410, 140, 35);

        BotonAcercaDe.setText("Acerca De / Ayuda ");
        BotonAcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAcercaDeActionPerformed(evt);
            }
        });
        getContentPane().add(BotonAcercaDe);
        BotonAcercaDe.setBounds(10, 460, 140, 35);

        BotonSalirMenu.setText("Salir");
        getContentPane().add(BotonSalirMenu);
        BotonSalirMenu.setBounds(10, 510, 140, 35);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/Menu.gif"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 440, 560);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotonIniciarHostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonIniciarHostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotonIniciarHostActionPerformed

    private void BotonAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAcercaDeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotonAcercaDeActionPerformed

//---------------------------LISTENERS DE ACCION PARA CADA BOTON-------------------------------------
    
    //crea cada uno de los action listener, los cuales esperan una accion en cada uno de los botones de la clase.
     
    public class AcercaDeButtonAction implements ActionListener{
            @Override
        public void actionPerformed(ActionEvent e) {
                       VentanaInstrucciones Instrucciones = new  VentanaInstrucciones();
                       Instrucciones.setVisible(true);
        }
    } 
    public class ConectarConHostButtonAction implements ActionListener{
            @Override
        public void actionPerformed(ActionEvent e) {
              ConectarCliente NuevaPartida = new ConectarCliente();
              NuevaPartida.setVisible(true);
        }
    }
    
    public class IniciarHostButtonAction implements ActionListener{
            @Override
        public void actionPerformed(ActionEvent e) {
              VentanaHost NuevoHost = new  VentanaHost(  );
              NuevoHost.setVisible(true);
        }
    } 
    public class SalirMenuButtonAction implements ActionListener{
            @Override
        public void actionPerformed(ActionEvent e) {
            exit(0);
        }
    } 
    
    
    
    
//----------------------------------------------------------------------------------------------------

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonAcercaDe;
    private javax.swing.JButton BotonConectarConHost;
    private javax.swing.JButton BotonIniciarHost;
    private javax.swing.JButton BotonSalirMenu;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}