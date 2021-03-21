/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automobiliste;

import java.io.InputStream;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import SysCentral.AmandeTableOperations;
import java.sql.SQLException;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.HBox;
import org.json.simple.parser.ParseException;

/**
 *
 * @author nzima-ivan
 */
public class Automobiliste extends Application {

    private final boolean isTest = true;
    private AmandeTableOperations amandeTable;
    private final InputStream arrowBackOutline = Automobiliste.class.getResourceAsStream("/Icons/arrow-back-outline.png");

    private final BorderPane PanelAnnuaire = new BorderPane();
    private final BorderPane panelSignup = new SignUp();
    private final BorderPane panelLogIn = new LogIn();



    @Override
    public void start(Stage stage) throws ClassNotFoundException, SQLException, ParseException {
        
        PanelAnnuaire.setCenter(panelLogIn);
        PanelAnnuaire.setId("main");
        Scene scene = new Scene(PanelAnnuaire, 800, 800);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        
        HBox header = new HBox();
        header.setId("header");
        header.setSpacing(12);
        header.setAlignment(Pos.TOP_RIGHT);
        
        PanelAnnuaire.setTop(header);
        
        stage.setTitle("Contravention");

        
        Hyperlink signInText = new Hyperlink("Se connecter");
        signInText.setId("cliked");
        

        Hyperlink signUpText = new Hyperlink("Créer un compte");

        header.getChildren().addAll(signInText, signUpText);
        
        signInText.setOnAction((ActionEvent)->{
            
            PanelAnnuaire.setCenter(panelLogIn);
            
        });
        
        signUpText.setOnAction((ActionEvent)->{
            
            PanelAnnuaire.setCenter(panelSignup);
            
        });
        
        
        stage.setScene(scene);
        stage.show();

    }



    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
