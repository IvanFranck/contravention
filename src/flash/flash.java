/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flash;

import java.io.IOException;
import org.json.simple.JSONObject;

/**
 *
 * @author nzima-ivan
 */
public class flash {
    
    /**
     *
     * @param args
     */
    public static void main(String[] args){
        
        JsonFlashClient jsonCl = new JsonFlashClient();
        RandomInfoFlashProvider random = new RandomInfoFlashProvider();
        JSONObject flashInfo = random.sendJson();
        try {
            jsonCl.connect("127.0.0.1", 3000);
            jsonCl.sendJson(flashInfo);
        } catch (IOException ex) {
            System.err.println("erreur de connexion :" + ex);
        }
        
        
    }
}
