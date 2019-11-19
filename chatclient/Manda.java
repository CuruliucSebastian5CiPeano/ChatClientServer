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
public class Manda implements Runnable {
    private UDPClient client;
    private String text;

    public Manda(UDPClient client, String text) {
        this.client = client;
        this.text = text;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            client.send(text);
        } catch (IOException ex) {
            Logger.getLogger(Manda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
