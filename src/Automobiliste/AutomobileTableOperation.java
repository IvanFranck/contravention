/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Automobiliste;

import SysCentral.BDConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author nzima-ivan
 */
public class AutomobileTableOperation extends BDConnection {
    
    private final String table = "automobile";
    private PreparedStatement pst;
    private final int DEFAULT_PROPRIO = 1;
    
    public AutomobileTableOperation(boolean isTest) throws SQLException, ClassNotFoundException {
        super(isTest);
    }
    
    public void prepareStatement(String requete) throws SQLException {
        pst = super.con.prepareStatement(requete);
    }
    
    public void update (String matricule, int code_proprio) throws SQLException{
        String requete = "UPDATE "+table+ " SET code_personne = "+code_proprio+" WHERE matricule = '"+matricule+"'";

        //definition de de la requete préparée grace au connecteur de la classe mère
        this.prepareStatement(requete);
        
        pst.executeUpdate();
        System.out.println("mise à jour de l'automobiliste éffectuée avec succes");
    }
    
    public void insertion (String matricule, int codeProprio) throws SQLException{
        String requete = "INSERT INTO "+table+ " (matricule, code_personne) VALUES (?,?)";
        
        //definition de de la requete préparée grace au connecteur de la classe mère
        this.prepareStatement(requete);
       
        // insertion du matricule dans la table
        pst.setString(1, (String)matricule);
      
        // insertion du code du proprio de l'auto
        pst.setInt(2, (int)codeProprio);
        
        pst.executeUpdate();
        System.out.println("insertion effectuée avec succes");
    }
    
    public ResultSet selection (String matricule) throws SQLException{
        // sekection de la ligne de al table Amande dont le matricule correspond à celui entré
        String requete = "SELECT * FROM "+table+" WHERE matricule = \""+matricule+"\"";
        
         //definition de de la requete préparée grace au connecteur de la classe mère
        this.prepareStatement(requete);
        
        // recupération du résultat de la requete
        ResultSet result = pst.executeQuery();
        
        
        return result;
    }
}
