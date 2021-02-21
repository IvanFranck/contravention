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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import SysCentral.AmandeTableOperations;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author nzima-ivan
 */
public class Automobiliste extends Application {
    
    private GridPane gridLogin;
    private GridPane gridSignIn;
    private Scene sc;
    
    private AmandeTableOperations amandeTable;
    private AutomobileTableOperation automobile;
    private PersonneTableOperation personne;
    private final boolean isTest = true;
    
    @Override
    public void start(Stage fen) throws NoSuchAlgorithmException {
        
        
        gridSignIn = new GridPane();
        sc = new Scene(gridSignIn, 700, 700);

        gridSignIn.setAlignment(Pos.CENTER);
        gridSignIn.setPadding(new Insets(10));
        gridSignIn.setHgap(10);
        gridSignIn.setVgap(10);
        
        
        Text titleSignIn = new Text("S'incrire");
        gridSignIn.add(titleSignIn, 0, 0);
        
        Label nomText = new Label("nom");
        TextField nomField = new TextField();
        gridSignIn.add(nomText, 0, 1);
        gridSignIn.add(nomField, 0, 2);
        
        Label prenomText = new Label("prenom");
        TextField prenomField = new TextField();
        gridSignIn.add(prenomText, 1, 1);
        gridSignIn.add(prenomField, 1, 2);
        
        Label numCNIText = new Label("numéro CNI");
        TextField numCNIField = new TextField();
        gridSignIn.add(numCNIText, 0, 3);
        gridSignIn.add(numCNIField, 0, 4);
        
        
        Text passText = new Text("mot de passe");
        PasswordField passwordField = new PasswordField();
        gridSignIn.add(passText, 0, 5);
        gridSignIn.add(passwordField, 0, 6);
        
        
        Text naissanceText = new Text("date de naissance");
        DatePicker naissanceDatePicker = new DatePicker();
        gridSignIn.add(naissanceText, 0, 7);
        gridSignIn.add(naissanceDatePicker, 0, 8);
        
        
        Text professionText = new Text("profession");
        ComboBox professionBox = new ComboBox();
        professionBox.getItems().addAll(
                "Etudiant",
                "Ingéninieur",
                "enseignant",
                "Menuisier",
                "chauffeur taxi",
                "Commercant"
        );
        gridSignIn.add(professionText, 0, 9);
        gridSignIn.add(professionBox, 0, 10);
        
        Text telText = new Text("contact");
        TextField textField = new TextField();
        gridSignIn.add(telText, 0, 11);
        gridSignIn.add(textField, 0, 12);
        
        
        
        Text mailText = new Text("adresse mail");
        TextField mailField = new TextField();
        gridSignIn.add(mailText, 0, 13);
        gridSignIn.add(mailField, 0, 14);
        
        
        Text adresseText = new Text("adresse");
        TextField adressField = new TextField();
        gridSignIn.add(adresseText, 0, 15);
        gridSignIn.add(adressField, 0, 16);
        
        Button logIn , signIn;
        logIn = new Button("Log in");
        signIn = new Button("Sign in");
        gridSignIn.add(logIn, 0, 17);
        gridSignIn.add(signIn, 1, 17);

        logIn.setOnAction((ActionEvent even)->{
            sc = new Scene(gridLogin, 700, 700);
        });
        
        
      
        gridLogin = new GridPane();
        gridLogin.setAlignment(Pos.CENTER);
        gridLogin.setVgap(20);
        gridLogin.setHgap(20);
        
        fen.setScene(sc);
        
        
        Text title = new Text("Paiment");
        fen.setTitle("interface paiement");
        
        gridLogin.add(title, 0,0, 6,1);
        
        Label matriculeLabel = new Label("matricule");
        Label mdpLabel = new Label("mot de passe");
        
        TextField matriculeInput = new TextField();
        gridLogin.add(matriculeInput, 1, 1, 5, 1);
        gridLogin.add(matriculeLabel, 0, 1);
        
        PasswordField mdp = new PasswordField();
        gridLogin.add(mdp,1 ,2, 5,1 );
        gridLogin.add(mdpLabel, 0, 2);
        
        Button logInBtn = new Button ("Log in");
        gridLogin.add(logInBtn, 3, 4, 1, 1);
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
