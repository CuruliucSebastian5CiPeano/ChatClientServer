/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver;

import java.net.InetAddress;

/**
 *
 * @author pc15
 */
public class Client {
    
    public int port;
    public InetAddress address;

    public Client(int port, InetAddress address) {
        this.port = port;
        this.address = address;
    }
}
