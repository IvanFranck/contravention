/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contravention;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author nzima-ivan
 */
public class jsonServer {
    
    
    public static void main(String [] args) throws IOException, ClassNotFoundException{
        
        ServerSocket serSock = new ServerSocket(3000);
        Socket socketClient = serSock.accept();
        
        
        System.out.println("connexion établie");
        
        InputStream in = socketClient.getInputStream();
        InputStreamReader isr = new InputStreamReader(in);
//        ObjectInputStream i = new ObjectInputStream(in);
        BufferedReader buff = new BufferedReader(isr);
//        Object obj = i.readObject();
        
        String msg = buff.readLine();
        JSONParser parser = new JSONParser();
        try {
            JSONObject json = (JSONObject) parser.parse(msg);
            System.out.println("client sends : " + json + "\n" + "vitesse : "+json.get("vitesse"));
            
        } catch (ParseException ex) {
            System.err.println("erreur de convertion");
        }
        
        
//         JSONObject js = new JSONObject((Map) i.readObject());
        
//        JSONObject json = new JSONObject(msg);
    }
    
}
