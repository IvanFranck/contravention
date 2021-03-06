/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automobiliste;


import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author nzima-ivan
 */
public class SignUp extends BorderPane {

    private final Boolean isTest = true;
    private PersonneTableOperation personneTable;
    private AutomobileTableOperation automobileTable;
    
    public SignUp(){
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.BASELINE_LEFT);
        grid.setId("grid-signup");
        
        
        BorderPane formGrid = new BorderPane();
        
        
        GridPane form1 = new GridPane();
        form1.setAlignment(Pos.BASELINE_LEFT);
        form1.setVgap(20);

        GridPane form2 = new GridPane();
        form2.setAlignment(Pos.BASELINE_LEFT);
        form2.setVgap(20);
        
        GridPane form3 = new GridPane();
        form3.setAlignment(Pos.BASELINE_LEFT);
        form3.setVgap(20);        
        
        VBox nomBox = new VBox();
        nomBox.setSpacing(5);
        
        VBox prenomBox = new VBox();
        prenomBox.setSpacing(5);
        
        VBox dateNaissanceBox = new VBox();
        dateNaissanceBox.setSpacing(5);
        
        VBox numCNIBox = new VBox();
        numCNIBox.setSpacing(5);
        
        VBox professionBox = new VBox();
        professionBox.setSpacing(5);
        
        VBox telBox = new VBox();
        telBox.setSpacing(5);
        
        VBox adresseBox = new VBox();
        adresseBox.setSpacing(5);
        
        VBox mailBox = new VBox();
        mailBox.setSpacing(5);
        
        VBox greatingBox = new VBox();
        greatingBox.setSpacing(16);
        
        VBox matriculeGroupBox = new VBox();
        matriculeGroupBox.setSpacing(5);
        matriculeGroupBox.setAlignment(Pos.BASELINE_LEFT); 
        
        VBox passGroupBox = new VBox();
        passGroupBox.setSpacing(5);
        passGroupBox.setAlignment(Pos.BASELINE_LEFT);
        
        
        
        HBox linkBox = new HBox();
        linkBox.setSpacing(20);
        linkBox.setAlignment(Pos.BASELINE_LEFT);
        
        
        
        GridPane.setMargin(greatingBox, new Insets(110, 0, 50, 420));
        GridPane.setMargin(formGrid, new Insets(0, 0, 20, 420));
        GridPane.setMargin(linkBox, new Insets(0, 0, 0, 420 ));
                
        formGrid.setCenter(form1);
        
        Hyperlink retour = new Hyperlink();
        InputStream inputRetour = Automobiliste.class.getResourceAsStream("Arrow-Left.png");
        Image imgRetour = new Image(inputRetour);
        ImageView viewRetour = new ImageView(imgRetour);
        viewRetour.setFitHeight(20);
        viewRetour.setFitWidth(20);
        viewRetour.setPreserveRatio(true);
        retour.setGraphic(viewRetour);
        
        this.setId("main");
        this.setCenter(grid);
        this.setBottom(retour);
       
        
        Text heyText = new Text("Créer votre compte");
        heyText.setFont(Font.font("Roboto", FontWeight.MEDIUM, 32));
        
        Text welcomeText = new Text("Veillez renseigner toutes les informations\nci-dessous pour créer un compte");
        welcomeText.setFont(Font.font("Roboto", FontWeight.THIN, 12));
        
        Label matricule = new Label("Matricule"); 
        matricule.setFont(Font.font("Roboto", FontWeight.NORMAL, 15));
//        InputStream inProfil = Automobiliste.class.getResourceAsStream("Profile.png");
//        Image img = new Image(inProfil);
//        ImageView view = new ImageView(img);
//        view.setFitHeight(12);
//        view.setFitWidth(12);
//        view.setPreserveRatio(true);
//        matricule.setGraphic(view);
        
