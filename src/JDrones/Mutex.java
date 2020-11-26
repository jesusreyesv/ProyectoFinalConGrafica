// LUIS JESUS REYES VELAZQUEZ 201732135
package JDrones;
public class Mutex{
    private boolean pass; //ESTADO DEL OBJETO
    
    Mutex(){
        this.pass = false;
    }
    
    public synchronized void lock(){
       while(pass){
           try{
               wait();
           }catch(InterruptedException e){
               System.out.println("ERROR" + e.toString());
           }
       }
       
    }
    
    public synchronized void unlock(){
        this.pass = false;
        notify();
    }
    
    public boolean trylock(){       //CASO DE FALLAS
        if(this.pass == false){
            return true;
        }
        else{
            try{
                wait();
            }catch(Exception e){}
            finally{
                unlock();
                return false;
            }
        }
        
    }
    
    public synchronized boolean getpass(){
        return pass;
    }
    
    public synchronized void setpass(boolean p){
        this.pass = p;
    }
}
