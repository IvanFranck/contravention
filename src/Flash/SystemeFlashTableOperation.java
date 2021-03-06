/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Flash;

import SysCentral.BDConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author nzima-ivan
 */
public class SystemeFlashTableOperation extends BDConnection{
    
    private final String table = "systeme_flash";
    private PreparedStatement pst;
    
    public SystemeFlashTableOperation(boolean isTest) throws SQLException, ClassNotFoundException{
        // appel de la classe mère pour la connexin à la base de données
        super(isTest);
    }
    
    public void prepareStatement(String requete) throws SQLException {
        pst = super.con.prepareStatement(requete);
    }
   
    /**
     *insert information in amande table
     * 
     * 
     * @param posteControl code du poste de control qui utilise flash
     * @throws SQLException
     */
    public void insertion (int posteControl) throws SQLException{
        String requete = "INSERT INTO "+table+ " (code_pc) VALUES (?)";
        
        //definition de de la requete préparée grace au connecteur de la classe mère
        this.prepareStatement(requete);
       
        // insertion du code du poste de controle en commande de ce flash
        pst.setInt(1, posteControl);
        
        pst.executeUpdate();
        System.out.println("Système de flash créé avec succes !");
    }
    
     public ResultSet selectionAll () throws SQLException{
        // selection de la ligne de tous les tuples de la table Poste_control
        String requete = "SELECT * FROM "+table;
        
         //definition de de la requete préparée grace au connecteur de la classe mère
        this.prepareStatement(requete);
        
        // recupération du résultat de la requete
        ResultSet result = pst.executeQuery();
        
        
        return result;
    }
    
}
