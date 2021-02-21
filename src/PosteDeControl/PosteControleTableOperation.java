/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PosteDeControl;

import SysCentral.BDConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author nzima-ivan
 */
public class PosteControleTableOperation extends BDConnection{
 
    private final String table = "poste_controle";
    private PreparedStatement pst;
    private final static int CODE_SC = 1;  
    
    public PosteControleTableOperation(boolean isTest) throws SQLException, ClassNotFoundException{
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
     * @param nomCheft nom du chef du poste de control
     * @param lieu 
     * @throws SQLException
     */
    public void insertion (String nomChef, String lieu) throws SQLException{
        String requete = "INSERT INTO "+table+ " (code_sc, nom_responsable, lieu) VALUES (?,?,?)";
        
        //definition de de la requete préparée grace au connecteur de la classe mère
        this.prepareStatement(requete);
       
        // insertion du code du sys centrale en commande de ce poste de controle
        pst.setInt(1, CODE_SC);
        
       
        // insertion du nom du chef de poste
        pst.setString(2, (String)nomChef);
        
        // insertion du lieu où est implaté le poste de control
        pst.setString(3, (String)lieu);
        
        pst.executeUpdate();
        System.out.println("Poste de control créé avec succes !");
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
    
    public ResultSet findPoste(String nomChef) throws SQLException{
        // selection de la ligne de tous les tuples de la table Poste_control
        String requete = "SELECT code_pc FROM "+table+" WHERE nom_responsable = \""+nomChef+"\"";
         
         //definition de de la requete préparée grace au connecteur de la classe mère
        this.prepareStatement(requete);
        
        // recupération du résultat de la requete
        ResultSet result = pst.executeQuery();
        
        
        return result;
    }
}
