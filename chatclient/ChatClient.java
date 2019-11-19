/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclient;

import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author pc15
 */
public class ChatClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UDPClient client = new UDPClient(6969, "10.103.0.38", 9090);
            Ricevi listener = new Ricevi(client);
            
            String username = JOptionPane.showInputDialog("Inserire Username");
            if(username == null) {
                System.exit(0);
            }
            while(username.length()<4) {
                username = JOptionPane.showInputDialog("L'Username deve contenere almeno 5 lettere");
                if(username == null) {
                    System.exit(0);
                }
            }
            
            listener.addGui(new GUI(client,username));
            
        } catch (SocketException ex) {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    
