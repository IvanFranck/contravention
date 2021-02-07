/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automobiliste;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import SysCentral.AmandeTableOperations;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

/**
 *
 * @author nzima-ivan
 */
public class Automobiliste extends Application {
    
    private AmandeTableOperations amandeTable;
    private AutomobileTableOperation automobile;
    private PersonneTableOperation personne;
    private final boolean isTest = true;
    
    @Override
    public void start(Stage fen) throws NoSuchAlgorithmException {
       
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(20);
        grid.setHgap(20);
        
        Scene sc = new Scene(grid, 700, 700);
        fen.setScene(sc);
        
        
        Text title = new Text("Paiment");
        fen.setTitle("interface paiement");
        
        grid.add(title, 0,0, 6,1);
        
        Label matriculeLabel = new Label("matricule");
        Label mdpLabel = new Label("mot de passe");
        
        TextField matriculeInput = new TextField();
        grid.add(matriculeInput, 1, 1, 5, 1);
        grid.add(matriculeLabel, 0, 1);
        
        PasswordField mdp = new PasswordField();
        grid.add(mdp,1 ,2, 5,1 );
        grid.add(mdpLabel, 0, 2);
        
        Button logInBtn = new Button ("Log in");
        grid.add(logInBtn, 3, 4, 1, 1);
        logInBtn.setDefaultButton(true);
        
        fen.show();
        
        logInBtn.setOnAction((ActionEvent even) -> {
            
            String matricule = matriculeInput.getText();
            String pass = mdp.getText();
            int codePers = 0;
            try {
                automobile = new AutomobileTableOperation(isTest);
                ResultSet resultSet = automobile.selection(matricule);
                if(resultSet.next()){
                    codePers = resultSet.getInt(2);
                    System.out.println("code pers : "+codePers);
                }
                
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e);
            }
            
            try {
                
                personne = new PersonneTableOperation(isTest);
                ResultSet resultPersonne = personne.selectionPass(codePers);
                
                if (resultPersonne.next()) {
                    System.out.println("mot de passe : "+resultPersonne.getString(2));
                }
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e);
            }
            
            
            try {
                amandeTable = new AmandeTableOperations(isTest);
                
                ResultSet result = amandeTable.selection(matricule);
//                result.first();
                while(result.next()){
                    
//                    RowId id = result.getRowId("matricule");
//                    System.out.println("row id : "+id);
                    String test = result.getString(2);
                    System.out.println("test : "+ test);
                }
                System.out.println("résultat : "+result.toString());;
            } catch (SQLException ex) {
                System.out.println("impossible de d'effetuer la recherche + "+ ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Automobiliste.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
