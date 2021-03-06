/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automobiliste;

import java.io.InputStream;
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

/**
 *
 * @author nzima-ivan
 */
public class LogIn extends BorderPane {
    
    
    public LogIn() {
        HBox header = new HBox();
        header.setId("header");
        header.setSpacing(12);
        header.setAlignment(Pos.TOP_RIGHT);
        
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
        this.setTop(header);
        
        Hyperlink signInText = new Hyperlink("Se connecter");
        signInText.setId("cliked");
        

        Hyperlink signUpText = new Hyperlink("Créer un compte");

        header.getChildren().addAll(signInText, signUpText);
        
        
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
        
        Button btn = new Button("S'incrire");
        
        Hyperlink createAccount = new Hyperlink("Vous n'avez pas de compte ?");
        GridPane.setMargin(createAccount, new Insets(25, 100, 0, 250));
        
        grid.add(greatingBox, 0, 0);
        grid.add(matriculeGroupBox, 0, 1);
        grid.add(passGroupBox, 0, 2);
        grid.add(createAccount, 0, 3);
        
        
        
        createAccount.setOnAction((ActionEvent arg0) -> {
            // TODO Auto-generated method stub
            
            
            Stage nouveauStage;
            nouveauStage = (Stage) ((Node) arg0.getSource()).getScene().getWindow();
            Scene scene1 = new Scene(new SignUp(),800, 800);
            nouveauStage.setScene(scene1);
        });
        
    }
    
    
}
