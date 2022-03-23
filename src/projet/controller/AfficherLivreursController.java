/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import animatefx.animation.SlideInLeft;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import projet.models.Livreur;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import projet.service.ServiceLivreur;
import projet.utils.JavaMailUtil ;
/**
 * FXML Controller class
 *
 * @author dell
 */
public class AfficherLivreursController implements Initializable {

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
    @FXML
    private TableView<Livreur> TVLivreur;
    @FXML
    private TableColumn<?, ?> colTelephone;
    @FXML
    private TableColumn<?, ?> colMail;
    @FXML
    private TableColumn<?, ?> colNom;
    @FXML
    private TableColumn<?, ?> colAdressel;
    @FXML
    private TextField telephonel;
    @FXML
    private TextField noml;
    @FXML
    private TextField adressel;
    @FXML
    private TextField maill;
    @FXML
    private Button ajoutliv;
    @FXML
    private Button mofliv;
    @FXML
    private Button suplivr;
                          ObservableList<Livreur> observableList_l = FXCollections.observableArrayList();
    @FXML
    private Label idll;
    @FXML
    private ImageView nomCheckMark;
    @FXML
    private ImageView adresseCheckMark;
    @FXML
    private ImageView mailCheckMark;
  Notifications n;
        String erreur;
    @FXML
    private Button topdf;
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
      public void  setLivreurList(){
        ServiceLivreur us = new ServiceLivreur();
observableList_l.clear();
       colTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
       colMail.setCellValueFactory(new PropertyValueFactory<>("email"));
       colAdressel.setCellValueFactory(new PropertyValueFactory<>("adresse"));
       colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    List<Livreur> lu = null;
        try {
            lu = us.AfficheLivreur();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        lu.forEach(e -> {
           System.out.println(e);
                observableList_l.add(e);
            });
        
        TVLivreur.setItems(observableList_l);
         FilteredList<Livreur> filteredData = new FilteredList<>(observableList_l,b-> true);
        
          searchu.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate((Livreur c)-> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (c.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (c.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}else if (c.getAdresse().toString().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                               
				     else  
				    	 return false; // Does not match.
			});

		});
		SortedList<Livreur> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(TVLivreur.comparatorProperty());
		TVLivreur.setItems(sortedData);
   }
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setLivreurList();
    }    
    
@FXML
    private void ajouterLivreur(ActionEvent event) throws Exception {
          if(!testSaisie())
       // if(txtnomentreprise.getText().isEmpty()txtsalaire.getText().isEmpty()txtdescription.getText().isEmpty()txtlocalisation.getText().isEmpty()txtnbrheure.getText().isEmpty()txtnivetude.getText().isEmpty()txtlangue.getText().isEmpty()||date.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Verifier vos champs");
        }
        else { 
                Livreur comSelect = TVLivreur.getSelectionModel().getSelectedItem();
         ServiceLivreur us = new ServiceLivreur();
         
                 Livreur liv;
                 if (comSelect!=null){
                             System.out.println(telephonel.getText());

        liv = new Livreur(Integer.parseInt(idll.getText()),Integer.parseInt(telephonel.getText()),adressel.getText(),maill.getText(),noml.getText());
        us.ModifierLivreur(liv);
                 }else {
        liv = new Livreur(Integer.parseInt(telephonel.getText()),adressel.getText(),maill.getText(),noml.getText());
                 if(us.Ajouter(liv)==1){
                       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("login");
                 alert.setContentText("Succes");
                 alert.showAndWait();
                 setLivreurList();
            }else {
                         Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("login");
                 alert.setContentText("Echec");
                 alert.show();
                 }      
                 }
                 JavaMailUtil mail = new JavaMailUtil();
           mail.sendMail(liv.getEmail());
                 setLivreurList();
                }
    }

     @FXML
    private void modifierLivreur(ActionEvent event) {
         Livreur comSelect = TVLivreur.getSelectionModel().getSelectedItem();
         telephonel.setText(String.valueOf(comSelect.getTelephone()));
         adressel.setText(comSelect.getAdresse());
         maill.setText(comSelect.getEmail());
         noml.setText(comSelect.getNom());
         idll.setText(String.valueOf(comSelect.getId()));
    }

    @FXML
    private void supprimerLivreur(ActionEvent event) {
        Livreur comSelect = TVLivreur.getSelectionModel().getSelectedItem();
        if (comSelect == null) {
            return;
        }
        ServiceLivreur us = new ServiceLivreur();

        us.SupprimerLivreur(comSelect);
        //pour refraichir le tableView
        observableList_l.remove(comSelect);
        setLivreurList();
    }
    
    @FXML
    private void Close(ActionEvent event) {
              Stage window = (Stage) closeb.getScene().getWindow();
        window.close();
    }

    
    
private Boolean testAdresse() {
        int nbNonChar = 0;
        for (int i = 1; i < adressel.getText().trim().length(); i++) {
            char ch = adressel.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && adressel.getText().trim().length() >= 1) {
            adresseCheckMark.setImage(new Image("image/checkMark.png"));
            return true;
        } else {
            adresseCheckMark.setImage(new Image("image/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }

private Boolean testNom() {
        int nbNonChar = 0;
        for (int i = 1; i < noml.getText().trim().length(); i++) {
            char ch = noml.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && noml.getText().trim().length() >= 1) {
            nomCheckMark.setImage(new Image("image/checkMark.png"));
            return true;
        } else {
            nomCheckMark.setImage(new Image("image/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }


private Boolean testEmail() {
        int nbNonChar = 0;
        for (int i = 1; i < maill.getText().trim().length(); i++) {
            char ch = maill.getText().charAt(i);
           
        }

        if (nbNonChar == 0 && maill.getText().trim().length() >= 1) {
            mailCheckMark.setImage(new Image("image/checkMark.png"));
            return true;
        } else {
            mailCheckMark.setImage(new Image("image/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }



  
    

private Boolean testSaisie() {
        erreur = "";
       
       
        
         if(!testAdresse())
        {
            erreur = erreur + ("Veuillez verifier le champ adresse \n");
        }
          
          
         if(!testNom())
        {
            erreur = erreur + ("Veuillez verifier que le champ nom \n");
        }
         
         
          if(!testEmail())
        {
            erreur = erreur + ("Veuillez verifier que le champ email\n");
        }
        if (!testNom()||!testAdresse()||!testEmail() ) {
            n = Notifications.create()
                    .title("Erreur de format")
                    .text(erreur)
                    .graphic(null)
                    .position(Pos.TOP_CENTER);
                    //.hideAfter(Duration.ofSeconds(6));
            n.showInformation();
        }

        return  testAdresse() && testEmail() && testNom() ;
      
    }

    @FXML
    private void To_Pdf(ActionEvent event) throws DocumentException {
                 Livreur liv = TVLivreur.getSelectionModel().getSelectedItem();
Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(System.getProperty("user.dir") + "\\src\\projet\\utils\\"+liv.getNom()+".pdf"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AfficherLivreursController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        doc.open();
        String para = " Nom = "+liv.getNom()+" \n"
                + " Adresse = "+liv.getAdresse()+" \n"
                +" Telephone = "+String.valueOf(liv.getTelephone())+" \n"
                 +" Email = "+liv.getEmail();
        
        doc.add(new Paragraph(para));
        doc.close();
        }
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
