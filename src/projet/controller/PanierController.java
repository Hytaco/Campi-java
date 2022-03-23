/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import projet.controller.ProfileBController;
import projet.models.Client;
import projet.models.Commande;
import projet.models.LigneCommande;
import projet.models.Produit;
import projet.models.Utilisateur;
import projet.service.ServiceCommande;
import projet.service.ServiceUtilisateur;
import projet.service.UserSession;
import animatefx.animation.FadeIn;
import animatefx.animation.SlideInDown;
import animatefx.animation.ZoomIn;
import animatefx.animation.ZoomOut;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconName;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import projet.models.produits;

/**
 * FXML Controller class
 *
 * @author user
 */
public class PanierController implements Initializable {

    private VBox content;
    @FXML
    private Label total;
    @FXML
    private Button commander;
    private ObservableMap<produits,Integer> observableMap;
   
    @FXML
    private Label article;
    @FXML
    private VBox vbox;
    @FXML
    private Label subtotal;
    @FXML
    private Button Programmes;
    @FXML
    private Button Alerts;
    @FXML
    private Button Categories;
    @FXML
    private Button Evenements;
    @FXML
    private Button Locaux;
    @FXML
    private Button Reclamations;
    @FXML
    private Label Propositions;
    @FXML
    private Button to_panier;
    @FXML
    private Button to_profile;
    @FXML
    private Button tocode;
    @FXML
    private Pane panec;
    @FXML
    private TextField codep;
    private Double  totalp ;
    private int pour ;
    @FXML
    private Circle circle;
    @FXML
    private Button nom_u;
    @FXML
    private Button to_menu;
    @FXML
    private Pane pane_m;
    @FXML
    private Label alerts;
    @FXML
    private Button Proposition;
    @FXML
    private Button Accueil;
    /**
     * Initializes the controller class.
     */
  
