/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author AMD
 */
public class Cliente {
        JFrame ventana_chat=null;
      JButton boton_enviar=null;
      JTextField Textoenviar=null;
      JTextArea area=null;
      JPanel panel_area= null;
      JPanel panel_boton=null;  
      JScrollPane scroll=null;
     public Cliente() {
        interfaz();
    }              
     
    
public void interfaz(){
       
      ventana_chat= new JFrame("Cliente");
      boton_enviar= new JButton("enviar");
      Textoenviar=new JTextField(4);     
      area= new JTextArea(45,20);
       scroll=new JScrollPane(area);
      panel_area=new JPanel();
      panel_area.setLayout(new GridLayout(1,1));
      panel_area.add(scroll);
       panel_boton=new JPanel();
      panel_boton.setLayout(new GridLayout(1,2));
      panel_boton.add(Textoenviar);
      panel_boton.add(boton_enviar);
      ventana_chat.setLayout(new BorderLayout());
      ventana_chat.add(area,BorderLayout.NORTH);
      ventana_chat.add(panel_boton,BorderLayout.SOUTH);
      ventana_chat.setSize(1300,820);
    
      ventana_chat.setResizable(false);
        ventana_chat.setVisible(true);
      ventana_chat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
}
 public static void main(String[] args) throws IOException {
         Servidor a= new Servidor();
      
       byte[] mensaje=new byte[25]; 
           String g=JOptionPane.showInputDialog("Por favor, rexistre o seu nome de Usuario");
                        a.ventana_chat.setTitle(g);
			 a.area.setText("Creando socket cliente");
			Socket clienteSocket=new Socket();
			a.area.setText("Estableciendo la conexion");
			
			InetSocketAddress addr=new InetSocketAddress("localhost",6666);
			clienteSocket.connect(addr);

			InputStream is = clienteSocket.getInputStream();
			OutputStream os= clienteSocket.getOutputStream();
                       
                          a.area.setText("Conectando");
                           for(int i=0;i<1;i++){
			os.write((g+" conectado").getBytes());
                       // byte[] mensaje=new byte[25]; 	
                        is.read(mensaje);
                               a.area.setText("Mensaxe do servidor:"+new String(mensaje));
                           }
                           
                          
                           a.boton_enviar.addActionListener(new ActionListener() {
             private String frase;
             @Override
                          public void actionPerformed(ActionEvent e){   
                 try {
                     frase=a.Textoenviar.getText();
                     a.area.setText(a.area.getText()+"\n envías:"+frase);
                     os.write(frase.getBytes());
                 } catch (IOException ex) {
                     System.out.println("Error de IO:"+ex);
                 }
                          }              
                        });
                         is.read(mensaje);          
                        
                        if(!a.ventana_chat.isActive()){
                            clienteSocket.close();
                        }
                      
                          
         
 }

}