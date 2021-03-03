/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automobiliste;


import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author nzima-ivan
 */
public class SignUp extends BorderPane {
    
    public SignUp(){
        GridPane grid = new GridPane();
        
        Text hello = new Text("hello");
        grid.add(hello, 0, 0);
        this.setCenter(grid);
    }
}
