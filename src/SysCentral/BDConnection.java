/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysCentral;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author nzima-ivan
 */
public class BDConnection {
    
    private final String URL_TEST = "jdbc:mysql://localhost:3306/contraventionTest?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC ";
    private final String URL = "jdbc:mysql://localhost:3306/contravention?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC ";
    private final String USER = "root";
    private final String PASS = "deutschland#007";
    protected final Connection con ;
    private final boolean isTest;
    
    /**
     *Connect to the BD 
     * 
     * @param isTest 
     *          if it is true, we connect us to the test BD: contraventionTest.
     *          default value is true
     * @throws SQLException
     */
    public BDConnection(boolean isTest) throws SQLException, ClassNotFoundException{
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("driver chargé avec succes :) ");
            
        
        this.isTest = isTest;
        if (this.isTest){
            
            con = DriverManager.getConnection(URL_TEST, USER, PASS);
            System.out.println("connexion à la BD établie avec success");
        }else{
             con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("connexion à la BD établie avec success");
        }
    }
}
