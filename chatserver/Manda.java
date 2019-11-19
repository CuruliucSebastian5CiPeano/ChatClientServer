/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc15
 */
public class Manda implements Runnable {
    private UDPServer server;
    private String text;
    private int index;

    public Manda(int index, String text, UDPServer server) {
        this.text = text;
        this.index = index;
        this.server = server;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            for(int i=0; i<server.getClients().size(); i++) {
                if(i != index) {
                    server.send(i, text);
                } else {
                    server.send(index, "#messaggio inviato ->");
                    server.send(index, text);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Manda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
