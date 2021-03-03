/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automobiliste;

import SysCentral.AmandeTableOperations;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.InflaterInputStream;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
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
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author nzima-ivan
 */
public class Automobiliste extends Application {

    private final boolean isTest = true;
    private final String matricule = "CE 787 DR";
    private AmandeTableOperations amandeTable;
    private final InputStream arrowBackOutline = Automobiliste.class.getResourceAsStream("/Icons/arrow-back-outline.png");

    private final BorderPane PanelAnnuaire = new BorderPane();
    private final BorderPane panelLogin = new LogIn();

    @Override
    public void start(Stage stage) {

        PanelAnnuaire.setCenter(panelLogin);
        Scene scene = new Scene(PanelAnnuaire, 800, 800);
        scene.getStylesheets().addAll(this.getClass().getResource("login.css").toExternalForm());
        stage.setTitle("test");
        stage.setScene(scene);
        stage.show();

//        try {
//            BorderPane borderPane = new BorderPane();
//            
//            Scene scene = new Scene(borderPane, 300, 640, Color.rgb(214, 245, 240));
//            
//            scene.getStylesheets().add(Automobiliste.class.getResource("style.css").toExternalForm());
//            
//            
//            stage.setScene(scene);
//            VBox vBox;
//            vBox = addheader();
//            borderPane.setCenter(vBox);
//            
//            getAmande();
//            
//            stage.show();
//        } catch (SQLException ex) {
//            System.out.println("erreur SQL: "+ex);
//        } catch (ParseException ex) {
//            Logger.getLogger(Automobiliste.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

//    
//    public void addText(Text text, int columnIndex, int rowIndex){
//        
//        grid.add(text, columnIndex, rowIndex);
//    }
    public VBox addheader() {

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(35, 0, 0, 45));

        GridPane grid = new GridPane();
        grid.setVgap(10);

        Text title = new Text("Contravention");
        title.setFont(Font.font("Roboto", FontWeight.NORMAL, 14));
        title.setFill(Color.rgb(19, 93, 77));
        grid.add(title, 0, 0);

        HBox hbBox = new HBox();
        hbBox.setSpacing(4);
        hbBox.setAlignment(Pos.BASELINE_LEFT);
        Text prize = new Text("75,000");
        prize.setFont(Font.font("Roboto", FontWeight.NORMAL, 20));
        prize.setFill(Color.rgb(19, 93, 77));

        Text monnaie = new Text("FCFA");
        monnaie.setFont(Font.font("Roboto", FontWeight.NORMAL, 10));
        monnaie.setFill(Color.rgb(19, 93, 77));

        hbBox.getChildren().addAll(prize, monnaie);

        grid.add(hbBox, 0, 1);

        Text dateDelais = new Text("A payer avant le 23/03/2021");
        dateDelais.setFont(Font.font("Roboto", FontWeight.LIGHT, 9));
        dateDelais.setFill(Color.rgb(29, 124, 115));

        grid.add(dateDelais, 0, 2);

        Text detailsCompte = new Text("Détails compte");
        detailsCompte.setFont(Font.font("Roboto", FontWeight.LIGHT, 9));
        detailsCompte.setFill(Color.rgb(142, 153, 164));

        grid.add(detailsCompte, 0, 3);

        HBox btn;
        btn = buyBtn();

        vBox.getChildren().addAll(grid, btn);

        return vBox;

    }

    private BorderPane addBody() {

        BorderPane body = new BorderPane();

        GridPane grid = new GridPane();
        grid.setVgap(15);

        body.setCenter(grid);

        return body;

    }

    public HBox buyBtn() {

        HBox btn = new HBox();

        InputStream in = Automobiliste.class.getResourceAsStream("/Icons/bouton-payer.png");
        Hyperlink link;
        link = addHyperlinkIcon(in);

        btn.getChildren().add(link);

        return btn;
    }

    public Hyperlink addHyperlinkIcon(InputStream inputStream) {
        // creation dun hyperlien
        Hyperlink hyperlink = new Hyperlink();

        //creatiion d'un graphic
        ImageView view = new ImageView();
        Image image = new Image(inputStream);
        view.setImage(image);
        view.setFitHeight(30);
        view.setFitWidth(225);

        //setting the graphic to the hyperlink
        hyperlink.setGraphic(view);

        return hyperlink;
    }

    private void getAmande() throws SQLException, ParseException {

        amandeTable = new AmandeTableOperations(isTest);

        ResultSet result = amandeTable.getInfractions(matricule);
        if (result.next()) {
            String listeInfractions = result.getString("liste_infraction");
            JSONParser parser = new JSONParser();
            JSONObject infractionJson = (JSONObject) parser.parse(listeInfractions);
            System.out.println(infractionJson);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
