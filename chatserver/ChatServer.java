/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver;

import java.io.IOException;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc15
 */
public class ChatServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UDPServer server = new UDPServer(7000);
            System.out.println(server.getClients().size());
            
            while(true) {
                server.receive();
            }
            
        } catch (SocketException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    

