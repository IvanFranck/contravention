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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

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
    private boolean isTest = true;

    //data and ui control
    private ObservableList<Contraventions> contraventionList = FXCollections.observableArrayList();
    private TableView<Contraventions> board = new TableView<Contraventions>();

    @Override
    public void start(Stage fen) throws SQLException, ClassNotFoundException, ParseException, java.text.ParseException {

        board.setEditable(true);
        board.setFixedCellSize(30);
        
        String matricule = null;
        int idAmande = 0;
        Date dateDebut = null;
        Date dateFin = null;
        int statut = 0;
        int amandeTotal = 0;
        int amandeImpayées = 0;

        tableAmande = new AmandeTableOperations(isTest);
        tableAuto = new AutomobileTableOperation(isTest);
        tableContravention = new ContraventionTableOperation(isTest);
        
        String[] libelleTable  = new String[5];
        int[] prixTable = new int[5];
//        int[] codeTable = new int[5];
        int compteur = 0;
        int montantAmande = 0; 
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
             
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            String strDateDebut = dateFormat.format(dateDebut);
            String strDateFin = dateFormat.format(dateFin);
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(strDateDebut));
            c.add(Calendar.DATE, 52);  // number of days to add
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
         
        
        
        

        BorderPane body = new BorderPane();
        body.setId("body");

        Scene scene = new Scene(body, 1500, 800);
        scene.getStylesheets().add(this.getClass().getResource("back.css").toExternalForm());
        fen.setScene(scene);

        GridPane grid = new GridPane();
        body.setCenter(grid);

        VBox BorderBox = new VBox();
        body.setRight(BorderBox);

        HBox stat = new HBox();
        stat.setSpacing(20);
        
        HBox greating = new HBox();
        grid.add(greating, 0, 0);

        GridPane.setMargin(greating, new Insets(80, 16, 16, 16));
        GridPane.setMargin(stat, new Insets(0, 16, 24, 16));
        GridPane.setMargin(board, new Insets(0, 16, 0, 16));

        

        Text hello = new Text("Hello their admin !");
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
        
        grid.add(stat, 0, 1);

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

        grid.add(board, 0, 2);

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
                
                tableAuto.insertion(matricule);
                
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
