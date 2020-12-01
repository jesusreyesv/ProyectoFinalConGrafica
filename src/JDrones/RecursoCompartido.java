
package JDrones;

import java.util.concurrent.Semaphore;


public class RecursoCompartido {
    private MiX x;
    private MiY y;
    private Semaphore sem;
    
    RecursoCompartido(){
        this.x=new MiX(0);
        this.y=new MiY(0);
        this.sem=new Semaphore(1,true);
    }
    
    public void setValoresRC(int x, int y){
      
        this.x.setMiX(x);
        this.y.setMiY(y);
    }
     
    public MiX getRCMiX(){
        return x;
    }
    public MiY getRCMiY(){
        return y;
        
    }
    public void operaX(int a){
        this.x.setMiX(this.x.getMiX()+a);
    }
    public void operaY(int b){
        this.y.setMiY(this.y.getMiY()+b);
    }
    public void release(){
        this.sem.release();
    }
    public void acquire(){
         try{
         
        this.sem.acquire();
       }catch(Exception e){}
    }
    
    public int getQueueLength(){
        return this.sem.getQueueLength();
    }
    
}
