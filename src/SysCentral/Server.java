/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysCentral;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author nzima-ivan
 */
public class Server {
    
    
    private ServerSocket sCServ ;
    private Socket sockCl;
    private int port;
    
    
    public void start(int port) throws IOException{
        this.port = port;
        this.sCServ = new ServerSocket(port);
        System.out.println("serveur lancé sur le port : "+port+" ....");
    }
    
    public void acceptConnection() throws IOException{
        sockCl = this.sCServ.accept();
        System.out.println("connexion au client flash établie :) ");
    }
    
    
    public String receiveMsg() throws IOException{
        InputStream in = sockCl.getInputStream();
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader buff = new BufferedReader(isr);
        String msg = buff.readLine();
        
        return msg;
    }
    
}
