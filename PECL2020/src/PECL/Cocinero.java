package PECL;

import java.util.Calendar;
import java.util.Date;
import javax.swing.JTextField;

public class Cocinero extends Thread {

    private String idCocinero;
    private MesaPlatos mplatos;
    private JTextField text;
    private Log log;
    private Pausar pararTodo;

    public Cocinero(String idCocinero, MesaPlatos mplatos, JTextField text,Log log, Pausar pararTodo) {
        this.idCocinero = idCocinero;
        this.mplatos = mplatos;
        this.text = text;
        this.log=log;
        this.pararTodo = pararTodo;
    }
    
    public void run() {
        String dato;
        String pedido;
        while (true) { // Los cocineros nunca cesan su ejecución
            try {
                pararTodo.comprobarHilo();
                pedido = mplatos.cogerPedidoCocina();  //cogen el pedido de la cocina 
                System.out.println("El " + idCocinero + " ha recogido el plato "+ pedido);
                Date date = Calendar.getInstance().getTime();
                dato=date+": "+ " El " + idCocinero + " ha recogido el plato "+ pedido;
                log.escribir(dato);
                
                text.setText("Cocinando pedido " + pedido);
                pararTodo.comprobarHilo();
                int tiempo = (2000 + (int) (-1500 * Math.random()));                
                sleep(tiempo);
                System.out.println("El " + idCocinero + " ha terminado y vuelve a por otro plato... ");
                date = Calendar.getInstance().getTime();
                dato=date+": "+" El " + idCocinero + " ha terminado y vuelve a por otro plato... ";
                log.escribir(dato);
            } catch (InterruptedException e) {
            }
        }
    }
}
