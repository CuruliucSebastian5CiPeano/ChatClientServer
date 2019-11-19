/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 *
 * @author pc15
 */
public class UDPServer {
    
    private ArrayList<Client> clients = new ArrayList<Client>();
    private DatagramSocket datagramSocket;
 
    public UDPServer(int port) throws SocketException {
        this.datagramSocket = new DatagramSocket(port);
    }
    
    public void receive() throws IOException {
        byte[] buffer = new byte[100];
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
        this.datagramSocket.receive(dp);
        String text = new String(dp.getData(), "ISO-8859-1");
        System.out.println(text);
        new Manda(addClient(dp), text, this);
        
    }
    
    public void send(int index, String text) throws UnsupportedEncodingException, IOException {
        byte[] buffer = new byte[100];
        buffer = text.getBytes("UTF-8");
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length, clients.get(index).address, clients.get(index).port);
        this.datagramSocket.send(dp);
    }
    
    private int addClient(DatagramPacket dp) {
        Client c = new Client(dp.getPort(),dp.getAddress());
        for (int i=0; i<clients.size(); i++) {
            if(c.address.equals(clients.get(i).address) && c.port == clients.get(i).port) {
                return i;
            }
        }
        clients.add(c);
        System.out.println(clients.size());
        System.out.println(c.toString());
        return (clients.size()-1);
    }

    public ArrayList<Client> getClients() {
        return clients;
    }
}
