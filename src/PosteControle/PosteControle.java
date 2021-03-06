/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PosteControle;

import SysCentral.Server;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author nzima-ivan
 */
public class PosteControle extends Application implements Runnable {
    
    private Server PCServer;
    private JSONObject jsonMesg;
    private JSONParser parser;
    private final int PORTR = 4000;
    
    private TextArea textArea;
    private final String cheminFichier = "/home/nzima-ivan/NetBeansProjects/Contravention/src/bd.json";
    private File file;
    
    @Override
    public void start(Stage fen) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        Scene sc = new Scene(grid, 600,600);
        fen.setScene(sc);
        
        textArea = new TextArea();
        Text title = new Text("Poste de control");
        grid.add(title, 0, 0);
        grid.add(textArea, 0, 1);
        
        
       fen.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Thread th = new Thread(new PosteControle());
        th.start();
        launch(args);
    }

    @Override
    public void run() {
        PCServer = new Server();
        
        try {
            PCServer.start(PORTR);
        } catch (IOException ex) {
            System.out.println("impossible de démarrer le serveur: "+ex);
        }
        try {
            
            while(true){
                // confirmation de toutes les connexions entrantes
                PCServer.acceptConnection();
                
                // ouverture du flux de données et recuprétions des messages entrants
                String msg = PCServer.receiveMsg();
                
//                 formatage des messages recus
//                parser = new JSONParser();
//                jsonMesg = (JSONObject) parser.parse(msg);
//                System.out.println("vitesse : "+jsonMesg.get("vitesse"));
//                String text1 = "Matricule : "+ jsonMesg.get("matricule") +"\n"+"vitesse : " + jsonMesg.get("vitesse") + " km/h \n";
//                String text2 = "";
//                JSONObject infractions =  (JSONObject) jsonMesg.get("infractions");
//                int size = infractions.size();
//                for (int i=0; i<size; i++){
//                     boolean d = (boolean) infractions.get(i);
//                    text2 = String.join("\n", text2, " "+i+" : "+d);
//                }
            
                                    
//                textArea.setText("ghgh");

                  byte[] data = msg.getBytes();
                  Path chemin = Paths.get(cheminFichier);
                  OutputStream output = null;
                  try {
                    file = new File(cheminFichier);
                      if (!file.exists()) {                          
                        output = new BufferedOutputStream(Files.newOutputStream(chemin, CREATE));
                      }else{
                        output = new BufferedOutputStream(Files.newOutputStream(chemin, APPEND));

                      }
                    output.write(data);
                    output.flush();
                    output.close();
                  } catch (Exception e) {
                      System.out.println("erreur lors de l'écriture : "+e); 
                  }
            }
        } catch (IOException ex) {
            System.out.println("echec connexion serveur: "+ex);
        } 
        
    }
    
}
