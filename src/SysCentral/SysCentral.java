/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysCentral;

import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Automobiliste.AutomobileTableOperation;
import Flash.SystemeFlashTableOperation;
import PosteDeControl.PosteControleTableOperation;
import contravention.ContraventionTableOperation;
import contravention.Contraventions;
import java.io.InputStream;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author nzima-ivan
 */
public class SysCentral extends Application implements Runnable{

    private Server serv;
    private JSONObject jsonMsg;
    private JSONParser parser;
    private final int PORTE = 7000;

    // BD operation
    private AmandeTableOperations tableAmande;
    private AutomobileTableOperation tableAuto;
    private ContraventionTableOperation tableContravention;
    private PosteControleTableOperation tablePoste;
    private SystemeFlashTableOperation tableSystemeFlash;
    private final boolean isTest = true;
    

    //data and ui control
    private ObservableList<Contraventions> contraventionList = FXCollections.observableArrayList();
    private TableView<Contraventions> board = new TableView<Contraventions>();
    
    //interfaces
    private BorderPane pannelLogin;
    private BorderPane pannelDahsboard;
    private final BorderPane mainPannel = new BorderPane();
    
    @Override
    public void start(Stage stage) throws SQLException, ClassNotFoundException, ParseException, java.text.ParseException {

            
//        PanelAnnuaire.setCenter(pannelLogin);
//        PanelAnnuaire.setId("main");
//        Scene scene = new Scene(PanelAnnuaire, 1500, 800);
//        scene.getStylesheets().addAll(this.getClass().getResource("back.css").toExternalForm());
//        
//        
//        stage.setTitle("Contravention");
//
//        
//        
//        
//        stage.setScene(scene);
//        stage.show();

        pannelLogin = new BorderPane();
        mainPannel.setCenter(pannelLogin);

        // login page
        
        
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.BASELINE_LEFT);
        
        VBox greatingBox = new VBox();
        
        VBox matriculeGroupBox = new VBox();
        matriculeGroupBox.setSpacing(10);
        matriculeGroupBox.setAlignment(Pos.BASELINE_LEFT); 
        
        VBox passGroupBox = new VBox();
        passGroupBox.setSpacing(10);
        passGroupBox.setAlignment(Pos.BASELINE_LEFT);
        
        GridPane.setMargin(greatingBox, new Insets(210, 0, 80, 350));
        GridPane.setMargin(matriculeGroupBox, new Insets(0, 0, 20, 350 ));
        GridPane.setMargin(passGroupBox, new Insets(0, 0, 20, 350 ));
                

        pannelLogin.setId("main");
        pannelLogin.setCenter(grid);
        
        
        
        Text welcomeText = new Text("C'est un plaisir \n de vous revoir chers administrateur");
        welcomeText.setFont(Font.font("Roboto", FontWeight.MEDIUM, 32));
        
        Label matriculeLabel = new Label("login"); 
        matriculeLabel.setFont(Font.font("Roboto", FontWeight.NORMAL, 15));
        InputStream inProfil = SysCentral.class.getResourceAsStream("Profile.png");
        Image img = new Image(inProfil);
        ImageView view = new ImageView(img);
        view.setFitHeight(12);
        view.setFitWidth(12);
        view.setPreserveRatio(true);
        matriculeLabel.setGraphic(view);
        
        Label pass = new Label("Mot de passe");
        pass.setFont(Font.font("Roboto", FontWeight.NORMAL, 15));
        InputStream inPass = SysCentral.class.getResourceAsStream("Password.png");
        Image imgPass = new Image(inPass);
        ImageView passView = new ImageView(imgPass);
        passView.setFitHeight(15);
        passView.setFitWidth(15);
        passView.setPreserveRatio(true);
        pass.setGraphic(passView);
        
        
        TextField matriculeField = new TextField();
        matriculeField.setPrefWidth(350);
        matriculeField.prefHeight(20);
        matriculeField.setPromptText("entrer votre nom d'administrateur");
        
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(350);
        passwordField.setPromptText("entrer votre mot de passe");
        
        matriculeGroupBox.getChildren().addAll(matriculeLabel, matriculeField);
        HBox.setMargin(matriculeLabel, new Insets(0, 25, 0,0));
        passGroupBox.getChildren().addAll(pass, passwordField);
        
        greatingBox.getChildren().addAll(welcomeText);
        
        
        InputStream inputArrow = SysCentral.class.getResourceAsStream("Arrow-Right-Circle.png");
        Image imgarrow = new Image(inputArrow);
        ImageView btnView = new ImageView(imgarrow);
        btnView.setFitHeight(15);
        btnView.setFitWidth(15);
        Button connect = new Button("Se connecter", btnView);
        connect.setId("btn");
        connect.setAlignment(Pos.BASELINE_CENTER);
        GridPane.setMargin(connect, new Insets(25, 100, 0, 350));
        
