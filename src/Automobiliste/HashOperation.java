/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automobiliste;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author nzima-ivan
 */
public class HashOperation {
    
    
    public static String hashText(String text) throws NoSuchAlgorithmException{
        
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(text.getBytes());
        byte[] hash = md.digest();
        
        // convertion du tableau de bits en un format hexedecimal
        StringBuilder sb = new StringBuilder();
        for(byte b: hash){
            sb.append(Integer.toString((b & 0xff) + 0x100, 16 ).substring(1));
        }
        
        return sb.toString();
    }
}
