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
    private int LimMaxY,LimMinY,LimMaxX,LimMinX;
    private Semaphore sem;
    private int tipoSinc; 
    private int vivo=1,flag=5;
    private Coordenadas misCoordenadas;
    private String nombre;
    private int alg=0;
    private RecursoCompartido rc;
    
    
    //DECLARACIÓN DEL CONSTRUCTOR QUE SE UTILIZARÁ
    Dron(String n,MiX x, MiY y, DibujaDrones panel,int LY,int ly,int LX,int lx,Lock mutex,int algoritmo, RecursoCompartido rc){
        setVariables(x,y,panel,LY,ly,LX,lx);  //(AHORRO DE ESPACIO) FUNCIÓN QUE ASIGNA LAS VARIABLES 
        this.sem= new Semaphore(1);           //INICIALIZACIÓN DEL SEMÁFORO
        this.mutex =new ReentrantLock();      //INICIALIZACIÓN DEL MUTEX
        this.tipoSinc=algoritmo;              //AQUÍ SE ASIGNA EL TIPO DE SINCRONIZACIÓN ESCOGIDO POR EL USUARIO
        this.nombre=n;
        this.rc=rc;
    }
   
    
    //MÉTODO RUN
    public void run(){
        while(vivo==1){  //MIENTRAS EL THREAD ESTE VIVO, ESTE CICLO SE EJECUTARÁ
           
           switch(tipoSinc){   //DEPENDIENDO DEL TIPO DE SINCRONIZACIÓN ES LA HERRAMIENTA QUE SE UTILIZARÁ 
               
               
               case 5:      //CON RECURSO COMPARTIDO SIN SINCRONIZACIÓN
                  
                  //SECCIÓN CRÍTICA 
                  rc.setValoresRC(this.x.getMiX(),this.y.getMiY() );
                  rc.operaX(a);
                  rc.operaY(b);
                  this.x.setMiX(rc.getRCMiX().getMiX());
                  this.y.setMiY(rc.getRCMiY().getMiY());
                  try{
                  Thread.sleep((int)(Math.random()*10));  //SLEEP RANDOM
                  }catch(InterruptedException e){}
                 
                if (getY().getMiY() == LimMaxY)
                    b = -b;
                if (getY().getMiY() == LimMinY)
                    b=-b;
                if (getX().getMiX() == LimMaxX)
                    a = -a;
                if (getX().getMiX() == LimMinX)
                    a = -a;
                
                  panel.repaint();    //MAPEO DEL PANEL PARA MOSTRAR EL MOVIMIENTO
                 
                   
                   break;
                   
                   
                   
                   
               case 0:          //CON RECURSO COMPARTIDO CON SINCRONIZACIÓN (PERO NO SE ESPERAN)
                   
                   rc.acquire();
                  //SECCIÓN CRÍTICA 
                  rc.setValoresRC(this.x.getMiX(),this.y.getMiY() );
                  rc.operaX(a);
                  rc.operaY(b);
                  this.x.setMiX(rc.getRCMiX().getMiX());
                  this.y.setMiY(rc.getRCMiY().getMiY());
                 this.rc.release();
                 
                  try{
                  Thread.sleep((int)(Math.random()*10));  //SLEEP RANDOM
                  
                     }catch(InterruptedException e){}
                 panel.repaint();    //MAPEO DEL PANEL PARA MOSTRAR EL MOVIMIENTO
                if (getY().getMiY() == LimMaxY)
                    b = -b;
                if (getY().getMiY() == LimMinY)
                    b=-b;
                if (getX().getMiX() == LimMaxX)
                    a = -a;
                if (getX().getMiX() == LimMinX)
                    a = -a;
                
                  
                 
                   
                   break;
                   
                   
                   
                   
                   
                   
                   
               case 1:          //Con SEMAFOROS  (se esperan)
                  
                  rc.acquire(); //EL SEMAFORO ES ADQUIRIDO DENTRO DEL RC
                  //SECCIÓN CRÍTICA 
                  rc.setValoresRC(this.x.getMiX(),this.y.getMiY() ); //SE LE SETEAN AL RC X Y Y
                  rc.operaX(a); //DENTRO DEL RC SE OPERA X
                  rc.operaY(b); //DENTRO DEL RC SE OPERA Y
                  this.x.setMiX(rc.getRCMiX().getMiX());  //SE OBTIENE DE NUEVO LAS ACTUALIZADAS
                  this.y.setMiY(rc.getRCMiY().getMiY());
                 try{
                  //SLEEP RANDOM
                  Thread.sleep((int)(Math.random()*4)); //SLEEP ALEATORIO (DENTRO)
                     }catch(InterruptedException e){}
                 //TERMINA SECCIÓN CRÍTICA
                 this.rc.release(); //EL SEMÁFORO ES LIBERADO
                panel.repaint();    //MAPEO DEL PANEL PARA MOSTRAR EL MOVIMIENTO
                //LIMITES DE CONDICIÓN DE REBOTE
                if (getY().getMiY() == LimMaxY)  
                    b = -b;
                if (getY().getMiY() == LimMinY)
                    b=-b;
                if (getX().getMiX() == LimMaxX)
                    a = -a;
                if (getX().getMiX() == LimMinX)
                    a = -a;
                //MAPEO PARA ACTUALIZAR EL JFREECHART
               if(flag>=0){
                    flag--;
                }else{
                    if(alg==1){
                    guardarCoordenadas();
                    flag=5;
                    }
                }
                  
                        
                 break;
                   
                   
                   
                   
                   
                   
                   
                   
                   
               case 2:          //Con SEMAFOROS SE ESPERAN (Y EL TIEMPO DE ESPERA SE REDUCE)
                 rc.acquire();
                  //SECCIÓN CRÍTICA 
                  rc.setValoresRC(this.x.getMiX(),this.y.getMiY() );
                  rc.operaX(a);
                  rc.operaY(b);
                  this.x.setMiX(rc.getRCMiX().getMiX());
                  this.y.setMiY(rc.getRCMiY().getMiY());
                 try{
                    
                 
                  Thread.sleep((int)(Math.random()*10));
                     }catch(InterruptedException e){}
                 this.rc.release(); 
                 panel.repaint();    //MAPEO DEL PANEL PARA MOSTRAR EL MOVIMIENTO
                if (getY().getMiY() >= LimMaxY)
                    b = -b;
                if (getY().getMiY() <= LimMinY)
                    b=-b;
                if (getX().getMiX() >= LimMaxX)
                    a = -a;
                if (getX().getMiX() <= LimMinX)
                    a = -a;
                if(flag>=0){
                    flag--;
                }else{
                    if(alg!=2){
                    guardarCoordenadas();
                    flag=5;
                    }
                }
                   break;
                   
                   
                   
                   
                   
                   
                   
                   
                        
               case 3:          //CON VARIABLES DE CONDICIÓN
                   
                   break;    
                   
                   
                   
                   
                   
                   
                   
                   
               case 4:          //CON MONITORES
                   
                   break;
              
               
               
               case 6:        //BARRIERS
           
           break; 
           }
           
           
            
            
        }
    }
   
    public void guardarCoordenadas(){
        misCoordenadas.gC(getX().getMiX(),getY().getMiY());
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
        this.flag=5;
        this.misCoordenadas=new Coordenadas();
    }
    
    // EN CASO DE QUERER DETENER EL PROCESO
    public void detener(){
        this.vivo=0;
    }
    
    public int getGraphMin(){
        return this.misCoordenadas.getContador();
    }
    
    public double getGX(int x){
        return this.misCoordenadas.getPosicionX(x);
    }
    public double getGY(int y){
        return this.misCoordenadas.getPosicionY(y);
    }
    public String getNombre(){
        return this.nombre;
    }
    
    public void setAlg(){
        this.alg=1;
    }
    public void setMov(int mov){
        switch(mov){
            case 1:
                this.a=1;
                this.b=1;
                break;
            case 2:
                this.a=2;
                this.b=2;
                break;
            case 4:
                this.a=4;
                this.b=4;
                break;
            case 6:
                this.a=6;
                this.b=6;
                break;
            case 8:
                this.a=8;
                this.b=8;
                break;
            case 10:
                this.a=10;
                this.b=10;
                        
                break;
            
        }
       // System.out.println("si cambie variables we");
    }
    
}