        grid.add(greatingBox, 0, 0);
        grid.add(matriculeGroupBox, 0, 1);
        grid.add(passGroupBox, 0, 2);
        grid.add(connect, 0, 3);
        
        connect.setOnAction((ActionEvent)->{
            if(passwordField.getText().trim().equals("admin")){
                mainPannel.setCenter(pannelDahsboard);
            }
        });

        





        board.setEditable(true);
        board.setFixedCellSize(30);
        
        
        String matricule;
        int idAmande;
        Date dateDebut;
        Date dateFin;
        int statut;
        int amandeTotal = 0;
        int amandeImpayées = 0;

        tableAmande = new AmandeTableOperations(isTest);
        tableAuto = new AutomobileTableOperation(isTest);
        tableContravention = new ContraventionTableOperation(isTest);
        
        String[] libelleTable  = new String[5];
        int[] prixTable = new int[5];
//        int[] codeTable = new int[5];
        int compteur = 0;
        int montantAmande; 
        int finances = 0;
        
        ResultSet allContravenSet = tableContravention.selectionAll();
        while(allContravenSet.next()){
            libelleTable[compteur] = allContravenSet.getString("libelle");
            prixTable[compteur] = allContravenSet.getInt("prix");
//            codeTable[compteur] = allContravenSet.getInt("code_contraventions");
            compteur++;
        }
        
        
        String listeInfractions = null;

        ResultSet allAmandeResultSet = tableAmande.selectionAll();
        while (allAmandeResultSet.next()) {
            matricule = allAmandeResultSet.getString("matricule");
            idAmande = allAmandeResultSet.getInt("code_amande");
            dateDebut = allAmandeResultSet.getDate("date_debut");
            dateFin = allAmandeResultSet.getDate("date_fin");
            statut = allAmandeResultSet.getInt("statut");
             
            DateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
            String strDateDebut = dateFormat.format(dateDebut);
            String strDateFin = dateFormat.format(dateFin);
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy");
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(strDateDebut));
            c.add(Calendar.MONTH, 1);  // number of days to add
            strDateFin = sdf.format(c.getTime());  // dt is now the new date
            
            
            ResultSet amandeResultSet = tableAmande.selection(matricule.trim());
            if(amandeResultSet.next()){
                listeInfractions = amandeResultSet.getString("liste_infraction");
            }
            
            JSONParser parser = new JSONParser();
            JSONObject infractionsJSON = (JSONObject) parser.parse(listeInfractions);

            montantAmande = 0;
            for(int i =0; i < 5; i++){

                boolean coupable = (boolean) infractionsJSON.get(Integer.toString(i));
                if( coupable ){

                    montantAmande = montantAmande + prixTable[i];

                }
            }
            
            amandeTotal++;
            String strStatut = null;
            if(statut == 0){
                amandeImpayées++;
                strStatut = "En cours...";
            }else{
                finances = finances + montantAmande;
                strStatut = "Payée";
            }
                         
