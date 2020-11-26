package JDrones;
import javax.swing.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.*; 

public class Dron extends Thread{
    //DECLARACIÓN DE VARIABLES A UTILIZAR PARA DIBUJAR/SINCRONIZAR 
    private MiX x;    
    private MiY y;
    private DibujaDrones panel;   
    private int a = 1;
    private int b = 1;
    private Lock mutex;
    private int randoml=0,random1=0;
    private int LimMaxY,LimMinY,LimMaxX,LimMinX;
    private Semaphore sem;
    private int tipoSinc; 
    private int vivo=1;
    
    //DECLARACIÓN DEL CONSTRUCTOR QUE SE UTILIZARÁ
    Dron(MiX x, MiY y, DibujaDrones panel,int LY,int ly,int LX,int lx,Lock mutex,int algoritmo){
        setVariables(x,y,panel,LY,ly,LX,lx);  //(AHORRO DE ESPACIO) FUNCIÓN QUE ASIGNA LAS VARIABLES 
        this.sem= new Semaphore(1);           //INICIALIZACIÓN DEL SEMÁFORO
        this.mutex =new ReentrantLock();      //INICIALIZACIÓN DEL MUTEX
        this.tipoSinc=algoritmo;              //AQUÍ SE ASIGNA EL TIPO DE SINCRONIZACIÓN ESCOGIDO POR EL USUARIO
    }
   
    
    //MÉTODO RUN
    public void run(){
        while(vivo==1){  //MIENTRAS EL THREAD ESTE VIVO, ESTE CICLO SE EJECUTARÁ
           //System.out.println("estado vivo: "+ vivo);
           switch(tipoSinc){   //DEPENDIENDO DEL TIPO DE SINCRONIZACIÓN ES LA HERRAMIENTA QUE SE UTILIZARÁ 
               case 0:          //Sin ninguna sincronización
                   
                   try{
                        SeccionCritica(); //(AHORRO DE ESPACIO) SON LAS CONDICIONES PARA QUE REBOTE 
                        panel.repaint();  //MAPEO DEL PANEL PARA MOSTRAR EL MOVIMIENTO
                        Random();         //CALCULO DEL TIEMPO RANDOM
                        Thread.sleep((long)randoml);  //SLEEP RANDOM
                   }catch(Exception e){   
                         System.out.println("entro a exception");
                   }
                   
                   break;
                   
                   
               case 1:          //Con SEMAFOROS
                   try{
                       sem.acquire();   //EL SEMÁFORO ES ADQUIRIDO
                        SeccionCritica(); //(AHORRO DE ESPACIO) SON LAS CONDICIONES PARA QUE REBOTE 
                        panel.repaint();    //MAPEO DEL PANEL PARA MOSTRAR EL MOVIMIENTO
                        Random();     //CALCULO DEL TIEMPO RANDOM
                        Thread.sleep((long)random1);  //SLEEP RANDOM
                        sem.release();   //EL SEMAFORO ES LIBERADO
                   }catch(Exception e){
                         System.out.println("entro a exception");
                   }
                   break;
                   
                   
               case 2:          //Con VARIABLES DE CONDICIÓN
                   
                   break;
                   
                        
               case 3:          //CON MONITORES
                   
                   break;    
                   
                   
               case 4:          //CON BARRIERS
                   
                   break;
               
           }
           
            
            
            
        }
    }

    public void Random(){   //FUNCIÓN RANDOM PARA CALCULAR TIEMPO RANDOM QUE DORMIRÁ EL PROCESO
        long r=(int)(Math.random()*12);
    randoml=(int)r;
    
    //System.out.println("Este es mi valor random: "+r);
    
    }
    
    public void SeccionCritica(){  //SECCIÓN DE DETECCIÓN DE LIMITES (PARA VER CUANDO EL DRON REBOTARÁ)
        getY().setMiY(getY().getMiY()+b);
                  getX().setMiX(getX().getMiX()+a);
                 if (getY().getMiY() == LimMaxY)
                    b = -b;
                if (getY().getMiY() == LimMinY)
                    b=-b;
                if (getX().getMiX() == LimMaxX)
                    a = -a;
                if (getX().getMiX() == LimMinX)
                    a = -a;
    }
 
    //SETTERS Y GETTERS
    public MiX getX() {  
        return x;
    }


    public void setX(MiX x) {
        this.x = x;
    }


    public MiY getY() {
        return y;
    }


    public void setY(MiY y) {
        this.y = y;
    }
    
    
    //(AHORRO DE ESPACIO) FUNCIÓN QUE ASIGNA LAS VARIABLES 
    public void setVariables(MiX x, MiY y, DibujaDrones panel,int LY,int ly,int LX,int lx){
        this.x = x;
        this.y = y;
        this.panel = panel;
        this.LimMaxY=LY;
        this.LimMinY=ly;
        this.LimMaxX=LX;
        this.LimMinX=lx;
        this.random1=8;
    }
    
    // EN CASO DE QUERER DETENER EL PROCESO
    public void detener(){
        this.vivo=0;
    }
}
