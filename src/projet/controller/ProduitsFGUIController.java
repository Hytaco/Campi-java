/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import animatefx.animation.SlideInDown;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import projet.controller.AjoutProduitsGUIController;
import projet.models.produits;
import projet.models.publicite;
import projet.service.ProduitsService;
import projet.service.PubliciteService;
import projet.service.UserSession;
/**
 * FXML Controller class
 *
 * @author Taher
 */
public class ProduitsFGUIController implements Initializable {

    @FXML
    private AnchorPane brand;

    @FXML
    private TextField Searchp;
    @FXML
    private VBox vboxx;
    @FXML
    private Button Categories;
    @FXML
    private Button Alerts;
    @FXML
    private VBox affich_produit;
    private ProduitsService cp = new ProduitsService();
      private produits o;

     List<produits> liste = new ArrayList <produits>();
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
     public void setImageU() {
               nom_u.setText(UserSession.getInstace().getUtilisateur().getPrenom()+" "+UserSession.getInstace().getUtilisateur().getNom());

        File file = new File ("C:\\xampp\\web\\web\\Hytaco\\public\\images\\properties\\" +UserSession.getInstace().getUtilisateur().getImage_name());
        try {
            circle.setFill(new ImagePattern(new Image(file.toURI().toURL().toExternalForm())));
        } catch (MalformedURLException ex) {
            Logger.getLogger(AfficherUtilisateursController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void initialize(URL url, ResourceBundle rb) {
                   setImageU();

        Font.loadFont(getClass().getResourceAsStream("/css/SourceSansPro-Regular.ttf"), 14);
              setVBOXP();
HBox item = new HBox();
                    affich_produit.getChildren().add(item);
           liste=cp.listerPromotion();
int taille=liste.size();
          for( int i=0;i<taille;i++)
            {
               
                    try {
                        if(i % 4 == 0){
                            item = new HBox();
                            affich_produit.getChildren().add(item);
                        }
                        VBox content = new VBox();
                           Image image = new Image( new FileInputStream("C:/xampp/web/web/Hytaco/public/images/properties/"+liste.get(i).getImage_name()));
                        ImageView imageView = new ImageView(image);
                       
                        Label title = new Label();
                        o=liste.get(i);                        
                        Label description = new Label((liste.get(i).getDescription_produit()));
                        description.setStyle("-fx-strikethrough: true");
                        description.getStyleClass().add("barre");
                                                
                        
                        Label prixpromo = new Label(liste.get(i).getNom_produit());
                        prixpromo.setStyle("-fx-font-weight: bold");
                        imageView.setFitHeight(150);
                        imageView.setFitWidth(200);
                        
                        content.getChildren().addAll(imageView,title,prixpromo,description);
                        Button btn = new Button("",content);
                          produits o1 = new produits(o.getId_produit(), o.getNom_produit(),o.getDescription_produit(),o.getImage_name(),o.getQuantite_produit(),o.getPrix_produit());

                         btn.setOnAction(event -> {
                            try {  
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/DetailproduitsController.fxml"));
                               /* ImageController dp=loader.getController();
                                dp.setLb_idPatient(o.getId());*/
                                Scene scene = new Scene(loader.load());
                                
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                
                                stage.setScene(scene);
                                
                                stage.show();
                                 DetailproduitsController controller = loader.<DetailproduitsController>getController();
       
                                  controller.initData(o1);
                                
                                
                                
                            } catch (IOException ex) {
                                Logger.getLogger(ProduitsFGUIController.class.getName()).log(Level.SEVERE, null, ex);
                            }         
        
            });
                        btn.setPrefWidth(200);
                        item.getChildren().add(btn);
                        affich_produit.setSpacing(50);
                        item.setSpacing(20);
                    }
                    catch (FileNotFoundException ex) {
                        Logger.getLogger(ProduitsFGUIController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }
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

    private void campi(ActionEvent event) throws IOException {
         Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/CategoriesGUI.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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