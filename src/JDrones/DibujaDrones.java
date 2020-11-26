package JDrones;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.ArrayList;
public class DibujaDrones extends JPanel{
    //DECLARACIÓN DE NUESTROS ARREGLOS, LA IMAGEN QUE IRÁ DEBAJO DEL PANEL ETC
    private int n;
    private BufferedImage ima;
    private ArrayList<Ellipse2D> circulos;
    private ArrayList<Dron> drones;
    private int nveces;
    
    //NUESTRO CONSTRUCTOR
    DibujaDrones(BufferedImage ima){
        setBackground(Color.GREEN);  //EL FONDO DEL PANEL SE SETEARÁ VERDE
        this.ima=ima; //SE LE PASA LA IMAGEN QUE PROYECTARÁ
        
        this.n=n;
        circulos= new ArrayList<Ellipse2D>(); //ARREGLO DEL NÚMERO DE CIRCULOS QUE SE IRÁN DIBUJANDO 
        nveces=0; //EL NÚMERO DE VECES QUE SE PRESIONÓ EL BOTÓN (SIRVE PARA VER CUANTAS LINEAS DIBUJARÁ)
    }
    public void paintComponent(Graphics g){
        int f = 0;  //FLAGS
        int f2 = 0;
        super.paintComponent(g);  //SE PINTAN TODOS LOS COMPONENTES
        Graphics2D g2 = (Graphics2D)g;  //SE DECLARÁ EL OBJETO GRAFICO QUE NOS SERVIRÁ PARA CONVERTIRLO EN BOLAS O LINEAS
        g2.drawImage(ima, 0, 0, this); 
        g2.setColor(Color.black); //SE LE SETEA EL COLOR NEGRO
        
        //aqui debe de ir el switch depende de donde se van a dibujar las cuadrículas
        switch(nveces){
            
            case 2:
                g2.fill(new Rectangle2D.Double(600, 0, 2, 700)); //LINEA DIVISORA A LA MITAD (VERTICAL)
                break;
            case 4:
                g2.fill(new Rectangle2D.Double(600, 0, 2, 700)); //LINEA DIVISORA A LA MITAD (VERTICAL)
                g2.fill(new Rectangle2D.Double(0, 350, 1200, 2));//LINEA DIVISORA A  MITAD (HORIZONTAL)
                
              break;
            case 6:
                g2.fill(new Rectangle2D.Double(600, 0, 2, 700)); //LINEA DIVISORA A LA MITAD (VERTICAL)
                g2.fill(new Rectangle2D.Double(0, 233, 1200, 2));//LINEA DIVISORA A  1ER TERCIO (HORIZONTAL)
                g2.fill(new Rectangle2D.Double(0, 466, 1200, 2));//LINEA DIVISORA A  2DO TERCIO (HORIZONTAL)
                break;
            case 8:
                g2.fill(new Rectangle2D.Double(600, 0, 2, 700)); //LINEA DIVISORA A LA MITAD (VERTICAL)
                g2.fill(new Rectangle2D.Double(0, 175, 1200, 2));//LINEA DIVISORA A  1ER CUARTO (HORIZONTAL)
                g2.fill(new Rectangle2D.Double(0, 350, 1200, 2));//LINEA DIVISORA A  2DO CUARTO (HORIZONTAL)
                g2.fill(new Rectangle2D.Double(0, 525, 1200, 2));//LINEA DIVISORA A  3ER CUARTO (HORIZONTAL)
                break;
            case 10:
                g2.fill(new Rectangle2D.Double(600, 0, 2, 700)); //LINEA DIVISORA A LA MITAD (VERTICAL)
                g2.fill(new Rectangle2D.Double(0, 140, 1200, 2));//LINEA DIVISORA A  1ER QUINTO (HORIZONTAL)
                g2.fill(new Rectangle2D.Double(0, 280, 1200, 2));//LINEA DIVISORA A  2DO QUINTO (HORIZONTAL)
                g2.fill(new Rectangle2D.Double(0, 420, 1200, 2));//LINEA DIVISORA A  3ER QUINTO (HORIZONTAL)
                g2.fill(new Rectangle2D.Double(0, 560, 1200, 2));//LINEA DIVISORA A 4TO QUINTO
                break;
        }
        
        
        
        if( getN() > 0){ //DEPENDIENDO DEL NUMERO DE DRONES PASADOS EL EL NUMERO DE DRONES QUE SE DIBUJARÁ
        for(Dron d:drones){ //FOR EACH PARA DIBUJAR DRONES
            f2 = 0;
            for(Ellipse2D c:circulos){
                if(f == f2){
                    c.setFrame(d.getX().getMiX(), d.getY().getMiY(), 20, 20);
                    g2.fill((Ellipse2D)c);
                    
                }
                f2++;
            }
            f++;
        
        }
        }
        g2.setColor(Color.white); 
        
    }
    public void CreaCirculo(){  //SE AGREGA A LA LISTA DE CIRCULOS MÁS CIRCULOS (CADA DRON CORRESPONDE A UN CIRCULO)
        circulos.add(new Ellipse2D.Double());
    }
    
   //SETTERS Y GETTERS
    public ArrayList<Dron> getDrones() {  
        return drones; 
    }

   
    public void setDrones(ArrayList<Dron> drones) {
        this.drones = drones;
    }

   
    public int getN() {
        return n;
    }

    
    public void setN(int n) {
        this.n = n;
    }
    
    
    public void setNVeces(int x){
        this.nveces=x;
    }
    
    public int getNVeces(){
        return nveces;
    }
}
