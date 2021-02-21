/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysCentral;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author nzima-ivan
 */
public class AmandeTableOperations extends BDConnection {
    
    private final String table = "amande";
    private PreparedStatement pst;
    
    public AmandeTableOperations(boolean isTest) throws SQLException, ClassNotFoundException{
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
     * @param matricule matricule of car
     * @param liste_infraction 
     * @throws SQLException
     */
    public void insertion (String matricule, String liste_infraction) throws SQLException{
        String requete = "INSERT INTO "+table+ " (matricule, date_debut, liste_infraction) VALUES (?,?,?)";
        
        //definition de de la requete préparée grace au connecteur de la classe mère
        this.prepareStatement(requete);
       
        // insertion du matricule en BD
        pst.setString(1, (String)matricule);
        
        // récupération de la date systeme
        Date currentDate = new Date();
        java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
        
        // insertion de la date d'édition de l'amande : date systeme courrente
        pst.setDate(2, sqlDate);
        
        // insertion de la liste d'infraction sous forme de chaine de caractères séparés par des virgules (ex 01,05,07)
        pst.setString(3, (String)liste_infraction);
        
        pst.executeUpdate();
        System.out.println("insertion effectuée avec succes");
    }
    
    
    public ResultSet selectionAll () throws SQLException{
        // selection de la ligne de la table Amande dont le matricule correspond à celui entré
        String requete = "SELECT * FROM "+table;
        
         //definition de de la requete préparée grace au connecteur de la classe mère
        this.prepareStatement(requete);
        
        // recupération du résultat de la requete
        ResultSet result = pst.executeQuery();
        
        
        return result;
    }
    
    public ResultSet selection (String matricule) throws SQLException{
        // selection de la ligne de la table Amande dont le matricule correspond à celui entré
        String requete = "SELECT * FROM "+table+" WHERE matricule="+matricule;
        
         //definition de de la requete préparée grace au connecteur de la classe mère
        this.prepareStatement(requete);
        
        // recupération du résultat de la requete
        ResultSet result = pst.executeQuery();
        
        
        return result;
    }
    
    
    public void setDatePaimentAmande(Date dateFin ,String matricule ) throws SQLException {
        
        java.sql.Date sqlDate = new java.sql.Date(dateFin.getTime());
        
        String requete = "UPDATE "+table+" SET date_fin = " +sqlDate+ " WHERE matricule ="+matricule;
        this.prepareStatement(requete);
        pst.executeUpdate();
        
    }
    
}
