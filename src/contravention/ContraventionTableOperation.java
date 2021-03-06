/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contravention;

import SysCentral.BDConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author nzima-ivan
 */
public class ContraventionTableOperation extends BDConnection {
    
    private final String table = "contraventions";
    private PreparedStatement pst;
    
    public ContraventionTableOperation(boolean isTest) throws SQLException, ClassNotFoundException{
        // appel de la classe mère pour la connexin à la base de données
        super(isTest);
    }
    
    public void prepareStatement(String requete) throws SQLException {
        pst = super.con.prepareStatement(requete);
    }
   
    
    
    public ResultSet selectionAll () throws SQLException{
        // sekection de la ligne de al table Amande dont le matricule correspond à celui entré
        String requete = "SELECT * FROM "+table;
        
         //definition de de la requete préparée grace au connecteur de la classe mère
        this.prepareStatement(requete);
        
        // recupération du résultat de la requete
        ResultSet result = pst.executeQuery();
        
        System.out.println("selection réussie");
        return result;
    }
    
    
    
}
