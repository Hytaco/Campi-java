/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import animatefx.animation.SlideInLeft;
import projet.models.Admin;
import projet.service.ServiceUtilisateur;
import projet.service.UserSession;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import projet.controller.AfficherUtilisateursController;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author Hassene
 */
public class ProfileBController implements Initializable {

    @FXML
    private Button closeb;
    @FXML
    private Circle circle;
    @FXML
    private Button nom_u;
    @FXML
    private Circle circle1;
    @FXML
    private Button modifier;
    @FXML
    private Label e_nom;
    @FXML
    private TextField nom_i;
    @FXML
    private Label e_mail;
    @FXML
    private TextField email_i;
    @FXML
    private Label e_telephone;
    @FXML
    private TextField telephone_i;
    @FXML
    private Label e_prenom;
    @FXML
    private TextField prenom_i;
    @FXML
    private TextField adresse_i;
    @FXML
    private TextField tf_image;
    @FXML
    private Button importb;
    @FXML
    private Label Nomc;
    private Button to_commandes;
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
 /**
     * Initializes the controller class.
     */
     public void setImage(String name,Circle circle) {
        File file = new File ("C:\\xampp\\web\\web\\Hytaco\\public\\images\\properties\\"+name);
       // circle.setRadius(55);
        try {
           
           System.out.println(new Image(file.toURI().toURL().toExternalForm()));
            circle.setFill(new ImagePattern(new Image(file.toURI().toURL().toExternalForm())));
        } catch (MalformedURLException ex) {
            Logger.getLogger(AfficherUtilisateursController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
      public void setUser(){
          nom_u.setText(UserSession.getInstace().getUtilisateur().getPrenom()+" "+UserSession.getInstace().getUtilisateur().getNom());
           setImage(UserSession.getInstace().getUtilisateur().getImage_name(),circle);
         nom_i.setText(UserSession.getInstace().getUtilisateur().getNom());
        prenom_i.setText(UserSession.getInstace().getUtilisateur().getPrenom());
        adresse_i.setText(UserSession.getInstace().getUtilisateur().getAdresse());
        telephone_i.setText(String.valueOf(UserSession.getInstace().getUtilisateur().getTelephone()));
        email_i.setText(UserSession.getInstace().getUtilisateur().getEmail());
        tf_image.setText(UserSession.getInstace().getUtilisateur().getImage_name());
        if (UserSession.getInstace().getUtilisateur().getImage_name()!=null){
            setImage(UserSession.getInstace().getUtilisateur().getImage_name(),circle1);
        }
      Nomc.setText(prenom_i.getText()+" "+nom_i.getText());

        email_i.textProperty().addListener((observable, oldValue, newValue) -> {
                String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
Pattern pattern = Pattern.compile(masque);
Matcher controler = pattern.matcher(newValue);
if (controler.matches()){
e_mail.setText("");
 
}else{
e_mail.setText("mail invalide ");
}
                });
             
             telephone_i.textProperty().addListener((observable, oldValue, newValue) -> {
                String masque = "[0-9]*";
Pattern pattern = Pattern.compile(masque);
Matcher controler = pattern.matcher(newValue);
if (controler.matches()){
    if (newValue.length()==8){
e_telephone.setText("");
    }
 
}else{
e_telephone.setText("le numero du telephone est invalid");
}
                });
             nom_i.textProperty().addListener((observable, oldValue, newValue) -> {
                String masque = "[a-zA-Z]*";
Pattern pattern = Pattern.compile(masque);
Matcher controler = pattern.matcher(newValue);
if (controler.matches()){
e_nom.setText(""); 
}else{
e_nom.setText("votre nom est invalid");
}
                });
             prenom_i.textProperty().addListener((observable, oldValue, newValue) -> {
                String masque = "[a-zA-Z]*";
Pattern pattern = Pattern.compile(masque);
Matcher controler = pattern.matcher(newValue);
if (controler.matches()){
e_prenom.setText("");
 
}else{
e_prenom.setText("votre prenom est invalid");
}
                });
            
    }
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
setUser() ;
nom_u.setText(UserSession.getInstace().getUtilisateur().getPrenom()+" "+UserSession.getInstace().getUtilisateur().getNom());
    }    

    @FXML
  private void modifierUtilisateur(ActionEvent event) {
                if ( e_nom.getText().equals("")&& e_prenom.getText().equals("")&& e_telephone.getText().equals("")&& e_mail.getText().equals("") )
                    {
        ServiceUtilisateur us = new ServiceUtilisateur();
      int state= 0; 
      Admin admin = new Admin(UserSession.getInstace().getUtilisateur().getId(),nom_i.getText(),prenom_i.getText(),Integer.parseInt(telephone_i.getText())
               ,adresse_i.getText(),email_i.getText(),tf_image.getText());
     state = us.ModifierUtilisateur(admin);
          if (state==1){
              UserSession.setInstace(admin);
              System.out.println(admin);
              System.out.println(UserSession.getInstace().getUtilisateur());
            
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("login");
                    alert.setContentText("Vos infos sont modifié avec succes");
                    alert.showAndWait();
                    setUser();
    }
                    }
                    else{
             Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Inscription");
                 alert.setContentText("Veuillez voir vos parametres");
                 alert.show();
                    }
     }

    @FXML
   private void importer(ActionEvent event) {
       String fileName = null ;
        FileChooser fc = new FileChooser();
        File SelectedFile = fc.showOpenDialog(null);
         fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", ".jpg", ".png"));
           //  e.setImage_event(SelectedFile.getAbsolutePath());
        if (SelectedFile != null) {

            String fcs = SelectedFile.toString();
            int index = fcs.lastIndexOf('\\');
            if (index > 0) {
                 fileName = fcs.substring(index + 1);
            }

        }
     String key =  UUID.randomUUID().toString();
        String fcs = SelectedFile.getAbsolutePath();
        File source = new File(fcs);
        File destination = new File("C:\\xampp\\web\\web\\Hytaco\\public\\images\\properties\\" +key+ fileName);
        String url =  destination.getAbsolutePath();
                        System.out.println(url);

        if (!destination.exists()) {
            try {
                FileUtils.copyFile(source, destination);
            } catch (Exception E) {

            }
               tf_image.setText(key+ fileName);
    }
    }

    @FXML
    private void Close(ActionEvent event) {
              Stage window = (Stage) closeb.getScene().getWindow();
        window.close();
    }

    private void ToCommande(ActionEvent event) {
          Parent   root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("/interfaces/AfficherCommandes.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ProfileBController.class.getName()).log(Level.SEVERE, null, ex);
        }
                           
                    Stage window = (Stage) to_commandes.getScene().getWindow();
                    window.setScene(new Scene(root));
    }

    private void ToUtilisateurs(ActionEvent event) {
         Parent   root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("/interfaces/AfficherUtilisateur.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ProfileBController.class.getName()).log(Level.SEVERE, null, ex);
        }
                                        
                    Stage window = (Stage) to_commandes.getScene().getWindow();
                    window.setScene(new Scene(root));

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
}
