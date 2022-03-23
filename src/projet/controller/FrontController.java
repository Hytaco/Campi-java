/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import animatefx.animation.SlideInDown;
import java.io.File;
import projet.models.evenements;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import projet.service.ServiceEvenement;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import projet.models.sponsors;
import projet.service.ServiceSponsors;
import projet.service.UserSession;


/**
 * FXML Controller class
 *
 * @author Firqs
 */
public class FrontController implements Initializable {

    @FXML
    private VBox affich_even;
    private ServiceEvenement cp = new ServiceEvenement();
      private evenements e;

     List<evenements> liste = new ArrayList <evenements>();
    @FXML
    private VBox affich_even1;
    private ServiceSponsors cps = new ServiceSponsors();
      private sponsors s;

     List<sponsors> listes = new ArrayList <sponsors>();
    @FXML
    private Pane pane_m;
    @FXML
    private Button to_profile;
    @FXML
    private Button to_panier;
    @FXML
    private Button Reclamations;
    @FXML
    private Button Alerts;
    @FXML
    private Label alerts;
    @FXML
    private Button Categories;
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
      public void setImageU() {
               nom_u.setText(UserSession.getInstace().getUtilisateur().getPrenom()+" "+UserSession.getInstace().getUtilisateur().getNom());

        File file = new File ("C:\\xampp\\web\\web\\Hytaco\\public\\images\\properties\\" +UserSession.getInstace().getUtilisateur().getImage_name());
        try {
            circle.setFill(new ImagePattern(new Image(file.toURI().toURL().toExternalForm())));
        } catch (MalformedURLException ex) {
            Logger.getLogger(AfficherUtilisateursController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setImageU();
         affiche();
        // TODO
   HBox item = new HBox();
                    affich_even.getChildren().add(item);
           liste=cp.listerPromotion();
int taille=liste.size();
          for( int i=0;i<taille;i++)
            {
               
                    try {
                        if(i % 4 == 0){
                            item = new HBox();
                            affich_even.getChildren().add(item);
                        }
                        VBox content = new VBox();
                           Image image = new Image( new FileInputStream("C:/xampp/web/web/Hytaco/public/images/properties/"+liste.get(i).getImage_name()));
                        ImageView imageView = new ImageView(image);
                        Label title = new Label();
                        e=liste.get(i);
                        //Button btn1 =new Button("detail");
                        
                       
                        
                        Label prixpromo = new Label(liste.get(i).getNom());
                        prixpromo.setStyle("-fx-font-weight: bold");
                        imageView.setFitHeight(190);
                        imageView.setFitWidth(200);
                        
                        content.getChildren().addAll(imageView,title,prixpromo);
                        Button btn = new Button("",content);
                                                       System.out.println(e.getNom());
                          evenements e1 = new evenements(e.getId(),e.getNom(),e.getDate(),e.getImage_name(),e.getNbrplace(),e.getDatef(),e.getLieu(),e.getDescription());

                         btn.setOnAction(event -> {
                            try {
                                
                                //detailProduit(event);
                               

                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/details.fxml"));
                               /* ImageController dp=loader.getController();
                                dp.setLb_idPatient(o.getId());*/
                                
                                Scene scene = new Scene(loader.load());
                                
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                
                                stage.setScene(scene);
                                
                                stage.show();
                                 DetailsController controller = loader.<DetailsController>getController();
       
                                  controller.initData(e1);
                                
                                
                                
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }         
        
            });
                        btn.setPrefWidth(100);
                        item.getChildren().add(btn);
                        affich_even.setSpacing(40);
                        item.setSpacing(20);
                    }
                    catch (FileNotFoundException ex) {
                        Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }

    
          
        

                  
    }

    
    
    
                 
         public void affiche(){  
          
   HBox items = new HBox();
                    affich_even1.getChildren().add(items);
           listes=cps.listerPromotion();
int tailles=listes.size();
          for( int is=0;is<tailles-1;is++)
            {
                System.out.println(listes.get(is).getImage_name());
               
                   
                        if(is % 4 == 0){
                            items = new HBox();
                            affich_even1.getChildren().add(items);
                        }
                        VBox contents = new VBox();
                           Image images = null;
                      


       try {
           images = new Image( new FileInputStream("C:/xampp/web/web/Hytaco/public/images/properties/"+listes.get(is).getImage_name()));
       } catch (FileNotFoundException ex) {
           Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
       }
                        ImageView imageView = new ImageView(images);
                        Label titles = new Label();
                        s=listes.get(is);
                        //Button btn1 =new Button("detail");
                        
                       
                        
                        Label prixpromo = new Label(listes.get(is).getNom());
                        prixpromo.setStyle("-fx-font-weight: bold");
                        imageView.setFitHeight(140);
                        imageView.setFitWidth(130);
                              
                        contents.getChildren().addAll(imageView,titles,prixpromo);
                        Button btns = new Button("",contents);
                                                       System.out.println(s.getNom());

                         btns.setOnAction(events -> {
                            try {
                                
                                //detailProduit(event);
                               

                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/details.fxml"));
                               /* ImageController dp=loader.getController();
                                dp.setLb_idPatient(o.getId());*/
                                
                                Scene scene = new Scene(loader.load());
                                
                                Stage stage = (Stage) ((Node) events.getSource()).getScene().getWindow();
                                
                                stage.setScene(scene);
                                
                                stage.show();
                                 DetailsController controller = loader.<DetailsController>getController();
       
                                
                                
                                
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }         
        
            });
                        btns.setPrefWidth(100);
                        items.getChildren().add(btns);
                        affich_even1.setSpacing(40);
                        items.setSpacing(20);
                    }
                   
    }
    private void proposer(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/propositionfront.fxml"));/* Exception */
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
