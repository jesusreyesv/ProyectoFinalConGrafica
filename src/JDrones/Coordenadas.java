//LUIS JESUS REYES VELAZQUEZ 201732135  
package JDrones;


public class Coordenadas {
    private double[] coordenadasX;
    private double[] coordenadasY;
    private int contador;
    
    public Coordenadas(){
        this.coordenadasX=new double[100000];
        this.coordenadasY=new double[100000];
        this.contador=0;
    }
    
    
    
   public void gC(int x, int y){
       
       try{
       coordenadasX[contador]=x;
       coordenadasY[contador]=y;
       contador++;
       }catch(Exception e ){}
   }
   
   public int getContador(){
       return this.contador;
   }
   
   public double getPosicionX(int x){
       return coordenadasX[x];
   }
   
   public double getPosicionY(int y){
       return coordenadasY[y];
   }
}
