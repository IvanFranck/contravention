/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automobiliste;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
            
           
            
            Text title = new Text("Hello "+userName+" !");
            title.setFont(Font.font("Roboto", FontWeight.BOLD, 24));
            title.setFill(Color.rgb(5, 21, 19));
            grid.add(title, 0, 0);
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
