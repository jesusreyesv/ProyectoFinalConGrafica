
package JDrones;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RecursoCompartido {
    private MiX x;
    private MiY y;
    private Semaphore sem;
    private CyclicBarrier cBfinal,cBini;
    private int cont;
    private int numDrones;
    
    RecursoCompartido(){
        this.x=new MiX(0);
        this.y=new MiY(0);
        this.sem=new Semaphore(1,true);
        this.cont=0;
        this.numDrones=0;
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
 
    public void setBarrier(CyclicBarrier cf,CyclicBarrier ci){
        this.cBfinal=cf;
        this.cBini=ci;
    }
    
    public void sumarCont(){
        this.cont++;
    }
    public void setNumDrones(int nD){
        this.numDrones=nD;
    }
    public void resetFin(){
        try {
            cBini.await();
            
                    
                    } catch (InterruptedException ex) {
            Logger.getLogger(RecursoCompartido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BrokenBarrierException ex) {
            Logger.getLogger(RecursoCompartido.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if((cBini.getNumberWaiting()==numDrones)){
            this.cBfinal.reset();
        }
        
       
    }
    
    public void resetIni(){
     try {
            cBfinal.await();
            
                    
                    } catch (InterruptedException ex) {
            Logger.getLogger(RecursoCompartido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BrokenBarrierException ex) {
            Logger.getLogger(RecursoCompartido.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if((cBfinal.getNumberWaiting()==numDrones)){
            this.cBini.reset();
        }   
    }
}
