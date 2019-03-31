
package chat;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.*;

public class Servidor {
      JFrame ventana_chat=null;
      JButton boton_enviar=null;
      JTextField Textoenviar=null;
      JTextArea area=null;
      JPanel panel_area= null;
      JPanel panel_boton=null;  
      JScrollPane scroll=null;
    public Servidor() {
        interfaz();
    }
    
public void interfaz(){
       
      ventana_chat= new JFrame("Servidor");
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
         String usuario = null;
  Socket newSocket = null;
  byte[] mensaje=new byte[25];
			a.area.setText("Inicializando el servidor");
	
			ServerSocket serverSocket=new ServerSocket();

			
			InetSocketAddress addr=new InetSocketAddress("localhost",6666);
			serverSocket.bind(addr);

			a.area.setText(a.area.getText()+"\nEsperando conexiones...");
                       while(true){
			newSocket= serverSocket.accept();
                        
			InputStream is=newSocket.getInputStream();
			OutputStream os=newSocket.getOutputStream();
                        //Identificacion
                        for(int i=0;i<1;i++){
                         byte[] mensaje2=new byte[25];
                         is.read(mensaje2);
                         usuario=new String(mensaje2);
                         a.area.setText(usuario);
                         os.write(mensaje2);
                        }
                        while(newSocket.isBound()){
                        is.read(mensaje);
                           a.area.setText(a.area.getText()+"\n"+usuario+": "+new String(mensaje));
                        }
                       }
                       
             
  
    }
    
}
