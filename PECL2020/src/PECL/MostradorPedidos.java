package PECL;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JTextField;

public class MostradorPedidos {

    private ArrayList<String> pedidosMostrador; //Array de pedidos
    private int maximo = 0, numElem = 0;
    Lock cerrojo = new ReentrantLock();
    private Condition lleno = cerrojo.newCondition();
    private Condition vacio = cerrojo.newCondition();
    private JTextField text;
    

    public MostradorPedidos(int max, JTextField text) {
        this.maximo = max;
        this.pedidosMostrador = new ArrayList<>();
        this.text = text;
    }

    public void dejarPedido(String pedido) throws InterruptedException {
        cerrojo.lock();
        while (numElem == maximo) { //Si el ArrayList está lleno... Espera
            lleno.await();
        }
        try {
            //Vamos añadiendo los pedidos de la clase productor-cocinero
            pedidosMostrador.add(pedido);
            numElem++;
            vacio.signal(); //El ArrayList ya no está vacío
            text.setText(getPedidosMostrador().toString());
        } finally {
            cerrojo.unlock();
        }
    }

    public String cogerPedido() throws InterruptedException {
        cerrojo.lock();
        while (numElem == 0) { //Si el ArrayList está vacio... Espera
            vacio.await();
        }
        try {
            String pedido = pedidosMostrador.get(0);
            pedidosMostrador.remove(0);
            numElem--;
            lleno.signal(); //El ArrayList ya no está lleno
            text.setText(getPedidosMostrador().toString());
            return (pedido);
        } finally {
            cerrojo.unlock();
        }
    }

    public ArrayList<String> getPedidosMostrador() {
        return pedidosMostrador;
    }
    
    public ArrayList<String> getPedidosMostrador2() throws InterruptedException {
        cerrojo.lock();        
        try{
           return pedidosMostrador;
        }finally {
            cerrojo.unlock();
        }
    }
}
