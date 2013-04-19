/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.Sprite;

/**
 * @author Sephi
 */
public class EscenaFinal extends Canvas implements CommandListener {
   private Image fondo,jugador,cadenaGanar,cadenaPerder,cadenaSalir,cadenaRegresar;
   private Sprite sJugador;
   private boolean ganar;
   public int estado=0;//0 mantener - 1 Regresar - 2 Salir
    /**
     * constructor
     */
    public EscenaFinal(boolean ganar) {
        this.setFullScreenMode(true);
        this.ganar = ganar;
        cargarImagenes();
//        this.repaint();
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
        dibujarImagenes(g);
        if(ganar==true){
            g.drawImage(this.cadenaGanar, this.getWidth()/2, this.getHeight()/2-60,Graphics.TOP | Graphics.LEFT);
        }else{
            g.drawImage(this.cadenaPerder, this.getWidth()/2, this.getHeight()/2-60,Graphics.TOP | Graphics.LEFT);
        }
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
        if(x>0 && x<this.cadenaRegresar.getWidth() && y>0 && y<this.cadenaRegresar.getHeight()){
            this.estado = 1;
            System.out.println("Regresar");
        }
        if(x>0 && x<this.cadenaSalir.getWidth() && y>this.getHeight()-100 && y<(this.getHeight()-100)+this.cadenaSalir.getHeight()){
            estado = 2;
            System.out.println("Salir");
        }
        this.estado =0;
    }

    /**
     * Called when action should be handled
     */
    public void commandAction(Command command, Displayable displayable) {
    }

    private void cargarImagenes() {
        try{
            this.fondo = Image.createImage("/cosina.png");
            this.cadenaGanar = Image.createImage("/cadena-ganaste.png");
            this.cadenaPerder = Image.createImage("/cadena-perdiste.png");
            this.jugador = Image.createImage("/agustina-caras2.png");
            this.sJugador = new Sprite(this.jugador,jugador.getWidth()/3,jugador.getHeight());
            this.cadenaRegresar = Image.createImage("/cadena-regresar.png");
            this.cadenaSalir = Image.createImage("/cadena-salir.png");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void dibujarImagenes(Graphics g) {
        if(ganar==true){
            this.sJugador.setFrame(0);
        }else{
            sJugador.setFrame(1);
        }
        g.drawImage(fondo, 0, 0, Graphics.TOP | Graphics.LEFT);
        sJugador.setPosition(0, this.getHeight()/2);
        this.sJugador.paint(g);
        g.drawImage(this.cadenaRegresar, 0, 0, Graphics.TOP | Graphics.LEFT);
        g.drawImage(this.cadenaSalir, 0, this.getHeight()-100, Graphics.TOP | Graphics.LEFT);
        
        
    }
}
