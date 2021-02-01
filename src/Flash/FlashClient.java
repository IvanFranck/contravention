/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Flash;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author nzima-ivan
 */
public class FlashClient {
    
    private String host;
    private Socket socket;
    private int port;
    private final String DEFAULT_HOST = "127.0.0.1";
    
    public void connect(int port) throws IOException{
        this.port = port;
        socket = new Socket(this.DEFAULT_HOST, port);
        System.out.println("client flash connecté....");
               
    }
    
    public void  connect(String host, int port) throws IOException{
        this.port = port;
        this.host = host;
        socket = new Socket(host, port) ;
        System.out.println("client flash conecté....");
    }
    // JSONObject jsonMsg
    public void sendMessage(String m) throws IOException{
        OutputStream out = socket.getOutputStream();
        PrintStream ps = new PrintStream(out);
        
        // convertion du message json en String
//        String msg = jsonMsg.toString();
        
        // envoie du message
        ps.println(m);
        
        }
}
