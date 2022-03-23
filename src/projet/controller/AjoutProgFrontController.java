/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import animatefx.animation.SlideInDown;
import projet.controller.InterfaceLocController;
import projet.models.Locaux;
import projet.models.Programmes;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;
import org.controlsfx.control.Notifications;
import projet.service.ServiceLocaux;
import projet.service.ServiceProgrammes;
import projet.service.UserSession;
import projet.utils.DataSource;

/**
 * FXML Controller class
 *
 * @author Hass
 */
public class AjoutProgFrontController implements Initializable {
    ObservableList<Programmes> oblist = FXCollections.observableArrayList();

    
    Notifications n;
        String erreur;
    @FXML
    private ImageView nomCheckmark;
    @FXML
    private ImageView detailsCheckmark;
    @FXML
    private ImageView dateCheckmark;
    @FXML
    private AnchorPane brand;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfdet;
    @FXML
    private Button btnajout;
    @FXML
    private TextField tfduree;
    @FXML
    private Label nom_u2;
    @FXML
    private DatePicker tfdate;
    @FXML
    private Button Reclamations;
    @FXML
    private Button Alerts;
    @FXML
    private Button Programmes;
    @FXML
    private Button Categories;
    @FXML
    private Button Evenements;
    @FXML
    private Button Locaux;
    @FXML
    private Label Propositions;
    @FXML
    private Button to_panier;
    @FXML
    private Button to_profile;
    @FXML
    private ComboBox<Locaux> id_projet;
    @FXML
    private Button retour;
    @FXML
    private Pane pane_m;
    @FXML
    private Label alerts;
    @FXML
    private Button Proposition;
    @FXML
    private Circle circle;
    @FXML
    private Button nom_u;
    @FXML
    private Button to_menu;
    @FXML
    private Button Accueil;
  public void setImageU() {
               nom_u.setText(UserSession.getInstace().getUtilisateur().getPrenom()+" "+UserSession.getInstace().getUtilisateur().getNom());

        File file = new File (System.getProperty("user.dir") + "\\src\\image\\" +UserSession.getInstace().getUtilisateur().getImage_name());
        try {
            circle.setFill(new ImagePattern(new Image(file.toURI().toURL().toExternalForm())));
        } catch (MalformedURLException ex) {
            Logger.getLogger(AfficherUtilisateursController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setImageU();
     javafx.util.Callback<ListView<Locaux>, ListCell<Locaux>> cellFactory = new javafx.util.Callback<ListView<Locaux>, ListCell<Locaux>>() {

            @Override
            public ListCell<Locaux> call(ListView<Locaux> l) {
                return new ListCell<Locaux>() {

                    @Override
                    protected void updateItem(Locaux item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.getNom());
                        }
                    }
                };
            }
        };
          id_projet.setButtonCell(cellFactory.call(null));
        id_projet.setCellFactory(cellFactory);
         ServiceLocaux sp = new ServiceLocaux();
        List<Locaux> arr = new ArrayList<>();
        try {
            arr = sp.DisplayAll();
        } catch (SQLException ex) {
            Logger.getLogger(InterfacepController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Locaux p : arr) {
            String titre = p.getNom();

            id_projet.getItems().add(p);
        }
        
    }   
   

    @FXML
    private void ajouter(ActionEvent event) {
        if(!testSaisie())
       // if(txtnomentreprise.getText().isEmpty()txtsalaire.getText().isEmpty()txtdescription.getText().isEmpty()txtlocalisation.getText().isEmpty()txtnbrheure.getText().isEmpty()txtnivetude.getText().isEmpty()txtlangue.getText().isEmpty()||date.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Verifier vos champs");
        }
        else { 
        String nom=tfnom.getText();
        String details =tfdet.getText();
           Date date=java.sql.Date.valueOf(tfdate.getValue());
      
   
        int duree;
        duree = Integer.parseInt(tfduree.getText());
       

        Programmes o = new Programmes(0 ,id_projet.getValue().getId(), nom, date, duree ,details);
        ServiceProgrammes sc = new ServiceProgrammes();
               sc.ajouter2(o);
            
        }
     
    }
    

    
 
private Boolean testnom() {
     
        if (tfnom.getText().trim().length() >= 1) {
            nomCheckmark.setImage(new Image("image/checkMark.png"));
            return true;
        } else {
            nomCheckmark.setImage(new Image("image/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }


private Boolean testDetails() {
       
        if (tfdet.getText().trim().length() >= 1) {
            detailsCheckmark.setImage(new Image("image/checkMark.png"));
            return true;
        } else {
            detailsCheckmark.setImage(new Image("image/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }




private Boolean testDate() {

     Date date=java.sql.Date.valueOf(tfdate.getValue());
     java.util.Date aujourdhui = new java.util.Date();
    if (tfdate.getValue().lengthOfMonth()<1)
    {
       dateCheckmark.setImage(new Image("image/erreurCheckMark.png"));
           return false;  
    }
     if (tfdate.getValue() != null) {
            if (date.compareTo(aujourdhui) > 0) {
                dateCheckmark.setImage(new Image("image/checkMark.png"));
                return true;
            } else  {
                dateCheckmark.setImage(new Image("image/erreurCheckMark.png"));
                 return false;
            }
           
        }
     else {
      dateCheckmark.setImage(new Image("image/erreurCheckMark.png"));
           return false;}
    }    
    

private Boolean testSaisie() {
        erreur = "";
       
       
        
         if(!testDetails())
        {
            erreur = erreur + ("Veuillez verifier le champ détails vide \n");
        }
          
                if(!testnom())
        {
            erreur = erreur + ("Veuillez verifier le champ nom vide \n");
        }
                
         if(!testDate())
        {
            erreur = erreur + ("Veuillez verifier que vous avez choisi une date superieur a la date courante \n");
        }
        if (!testnom() ||!testDate()||!testDetails() ) {
            n = Notifications.create()
                    .title("Erreur de format")
                    .text(erreur)
                    .graphic(null)
                    .position(Pos.TOP_CENTER);
                    //.hideAfter(Duration.ofSeconds(6));
            n.showInformation();
        }

        return testnom() && testDetails() && testDate() ;
      
    }



   

    @FXML
    private void rtr(ActionEvent event) throws IOException {
            Node node = (Node) event.getSource();  
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/AnnonceFront.fxml"));/* Exception */
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
