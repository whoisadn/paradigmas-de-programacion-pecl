package PECL;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JTextField;

public class MesaPlatos {

    private ArrayList<String> pedidosMesa; //Array de pedidos
    private int maximo = 0, numElem = 0;
    Lock cerrojo = new ReentrantLock();
    private Condition lleno = cerrojo.newCondition();
    private Condition vacio = cerrojo.newCondition();
    private JTextField text;
    
    public MesaPlatos(int max, JTextField text) {
        this.maximo = max;
        this.pedidosMesa = new ArrayList<>();
        this.text = text;

    }

    public void dejarPedidoCocina(String pedido) throws InterruptedException {
        cerrojo.lock();
        while (numElem == maximo) {
            lleno.await();
        }
        try {
            pedidosMesa.add(pedido);
            numElem++;
            vacio.signal();
            text.setText(getMesaPlatos().toString());
        } finally {
            cerrojo.unlock();
        }
    }

    public String cogerPedidoCocina() throws InterruptedException {
        cerrojo.lock();
        while (numElem == 0) {
            vacio.await();
        }
        try {
            String pedido = pedidosMesa.get(0);
            pedidosMesa.remove(0);
            numElem--;
            lleno.signal();
            text.setText(getMesaPlatos().toString());
            return (pedido);
        } finally {
            cerrojo.unlock();
        }

    } 
    
    public ArrayList<String> getMesaPlatos() {
        return pedidosMesa;
    }
    
    public ArrayList<String> getMesaPlatos2() throws InterruptedException {
        cerrojo.lock();     
        try{
           return pedidosMesa;
        } finally {
            cerrojo.unlock();
        }
    }
}
