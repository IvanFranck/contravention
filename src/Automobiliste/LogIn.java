/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automobiliste;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

/**
 *
 * @author nzima-ivan
 */
public class LogIn extends BorderPane {
    
    
    private final boolean isTest = true;
    private PersonneTableOperation tablePersonne;
    private AutomobileTableOperation tableAuto;
    
    public LogIn() {
        
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.BASELINE_LEFT);
        grid.setPadding(new Insets(15, 100, 0, 0));
        grid.setId("grid-login");
        
        VBox greatingBox = new VBox();
        
        VBox matriculeGroupBox = new VBox();
        matriculeGroupBox.setSpacing(10);
        matriculeGroupBox.setAlignment(Pos.BASELINE_LEFT); 
        
        VBox passGroupBox = new VBox();
        passGroupBox.setSpacing(10);
        passGroupBox.setAlignment(Pos.BASELINE_LEFT);
        
        GridPane.setMargin(greatingBox, new Insets(210, 0, 150, 50));
        GridPane.setMargin(matriculeGroupBox, new Insets(0, 0, 20, 250 ));
        GridPane.setMargin(passGroupBox, new Insets(0, 0, 20, 250 ));
                
        
        
        this.setId("main");
        this.setCenter(grid);
        
        
        
        Text heyText = new Text("Hey !");
        heyText.setFont(Font.font("Roboto", FontWeight.MEDIUM, 32));
        
        Text welcomeText = new Text("C'est un plaisir \n de vous revoir");
        welcomeText.setFont(Font.font("Roboto", FontWeight.MEDIUM, 32));
        
        Label matricule = new Label("Matricule"); 
        matricule.setFont(Font.font("Roboto", FontWeight.NORMAL, 15));
        InputStream inProfil = Automobiliste.class.getResourceAsStream("Profile.png");
        Image img = new Image(inProfil);
        ImageView view = new ImageView(img);
        view.setFitHeight(12);
        view.setFitWidth(12);
        view.setPreserveRatio(true);
        matricule.setGraphic(view);
        
        Label pass = new Label("Mot de passe");
        pass.setFont(Font.font("Roboto", FontWeight.NORMAL, 15));
        InputStream inPass = Automobiliste.class.getResourceAsStream("Password.png");
        Image imgPass = new Image(inPass);
        ImageView passView = new ImageView(imgPass);
        passView.setFitHeight(15);
        passView.setFitWidth(15);
        passView.setPreserveRatio(true);
        pass.setGraphic(passView);
        
        
        TextField matriculeField = new TextField();
        matriculeField.setPrefWidth(350);
        matriculeField.setPromptText("entrer le matricule de votre véhicule");
        
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(350);
        passwordField.setPromptText("entrer votre mot de passe");
        
        matriculeGroupBox.getChildren().addAll(matricule, matriculeField);
        HBox.setMargin(matricule, new Insets(0, 25, 0,0));
        passGroupBox.getChildren().addAll(pass, passwordField);
        
        greatingBox.getChildren().addAll(heyText, welcomeText);
        
        
        InputStream input = LogIn.class.getResourceAsStream("Arrow-Right-Circle.png");
        Image imgarrow = new Image(input);
        ImageView btnView = new ImageView(imgarrow);
        btnView.setFitHeight(15);
        btnView.setFitWidth(15);
        Button connect = new Button("Se connecter", btnView);
        connect.setId("btn");
        connect.setAlignment(Pos.BASELINE_CENTER);
        GridPane.setMargin(connect, new Insets(25, 100, 0, 250));
        
        grid.add(greatingBox, 0, 0);
        grid.add(matriculeGroupBox, 0, 1);
        grid.add(passGroupBox, 0, 2);
        grid.add(connect, 0, 3);
        
        
        connect.setOnAction((ActionEvent arg0)-> {
            MessageDigest md;
            
            try {
                
                tableAuto = new AutomobileTableOperation(isTest);
                tablePersonne = new PersonneTableOperation(isTest);
            // hachage mot de passe
                
                md = MessageDigest.getInstance("MD5");
                md.update(passwordField.getText().getBytes());
                
                byte byteData[] = md.digest();
                
                //convertir le tableau de bits en un format hex
                StringBuilder hashPass = new StringBuilder();
                for (int i = 0; i < byteData.length; i++){
                    String hex = Integer.toHexString(0xff & byteData[i]);
                    if (hex.length()==1) hashPass.append('0');
                    hashPass.append(hex);
                    
                    
                }
                
                ResultSet autoResultSet = tableAuto.selection(matriculeField.getText().trim().toUpperCase());
                int codePers = 0;
                String mat = null;
                if(autoResultSet.next()){
                    codePers = autoResultSet.getInt("code_personne");
                    mat = autoResultSet.getString("matricule");
                }
                
                ResultSet personne = tablePersonne.getPersonne(codePers);
                String password = null;
                String nom = null;
                if(personne.next()){
                    password = personne.getString("mot_de_passe");
                    nom = personne.getString("nom");
                }
                
                if(hashPass.toString().equals(password) && matriculeField.getText().equals(mat)){
//                    Stage nouveauStage;
//                    nouveauStage = (Stage) ((Node) arg0.getSource()).getScene().getWindow();
//                    Scene scene2 = new Scene(new Contravention(nom, mat),800, 800);
//                    scene2.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
//                    nouveauStage.setScene(scene2);
                      BorderPane parent = (BorderPane) this.getParent();
                      parent.setCenter(new Contravention(nom, mat));
                }else{
                    //inserer une page d'erreur
                }
            } catch (NoSuchAlgorithmException | SQLException ex) {
                Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        
        
        
    }
    
    
}
