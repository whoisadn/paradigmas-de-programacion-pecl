package PECL;

import java.util.Calendar;
import java.util.Date;
import javax.swing.JTextField;

public class Empleado extends Thread {

    private String idEmpleado;
    private MostradorPedidos mpedidos;
    private MesaPlatos mplatos;
    private JTextField text;
    private Log log;
    private Pausar pararEmpleado;
    private Pausar pararTodo;

    public Empleado(String idEmpleado, MostradorPedidos mpedidos, MesaPlatos mplatos, JTextField text, Log log, Pausar pararTodo, Pausar pararEmpleado) {
        this.idEmpleado = idEmpleado;
        this.mpedidos = mpedidos;
        this.mplatos = mplatos;
        this.text = text;
        this.log= log;
        this.pararTodo = pararTodo;
        this.pararEmpleado = pararEmpleado;
    }

    public void run() {
        String pedido;
        String dato;
        while (true) { // Los empleados nunca cesan su ejecución
            try {
                pararTodo.comprobarHilo();
                pararEmpleado.comprobarHilo();
                pedido = mpedidos.cogerPedido(); //recoger pedido del mostrador
                System.out.println("El " + idEmpleado + " ha recogido el plato del mostrador " + pedido);
                Date date = Calendar.getInstance().getTime();
                dato=date+": "+" El " + idEmpleado + " ha recogido el plato del mostrador " + pedido;
                log.escribir(dato);
                
                pararTodo.comprobarHilo();
                pararEmpleado.comprobarHilo();
                mplatos.dejarPedidoCocina(pedido); //dejar pedido en la cocina
                System.out.println("El " + idEmpleado + " ha dejado el plato en cocina " + pedido);
                date=Calendar.getInstance().getTime();
                dato=date+": "+" El " + idEmpleado + " ha dejado el plato en cocina " + pedido;
                log.escribir(dato);
                
                text.setText("Llevando " + pedido + " a la mesa de platos");
                pararTodo.comprobarHilo();
                pararEmpleado.comprobarHilo();
                int tiempo = (700 + (int) (-300 * Math.random()));
                sleep(tiempo);
                System.out.println("El " + idEmpleado + " ha terminado y vuelve a por otro plato... ");
                date=Calendar.getInstance().getTime();
                dato=date+": "+" El " + idEmpleado + " ha terminado y vuelve a por otro plato... ";
                log.escribir(dato);
                pararTodo.comprobarHilo();
                pararEmpleado.comprobarHilo();
            } catch (InterruptedException e) {
            }
        }
    }

}
