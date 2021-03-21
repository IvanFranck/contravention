/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automobiliste;

import SysCentral.AmandeTableOperations;
import contravention.ContraventionTableOperation;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author nzima-ivan
 */
public class Contravention extends BorderPane{
    
    
    private final boolean isTest = true;
    private final AmandeTableOperations amandeTable;
    private final ContraventionTableOperation contraventionTable;
    
    public Contravention(String userName, String matricule) throws ClassNotFoundException, SQLException, ParseException{
        
        amandeTable = new AmandeTableOperations(isTest);
        contraventionTable = new ContraventionTableOperation(isTest);
        
        String[] libelleTable  = new String[5];
        int[] prixTable = new int[5];
//        int[] codeTable = new int[5];
        int compteur = 0;
        int montantFinal = 0;        
        
        
        ResultSet allContravenSet = contraventionTable.selectionAll();
        while(allContravenSet.next()){
            libelleTable[compteur] = allContravenSet.getString("libelle");
            prixTable[compteur] = allContravenSet.getInt("prix");
//            codeTable[compteur] = allContravenSet.getInt("code_contraventions");
            compteur++;
        }
        
        String listeInfractions = null;
        ResultSet amandeResultSet = amandeTable.selection(matricule.trim());
        if(amandeResultSet.next()){
            listeInfractions = amandeResultSet.getString("liste_infraction");
        }
        
        JSONParser parser = new JSONParser();
        JSONObject infractionsJSON = (JSONObject) parser.parse(listeInfractions);
        
        
         for(int i =0; i < 5; i++){
            
            boolean coupable = (boolean) infractionsJSON.get(Integer.toString(i));
            if( coupable ){
                
                montantFinal = montantFinal + prixTable[i];
                
            }
        }
        
        
        
        VBox header = new VBox();
        header.setPadding(new Insets(40, 0, 0, 45));
        header.setId("header");
        
        GridPane infractionGrid = new GridPane();
        infractionGrid.setPadding(new Insets(60, 0, 20, 45));
        
        
        
        GridPane grid = new GridPane();
        grid.setVgap(10);
        
        
        
       HBox head = new HBox();
            head.setPadding(new Insets(0, 50, 0, 0));
            head.setId("header");
            head.setSpacing(12);
            head.setAlignment(Pos.BASELINE_RIGHT);
            
            Label dx = new Label("Déconnexion");
            dx.setFont(Font.font("Roboto", FontWeight.NORMAL, 15));
            
            Hyperlink logout = new Hyperlink();
            InputStream inputLogout = Automobiliste.class.getResourceAsStream("logout.png");
            Image imgLogout = new Image(inputLogout);
            ImageView logoutView = new ImageView(imgLogout);
            logoutView.setFitHeight(20);
            logoutView.setFitWidth(16);
            logout.setGraphic(logoutView);

            logout.setOnAction(((event) -> {
                BorderPane parentPane = (BorderPane) this.getParent();
                parentPane.setCenter(new LogIn());
            }));
        
        head.getChildren().addAll(logout);
        grid.add(head,0,0);
        
        Text title = new Text("Hello "+userName+" !");
        title.setFont(Font.font("Roboto", FontWeight.BOLD, 24));
        title.setFill(Color.rgb(5, 21, 19));
        grid.add(title, 0, 1);
        GridPane.setMargin(title, new Insets(0, 0, 45, 0));
        
        Text contravention = new Text("Contravention");
        contravention.setFont(Font.font("Roboto", FontWeight.NORMAL, 18));
        contravention.setFill(Color.rgb(19, 93, 77));
        grid.add(contravention, 0, 2);

        HBox hbBox = new HBox();
        hbBox.setSpacing(4);
        hbBox.setAlignment(Pos.BASELINE_LEFT);
        
        Text prize = new Text(Integer.toString(montantFinal));
        prize.setFont(Font.font("Roboto", FontWeight.NORMAL, 25));
        prize.setFill(Color.rgb(19, 93, 77));

        Text monnaie = new Text("FCFA");
        monnaie.setFont(Font.font("Roboto", FontWeight.NORMAL, 14));
        monnaie.setFill(Color.rgb(19, 93, 77));

        hbBox.getChildren().addAll(prize, monnaie);
        grid.add(hbBox, 0, 3);

        Text dateDelais = new Text("A payer avant le 23/03/2021");
        dateDelais.setFont(Font.font("Roboto", FontWeight.LIGHT, 15));
        dateDelais.setFill(Color.rgb(142, 154, 163));
        grid.add(dateDelais, 0, 4);
        GridPane.setMargin(dateDelais, new Insets(0, 0, 40, 0));
        
        header.getChildren().add(grid);
        
       
        
        for(int i =0; i < 5; i++){
            
            boolean coupable = (boolean) infractionsJSON.get(Integer.toString(i));
            if( coupable ){
                
                HBox infractionBox = new HBox();
                infractionBox.setSpacing(25);
                infractionBox.setAlignment(Pos.BASELINE_LEFT);

                TextFlow libelleFlow = new TextFlow();
                libelleFlow.setPrefSize(420, 50);

                Text libelle = new Text(libelleTable[i]);
                libelle.setFont(Font.font("Roboto", FontWeight.LIGHT, 18));
                libelle.setFill(Color.rgb(10, 41, 38));
                libelleFlow.getChildren().add(libelle);

                Text prix = new Text(Integer.toString(prixTable[i]));
                prix.setFont(Font.font("Roboto", FontWeight.LIGHT, 14));
                prix.setFill(Color.rgb(142, 154, 163));
                
                Text fcfaText = new Text("FCFA");
                fcfaText.setFont(Font.font("Roboto", FontWeight.NORMAL, 14));
                fcfaText.setFill(Color.rgb(19, 93, 77));

                infractionBox.getChildren().addAll(libelleFlow, prix);

                infractionGrid.add(infractionBox, 0, i);
            }
        }
        
        
            GridPane bottomGridPane = new GridPane();
        
            //Creating a hyper link
          Hyperlink link = new Hyperlink();
          //Creating a graphic
          ImageView view = new ImageView();
          InputStream input = LogIn.class.getResourceAsStream("bouton-payer.png");
          Image image = new Image(input);
          view.setImage(image);
          view.setFitHeight(40);
          view.setFitWidth(220);
          //Setting the graphic to the hyperlink
          link.setGraphic(view);
          bottomGridPane.add(link, 0, 0);
        
          GridPane.setMargin(link, new Insets(0, 0, 60, 45));
          
          link.setOnAction((event) -> {
            try {
                amandeTable.buy(matricule);
                BorderPane parent = (BorderPane) this.getParent();
                parent.setCenter(new NoContravention(userName));
            } catch (SQLException ex) {
                Logger.getLogger(Contravention.class.getName()).log(Level.SEVERE, null, ex);
            }
          });
          
        this.setBottom(bottomGridPane);
        this.setTop(header);
        this.setCenter(infractionGrid);
    }


}