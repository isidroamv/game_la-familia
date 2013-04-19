/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.Sprite;

/**
 * @author Sephi
 */
public class Presentacion extends GameCanvas implements CommandListener, Runnable {
    private Image fondo,jugador,acusado;
    private int toque=0;
    private Sprite sJugador,sAcusado;
    public boolean jugando;
    
    
    /**
     * constructor
     */
    public Presentacion() {
        super(true); 
        this.flushGraphics();
        this.setFullScreenMode(true);
        jugando = true;
        crearImagenes();
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
//        g.drawString("Sample Text", 0, 0, Graphics.TOP | Graphics.LEFT);
        g.setColor(255, 255, 255);
        dibujarFondo(g);
        dibujarJugador(g);
        dibujarAcusado(g);        
       
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
        switch(this.toque){
            case 0:
                break;
            case 1:
                this.sJugador.setFrame(1);
                break;
            case 2:
                this.sAcusado.setFrame(1);
                this.sJugador.setFrame(2);
                break;
            case 3:
                this.jugando = false;
                break;
            default:
                break;
        }
        this.toque++;
        this.repaint();
        
        
//        if(this.frameJugador<3){
//            this.sJugador.setFrame(this.frameJugador);
//            this.frameJugador++;
//            this.repaint();
//        }else{
//            jugando = false;
//        }
//        
//        if(this.frameAcusado<2){
//            this.sAcusado.setFrame(this.frameJugador);
//            this.frameAcusado++;
//            this.repaint();
//        }
    }

    /**
     * Called when action should be handled
     */
    public void commandAction(Command command, Displayable displayable) {
    }

    private void crearImagenes() {
        try{
            fondo = Image.createImage("/cosina.png");
//            fondo = this.ReescalaImagenOpaca(fondo, this.getHeight(), this.getHeight());
            
            jugador = Image.createImage("/agustina-caras2.png");
//            jugador = this.resizeImage(jugador);
            sJugador = new Sprite(jugador,(jugador.getWidth()/3),jugador.getHeight());
            
            acusado = Image.createImage("/carlos-caras.png");
            sAcusado = new Sprite(acusado,(acusado.getWidth()/2),acusado.getHeight());
            
        }catch(Exception e){
            System.out.println("No se cargo La imagen Correctamente");
            e.printStackTrace();}
//        super.getGraphics().drawImage(imagen, 0, 0, Graphics.TOP|Graphics.LEFT);
//        repaint();
        
    }
    
    private void dibujarFondo(Graphics g) {
        g.drawImage(fondo, 0, 0, Graphics.TOP|Graphics.LEFT);
    }
    
    protected Image ReescalaImagenOpaca(Image imagenOriginal, int anchoNuevo, int altoNuevo){
        //Inicializaciones
        int anchoOrigen = imagenOriginal.getWidth();
        int altoOrigen = imagenOriginal.getHeight();
        Image imagenFinal = Image.createImage(anchoNuevo, altoNuevo);
        Graphics g = imagenFinal.getGraphics();
        //Bucles que tratan el reescalado
        for(int y=0; y<altoNuevo; y++){
            for(int x=0; x<anchoNuevo; x++){
                g.setClip(x, y, 1, 1);
                int xAux = x * anchoOrigen / anchoNuevo;
                int yAux = y * altoOrigen / altoNuevo;
                g.drawImage(imagenOriginal, x-xAux, y-yAux, Graphics.LEFT | Graphics.TOP);
            }
        }
        return Image.createImage(imagenFinal);
    }

    private void dibujarJugador(Graphics g) {
//        g.drawImage((Image)sJugador, 0, this.getHeight()-80,0);
        this.sJugador.setPosition(0, this.getHeight()-120);        
        this.sJugador.paint(g);
    }

    public void run() {
        
    }

    private void dibujarAcusado(Graphics g) {
        this.sAcusado.setPosition(0, 0);
        this.sAcusado.paint(g);
    }
    
    


    
}
