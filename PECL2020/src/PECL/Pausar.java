package PECL;




//Para pausar un hilo usaremos -> wait() y para despertarlos notifyAll() cuando cumpla la condición
public class Pausar {
    boolean parada = false;

    public synchronized void comprobarHilo() throws InterruptedException {   
        //Comprueba el estado del hilo, si la parada está a true, entra en el bucle y espera hasta que cambie a false
        while (parada) {           
                wait(); 
        }
    }
     public synchronized void parar() {
        parada = true;
    }

    public synchronized void seguir() {
        parada = false;
        notifyAll();
    }
}
