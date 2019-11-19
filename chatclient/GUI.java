/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclient;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author pc15
 */
public class GUI extends JFrame implements ActionListener, KeyListener {
    
    private String username;
    private UDPClient client;
    
    public GUI(UDPClient client, String username) {
        this.username = username;
        this.client = client;
        inizializeComponents();
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void inizializeComponents() {
        this.setLayout(new GridBagLayout());
        
        
        this.JPanelLeft = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.ipadx = 100;
        gbc.ipady = 450;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0,0,0,0);
        this.add(this.JPanelLeft,gbc);
        
        this.JPanelOutput = new JPanel();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.ipadx = 400;
        gbc.ipady = 425;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0,0,0,0);
        this.add(this.JPanelOutput,gbc);
        
        this.JPanelInput = new JPanel();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.ipadx = 400;
        gbc.ipady = 25;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0,0,0,0);
        this.add(this.JPanelInput,gbc);
        
        inizializeJPanelLeft();
        inizializeJPanelOutput();
        inizializeJPanelInput();
        
        // Action Listener
        this.JButtonSend.addActionListener(this);
        this.JTextFieldInput.addKeyListener(this);
    }

    // Graphical Variable declaration
    private GridBagConstraints gbc = new GridBagConstraints();
    private JPanel JPanelLeft;
    private JPanel JPanelOutput;
    private JPanel JPanelInput;
    private JTextArea JTextAreaChat;
    private JScrollPane JScrollPaneChat;
    private JTextField JTextFieldInput;
    private JButton JButtonSend;

    // Inizialize Components
    private void inizializeJPanelLeft() {
       this.JPanelLeft.setBackground( new Color(50,90,140) );
    }

    private void inizializeJPanelOutput() {
        this.JPanelOutput.setLayout(new GridLayout(1,1));
        this.JPanelOutput.setBackground( new Color(10,10,100) );
        
        this.JTextAreaChat = new JTextArea();
        this.JTextAreaChat.setEditable(false);
        this.JScrollPaneChat = new JScrollPane(this.JTextAreaChat);
        
        this.JPanelOutput.add(JScrollPaneChat);
    }

    private void inizializeJPanelInput() {
        this.JPanelInput.setLayout(new GridLayout());
        this.JPanelInput.setBackground( new Color(10,100,10) );
        
        this.JTextFieldInput = new JTextField();
        this.JButtonSend = new JButton("Send");

        this.JPanelInput.add(this.JTextFieldInput);
        this.JPanelInput.add(this.JButtonSend);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            default: break;
            case "Send" : 
                if (!this.JTextFieldInput.getText().equals("")) {
                    new Manda(client, "[" + this.username + "] : " + this.JTextFieldInput.getText());
                    this.JTextFieldInput.setText("");
                }
                break;
        }
    }

    void addMessage(String text) {
        this.JTextAreaChat.append(text + "\n");
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!this.JTextFieldInput.getText().equals("")) {
                new Manda(client, "[" + this.username + "] : " + this.JTextFieldInput.getText());
                this.JTextFieldInput.setText("");
            }
        }
    }
}
