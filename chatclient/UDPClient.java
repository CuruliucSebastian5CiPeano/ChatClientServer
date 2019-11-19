/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author pc15
 */
public class UDPClient {
    private DatagramSocket datagramSocket;
    private String ipServer;
    private int portServer;
    
    public UDPClient(int portClient, String ip, int portServer) throws SocketException{
        datagramSocket = new DatagramSocket(portClient);
        this.ipServer = ip;
        this.portServer = portServer;
    }
    
    public void send(String text) throws UnknownHostException, IOException{
        byte[] buffer = new byte[100];
        buffer = text.getBytes("UTF-8");
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(ipServer), this.portServer);
        this.datagramSocket.send(dp);
    }
    
    public String receive() throws IOException {
        byte[] buffer = new byte[100];
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
        this.datagramSocket.receive(dp);
        String text = new String(dp.getData(), "ISO-8859-1");
        return text;
    }
}
