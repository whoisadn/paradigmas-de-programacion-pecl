package PECL;

import java.io.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {
    private FileWriter escritor;
    private File archivo;
    private Lock cerrojo = new ReentrantLock();
    
    public void crearArchivo(){
           try {
            archivo = new File("evolucionRestaurante.txt");
            if (archivo.createNewFile()){
                System.out.println("El archivo se ha creado");
            }else{
                System.out.println("El archivo ya existe y se va a sobreescribir ");
                archivo.delete();
                archivo.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("No se ha podido ejecutar");
            e.printStackTrace();
        }
    }
    
    public void escribir(String texto){
        cerrojo.lock();
        try {
            escritor=new FileWriter("evolucionRestaurante.txt",true); //El escritor se inicializa con la misma ruta de antes para estar listo para escribir
            escritor.write(texto+"\n");
            escritor.flush();
            escritor.close();
            } catch (IOException ex) {
                Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
            }
        finally {
            cerrojo.unlock();
        }
    }
}
