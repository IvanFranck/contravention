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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.ParseException;

/**
 *
 * @author nzima-ivan
 */
public class Automobiliste extends Application {

    private final boolean isTest = true;
    private final String matricule = "ES 084 HA";
    private AmandeTableOperations amandeTable;
    private final InputStream arrowBackOutline = Automobiliste.class.getResourceAsStream("/Icons/arrow-back-outline.png");

    private final BorderPane PanelAnnuaire = new BorderPane();
    private final BorderPane panelSignup = new SignUp();
    private Contravention panelContravention;



    @Override
    public void start(Stage stage) {
        
        try {
            panelContravention = new Contravention("John", matricule);
            PanelAnnuaire.setCenter(panelContravention);
            Scene scene = new Scene(PanelAnnuaire, 600, 800);
            scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
            stage.setTitle("test");
            stage.setScene(scene);
            stage.show();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Automobiliste.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Automobiliste.class.getName()).log(Level.SEVERE, null, ex);
        }

    }



    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
