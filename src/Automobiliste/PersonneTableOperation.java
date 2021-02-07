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
public class PersonneTableOperation extends BDConnection {
    
    private final String table = "personne";
    private PreparedStatement pst;
    
    public PersonneTableOperation(boolean isTest) throws SQLException, ClassNotFoundException {
        super(isTest);
    }
    
    public void prepareStatement(String requete) throws SQLException {
        pst = super.con.prepareStatement(requete);
    }
    
    public ResultSet selectionPass(int code_personne) throws SQLException{
        String requete = "SELECT * FROM "+ table +" WHERE code_personne = \""+code_personne+"\"";
        prepareStatement(requete);
                
        ResultSet result = pst.executeQuery();
        
        return result;
    }
    
    public boolean comparePassword(String inputPass){
    
        
        
        return false;
    }
    
    
}
