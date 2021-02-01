/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Automobiliste;

import SysCentral.BDConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;


/**
 *
 * @author nzima-ivan
 */
public class AutomobileTableOperation extends BDConnection {
    
    private final String table = "automobile";
    private PreparedStatement pst;
    private final int DEFAULT_PROPRIO = 1;
    
    public AutomobileTableOperation(boolean isTest) throws SQLException {
        super(isTest);
    }
    
    public void prepareStatement(String requete) throws SQLException {
        pst = super.con.prepareStatement(requete);
    }
    
    public void insertion (String matricule) throws SQLException{
        String requete = "INSERT INTO "+table+ " (matricule, code_personne) VALUES (?,?)";
        
        //definition de de la requete préparée grace au connecteur de la classe mère
        this.prepareStatement(requete);
       
        // insertion du matricule dans la table
        pst.setString(1, (String)matricule);
      
        // insertion du code du proprio de l'auto
        pst.setInt(2, DEFAULT_PROPRIO);
        
        pst.executeUpdate();
        System.out.println("insertion effectuée avec succes");
    }
}
