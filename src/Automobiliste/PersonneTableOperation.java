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
import java.util.Date;

/**
 *
 * @author nzima-ivan
 */
public class PersonneTableOperation extends BDConnection {
    
    private final String table = "personne";
    private PreparedStatement pst;
    
    public PersonneTableOperation(boolean isTest) throws SQLException, ClassNotFoundException {
        super(isTest);
    }
    
    public void prepareStatement(String requete) throws SQLException {
        pst = super.con.prepareStatement(requete);
    }
    
    public ResultSet getPersonne(int code_personne) throws SQLException{
        String requete = "SELECT * FROM "+ table +" WHERE code_personne = \""+code_personne+"\"";
        prepareStatement(requete);
                
        ResultSet result = pst.executeQuery();
        
        return result;
    }
    
    
    public ResultSet getCode(String nom) throws SQLException{
        String requete = "SELECT code_personne FROM "+ table +" WHERE nom = \""+nom+"\"";
        prepareStatement(requete);
                
        ResultSet result = pst.executeQuery();
        
        return result;
    }
    
    public void insertion (String nom, String prenom, String numCNI, Date date, String profession, int tel, String mail, String adresse, String pass ) throws SQLException{
        String requete = "INSERT INTO "+table+ " (nom, prenom, num_cni, date_naissance, profession, tel, adresse_mail, adresse, mot_de_passe) VALUES (?,?,?,?,?,?,?,?,?)";
        
        //definition de de la requete préparée grace au connecteur de la classe mère
        this.prepareStatement(requete);
       
        pst.setString(1, (String)nom);
        pst.setString(2, (String)prenom);
        pst.setString(3, (String)numCNI);
        pst.setDate(4, (java.sql.Date) date);
        pst.setString(5, (String)profession);
        pst.setInt(6, (int)tel);
        pst.setString(7, (String)mail);
        pst.setString(8, (String)adresse);
        pst.setString(9, (String)pass);

        
        // insertion de la liste d'infraction sous forme de chaine de caractères séparés par des virgules (ex 01,05,07)
//        pst.setString(3, (String)liste_infraction);
        
        pst.executeUpdate();
        System.out.println("insertion effectuée avec succes");
        
    }
    
    
}