        @Override
    public void initialize(URL url, ResourceBundle rb) {
 
setImageU();
          Font.loadFont(getClass().getResourceAsStream("/css/RopaSans-egular.ttf"), 14);
          Font.loadFont(getClass().getResourceAsStream("/css/SourceSansPro-Regular.ttf"), 14);
         //article.setFont(font);
        //     Font.loadFont(getClass().getResourceAsStream("/css/Montserrat-Medium.ttf"), 14);
      //  UserSession.getInstace().AddLignePanier(new Produit(3,"Tent",89.0,"erer",1222,"tent.jpg"));
        //UserSession.getInstace().AddLignePanier(new Produit(2,"Sleeping Bag",89.0,"ezer",1222,"sleepingbag.jpg"));
 setPanier();
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
  
          
     public Circle setImage(String name) {
        File file = new File ("C:/xampp/web/web/Hytaco/public/images/properties/"+name);
        Circle circle = new Circle();
        circle.setRadius(55);
        try {
           System.out.println(new Image(file.toURI().toURL().toExternalForm()));
            circle.setFill(new ImagePattern(new Image(file.toURI().toURL().toExternalForm())));
        } catch (MalformedURLException ex) {
            Logger.getLogger(AfficherUtilisateursController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return circle;
    }
     
     
     
       private void setPanier(){
            totalp =0.0;
        if(UserSession.getInstace().getPanier() != null){
vbox.getChildren().clear();

            observableMap = FXCollections.observableMap(UserSession.getInstace().getPanier());
            if(observableMap.size()==0){
    total.setText("0 TND");
   subtotal.setText("0 TND");
}
            for (produits produit : observableMap.keySet()) {      
                HBox hbox = new HBox();
          Circle circle =       setImage(produit.getImage_name());
                Separator sp = new Separator(Orientation.HORIZONTAL);
                VBox vbox1 = new VBox();
                Label nom = new Label(produit.getNom_produit());
                nom.getStylesheets().add("css/Panier.css");
                nom.getStyleClass().add("nom_prod");
                Label  desc = new Label(produit.getDescription_produit());
                                desc.getStylesheets().add("css/Panier.css");
                                desc.setPrefWidth(150);
                desc.getStyleClass().add("desc_prod");
                vbox1.getChildren().addAll(nom,desc);
               vbox1.setMargin(desc, new Insets(5, 0, 0, 0));
               
               vbox1.setAlignment(Pos.CENTER_LEFT);
               hbox.setAlignment(Pos.CENTER_LEFT);
                hbox.setMargin(vbox1, new Insets(0, 0, 0, 6));
                 TextField qte = new TextField();
                    qte.setText(String.valueOf(observableMap.get(produit)));
nom.setPrefWidth(130);
          qte.textProperty().addListener((observable, oldValue, newValue) -> {
              if (!newValue.matches("[0-9]+")){
                              qte.setText(String.valueOf(UserSession.getInstace().getPanier().get(produit)  ));
              }else{
               if(!newValue.equals("") && Integer.valueOf(newValue) > 0  ){      
                                 System.out.println(qte.getText());
                                 quantiteEvent(produit,Integer.parseInt(qte.getText()));
               }
              }
          });
  
qte.setPrefWidth(45);
    Text remove = GlyphsDude.createIcon(FontAwesomeIconName.TRASH_ALT);

             Button removeb = new Button("",remove);
             removeb.getStylesheets().add("css/Panier.css");
                removeb.getStyleClass().add("buttonr");
                       
                removeb.setOnAction((e)->{
 new FadeIn(hbox).play();
          
                UserSession.getInstace().RemoveLignePanier(produit);
                setPanier();     
                //total.setText(String.valueOf(Integer.parseInt(total.getText().substring(total.getText().length()-4, total.getText().length()))-produit.getPrix()*Integer.parseInt(qte.getText())));
                });
             Label prix = new Label(String.valueOf(produit.getPrix_produit()*UserSession.getInstace().getPanier().get(produit))+" TND");
                prix.getStylesheets().add("css/Panier.css");
                prix.getStyleClass().add("nom_prod");
               hbox.getChildren().addAll(circle,vbox1,qte,removeb,prix);
                hbox.setMargin(removeb, new Insets(0, 0, 0, 70));
                hbox.setMargin(prix, new Insets(0, 0, 0, 80));

                 vbox.getChildren().addAll(hbox,sp);
            totalp+=produit.getPrix_produit()*UserSession.getInstace().getPanier().get(produit);
            subtotal.setText(String.valueOf(totalp)+ " TND");
            total.setText(String.valueOf(totalp+10)+ " TND");
        } 
    
        }
       }

   private void quantiteEvent(produits produit, int qt){
       observableMap.put(produit, qt);
              UserSession.getInstace().SetLignePanier(produit, qt);

       setPanier();
    }
   
    @FXML
    private void Commande(ActionEvent event){
    Date date = new Date();
    Boolean state = true ;
    ServiceCommande sc = new ServiceCommande();
       
    double  totalc = totalp*(1-(double)( pour/100.0));
   System.out.println(UserSession.getInstace().getUtilisateur());
        Commande commande = new Commande(date,UserSession.getInstace().getUtilisateur().getId(),totalc);
    System.out.println(commande);
        commande = sc.AjouterCommande(commande) ;
    for (produits i : observableMap.keySet()) {
     LigneCommande lcommande = new LigneCommande(i,commande,observableMap.get(i),i.getPrix_produit()*(1-(double)( pour/100.0)));       
     System.out.print(lcommande);
     if (sc.AjouterLigneCommande(lcommande)!=1){
        state = false ;
   }
    }
    if (state){
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("login");
                    alert.setContentText("Les commandes sont ajouté avec succes");
                    alert.showAndWait();
                    Parent   root=null;
                    CommandeController.com=commande;
                    CommandeController.stotal= totalc-10 ;
        try {
            root = FXMLLoader.load(getClass().getResource("/interfaces/Commande.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ProfileBController.class.getName()).log(Level.SEVERE, null, ex);
        }
                                        
                    Stage window = (Stage) vbox.getScene().getWindow();
                    window.setScene(new Scene(root));
    }
     
    }


   
    @FXML
    private void To_Code(ActionEvent event) {
     if(panec.isVisible()){
                         panec.setVisible(false);
    }else {
        new ZoomIn(panec).play();
                 panec.setVisible(true);
    }
    }

    @FXML
    private void AddPour(ActionEvent event) {
            ServiceCommande sc = new ServiceCommande();
Double totalc ;
      pour =   sc.getReduction(Integer.parseInt(codep.getText()));
      System.out.println((double)( pour/100.0));
      totalc = totalp*(1-(double)( pour/100.0));
      
            subtotal.setText(String.valueOf(new DecimalFormat("##.##").format(totalc))+ " TND");
            total.setText(String.valueOf(new DecimalFormat("##.##").format(totalc+10))+ " TND");   
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

    @FXML
    private void ContinuerAchat(ActionEvent event) throws IOException {
         Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/accueilFGUI.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