        Label pass = new Label("Mot de passe");
        pass.setFont(Font.font("Roboto", FontWeight.NORMAL, 15));
//        InputStream inPass = Automobiliste.class.getResourceAsStream("Password.png");
//        Image imgPass = new Image(inPass);
//        ImageView passView = new ImageView(imgPass);
//        passView.setFitHeight(15);
//        passView.setFitWidth(15);
//        passView.setPreserveRatio(true);
//        pass.setGraphic(passView);
        
        
        Label nomLabel = new Label("Nom");
        nomLabel.setFont(Font.font("Roboto", FontWeight.NORMAL, 15));
        
        Label prenomLabel = new Label("Prenom");
        prenomLabel.setFont(Font.font("Roboto", FontWeight.NORMAL, 15));
        
        Label dateNaissanceLabel = new Label("Date de naissance");
        dateNaissanceLabel.setFont(Font.font("Roboto", FontWeight.NORMAL, 15));
        
        Label numCNILabel = new Label("Numéro de la CNI");
        numCNILabel.setFont(Font.font("Roboto", FontWeight.NORMAL, 15));
        
        Label professionLabel = new Label("Profession");
        professionLabel.setFont(Font.font("Roboto", FontWeight.NORMAL, 15));
        
        Label telLabel = new Label("Téléphone");
        telLabel.setFont(Font.font("Roboto", FontWeight.NORMAL, 15));

        Label adresseLabel = new Label("Adresse");
        adresseLabel.setFont(Font.font("Roboto", FontWeight.NORMAL, 15));

        Label mailLabel = new Label("Mail");
        mailLabel.setFont(Font.font("Roboto", FontWeight.NORMAL, 15));

        
        TextField nomField = new TextField();
        nomField.setPrefWidth(310);
        
        TextField prenomField = new TextField();
        prenomField.setPrefWidth(310);
        
        
        DatePicker dateNaissance = new DatePicker();

        TextField numCNIField = new TextField();
        numCNIField.setPrefWidth(310);
        
        TextField professionField = new TextField();
        professionField.setPrefWidth(310);

        TextField telField = new TextField();
        telField.setPrefWidth(310);

        TextField adresseField = new TextField();
        adresseField.setPrefWidth(310);
        
        TextField mailField = new TextField();
        mailField.setPrefWidth(310);
        
        
        TextField matriculeField = new TextField();
        matriculeField.setPrefWidth(310);
//        matriculeField.setPromptText("entrer le matricule de votre véhicule");
        
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(310);
//        passwordField.setPromptText("entrer votre mot de passe");
        
        InputStream input = Automobiliste.class.getResourceAsStream("Arrow-Right-Circle.png");
        Image imgPass = new Image(input);
        ImageView btnView = new ImageView(imgPass);
        btnView.setFitHeight(15);
        btnView.setFitWidth(15);
        Button btn = new Button("Créer un compte", btnView);
        btn.setId("btn");
        btn.setAlignment(Pos.BASELINE_CENTER);
        GridPane.setMargin(btn, new Insets(40, 0 ,50,0));
        
        Hyperlink prec = new Hyperlink("Précédent");
        prec.setId("precHyperlink");
//        prec.setDisable(true);
        
        Hyperlink suiv = new Hyperlink("Suivant");
        suiv.setId("suivHyperlink");
       
        
        linkBox.getChildren().addAll(prec, suiv);
        
        matriculeGroupBox.getChildren().addAll(matricule, matriculeField);
        
        passGroupBox.getChildren().addAll(pass, passwordField);
        
        greatingBox.getChildren().addAll(heyText, welcomeText);
        
        
        nomBox.getChildren().addAll(nomLabel, nomField);
        
        prenomBox.getChildren().addAll(prenomLabel, prenomField);
        
        dateNaissanceBox.getChildren().addAll(dateNaissanceLabel, dateNaissance);
        
        numCNIBox.getChildren().addAll(numCNILabel, numCNIField);
        
        professionBox.getChildren().addAll(professionLabel, professionField);
        
        telBox.getChildren().addAll(telLabel, telField);
        
