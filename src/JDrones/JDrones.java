//LUIS JESUS REYES VELAZQUEZ 201732135
package JDrones;

import javax.swing.JFrame;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


import java.awt.Color;
import java.util.concurrent.CyclicBarrier;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class JDrones extends javax.swing.JFrame {   //DECLARACIÓN DE VARIABLES GENERALES
    private DibujaDrones panel;        //PANEL PARA DÓNDE SE EJECUTARÁ TODO
    private int veces;                 //VARIABLE PARA VER CUANTOS DRONES SE DIBUJARÁN
    private MiX x;                     //Estás 4 variables se usarán para crear los drones
    private MiY y;                     
    private int x1=0;                  
    private int y1=0;
    private RecursoCompartido RC;
    private Mapeo mapeo;
    private int mov=1;
    private CyclicBarrier CB,cfinal;           //BARRIER PARA SINCRONIZACIÓN POR BARRIERS
    private NumDron n;                  //Contador que nos dice cuantos drones se deben dibujar (se utilizará en JFreeChart
    private BufferedImage ima;          //Imagen que se proyectará en el panel 
    private double[] datosX;            //variables que se usarán para la graficación de JFreeChart
    private double[] datosY;
    private Lock s;                     //mutex
    private ArrayList<Dron> drones;     //Arreglo de drones 
    private int numDrones = 0;          //Contador entero de drones 
    private int alg;                    //Variable que nos dice que algoritmo se escogió para la sincronización
   
    
    public JDrones(int algoritmo) {
        
        this.RC=new RecursoCompartido();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents(); //Inicialización de componentes
        this.alg=algoritmo; //Asignación del algoritmo elegido por el usuario a la variable alg
        this.mapeo=new Mapeo(drones);
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
        veces=1; //Se le setea un 1 a nuestra variable veces debido a que inicialmente se dibujará un solo dron
       
        s=new ReentrantLock();  //inicialización del mutex
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        botonGraficar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemNuevo = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        jMenu3.setText("jMenu3");

        jMenu4.setText("jMenu4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Proyecto Concurrente");

        botonGraficar.setText("GRAFICAR JFREECHART");
        botonGraficar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGraficarActionPerformed(evt);
            }
        });

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

        jMenuItem6.setText("Sincronizado sin espera de tiempo");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem6);

        jMenuItem5.setText("Semaforos con espera");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem5);

        jMenuItem4.setText("Semaforos con espera acelerada");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem4);

        jMenuItem2.setText("BARRIER+MONITOR");
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botonGraficar)
                .addContainerGap(1037, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(730, Short.MAX_VALUE)
                .addComponent(botonGraficar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
      System.exit(0);   //boton salir
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItemNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNuevoActionPerformed
       this.CB=new CyclicBarrier(1);
        
        drones.clear();   //SE LIMPIAN LOS DRONES PARA RESETEARLE LAS POSICIONES
        //System.out.println("Valor de veces: "+veces); 
        if(veces==1){    //DEPENDE DE EL NÚMERO DE VECES QUE SE HAYA PRESIONADO EL BOTÓN VECES, ES EL NÚMERO DE DRONES QUE SE MANEJARÁN
           // System.out.println("Entro a 1");
        x = new MiX(1);  //ASIGNACIÓN DE LAS VARIABLES DEL PRIMER DRON
        y = new MiY(1);
        n = new NumDron(); //SE CREA EL PRIMER DRON EN LA VARIABLE N (TEMPORAL)
        drones.add(new Dron("1",x,y,panel,700,0,1200,0,CB,this.alg,RC)); //SE AGREGA AL ARREGLO DRON
        numDrones += 1; //SE AUMENTA EL NÚMERO DE DRONES A DIBUJAR
        panel.setN(numDrones); //SE LE PASA AL PANEL CUANTOS DRONES TIENE QUE DIBUJAR
        panel.CreaCirculo();  //SE CREA UN CIRCULO DENTRO DEL ARREGLO DE CIRCULOS DEL DRON, 1 DRON CORRESPONDE A 1 CIRCULO.
        panel.setDrones(drones);  //SE LE PASA EL ARREGLO DE DRONES, ESTE CASO SOLO CONTENDRÁ 1 DRON
        this.mapeo=new Mapeo(drones);
        for(Dron d: drones){  //PARA CADA DRON SE INICIALIZA EL HILO QUE CAMBIARÁ DE POSICIÓN LA BOLITA (MOVIMIENTO)
            int flag = 1;
            
                if(!d.isAlive())
                try{
                    d.start();
                    
                }catch(IllegalThreadStateException ex){ex.printStackTrace();}
           
            flag += 1;
        }
        comenzarMapeo();
        mov=1;
        x1=700;   
        //System.out.println(numDrones);
        veces++;  //SE AUMENTA LA VARIABLE VECES DEBIDO A QUE LA SIGUIENTE VEZ TENDRÁ QUE PINTAR 2 DRONES EN LUGAR DE SOLO 1
       
        
        }else{  //ESTE ES EL CASO DE QUE NO SEA SOLO 1 DRON, SI NO VARIOS.
            drones.clear();   //SE LIMPIA EL ARREGLO DE DRONES.
            //System.out.println("Entro a 2");
            
            switch(veces){   //SWITCH QUE SETEARÁ LOS N DRONES (SIEMPRE PARES)
                case 2:
                    this.CB=new CyclicBarrier(2);
                    drones.add(new Dron("1",new MiX(1),new MiY(1),panel,700,0,600,0,CB,this.alg,RC));        //DRON 1 
                    drones.add(new Dron("2",new MiX(601),new MiY(1),panel,700,0,1200,600,CB,this.alg,RC));   //DRON 2
                    numDrones =2;  //SE SETEA EL NUMERO DE DRONES A 2
                    panel.setN(numDrones);  //SE ACTUALIZA AL DIBUJADRONES CUANTOS DRONES DIBUJARÁ
                    panel.setNVeces(2);    //SE ACTUALIZA EL NÚMERO DE VECES QUE SE PRESIONÓ EL BOTÓN
                    panel.CreaCirculo();   //LE DICE AL BIUJADRONES QUE  AHORA CREARÁ 2 CIRCULOS
                    panel.setDrones(drones);  //SE LE PASA EL ARREGLO DE DRONES 
                    mov=2;
                    break;
                    
                case 4:
                    this.CB=new CyclicBarrier(4);
                    drones.add(new Dron("1",new MiX(1),new MiY(1),panel,350,0,600,0,CB,this.alg,RC));           //DRON 1 
                    drones.add(new Dron("2",new MiX(1),new MiY(351),panel,700,350,600,0,CB,this.alg,RC));//DRON 2
                    drones.add(new Dron("3",new MiX(601),new MiY(1),panel,350,0,1200,600,CB,this.alg,RC));//DRON 3
                    drones.add(new Dron("4",new MiX(601),new MiY(351),panel,700,350,1200,600,CB,this.alg,RC));//DRON 4
                    numDrones =4;   //SE SETEA EL NUMERO DE DRONES A 4
                    panel.setN(numDrones); //SE ACTUALIZA AL DIBUJADRONES CUANTOS DRONES DIBUJARÁ
                    panel.setNVeces(4);  //SE ACTUALIZA EL NÚMERO DE VECES QUE SE PRESIONÓ EL BOTÓN
                    panel.CreaCirculo();  //LE DICE AL BIUJADRONES QUE  AHORA CREARÁ 4 CIRCULOS
                    panel.CreaCirculo(); 
                    panel.setDrones(drones); //SE LE PASA EL ARREGLO DE DRONES 
                    mov=4;
                    break;
                    
                case 6:
                    this.CB=new CyclicBarrier(6);
                    drones.add(new Dron("1",new MiX(1),new MiY(1),panel,233,0,600,0,CB,this.alg,RC));
                    drones.add(new Dron("2",new MiX(1),new MiY(234),panel,466,233,600,0,CB,this.alg,RC));
                    drones.add(new Dron("3",new MiX(1),new MiY(467),panel,700,466,600,0,CB,this.alg,RC));
                    drones.add(new Dron("4",new MiX(601),new MiY(1),panel,233,0,1200,600,CB,this.alg,RC));
                    drones.add(new Dron("5",new MiX(601),new MiY(234),panel,466,233,1200,600,CB,this.alg,RC));
                    drones.add(new Dron("6",new MiX(601),new MiY(467),panel,700,466,1200,600,CB,this.alg,RC));
                    
                    numDrones =6 ; //SE SETEA EL NUMERO DE DRONES A 6 
                    panel.setN(numDrones); //SE ACTUALIZA AL DIBUJADRONES CUANTOS DRONES DIBUJARÁ
                    panel.setNVeces(6);
                    panel.CreaCirculo(); //LE DICE AL BIUJADRONES QUE  AHORA CREARÁ 6 CIRCULOS
                    panel.CreaCirculo();
                    panel.setDrones(drones); //SE LE PASA EL ARREGLO DE DRONES 
                    mov=6;
                    break;
                    
                case 8:
                   this.CB=new CyclicBarrier(8);
                    drones.add(new Dron("1",new MiX(1),new MiY(1),panel, 175,0,600,0 ,CB,this.alg,RC ));
                    drones.add(new Dron("2",new MiX(1),new MiY(176),panel, 350,175,600,0 ,CB,this.alg,RC));
                    drones.add(new Dron("3",new MiX(1),new MiY(351),panel, 525,350,600,0 ,CB,this.alg,RC));
                    drones.add(new Dron("4",new MiX(1),new MiY(526),panel, 700,525,600,0,CB,this.alg,RC));
                    drones.add(new Dron("5",new MiX(601),new MiY(1),panel, 175,0,1200,600,CB,this.alg,RC));
                    drones.add(new Dron("6",new MiX(601),new MiY(176),panel, 350,175,1200,600 ,CB ,this.alg,RC));
                    drones.add(new Dron("7",new MiX(601),new MiY(351),panel, 525,350,1200,600,CB ,this.alg,RC));
                    drones.add(new Dron("8",new MiX(601),new MiY(526),panel, 700,525,1200,600 ,CB ,this.alg,RC));
                    numDrones =8;  //SE SETEA EL NUMERO DE DRONES A 8
                    panel.setN(numDrones); //SE ACTUALIZA AL DIBUJADRONES CUANTOS DRONES DIBUJARÁ
                    panel.setNVeces(8);
                    panel.CreaCirculo();  //LE DICE AL BIUJADRONES QUE  AHORA CREARÁ 8 CIRCULOS
                    panel.CreaCirculo();
                    panel.setDrones(drones); //SE LE PASA EL ARREGLO DE DRONES 
                    mov=8;
                    break; 
                
                case 10:
                    this.CB=new CyclicBarrier(10);
                    drones.add(new Dron("1",new MiX(1),new MiY(1),panel, 140,0,600,0,CB,this.alg,RC));
                    drones.add(new Dron("2",new MiX(1),new MiY(141),panel, 280,140,600,0 ,CB,this.alg,RC));
                    drones.add(new Dron("3",new MiX(1),new MiY(281),panel, 420,280,600,0 ,CB,this.alg,RC));
                    drones.add(new Dron("4",new MiX(1),new MiY(421),panel, 560,420,600,0 ,CB,this.alg,RC));
                    drones.add(new Dron("5",new MiX(1),new MiY(561),panel, 700,560,600,0 ,CB,this.alg,RC));
                    drones.add(new Dron("6",new MiX(601),new MiY(1),panel, 140,0,1200,600 ,CB,this.alg,RC));
                    drones.add(new Dron("7",new MiX(601),new MiY(141),panel, 280,140,1200,600,CB,this.alg,RC ));
                    drones.add(new Dron("8",new MiX(601),new MiY(281),panel, 420,280,1200,600,CB ,this.alg,RC));
                    drones.add(new Dron("9",new MiX(601),new MiY(421),panel, 560,420,1200,600 ,CB,this.alg,RC));
                    drones.add(new Dron("10",new MiX(601),new MiY(561),panel, 700,560,1200,600,CB ,this.alg,RC));
                    
                    
                    numDrones = 10; //SE SETEA EL NUMERO DE DRONES A 10
                    panel.setN(numDrones); //SE ACTUALIZA AL DIBUJADRONES CUANTOS DRONES DIBUJARÁ
                    panel.setNVeces(10);
                    panel.CreaCirculo(); //LE DICE AL BIUJADRONES QUE  AHORA CREARÁ 10 CIRCULOS
                    panel.CreaCirculo();
                    panel.setDrones(drones); //SE LE PASA EL ARREGLO DE DRONES 
                    jMenuItemNuevo.setEnabled(false); //SE DESHABILITÁ EL BOTÓN, DEBIDO A QUE SOLO ES PARA UN MÁXIMO DE 10 DRONES
                    mov=10;
                    break;
            }
            veces++; //SE AUMENTA EL CONTADOR DE DRONES 2 VECES, DEBIDO A QUE ESTÁS
            veces++;
            this.mapeo=new Mapeo(drones);
            for(Dron d: drones){   //SE INICIAN LOS DRONES, NO IMPORTA SI SON 2, 4,6,8 O 10
            int flag = 1;
            
                if(!d.isAlive())
                try{
                    if(this.alg==2&&this.alg==6){
                    d.setMov(mov);
                    }
                    d.start();
                }catch(IllegalThreadStateException ex){ex.printStackTrace();}
           
            flag += 1;
        }
            comenzarMapeo();
            
            
            
        }
       
       
    }//GEN-LAST:event_jMenuItemNuevoActionPerformed

    public void comenzarMapeo(){
//        if(this.alg==0){
                mapeo.start();
//        }else{
//            for(Dron d:drones){
//            d.setAlg();
//            }
//        }
        
    }
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

  // SE SELECCIONA LA SINCRONIZACIÓN POR BARRIERS
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        drones.clear();
        this.setVisible(false);
        new JDrones(6).setVisible(true);;
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void botonGraficarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGraficarActionPerformed
        //BUTTON DE GRAFICAR.
       //DECLARACIÓN DEL OBJETO DONDE SE GUARDARAN TODAS LAS SERIES DE DATOS
        XYSeriesCollection dataset = new XYSeriesCollection();  
        XYSeries temporal; //TEMPORAL PARA GRAFICAR LA ULTIMA POSICIÓN
        int menor=10000; //DECLARACIÓN DEL LA MÁXIMA DURACIÓN DEL GRAFICO
        for(Dron d:drones){  //SI EL GRAFICO SERÁ MÁS CORTO QUE EL MÁXIMO, SE RECORTA
            if(d.getGraphMin()<=menor){
                menor=d.getGraphMin(); 
            }
        }
        
        System.out.println("Este es el contador graph:"+menor);
        for(Dron d:drones){ //PARA TODOS LOS DATOS GUARDADOS DENTRO DE NUESTRA
                            //MI COORDENADAS DE LOS DRONES, SE GUARDARÁ EN LA COLECCION
            temporal=new XYSeries(d.getNombre());
            for(int j=0;j<(menor-7);j++){
                temporal.add(d.getGX(j),d.getGY(j));  //SE GUARDA LA ÚLTIMA POSICIÓN (DESDE 0 HASTA MINIMO)
               
            }
            
            dataset.addSeries(temporal); //SE AGREGA A LA SERIE A LA QUE CORRESPONDE (DEPENDE DEL DRON).
        }
        //FOR EACH QUE SIRVE PARA GRAFICAR COMO ITEM DIFERENTE LA ÚLTIMA POSICIÓN REGISTRADA.
        for(Dron d:drones){
            temporal=new XYSeries("Dron "+d.getNombre()+" termino");
             temporal.add(d.getGX(menor-6),d.getGY(menor-6));
            dataset.addSeries(temporal);
        }
        
        //CREACIÓN DE NUESTRO PANMEL JFREECHART ADEMÁS DE LA CONFIGURACIÓN.
        JFreeChart xylineChart= ChartFactory.createXYLineChart("Gráfica jFreeChart XY (Reyes Velazquez)", "Posición X", "Posición Y", dataset,PlotOrientation.VERTICAL,true,true,false);
        //XYPLOT QUE SE UTILIZA PARA CONFIGURAR DE MANERA VISUAL NUESTRO GRAFICO
        XYPlot plot= xylineChart.getXYPlot();
        // EL RENDEDER ES EL ENCARGADO DE GRAFICAR TODO EN EL PANEL.
        XYLineAndShapeRenderer renderer= new XYLineAndShapeRenderer();
        
        //SWITCH PARA DIFERENCIAR QUIENES SON LAS TERMINACIONES DE CADA DRON.
        switch(veces){
                case 2:  //UNA BOLA
                renderer.setSeriesPaint(1,Color.BLACK);
                break;
                case 4: //DOS BOLAS
                renderer.setSeriesPaint(2,Color.BLACK);
                renderer.setSeriesPaint(3,Color.BLACK);
                break;
                case 6: //CUATRO BOLAS
                renderer.setSeriesPaint(4,Color.BLACK);
                renderer.setSeriesPaint(5,Color.BLACK);
                renderer.setSeriesPaint(6,Color.BLACK);
                renderer.setSeriesPaint(7,Color.BLACK);
                break;
                case 8: //SEIS BOLAS
                renderer.setSeriesPaint(6,Color.BLACK);
                renderer.setSeriesPaint(7,Color.BLACK);
                renderer.setSeriesPaint(8,Color.BLACK);
                renderer.setSeriesPaint(9,Color.BLACK);
                renderer.setSeriesPaint(10,Color.BLACK);
                renderer.setSeriesPaint(11,Color.BLACK);
                break;
                case 10: //OCHO BOLAS BOLAS
                renderer.setSeriesPaint(8,Color.BLACK);
                renderer.setSeriesPaint(9,Color.BLACK);
                renderer.setSeriesPaint(10,Color.BLACK);
                renderer.setSeriesPaint(11,Color.BLACK);
                renderer.setSeriesPaint(12,Color.BLACK);
                renderer.setSeriesPaint(13,Color.BLACK);
                renderer.setSeriesPaint(14,Color.BLACK);
                renderer.setSeriesPaint(15,Color.BLACK);
                break;
                
                case 12: //OCHO BOLAS BOLAS
                renderer.setSeriesPaint(10,Color.BLACK);
                renderer.setSeriesPaint(11,Color.BLACK);
                renderer.setSeriesPaint(12,Color.BLACK);
                renderer.setSeriesPaint(13,Color.BLACK);
                renderer.setSeriesPaint(14,Color.BLACK);
                renderer.setSeriesPaint(15,Color.BLACK);
                renderer.setSeriesPaint(16,Color.BLACK);
                renderer.setSeriesPaint(17,Color.BLACK);
                renderer.setSeriesPaint(18,Color.BLACK);
                renderer.setSeriesPaint(19,Color.BLACK);
                break;
        }
        renderer.setBaseLinesVisible(false); //NO SE LE PONE LINEAS POR PROBLEMA VISUAL
        renderer.setBaseItemLabelsVisible(false); //TAMPOCO SE LE PONE ETIQUETAS
        //SE RENDERIZA EL OBJETO QUE GRAFICARA TODO
        plot.setRenderer(renderer);
        //SE CREA EL PANEL CHART
        ChartPanel panel= new ChartPanel(xylineChart);
        JFrame ventana = new JFrame("Grafica"); //SE CREA UNA NUEVA VENTANA DONDE SE MOSTRARA EL PANEL CHART
        ventana.setVisible(true);  //SE VUELVE VISIBLE
        ventana.setSize(1600,900); //SE LE DAN LAS BOUNDS
        ventana.add(panel);// SE AGREGA EL PANEL A LA VENTANA CREADA.
    }//GEN-LAST:event_botonGraficarActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        drones.clear();
        this.setVisible(false);
        new JDrones(0).setVisible(true);;
        
        
    }//GEN-LAST:event_jMenuItem6ActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JDrones(5).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonGraficar;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItemNuevo;
    // End of variables declaration//GEN-END:variables
}

  