            contraventionList.add(new Contraventions(idAmande, matricule, strDateDebut, strDateFin, montantAmande, strStatut));
        }
         
        
        
        

        pannelDahsboard = new BorderPane();
        pannelDahsboard.setId("body");

        Scene scene = new Scene(mainPannel, 1500, 800);
        scene.getStylesheets().add(this.getClass().getResource("back.css").toExternalForm());
        stage.setScene(scene);
        
        HBox header = new HBox();
        header.setPadding(new Insets(10, 50, 0, 0));
        header.setId("header");
        header.setSpacing(12);
        header.setAlignment(Pos.TOP_RIGHT);
        
        GridPane mainGrid = new GridPane();
        pannelDahsboard.setCenter(mainGrid);

        VBox BorderBox = new VBox();
        pannelDahsboard.setRight(BorderBox);

        HBox stat = new HBox();
        stat.setSpacing(20);
        
        HBox greating = new HBox();
        mainGrid.add(greating, 0, 0);

        GridPane.setMargin(greating, new Insets(80, 16, 16, 16));
        GridPane.setMargin(stat, new Insets(0, 16, 24, 16));
        GridPane.setMargin(board, new Insets(0, 16, 0, 16));

        
        Hyperlink logout = new Hyperlink();
        InputStream input = SysCentral.class.getResourceAsStream("logout.png");
        Image imgLogout = new Image(input);
        ImageView logoutView = new ImageView(imgLogout);
        logoutView.setFitHeight(20);
        logoutView.setFitWidth(16);
        logout.setGraphic(logoutView);
        
        logout.setOnAction(((event) -> {
            mainPannel.setCenter(pannelLogin);
        }));
        
        
        Hyperlink refresh = new Hyperlink();
        InputStream input2 = SysCentral.class.getResourceAsStream("refresh.png");
        Image imgRefresh = new Image(input2);
        ImageView refreshView = new ImageView(imgRefresh);
        refreshView.setFitHeight(20);
        refreshView.setFitWidth(20);
        refresh.setGraphic(refreshView);
        
        refresh.setOnAction((ActionEvent)->{
            // à revoir
            board.refresh();
            
        });
        
        header.getChildren().addAll(refresh, logout);
       

        pannelDahsboard.setTop(header);
        
        Text hello = new Text("Hello cher administrateur !");
        hello.setFont(Font.font("Roboto", FontWeight.MEDIUM, 25));
        greating.getChildren().add(hello);

        
        // box des contraventions en cours
        VBox encoursBox = new VBox();
        encoursBox.setId("box");
        encoursBox.setAlignment(Pos.BASELINE_LEFT);
        encoursBox.setSpacing(10);
        

        InputStream inEncours = SysCentral.class.getResourceAsStream("encours-icon.png");
        Image encoursImg = new Image(inEncours);
        ImageView encoursView = new ImageView(encoursImg);
        encoursView.setFitHeight(15);
        encoursView.setFitWidth(15);
        encoursView.setPreserveRatio(true);

        Label encoursText = new Label("Contraventions", encoursView);
        encoursText.setFont(Font.font("Roboto", FontWeight.NORMAL, 15));
        encoursText.setAlignment(Pos.BASELINE_CENTER);
        encoursText.setStyle("-fx-text-fill: #2EC4B6");
   

        Label encoursNbre = new Label(Integer.toString(amandeTotal));
        encoursNbre.setFont(Font.font("Roboto", FontWeight.NORMAL, 25));
        encoursNbre.setStyle("-fx-text-fill: #0A2926");
        
        encoursBox.getChildren().addAll(encoursText, encoursNbre);
        
        
        
        // box des contraventions payées
        
        VBox buyBox = new VBox();
        buyBox.setId("box");
        buyBox.setAlignment(Pos.BASELINE_LEFT);
        buyBox.setSpacing(10);
        

        InputStream inBuy = SysCentral.class.getResourceAsStream("buy-icon.png");
        Image buyImg = new Image(inBuy);
        ImageView buyView = new ImageView(buyImg);
        buyView.setFitHeight(15);
        buyView.setFitWidth(15);
        buyView.setPreserveRatio(true);

        Label buyText = new Label("Payées", buyView);
        buyText.setFont(Font.font("Roboto", FontWeight.NORMAL, 15));
        buyText.setAlignment(Pos.BASELINE_CENTER);
        buyText.setStyle("-fx-text-fill: #F0C500");
   

        Label buyNbre = new Label(Integer.toString(amandeTotal-amandeImpayées));
        buyNbre.setFont(Font.font("Roboto", FontWeight.NORMAL, 25));
        buyNbre.setStyle("-fx-text-fill: #0A2926");
        
        buyBox.getChildren().addAll(buyText, buyNbre);
             
        
        // box finances
        VBox financesBox = new VBox();
        financesBox.setId("box");
        financesBox.setAlignment(Pos.BASELINE_LEFT);
        financesBox.setSpacing(10);
        

        InputStream inFinances = SysCentral.class.getResourceAsStream("finance-icon.png");
        Image financesImg = new Image(inFinances);
        ImageView financesView = new ImageView(financesImg);
        financesView.setFitHeight(15);
        financesView.setFitWidth(15);
        financesView.setPreserveRatio(true);

        Label financesText = new Label("Finances", financesView);
        financesText.setFont(Font.font("Roboto", FontWeight.NORMAL, 15));
        financesText.setAlignment(Pos.BASELINE_CENTER);
        financesText.setStyle("-fx-text-fill: #FF3924");
   

        Label financesNbre = new Label(Integer.toString(finances));
        financesNbre.setFont(Font.font("Roboto", FontWeight.NORMAL, 25));
        financesNbre.setStyle("-fx-text-fill: #0A2926");
        
        financesBox.getChildren().addAll(financesText, financesNbre);
        
        
        stat.getChildren().addAll(encoursBox, buyBox, financesBox);
        
        mainGrid.add(stat, 0, 1);

        // liste observable
        TableColumn idColumn = new TableColumn("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<Contraventions, Integer>("id"));
        idColumn.setPrefWidth(50);

        TableColumn dateDebutColumn = new TableColumn("Date");
        dateDebutColumn.setCellValueFactory(new PropertyValueFactory<Contraventions, String>("DateDebut"));
        dateDebutColumn.setPrefWidth(200);

        TableColumn dateFinColumn = new TableColumn("Delai");
        dateFinColumn.setCellValueFactory(new PropertyValueFactory<Contraventions, String>("dateFin"));
        dateFinColumn.setPrefWidth(200);

        TableColumn matriculeColumn = new TableColumn("Matricule");
        matriculeColumn.setCellValueFactory(new PropertyValueFactory<Contraventions, String>("matricule"));
        matriculeColumn.setPrefWidth(200);

        TableColumn montantColumn = new TableColumn("Montant");
        montantColumn.setCellValueFactory(new PropertyValueFactory<Contraventions, Integer>("montant"));
        montantColumn.setPrefWidth(200);

        TableColumn statutColumn = new TableColumn("Statut");
        statutColumn.setCellValueFactory(new PropertyValueFactory<Contraventions, Integer>("statut"));
        statutColumn.setPrefWidth(250);

        board.setItems(contraventionList);
        board.getColumns().addAll(idColumn, matriculeColumn,
                dateDebutColumn, dateFinColumn, montantColumn, statutColumn);

        mainGrid.add(board, 0, 2);
        
        stage.show();

//        GridPane grid = new GridPane();
//        grid.setAlignment(Pos.CENTER);
//        Scene sc = new Scene(grid, 700, 500);
//        
//        fen.setScene(sc);
//        fen.setTitle("sys central");
//        Text title = new Text("Syteme central");
//        
//        
//        // style
//        title.setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 15));
//        
//        grid.add(title, 0, 0); // fixe à (0,0) et occupe 1 ligne ET 2 c
//        grid.setPadding(new Insets(10));
//        grid.setHgap(10);
//        grid.setVgap(10);
        /// Creation de sys de postes de controle
//        TextField nomChefPoste = new TextField();
//        nomChefPoste.setPromptText("Nom du Chef de poste");
//        
//        
//        TextField lieu = new TextField();
//        lieu.setPromptText("lieu");
//        
//        Button btn = new Button("OK");
//        
//        
//        grid.add(nomChefPoste, 0, 1);
//        grid.add(lieu, 0, 2);        
//        grid.add(btn, 0, 3);
//
//        
//        btn.setOnAction((event) -> {
//            try {
//                tablePoste = new PosteControleTableOperation(isTest);
//                tablePoste.insertion(nomChefPoste.getText(), lieu.getText());
//                
//                
//            } catch (SQLException ex) {
//                Logger.getLogger(SysCentral.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(SysCentral.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//        });
        /// Creation de sys de flash
//        ResultSet allPosteControl = null;
//        
//        try {
//
//            tablePoste = new PosteControleTableOperation(isTest);
//            allPosteControl =  tablePoste.selectionAll();
//            
//            
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(SysCentral.class.getName()).log(Level.SEVERE, null, ex);
//        }
//         
//         
//        ComboBox posteControlBox = new ComboBox();
//        Button btn = new Button("OK");
//        
//        while(allPosteControl.next()){
//            
//            posteControlBox.getItems().add("Poste du chef :"+allPosteControl.getString(3));
//        }
//        
//        grid.add(posteControlBox, 0, 1);
//        grid.add(btn, 0, 2);        
//
//        
//        btn.setOnAction((event) -> {
//            try {
//                tableSystemeFlash = new SystemeFlashTableOperation(isTest);
//                String posteControlBoxTable = (String)posteControlBox.getValue();
//                String poste =  posteControlBoxTable.split(":")[1].trim();
//                ResultSet result = tablePoste.findPoste(poste);
//                if(result.next()){
//                    System.out.println(result.getInt(1));
//                }
//                
//                
////                tableSystemeFlash.insertion(codePoste);
//            } catch (SQLException ex) {
//                Logger.getLogger(SysCentral.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(SysCentral.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//        });
//        
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

        // Serveur socket relié aux clients flash
        serv = new Server();

        try {
            // demarrage du server de socket (écoute sur le port 3000)
            serv.start(PORTE);
            System.out.println("socket chargé");
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
                
                tableAuto.insertion(matricule,1);
                
                tableAmande.insertion(matricule, infractions);
                
            }
        } catch (IOException ex) {
            System.out.println("echec connexion serveur");
        } catch (SQLException ex) {
            System.out.println("impossible de se connecter à la BD : "+ex);
        } catch (ParseException | ClassNotFoundException | java.text.ParseException ex) {
            Logger.getLogger(SysCentral.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
