//LUIS JESUS REYES VELAZQUEZ 201732135
package JDrones;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
import java.util.concurrent.*; 
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class JDrones extends javax.swing.JFrame {   //DECLARACIÓN DE VARIABLES GENERALES
    private DibujaDrones panel;        //PANEL PARA DÓNDE SE EJECUTARÁ TODO
    private int veces;                 //VARIABLE PARA VER CUANTOS DRONES SE DIBUJARÁN
    private MiX x;                     //Estás 4 variables se usarán para crear los drones
    private MiY y;                     
    private int x1=0;                  
    private int y1=0;
    private NumDron n;                  //Contador que nos dice cuantos drones se deben dibujar (se utilizará en JFreeChart
    private BufferedImage ima;          //Imagen que se proyectará en el panel 
    private double[] datosX;            //variables que se usarán para la graficación de JFreeChart
    private double[] datosY;
    private Lock s;                     //mutex
    private ArrayList<Dron> drones;     //Arreglo de drones 
    private int numDrones = 0;          //Contador entero de drones 
    private int alg;                    //Variable que nos dice que algoritmo se escogió para la sincronización
   
    
    public JDrones(int algoritmo) {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents(); //Inicialización de componentes
        this.alg=algoritmo; //Asignación del algoritmo elegido por el usuario a la variable alg
        if(algoritmo==0){   //Depende del algoritmo que se elija entrará al switch para imprimir el letrero
            
            System.out.println("No hay algoritmo, por lo tanto no habrá sincronización");
        }else{
            switch(algoritmo){
                case 1:
                    System.out.println("El algoritmo elegido es Semaforos ");
                    
                   break;
                   case 2:
                    System.out.println("El algoritmo elegido es Variables de Condición ");
                    
                   break;
                   case 3:
                    System.out.println("El algoritmo elegido es Monitores");
                    
                   break;
                   case 4:
                    System.out.println("El algoritmo elegido es Barriers ");
                    
                   break;
                
            }
        }
        
        try{     //Se abre la imagen del mapa para mostrarlo en el panel                                    
            ima = ImageIO.read(new File("C:\\Users\\draco\\Documents\\NetBeansProjects\\Drones\\DronesFinal\\src\\JDrones\\mapa.png"));
        }catch(IOException e){e.printStackTrace();}
        datosX = new double[1000];  //Inicialización de variables
        datosY = new double[1000];  
        drones = new ArrayList<Dron>();
        panel= new DibujaDrones(ima);
        panel.setBounds(0, 0, 1200, 700); //Se le setean las medidas al panel
        add(panel); //Se agrega el panel a nuestro frame
        veces=1; //Se le setea un 1 a nuestra variable veces debido a que inicialmente se dibujará un solo frame
        s=new ReentrantLock();  //inicialización del mutex
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemNuevo = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        jMenu3.setText("jMenu3");

        jMenu4.setText("jMenu4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Proyecto Concurrente");

        jMenu1.setText("File");

        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Drones");

        jMenuItemNuevo.setText("Nuevo");
        jMenuItemNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNuevoActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemNuevo);

        jMenuBar1.add(jMenu2);

        jMenu5.setText("Sincronización");

        jMenuItem5.setText("Semaforos");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem5);

        jMenuItem4.setText("Variables de Condición");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem4);

        jMenuItem3.setText("Monitores");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem3);

        jMenuItem2.setText("Barriers");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem2);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1209, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 712, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
      System.exit(0);   //boton salir
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItemNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNuevoActionPerformed
        drones.clear();   //SE LIMPIAN LOS DRONES PARA RESETEARLE LAS POSICIONES
        //System.out.println("Valor de veces: "+veces); 
        if(veces==1){    //DEPENDE DE EL NÚMERO DE VECES QUE SE HAYA PRESIONADO EL BOTÓN VECES, ES EL NÚMERO DE DRONES QUE SE MANEJARÁN
           // System.out.println("Entro a 1");
        x = new MiX(1);  //ASIGNACIÓN DE LAS VARIABLES DEL PRIMER DRON
        y = new MiY(1);
        n = new NumDron(); //SE CREA EL PRIMER DRON EN LA VARIABLE N (TEMPORAL)
        drones.add(new Dron(x,y,panel,700,0,1200,0,s,this.alg)); //SE AGREGA AL ARREGLO DRON
        numDrones += 1; //SE AUMENTA EL NÚMERO DE DRONES A DIBUJAR
        panel.setN(numDrones); //SE LE PASA AL PANEL CUANTOS DRONES TIENE QUE DIBUJAR
        panel.CreaCirculo();  //SE CREA UN CIRCULO DENTRO DEL ARREGLO DE CIRCULOS DEL DRON, 1 DRON CORRESPONDE A 1 CIRCULO.
        panel.setDrones(drones);  //SE LE PASA EL ARREGLO DE DRONES, ESTE CASO SOLO CONTENDRÁ 1 DRON
        for(Dron d: drones){  //PARA CADA DRON SE INICIALIZA EL HILO QUE CAMBIARÁ DE POSICIÓN LA BOLITA (MOVIMIENTO)
            int flag = 1;
            
                if(!d.isAlive())
                try{
                    d.start();
                }catch(IllegalThreadStateException ex){ex.printStackTrace();}
           
            flag += 1;
        }
        x1=700;   
        //System.out.println(numDrones);
        veces++;  //SE AUMENTA LA VARIABLE VECES DEBIDO A QUE LA SIGUIENTE VEZ TENDRÁ QUE PINTAR 2 DRONES EN LUGAR DE SOLO 1
       
        
        }else{  //ESTE ES EL CASO DE QUE NO SEA SOLO 1 DRON, SI NO VARIOS.
            drones.clear();   //SE LIMPIA EL ARREGLO DE DRONES.
            //System.out.println("Entro a 2");
            
            switch(veces){   //SWITCH QUE SETEARÁ LOS N DRONES (SIEMPRE PARES)
                case 2:
                    
                    drones.add(new Dron(new MiX(1),new MiY(1),panel,700,0,600,0,s,this.alg));        //DRON 1 
                    drones.add(new Dron(new MiX(601),new MiY(1),panel,700,0,1200,600,s,this.alg));   //DRON 2
                    numDrones =2;  //SE SETEA EL NUMERO DE DRONES A 2
                    panel.setN(numDrones);  //SE ACTUALIZA AL DIBUJADRONES CUANTOS DRONES DIBUJARÁ
                    panel.setNVeces(2);    //SE ACTUALIZA EL NÚMERO DE VECES QUE SE PRESIONÓ EL BOTÓN
                    panel.CreaCirculo();   //LE DICE AL BIUJADRONES QUE  AHORA CREARÁ 2 CIRCULOS
                    panel.setDrones(drones);  //SE LE PASA EL ARREGLO DE DRONES 
                    break;
                    
                case 4:
                    drones.add(new Dron(new MiX(1),new MiY(1),panel,350,0,600,0,s,this.alg));           //DRON 1 
                    drones.add(new Dron(new MiX(1),new MiY(351),panel,700,350,600,0,s,this.alg));//DRON 2
                    drones.add(new Dron(new MiX(601),new MiY(1),panel,350,0,1200,600,s,this.alg));//DRON 3
                    drones.add(new Dron(new MiX(601),new MiY(351),panel,700,350,1200,600,s,this.alg));//DRON 4
                    numDrones =4;   //SE SETEA EL NUMERO DE DRONES A 4
                    panel.setN(numDrones); //SE ACTUALIZA AL DIBUJADRONES CUANTOS DRONES DIBUJARÁ
                    panel.setNVeces(4);  //SE ACTUALIZA EL NÚMERO DE VECES QUE SE PRESIONÓ EL BOTÓN
                    panel.CreaCirculo();  //LE DICE AL BIUJADRONES QUE  AHORA CREARÁ 4 CIRCULOS
                    panel.CreaCirculo(); 
                    panel.setDrones(drones); //SE LE PASA EL ARREGLO DE DRONES 
                    break;
                    
                case 6:
                    drones.add(new Dron(new MiX(1),new MiY(1),panel,233,0,600,0,s,this.alg));
                    drones.add(new Dron(new MiX(1),new MiY(234),panel,466,233,600,0,s,this.alg));
                    drones.add(new Dron(new MiX(1),new MiY(467),panel,700,466,600,0,s,this.alg));
                    drones.add(new Dron(new MiX(601),new MiY(1),panel,233,0,1200,600,s,this.alg));
                    drones.add(new Dron(new MiX(601),new MiY(234),panel,466,233,1200,600,s,this.alg));
                    drones.add(new Dron(new MiX(601),new MiY(467),panel,700,466,1200,600,s,this.alg));
                    
                    numDrones =6 ; //SE SETEA EL NUMERO DE DRONES A 6 
                    panel.setN(numDrones); //SE ACTUALIZA AL DIBUJADRONES CUANTOS DRONES DIBUJARÁ
                    panel.setNVeces(6);
                    panel.CreaCirculo(); //LE DICE AL BIUJADRONES QUE  AHORA CREARÁ 6 CIRCULOS
                    panel.CreaCirculo();
                    panel.setDrones(drones); //SE LE PASA EL ARREGLO DE DRONES 
                    break;
                    
                case 8:
                    drones.add(new Dron(new MiX(1),new MiY(1),panel, 175,0,600,0 ,s,this.alg ));
                    drones.add(new Dron(new MiX(1),new MiY(176),panel, 350,175,600,0 ,s,this.alg));
                    drones.add(new Dron(new MiX(1),new MiY(351),panel, 525,350,600,0 ,s,this.alg));
                    drones.add(new Dron(new MiX(1),new MiY(526),panel, 700,525,600,0,s,this.alg));
                    drones.add(new Dron(new MiX(601),new MiY(1),panel, 175,0,1200,600,s,this.alg));
                    drones.add(new Dron(new MiX(601),new MiY(176),panel, 350,175,1200,600 ,s ,this.alg));
                    drones.add(new Dron(new MiX(601),new MiY(351),panel, 525,350,1200,600,s ,this.alg));
                    drones.add(new Dron(new MiX(601),new MiY(526),panel, 700,525,1200,600 ,s ,this.alg));
                    numDrones =8;  //SE SETEA EL NUMERO DE DRONES A 8
                    panel.setN(numDrones); //SE ACTUALIZA AL DIBUJADRONES CUANTOS DRONES DIBUJARÁ
                    panel.setNVeces(8);
                    panel.CreaCirculo();  //LE DICE AL BIUJADRONES QUE  AHORA CREARÁ 8 CIRCULOS
                    panel.CreaCirculo();
                    panel.setDrones(drones); //SE LE PASA EL ARREGLO DE DRONES 
                    break; 
                
                case 10:
                    drones.add(new Dron(new MiX(1),new MiY(1),panel, 140,0,600,0,s ,this.alg));
                    drones.add(new Dron(new MiX(1),new MiY(141),panel, 280,140,600,0 ,s,this.alg));
                    drones.add(new Dron(new MiX(1),new MiY(281),panel, 420,280,600,0 ,s,this.alg));
                    drones.add(new Dron(new MiX(1),new MiY(421),panel, 560,420,600,0 ,s,this.alg));
                    drones.add(new Dron(new MiX(1),new MiY(561),panel, 700,560,600,0 ,s,this.alg));
                    drones.add(new Dron(new MiX(601),new MiY(1),panel, 140,0,1200,600 ,s,this.alg));
                    drones.add(new Dron(new MiX(601),new MiY(141),panel, 280,140,1200,600,s,this.alg ));
                    drones.add(new Dron(new MiX(601),new MiY(281),panel, 420,280,1200,600,s ,this.alg));
                    drones.add(new Dron(new MiX(601),new MiY(421),panel, 560,420,1200,600 ,s,this.alg));
                    drones.add(new Dron(new MiX(601),new MiY(561),panel, 700,560,1200,600,s ,this.alg));
                    
                    
                    numDrones = 10; //SE SETEA EL NUMERO DE DRONES A 10
                    panel.setN(numDrones); //SE ACTUALIZA AL DIBUJADRONES CUANTOS DRONES DIBUJARÁ
                    panel.setNVeces(10);
                    panel.CreaCirculo(); //LE DICE AL BIUJADRONES QUE  AHORA CREARÁ 10 CIRCULOS
                    panel.CreaCirculo();
                    panel.setDrones(drones); //SE LE PASA EL ARREGLO DE DRONES 
                    jMenuItemNuevo.setEnabled(false); //SE DESHABILITÁ EL BOTÓN, DEBIDO A QUE SOLO ES PARA UN MÁXIMO DE 10 DRONES
                    break;
            }
            veces++; //SE AUMENTA EL CONTADOR DE DRONES 2 VECES, DEBIDO A QUE ESTÁS
            veces++;
            for(Dron d: drones){   //SE INICIAN LOS DRONES, NO IMPORTA SI SON 2, 4,6,8 O 10
            int flag = 1;
            
                if(!d.isAlive())
                try{
                    d.start();
                }catch(IllegalThreadStateException ex){ex.printStackTrace();}
           
            flag += 1;
        }
            
            
            
        }
       
        
    }//GEN-LAST:event_jMenuItemNuevoActionPerformed

    
    //SE SELECCIONÁ LA SINCRONIZACIÓN POR SEMAFOROS
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        drones.clear();
        this.setVisible(false);
        new JDrones(1).setVisible(true);;
        
    }//GEN-LAST:event_jMenuItem5ActionPerformed
   //SE SELECCIONA LA SINCRONIZACIÓN POR VARIABLE DE CONDICIÓN
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        drones.clear();
        this.setVisible(false);
        new JDrones(2).setVisible(true);;
    }//GEN-LAST:event_jMenuItem4ActionPerformed
   //SE SELECCIONA LA SINCRONIZACIÓN POR MONITORES 
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        drones.clear();
        this.setVisible(false);
        new JDrones(3).setVisible(true);;
    }//GEN-LAST:event_jMenuItem3ActionPerformed
   // SE SELECCIONA LA SINCRONIZACIÓN POR BARRIERS
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        drones.clear();
        this.setVisible(false);
        new JDrones(4).setVisible(true);;
    }//GEN-LAST:event_jMenuItem2ActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JDrones(0).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItemNuevo;
    // End of variables declaration//GEN-END:variables
}

  
