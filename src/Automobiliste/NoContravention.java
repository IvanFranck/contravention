/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automobiliste;

import java.io.InputStream;
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

/**
 *
 * @author nzima-ivan
 */
public class NoContravention extends BorderPane{
    
        public NoContravention(String userName){
            VBox header = new VBox();
            header.setPadding(new Insets(40, 0, 0, 45));
            header.setId("header");

            GridPane bodyGrid = new GridPane();
            bodyGrid.setPadding(new Insets(200, 0, 20, 245));

            this.setTop(header);
            this.setCenter(bodyGrid);

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

            head.getChildren().addAll(dx,logout);
            GridPane.setMargin(head, new Insets(0, 20, 0, 550));
            grid.add(head, 0, 0);
           
            
            Text title = new Text("Hello "+userName+" !");
            title.setFont(Font.font("Roboto", FontWeight.BOLD, 24));
            title.setFill(Color.rgb(5, 21, 19));
            grid.add(title, 0, 1);
            GridPane.setMargin(title, new Insets(0, 0, 45, 0));
            
           
             VBox body = new VBox();
             body.setAlignment(Pos.CENTER);
             
             
            Text oups = new Text("Bravo ");
            oups.setFont(Font.font("Roboto", FontWeight.MEDIUM, 35));
            oups.setFill(Color.rgb(142, 153, 164));
            body.getChildren().add(oups);
            
            
            Text text = new Text("vous n'avez aucune amande !");
            text.setFont(Font.font("Roboto", FontWeight.NORMAL, 20));
            text.setFill(Color.rgb(142, 153, 164));
            body.getChildren().add(text);
            
            

            header.getChildren().add(grid);
            
            bodyGrid.add(body, 0, 0);
            GridPane.setMargin(body, new Insets(100, 0, 0, 200));
            
            GridPane.setMargin(body, new Insets(0, 0, 45, 0));
        }
    
}