        adresseBox.getChildren().addAll(adresseLabel, adresseField);
        
        mailBox.getChildren().addAll(mailLabel, mailField);
        
        
        form1.add(nomBox, 0, 0);
        form1.add(prenomBox, 0, 1);
        form1.add(dateNaissanceBox, 0, 2);
        form1.add(numCNIBox, 0, 3);
        
        form2.add(professionBox, 0, 0);
        form2.add(telBox, 0, 1);
        form2.add(mailBox, 0, 2);
        form2.add(adresseBox, 0, 3);
        
        form3.add(matriculeGroupBox, 0, 0);
        form3.add(passGroupBox, 0, 2);
        form3.add(btn, 0, 3);
        
        grid.add(greatingBox, 0, 0);
        grid.add(formGrid, 0, 1);
        grid.add(linkBox, 0, 2);
        
        
        suiv.setOnAction((ActionEvent)->{
            
//            prec.setDisable(false);
            
            if(formGrid.getCenter() == form1){
                formGrid.setCenter(form2);
            }else if(formGrid.getCenter() == form2) {
                formGrid.setCenter(form3);
//                suiv.setDisable(true);
            }
        });
        
        prec.setOnAction((ActionEvent)->{
            
            if(formGrid.getCenter() == form3){
                formGrid.setCenter(form2);
            }else if(formGrid.getCenter() == form2) {
                formGrid.setCenter(form1);
//                prec.setDisable(true);
            }
        });
       
        
        btn.setOnAction((ActionEvent) -> {
            
            MessageDigest md;
            
            
            try {
                automobileTable = new AutomobileTableOperation(isTest);
                personneTable = new PersonneTableOperation(isTest);
                
                // hachage mot de passe
                md = MessageDigest.getInstance("MD5");
                md.update(passwordField.getText().getBytes());
                
                byte byteData[] = md.digest();
                
                //convertir le tableau de bits en un format hex
                StringBuilder hashPass = new StringBuilder();
                for (int i = 0; i < byteData.length; i++){
                    String hex = Integer.toHexString(0xff & byteData[i]);
                    if (hex.length()==1) hashPass.append('0');
                    hashPass.append(hex);
                }
                
                // convert date picker value to sql date
                
                String dateString = dateNaissance.getEditor().getText();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

                Date dateNai = dateFormat.parse(dateString);

                java.sql.Date sDateNai = convertUtilToSql(dateNai);
                
                
                personneTable.insertion(nomField.getText(), prenomField.getText(), numCNIField.getText(), sDateNai, professionField.getText(),
                        Integer.parseInt(telField.getText().trim()), mailField.getText(), adresseField.getText(), hashPass.toString());
                
                

                // formatage du matricule
                String [] table = matriculeField.getText().trim().split(" ");
                String newMatricule;
                
                if(table.length == 1){
                    String[] chars = matriculeField.getText().trim().split("");
                    newMatricule = String.join(" ", chars[0]+chars[1], chars[2]+chars[3]+chars[4], chars[5]+chars[6]).toUpperCase();
                     
                }else{
                    newMatricule  = matriculeField.getText().toUpperCase();
                }
                
                
                // enregistrement de l'automobile
                ResultSet getCodeResult = personneTable.getCode(nomField.getText());
                int code = 1;
                if(getCodeResult.next()){
                    code = getCodeResult.getInt("code_personne");
                }
                automobileTable.insertion(newMatricule, code);
                
                

            } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException ex) {
                Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
            }catch (ParseException ex) {
                System.out.println("impossible de parser la date : "+ex);
            }
           
            
         
        });
        
        retour.setOnAction((ActionEvent arg0)-> {
            Stage nouveauStage;
            nouveauStage = (Stage) ((Node) arg0.getSource()).getScene().getWindow();
            Scene scene2 = new Scene(new LogIn(),800, 800);
            scene2.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
            nouveauStage.setScene(scene2);
        });
    }
    
    private java.sql.Date convertUtilToSql(java.util.Date uDate){
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
}
