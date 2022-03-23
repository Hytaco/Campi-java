/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import animatefx.animation.SlideInDown;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import projet.models.produits;
import projet.models.publicite;
import projet.service.PubliciteService;
import projet.service.UserSession;

/**
 * FXML Controller class
 *
 * @author Taher
 */
public class DetailproduitsController implements Initializable {

    @FXML
    private ImageView image;
    private produits o;
    @FXML
    private AnchorPane brand;
    @FXML
    private Button Categories;
    @FXML
    private Button Alerts;
    @FXML
    private Label nom;
    @FXML
    private Label description;
    @FXML
    private Label prix;
    @FXML
    private Label quantite;
    @FXML
    private Button ajouter;
    @FXML
    private Button Retour;
    @FXML
    private VBox vboxx;
    @FXML
    private Button dislike;
    @FXML
    private Button like;
    @FXML
    private Pane pane_m;
    @FXML
    private Button to_profile;
    @FXML
    private Button to_panier;
    @FXML
    private Button Reclamations;
    @FXML
    private Label alerts;
    @FXML
    private Button Programmes;
    @FXML
    private Button Locaux;
    @FXML
    private Button Evenements;
    @FXML
    private Button Proposition;
    @FXML
    private Label Propositions;
    @FXML
    private Circle circle;
    @FXML
    private Button nom_u;
    @FXML
    private Button to_menu;
    @FXML
    private Button Accueil;
    /**
     * Initializes the controller class.
     */
    
         
     public ImageView setImage(String  nom){
        ImageView   image = new ImageView ();
        File file = new File ("C:/xampp/web/web/Hytaco/public/images/properties/"+nom);
Image imageForFile = null;
        try {
             System.out.println(file.toURI().toURL());
            imageForFile = new Image(file.toURI().toURL().toExternalForm());
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProduitsFGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
            image.setImage(imageForFile);
            return image ;
    }
 
    
    public void setVBOXP(){
int count =0 ;
int coun =0 ;
int cou =0 ;
HBox hbell = new HBox();

PubliciteService us = new PubliciteService();
           List<publicite> lu = null;
           lu = us.afficherpublicite();

    for (int i = 0; i < lu.size(); i++) {     
        count++;  coun++;cou++;

     VBox vbell1 = new VBox();
     Label nom = new Label();
     nom.setText(lu.get(i).getNom_publicite());

     Label desc = new Label();
     desc.setText(lu.get(i).getLien_publicite());
               vbell1.setAlignment(Pos.CENTER);

ImageView image = setImage(lu.get(i).getImage_name());
image.setFitHeight(60);
image.setFitWidth(60);
vbell1.getChildren().addAll(image,nom,desc);

hbell.getChildren().add(vbell1);
hbell.setMargin(vbell1, new Insets(50, 0, 0, 50));
//    hbell.getChildren().add(image);
if ((count % 4)==0)
{
                   vbell1.setAlignment(Pos.CENTER);

vboxx.getChildren().add(hbell);
hbell = new HBox();
}

}
    if ((coun % 3)==0)
{
vboxx.getChildren().add(hbell);
hbell = new HBox();
}
    
        if ((cou % 1)==0)
{
vboxx.getChildren().add(hbell);
hbell = new HBox();
}
        }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                   setImageU();

        setVBOXP();
    }    
     public void setImageU() {
               nom_u.setText(UserSession.getInstace().getUtilisateur().getPrenom()+" "+UserSession.getInstace().getUtilisateur().getNom());

        File file = new File ("C:/xampp/web/web/Hytaco/public/images/properties/" +UserSession.getInstace().getUtilisateur().getImage_name());
        try {
            circle.setFill(new ImagePattern(new Image(file.toURI().toURL().toExternalForm())));
        } catch (MalformedURLException ex) {
            Logger.getLogger(AfficherUtilisateursController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void initData (produits o){
    
    this.o=o;

        
     File file = new File ("C:/xampp/web/web/Hytaco/public/images/properties/"+o.getImage_name());
    Image imageForFile = null ;
        try {
            
         //   cl_mail.setText(o.getMailen());
          nom.setText(o.getNom_produit());
      nom.setWrapText(true);

          description.setText(o.getDescription_produit());
          description.setWrapText(true);
          prix.setText(o.getPrix_produit()+"dt");
          quantite.setText(o.getQuantite_produit()+"Pieces");
            System.out.println(file.toURI().toURL());
            imageForFile = new Image(file.toURI().toURL().toExternalForm());
        } catch (MalformedURLException ex) {
            Logger.getLogger(DetailproduitsController.class.getName()).log(Level.SEVERE, null, ex);
        }
   image.setImage(imageForFile);}


 


    @FXML
    private void ajouter(ActionEvent event) {
        o.getImage_name();
        UserSession.getInstace().AddLignePanier(o);
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/ProduitsFGUI.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void dislike(ActionEvent event) {
    }

    @FXML
    private void like(ActionEvent event) {
    }

   
    @FXML
    private void ToMenu(ActionEvent event) {
        if (!(pane_m.isVisible())){
             new SlideInDown(pane_m).play();
                 pane_m.setVisible(true);
        }else {
                 pane_m.setVisible(false);
        }
    }

    @FXML
    private void Deconnexion(ActionEvent event) {
          Parent   root=null;
          UserSession.setInstace(null);
        try {
            root = FXMLLoader.load(getClass().getResource("/interfaces/Login.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ProfileBController.class.getName()).log(Level.SEVERE, null, ex);
        }
                                        
                    Stage window = (Stage) to_profile.getScene().getWindow();
                    window.setScene(new Scene(root));

    }
    @FXML
    private void Categories(ActionEvent event) throws IOException {
                Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/accueilFGUI.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Alerts(ActionEvent event) throws IOException {
                Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/AlertsFGUI.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Reclamations(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/reclamationfront.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Programmes(ActionEvent event)throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/AnnonceFront.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Locaux(ActionEvent event)throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/LocauxFront.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Evenements(ActionEvent event)throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/front.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Proposition(ActionEvent event)throws IOException {
        
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/propositionfront.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
     @FXML
    private void To_Panier(ActionEvent event) {
         Parent   root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("/interfaces/Panier.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ProfileBController.class.getName()).log(Level.SEVERE, null, ex);
        }
                                        
                    Stage window = (Stage) to_profile.getScene().getWindow();
                    window.setScene(new Scene(root));

    }

    @FXML
    private void To_Profile(ActionEvent event) {
                Parent   root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("/interfaces/Profile.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ProfileBController.class.getName()).log(Level.SEVERE, null, ex);
        }
                                        
                    Stage window = (Stage) to_profile.getScene().getWindow();
                    window.setScene(new Scene(root));

    }

    @FXML
    private void To_Accueil(ActionEvent event) {
               Parent   root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("/interfaces/accueilFGUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ProfileBController.class.getName()).log(Level.SEVERE, null, ex);
        }
                                        
                    Stage window = (Stage) to_profile.getScene().getWindow();
                    window.setScene(new Scene(root));

    }

    
}
