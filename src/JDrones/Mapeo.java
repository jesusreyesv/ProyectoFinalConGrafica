//LUIS JESUS REYES VELAZQUEZ 
package JDrones;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Mapeo extends Thread {
    
    private ArrayList<Dron> drones;
    private int contador;
    private int ad=5;
    
    Mapeo (ArrayList<Dron> drons    ){
        this.drones=drons;
    }
     
    public void run(){
        while(true){
        if(ad>=0){
         ad--;
        }else{
            try {
            
            Thread.sleep(100);  //ESPERA 100 MILISEGUNDOS ANTES DE HACER OTRO REGSTRO
            for(Dron d: drones){
                d.guardarCoordenadas(); //HACE QUE LOS DRONES GUARDEN SUS COORDENADAS
                //ACTUALES
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Mapeo.class.getName()).log(Level.SEVERE, null, ex);
        }
        ad=5;
        }
        }
        
        
    }
    
    public void actualizarDrones(ArrayList<Dron> drons){
        this.drones=drons;
    }
}
