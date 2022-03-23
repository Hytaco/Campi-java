/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import animatefx.animation.SlideInLeft;
import projet.models.Livraison;
import projet.models.Livraison;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Circle;
import javax.swing.JOptionPane;
import projet.service.ServiceLivraison;
import org.controlsfx.control.Notifications;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author dell
 */
public class AfficherLivraisonsController implements Initializable {

    @FXML
    private Button closeb;
    @FXML
    private Circle circle;
    @FXML
    private Button nom_u;
    @FXML
    private Label e_nom;
    @FXML
    private Label e_mail;
    @FXML
    private Label e_password;
    @FXML
    private Label e_telephone;
    @FXML
    private TextField searchu;
    ObservableList<Livraison> observableList = FXCollections.observableArrayList();
    @FXML
    private TableView<Livraison> TVLivraison;
    @FXML
    private TableColumn<Livraison, Integer> colID;
    @FXML
    private TableColumn<Livraison, Date> colDateLivraison;
    @FXML
    private TableColumn<Livraison, String> colAdresse;
    @FXML
    private TextField tfADRESSE;
    @FXML
    private Button btnA;
    @FXML
    private Button btnM;
    @FXML
    private Button btnS;
    @FXML
    private Label idl;
    @FXML
    private DatePicker tfDATELIVRAISON;
    @FXML
    private ImageView dateCheckMark;
    @FXML
    private ImageView adresseCheckMark;
   Notifications n;
        String erreur;
    @FXML
    private VBox Activite_menu;
    @FXML
    private VBox Convention_menu;
    @FXML
    private VBox Produit_menu;
    @FXML
    private VBox Fonctionnalites_menu;
    @FXML
    private VBox Transport_menu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setLivraisonList();
    }    
       public void setLivraisonList(){
        ServiceLivraison us = new ServiceLivraison();
observableList.clear();
       colID.setCellValueFactory(new PropertyValueFactory<>("id"));
       colDateLivraison.setCellValueFactory(new PropertyValueFactory<>("datelivraison"));
       colAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
    List<Livraison> lu = null;
    lu = us.AfficheLivraison();
        lu.forEach(e -> {
                observableList.add(e);
            });
        TVLivraison.setItems(observableList);
         FilteredList<Livraison> filteredData = new FilteredList<>(observableList,b-> true);
        
          searchu.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate((Livraison c)-> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				
				if (c.getAdresse().toString().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                               
				     else  
				    	 return false; // Does not match.
			});

		});
		SortedList<Livraison> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(TVLivraison.comparatorProperty());
		TVLivraison.setItems(sortedData);
   }
    @FXML
      private void ajouterLivraison(ActionEvent event) {
             if(!testSaisie())
       // if(txtnomentreprise.getText().isEmpty()txtsalaire.getText().isEmpty()txtdescription.getText().isEmpty()txtlocalisation.getText().isEmpty()txtnbrheure.getText().isEmpty()txtnivetude.getText().isEmpty()txtlangue.getText().isEmpty()||date.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Verifier vos champs");
        }
        else { 
                         Livraison comSelect = TVLivraison.getSelectionModel().getSelectedItem();
         ServiceLivraison us = new ServiceLivraison();
         
                 Livraison liv;
                 if (comSelect!=null){
        liv = new Livraison(Integer.parseInt(idl.getText()),java.sql.Date.valueOf(tfDATELIVRAISON.getValue()),tfADRESSE.getText());
          us.ModifierLivraison(liv);

                 }else {
        liv = new Livraison(java.sql.Date.valueOf(tfDATELIVRAISON.getValue()),tfADRESSE.getText());
                 if(us.Ajouter(liv)==1){
                       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("login");
                 alert.setContentText("Succes");
                 alert.showAndWait();
                 setLivraisonList();
            }else {
                         Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("login");
                 alert.setContentText("Echec");
                 alert.show();
                 }      
                 }
                 setLivraisonList();}
    }
   public void showLivraison(){

       
   }    
    @FXML
   private void supprimerLivraison(ActionEvent event) {
        //récuprer la ligne selectionné
        
        Livraison comSelect = TVLivraison.getSelectionModel().getSelectedItem();
        if (comSelect == null) {
           
            return;
        }
        ServiceLivraison us = new ServiceLivraison();

        us.SupprimerLivraison(comSelect);
        //pour refraichir le tableView
        observableList.remove(comSelect);
        setLivraisonList();

    }
       @FXML

      private void modifierLivraison(ActionEvent event) {
                 Livraison comSelect = TVLivraison.getSelectionModel().getSelectedItem();
       tfDATELIVRAISON.setValue(Instant.ofEpochMilli(comSelect.getDatelivraison().getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
       tfADRESSE.setText(comSelect.getAdresse());
       idl.setText(String.valueOf(comSelect.getId()));
                  
                         
                  
      }

    @FXML
    private void Close(ActionEvent event) {
              Stage window = (Stage) closeb.getScene().getWindow();
        window.close();
    }
    
    

private Boolean testAdresse() {
        int nbNonChar = 0;
        for (int i = 1; i < tfADRESSE.getText().trim().length(); i++) {
            char ch = tfADRESSE.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && tfADRESSE.getText().trim().length() >= 1) {
            adresseCheckMark.setImage(new Image("image/checkMark.png"));
            return true;
        } else {
            adresseCheckMark.setImage(new Image("image/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }




private Boolean testDate() {

     Date date=java.sql.Date.valueOf(tfDATELIVRAISON.getValue());
     java.util.Date aujourdhui = new java.util.Date();
    if (tfDATELIVRAISON.getValue().lengthOfMonth()<1)
    {
       dateCheckMark.setImage(new Image("image/erreurCheckMark.png"));
           return false;  
    }
     if (tfDATELIVRAISON.getValue() != null) {
            if (date.compareTo(aujourdhui) > 0) {
                dateCheckMark.setImage(new Image("image/checkMark.png"));
                return true;
            } else  {
                dateCheckMark.setImage(new Image("image/erreurCheckMark.png"));
                 return false;
            }
           
        }
     else {
      dateCheckMark.setImage(new Image("image/erreurCheckMark.png"));
           return false;}
    }    
    

private Boolean testSaisie() {
        erreur = "";
       
       
        
         if(!testAdresse())
        {
            erreur = erreur + ("Veuillez verifier le champ détails vide \n");
        }
          
          
         if(!testDate())
        {
            erreur = erreur + ("Veuillez verifier que vous avez choisi une date superieur a la date courante \n");
        }
        if (!testDate()||!testAdresse() ) {
            n = Notifications.create()
                    .title("Erreur de format")
                    .text(erreur)
                    .graphic(null)
                    .position(Pos.TOP_CENTER);
                    //.hideAfter(Duration.ofSeconds(6));
            n.showInformation();
        }

        return  testAdresse() && testDate() ;
      
    }

    private void Livraisons(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AfficherLivreurs.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

  


    @FXML
    private void stat(javafx.scene.input.MouseEvent event) throws IOException {
         Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/stat.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ToUtilisateur(ActionEvent event) throws IOException {
         Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/AfficherUtilisateur.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    private void To_ProfileB(ActionEvent event) throws IOException {
         Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/ProfileB.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void To_Activite(ActionEvent event) {
        if (!(Activite_menu.isVisible())){
             new SlideInLeft(Activite_menu).play();
                 Activite_menu.setVisible(true);
        }else {
                 Activite_menu.setVisible(false);
        }
    }

    @FXML
    private void To_Convention(ActionEvent event) {
           if (!(Convention_menu.isVisible())){
             new SlideInLeft(Convention_menu).play();
                 Convention_menu.setVisible(true);
        }else {
                 Convention_menu.setVisible(false);
        }
    }

    @FXML
    private void To_Produit(ActionEvent event) throws IOException {
         Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/AjoutproduitsGUI.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void To_Transport(ActionEvent event)  {
           if (!(Transport_menu.isVisible())){
             new SlideInLeft(Transport_menu).play();
                 Transport_menu.setVisible(true);
        }else {
                 Transport_menu.setVisible(false);
        }
    }

    @FXML
    private void To_Fonctionnalite(ActionEvent event) {
           if (!(Fonctionnalites_menu.isVisible())){
             new SlideInLeft(Fonctionnalites_menu).play();
                 Fonctionnalites_menu.setVisible(true);
        }else {
                 Fonctionnalites_menu.setVisible(false);
        }
    }

    @FXML
    private void To_Programme(ActionEvent event) throws IOException {
         Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/interfacep.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void To_Evenement(ActionEvent event) throws IOException {
          Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/evenements.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void To_Locaux(ActionEvent event) throws IOException {
         Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/interfaceloc.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void To_Publicite(ActionEvent event) throws IOException {
          Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/PubliciteGUI.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void To_Sponsor(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/sponors.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void To_Categorie(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/CategoriesGUI.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void To_Commande(ActionEvent event)throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/AfficherCommandes.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void To_Transporteur(ActionEvent event)throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/tansback.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void To_Livreur(ActionEvent event)throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/AfficherLivreurs.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void To_Reclamation(ActionEvent event)throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/reclamback.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void To_Alert(ActionEvent event)throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/AlertsGUI.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void To_ProduitM(ActionEvent event) {
          if (!(Produit_menu.isVisible())){
             new SlideInLeft(Produit_menu).play();
                 Produit_menu.setVisible(true);
        }else {
                 Produit_menu.setVisible(false);
        }
    }

    @FXML
    private void accueilback(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/AccueilBack.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void To_livraison(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/AfficherLivraisons.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void To_Proposevent(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/propositionback.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
