
package PECL;

import java.rmi.Remote;
import java.rmi.RemoteException;




public interface InterfaceRMI extends Remote{
    //Método que se publica
    String mostrarPedidos() throws RemoteException;       
    String mostrarPlatos() throws RemoteException;     
   
}
