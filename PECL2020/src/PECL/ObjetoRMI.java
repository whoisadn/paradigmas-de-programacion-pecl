package PECL;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;



public class ObjetoRMI extends UnicastRemoteObject implements InterfaceRMI{
    private MesaPlatos mesaplatos;
    private MostradorPedidos mostradorpedidos;

    public ObjetoRMI(MesaPlatos mesaplatos, MostradorPedidos mostradorpedidos)  throws RemoteException {
        this.mesaplatos = mesaplatos;
        this.mostradorpedidos = mostradorpedidos;
    }
    
    
    public String mostrarPedidos() throws RemoteException {// Implementación del método remoto
        String pedido = "  ";
        try {
            for (int i=0; i<mostradorpedidos.getPedidosMostrador2().size();i++) {
                pedido = pedido+mostradorpedidos.getPedidosMostrador().get(i)+" , ";
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ObjetoRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pedido;
    }
    
    public String mostrarPlatos() throws RemoteException {  
        String plato = "  ";
        try {
            for (int i=0; i<mesaplatos.getMesaPlatos2().size();i++) {
                plato = plato+mesaplatos.getMesaPlatos().get(i)+" , ";
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ObjetoRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return plato;
    }
}
