/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Flash;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.JSONObject;


/**
 *
 * @author nzima-ivan
 */
public class Flash extends Application implements Runnable{
    
    private JSONObject jsonMsg;
    
   

    @Override
    public void start(Stage fen) throws Exception {
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(20);
        grid.setHgap(20);
        
        Scene sc = new Scene(grid, 600, 500);
        
        fen.setScene(sc);
        fen.setTitle("FLASH");
        
        Text title = new Text("flash info generator");
        
        grid.add(title,0,0,2,1);
        
        TextArea textArea = new TextArea();
        grid.add(textArea, 0, 1, 2, 1);
        textArea.setEditable(false);
        
        Button generateBtn =  new Button("generate");
        Button envoyerBtn =  new Button("envoyer");
        
        grid.add(generateBtn, 0, 3, 1, 1);
        grid.add(envoyerBtn, 1, 3, 1, 1);
        
        
        fen.show();
        
        generateBtn.setOnAction((ActionEvent even) ->{
       
            RandomInfoFlashProvider rand = new RandomInfoFlashProvider();
            jsonMsg = rand.sendJson();
             
            String text1 = "Matricule : "+ jsonMsg.get("matricule") +"\n"+"vitesse : " + jsonMsg.get("vitesse") + " km/h \n";
            String text2 = "";
            JSONObject infractions =  (JSONObject) jsonMsg.get("infractions");
            int size = infractions.size();
            for (int i=0; i<size; i++){
                 boolean d = (boolean) infractions.get(i);
                text2 = String.join("\n", text2, " "+i+" : "+d);
            }
            
                                    
            textArea.setText(String.join("", text1, text2));
            
       
        });
        
        envoyerBtn.setOnAction((ActionEvent even) -> {
            
            try {
//                FlashClient sockCl = new FlashClient();
                FlashClient sockCl2 = new FlashClient();
                
//                sockCl.connect(3000);
                sockCl2.connect(4000);
                
                System.out.println("message à envoyer : " + String.join("", jsonMsg.toString(),"\n"));
                String matricule = (String) jsonMsg.get("matricule");
                float vitesse = (float) jsonMsg.get("vitesse");
                
//                sockCl.sendMessage(jsonMsg.toString()+"\n");
                sockCl2.sendMessage(jsonMsg.toJSONString()+"\n");
                
//                sockCl.sendMessage(jsonMsg);
            } catch (IOException ex) {
                System.out.println("erreur lors de l'envoie du message par le client flash !!!");
            }
            
        });
        
        
    }
    
    @Override
    public void run (){
       
    }
    
    public static void main(String[] args) {
        Thread th = new Thread (new Flash());
        th.start();
        launch(args);
    }
}
