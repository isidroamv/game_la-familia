/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.*;

/**
 * @author Sephi
 */
public class LaFamia extends MIDlet {
    private Display display;
    private Presentacion presentacion ;
    private Campo_Juego juego;
    private EscenaFinal ef ;

    public void startApp() {
        if(display==null){
            iniciarMIDLET();
        }
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
        finalizarMIDLET();
    }

    private void finalizarMIDLET() {
        this.notifyDestroyed();
    }

    private void iniciarMIDLET() {
        display = Display.getDisplay(this);
 
        do{
            presentacion = new Presentacion();
            while(presentacion.jugando==true){
            display.setCurrent(presentacion);
            }
            juego = new Campo_Juego();
            while(juego.terminado!=true){
                display.setCurrent(juego);
            }
            juego.stop(); 
            ef = new EscenaFinal(true/*juego.ganado*/);
            while(ef.estado==0){
                display.setCurrent(ef);
            }
            presentacion.jugando = true;
            juego.terminado = false;
        }while(ef.estado==1);
        this.finalizarMIDLET();
        
        
        
        
        
    }
}
