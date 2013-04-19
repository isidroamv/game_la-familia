/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Random;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.Sprite;

/**
 * @author Sephi
 */
public class Campo_Juego extends GameCanvas implements CommandListener,Runnable {
    private Image acusado,fondo,cadenaPuntos,cadenaGolpes;
    private  int ANCHO_ACUSADO;
    private  int ALTO_ACUSADO ;
    private Random random = new Random();
    private int x=0,y=0;
    public Thread hilo;
    private int puntos,golpes;
    private Sprite numerosPuntos,numerosGolpes;
    public boolean terminado=false;
    public boolean ganado;
    
    /**
     * constructor
     */
    public Campo_Juego() {
        super(true);      
        cargarImagenes();
        this.start();     
        try {
            // Set up this canvas to listen to command events
            setCommandListener(this);
            // Add the Exit command
            addCommand(new Command("Exit", Command.EXIT, 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * paint
     */
    public void paint(Graphics g) {
        this.setFullScreenMode(true);
        crearFondo(g);
        establecerLetreros(g);
         establecerAcusado(g);
        
    }

    /**
     * Called when a key is pressed.
     */
    protected void keyPressed(int keyCode) {
    }

    /**
     * Called when a key is released.
     */
    protected void keyReleased(int keyCode) {
    }

    /**
     * Called when a key is repeated (held down).
     */
    protected void keyRepeated(int keyCode) {
    }

    /**
     * Called when the pointer is dragged.
     */
    protected void pointerDragged(int x, int y) {
    }

    /**
     * Called when the pointer is pressed.
     */
    protected void pointerPressed(int x, int y) {
 
    }

    /**
     * Called when the pointer is released.
     */
    protected void pointerReleased(int x, int y) {
        
        if(acusadoPresionado(x,y)==true){           
            golpes++;
            this.numerosGolpes.nextFrame();
        }
        if(this.puntos>19 && golpes<11){
            ganado = false;
            terminado = true;
            
        }
        
        if(puntos<19 && golpes>10){
            ganado = true;
            terminado = true;
        }
        
        this.numerosPuntos.nextFrame();
        this.puntos++;
        this.repaint();
    }

    /**
     * Called when action should be handled
     */
    public void commandAction(Command command, Displayable displayable) {
    }

    private void cargarImagenes() {
        try{
            this.acusado = Image.createImage("/carlos-mini.png");
            this.fondo   = Image.createImage("/cosina.png");
            
            this.ANCHO_ACUSADO = this.acusado.getWidth();
            this.ALTO_ACUSADO = this.acusado.getHeight();
            
            this.cadenaGolpes = Image.createImage("/cadena-golpes.png");
            this.cadenaPuntos = Image.createImage("/cadena-puntos.png");
            
            this.numerosPuntos = new Sprite(Image.createImage("/cadena-numeros.png"),Image.createImage("/cadena-numeros.png").getWidth(),Image.createImage("/cadena-numeros.png").getHeight()/21);
            this.numerosPuntos.setPosition(0,105);
            this.numerosGolpes = new Sprite(Image.createImage("/cadena-numeros.png"),Image.createImage("/cadena-numeros.png").getWidth(),Image.createImage("/cadena-numeros.png").getHeight()/21);
            this.numerosGolpes.setPosition(0,235);
        }catch(Exception e){e.printStackTrace(); System.out.println("no se cargo la imagen =(");
        }
    }

    private void crearFondo(Graphics g) {
        g.drawImage(fondo, 0, 0,  Graphics.TOP | Graphics.LEFT);
    }

    public void run() {
        while(true){
            try{
                Thread.sleep(1300);
                x = random.nextInt(this.getWidth()-40)+20;
                y = random.nextInt(this.getHeight()-40);
//                y=0;x=0;
                repaint();
            }catch(Exception e){}
        }
    }
    
    public void start(){
        if(hilo==null){
            hilo = new Thread(this);
            hilo.start();
        }
    }
    
    public void stop(){
        hilo = null;
    }
    

    private void establecerAcusado(Graphics g) {
        g.drawImage(this.acusado, x, y,  Graphics.TOP | Graphics.LEFT);      
    }

    private boolean acusadoPresionado(int px, int py) {
        
        if(px>x && px<x+this.ANCHO_ACUSADO && py>y && py<y+this.ALTO_ACUSADO){
            return true;
        }
        return false;
    }

    private void establecerLetreros(Graphics g) {
        g.drawImage(this.cadenaPuntos, 0, 0, Graphics.TOP | Graphics.LEFT);
        g.drawImage(this.cadenaGolpes, 0, 140, Graphics.TOP | Graphics.LEFT);
        this.numerosGolpes.paint(g);
        this.numerosPuntos.paint(g);
    }

}
