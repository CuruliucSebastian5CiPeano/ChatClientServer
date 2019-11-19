/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclient;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc15
 */
public class Ricevi implements Runnable {
    private UDPClient client;
    private GUI gui;
    
    public Ricevi(UDPClient client) {
        this.client = client;
        new Thread(this).start();
    }
    
    public void addGui(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void run() {
        try {
            while(true) {
                String text = client.receive();        
                if (gui != null) {
                    gui.addMessage(text);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Ricevi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
