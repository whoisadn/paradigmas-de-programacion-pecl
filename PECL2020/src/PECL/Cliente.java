package PECL;

import java.util.Calendar;
import java.util.Date;

public class Cliente extends Thread {

    private String idCliente;
    private MostradorPedidos mpedidos;
    private Log log;
    private Pausar pararTodo;

    public Cliente(String idCliente, MostradorPedidos mpedidos,Log log, Pausar pararTodo) {
        this.idCliente = idCliente;
        this.mpedidos = mpedidos;
        this.log=log;
        this.pararTodo = pararTodo;
    }

    public void run() {
        String pedido;
        // Hay 200 clientes, ejecutarán su tarea y finalizarán
        try {
            pararTodo.comprobarHilo();
            String pedido1 = idCliente + "-P1";
            mpedidos.dejarPedido(pedido1);
            int tiempo = (1000 + (int) (-500 * Math.random()));
            System.out.println("El " + idCliente + " deja un primer plato " + pedido1);
            Date date = Calendar.getInstance().getTime();
            pedido=date+": "+" El " + idCliente + " deja un primer plato " + pedido1;
            log.escribir(pedido);
            sleep(tiempo);

            pararTodo.comprobarHilo();
            String pedido2 = idCliente + "-P2";
            mpedidos.dejarPedido(pedido2);
            System.out.println("El " + idCliente + " deja un segundo plato " + pedido2);
            date = Calendar.getInstance().getTime();
            pedido=date+":"+" El " + idCliente + " deja un segundo plato " + pedido2;
            log.escribir(pedido);

        } catch (InterruptedException e) {
        }

    }
}
