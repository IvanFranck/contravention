/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysCentral;

import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Automobiliste.AutomobileTableOperation;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nzima-ivan
 */
public class SysCentral extends Application implements Runnable {

    private SCServer serv;
    private JSONObject jsonMsg;
    private JSONParser parser;
    private final int PORTE = 3000;
    
    // BD operation
    private AmandeTableOperations tableAmande ; 
    private AutomobileTableOperation tableAuto;
    private boolean isTest = true;
    
    
    @Override
    public void start(Stage fen) {
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        Scene sc = new Scene(grid, 700, 500);
        
        fen.setScene(sc);
        fen.setTitle("sys central");
        Text title = new Text("Syteme central");
        
        
            // style
            title.setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 15));
        // positionnement du titre
        grid.add(title, 0, 0); // fixe à (0,0) et occupe 1 ligne ET 2 c
        
        Button btn =  new Button ("ok");
        
        grid.add(btn, 0, 1);
        
        
        fen.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Thread th = new Thread(new SysCentral());
        th.start();
        launch(args);
    }

    @Override
    public void run() {

        serv = new SCServer();
        try {
            // demarrage du server de socket (écoute sur le port 3000)
            serv.start(PORTE);
        } catch (IOException ex) {
            System.out.println("impossible de démarrer le serveur");
        }
        try {
            
            while(true){
                // confirmation de toutes les connexions entrantes
                serv.acceptConnection();
                
                // ouverture du flux de données et recuprétions des messages entrants
                String msg = serv.receiveMsg();
                
                // convertion des messages recus en JSON
                parser = new JSONParser();
                jsonMsg = (JSONObject) parser.parse(msg);
                System.out.println("vitesse : "+jsonMsg.get("vitesse"));
                
                
                // enregistrement des amandes et des auto ayant les matricules recupérés en BD
                
                // connexion à la BD
                tableAuto = new AutomobileTableOperation(isTest);
                tableAmande = new AmandeTableOperations(isTest);
                
                
                // insertion des données recus dans la table Amande
                String matricule = (String) jsonMsg.get("matricule");
                JSONObject list = (JSONObject) jsonMsg.get("infractions");
                String infractions = list.toString() ;
                
                tableAuto.insertion(matricule);
                
                tableAmande.insertion(matricule, infractions);
                
            }
        } catch (IOException ex) {
            System.out.println("echec connexion serveur");
        } catch (SQLException ex) {
            System.out.println("impossible de se connecter à la BD : "+ex);
        } catch (ParseException ex) {
            Logger.getLogger(SysCentral.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       

}
