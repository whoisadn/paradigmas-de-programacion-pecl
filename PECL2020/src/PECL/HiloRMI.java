package PECL;

import java.rmi.Naming;
import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class HiloRMI extends Thread {
    
     private JTextField textoPedido;
     private JTextArea textoPlato;

    public HiloRMI(JTextField textoPedido, JTextArea textoPlato) {
        this.textoPedido = textoPedido;
        this.textoPlato = textoPlato;
    }
     public void run() {
        try {                 
            //Localiza el objeto distribuido:
            InterfaceRMI obj = (InterfaceRMI) Naming.lookup("//127.0.0.1/ModuloV");
            while (true){
                textoPedido.setText(obj.mostrarPedidos());
                textoPlato.setText(obj.mostrarPlatos());  
            }
        } catch (Exception e) {
            System.out.println("Excepcion: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}
