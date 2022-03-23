/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import animatefx.animation.SlideInDown;
import java.io.File;
import projet.models.proposition;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;
import projet.models.Transporteur;
import projet.service.ServiceProposition;
import projet.service.TransporteurServices;
import projet.service.UserSession;
import projet.utils.JavaMailUtilSponsor;

import animatefx.animation.SlideInDown;
import java.io.File;
import projet.models.Transporteur;
import projet.service.TransporteurServices;
import javafx.scene.image.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;
import projet.models.Livreur;
import projet.service.ServiceLivreur;
import projet.service.UserSession;
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
 * @author Firqs
 */
public class PropositionfrontController implements Initializable {

    @FXML
    private TextField tf_nom;
    @FXML
    private DatePicker tf_date;
    @FXML
    private TextField tf_nb;
    @FXML
    private TextField tf_mail;
    @FXML
    private Button proposer;
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
    @FXML
    private Pane pane_m;
    @FXML
    private Button to_profile;
    @FXML
    private Button to_panier;
    @FXML
    private AnchorPane brand;
    @FXML
    private TextField txttype;
    @FXML
    private TextField txtadresse;
                              ObservableList<Livreur> observableList_l = FXCollections.observableArrayList();

    @FXML
    private Button btnajout;
        private TableView<Transporteur> tv;
    private TableColumn<Transporteur, Integer> colid;
    private TableColumn<Transporteur, String> coltype;
     private TableColumn<Transporteur, Integer> colnumero;
    private TableColumn<Transporteur, String> colnom;
    private TableColumn<Transporteur, String> coladresse;
    private TableColumn<Transporteur, String> colmail;
    private TextField txtdescprition;
    private TextField txtemail;
     Notifications n;
        String erreur;
          private TableView<Livreur> TVLivreur;
    private TableColumn<?, ?> colTelephone;
    private TableColumn<?, ?> colMail;
    private TableColumn<?, ?> colNom;
    private TableColumn<?, ?> colAdressel;
    @FXML
    private TextField txtmail;
    @FXML
    private TextField txtnumero;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField noml;
    @FXML
    private TextField adressel;
    @FXML
    private Button ajoutliv;
    @FXML
    private TextField maill;
    @FXML
    private TextField telephonel;
    @FXML
    private ImageView emailCheckMark;
    @FXML
    private ImageView telCheckMark;
    @FXML
    private ImageView nomCheckMark;
    @FXML
    private ImageView adresseCheckMark;
    @FXML
    private ImageView mailCheckMark;
    @FXML
    private Label idll;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              String[] possibleWords = {"bus1","bus2","bus3"};
        TextFields.bindAutoCompletion(txttype, possibleWords);
        setImageU();
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
    @FXML
    private void proposer(ActionEvent event) throws Exception {
        
            String nom=tf_nom.getText();
        Date date;
        date = java.sql.Date.valueOf(tf_date.getValue());
        
      
       int nombre_place = Integer.parseInt(tf_nb.getText());
        
       String mail=tf_mail.getText();
       
  if ( (nom.isEmpty())&& (mail.isEmpty())) {
            JOptionPane.showMessageDialog(null, "il faut remplir tous les champs  ");
        }
        else if (nom.equals("")) {
            JOptionPane.showMessageDialog(null, "la saisi du nom est obligatoire ");
        } else if (mail.equals("")) {
            JOptionPane.showMessageDialog(null, "Vous devez saisir votre adresse mail");
        } 
       else{
        proposition p = new proposition(0 , nom, date , nombre_place ,"" , mail);
        ServiceProposition sc = new ServiceProposition();
               sc.ajouter(p);
               JavaMailUtilSponsor mail1 = new JavaMailUtilSponsor();
            mail1.sendMail1(p.getMail());

              // loadTable();
            double s = 1;
            //double h = 1;

               tf_nom.clear();  
               tf_nb.clear();  
               tf_mail.clear();  
  Notifications notificationBuilder =Notifications.create()
          .title("La proposition a ete ajoute")
          .text("")
           .graphic(null)
           .hideAfter(Duration.minutes(s))  
           .position(Pos.BOTTOM_RIGHT)     
           .onAction(new EventHandler<ActionEvent>(){
       @Override
        public void handle(ActionEvent event){
            System.out.println("clicked on notification");
        }
    });
  //notificationBuilder.darkStyle();
notificationBuilder.showInformation();
               
               }
    }

    private void goEvent(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/views/front.fxml"));/* Exception */
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

 @FXML
    private void addtransporteur(ActionEvent event) {
         if(txttype.getText().isEmpty()||txtnumero.getText().isEmpty()||txtnom.getText().isEmpty()||txtadresse.getText().isEmpty()||txtmail.getText().isEmpty()|| !testSaisie())
        {
            JOptionPane.showMessageDialog(null, "verifer les champs");  
        }
        else
        {
        String type = txttype.getText();
        int numero = Integer.parseInt(txtnumero.getText());
         String nom = txtnom.getText();
         String adresse = txtadresse.getText();
         String mail = txtmail.getText();
        Transporteur t = new Transporteur(5,type,numero,nom,adresse,mail);
        TransporteurServices sc = new TransporteurServices();
        sc.ajouterTransporteur(t);
                    JOptionPane.showMessageDialog(null, "ajout avec succes");
        txttype.clear();
        txtnumero.clear();
        txtnom.clear();
        txtadresse.clear();
        txtmail.clear();    
       
         ArrayList<Transporteur> publiciteList =  (ArrayList<Transporteur>) sc.getTransporteur() ; 
    ObservableList<Transporteur> data = FXCollections.observableArrayList(publiciteList);
    
    tv.setItems(data);
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("type"));
        colnumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coladresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        colmail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        
         }
    }


    private Boolean testMail() {

          String emailRegex;
       
                
        emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

        Pattern pat = Pattern.compile(emailRegex);
        if (txtmail.getText() == null) {
            return false;
        }

        if (pat.matcher(txtmail.getText()).matches() == false) {
            emailCheckMark.setImage(new Image("image/erreurCheckMark.png"));
//                erreur = erreur + ("Veuillez verifier votre adresse email\n");
            return false;
//

        } else {
            emailCheckMark.setImage(new Image("image/checkMark.png"));
        }
        return true;

    }
    private Boolean testSaisie() {
        erreur = "";
        if (!testMail()) {
            erreur = erreur + ("Veuillez verifier que votre adresse email est de la forme : ***@***.** \n");
        }
        
        else if 
                (!testTel()) {
            erreur = erreur + ("Veuillez verifier que votre numero est compris 8 chiffres \n");
        }
        
        if ( !testMail() || !testTel () ) {
            n = Notifications.create()
                    .title("Erreur de format")
                    .text(erreur)
                    .graphic(null)
                    .position(Pos.TOP_CENTER);
                    //.hideAfter(Duration.ofSeconds(6));
            n.showInformation();
        }

        return  testMail() && testTel() ;
    }
    
    private Boolean testTel() {
        if (txtnumero.getText().trim().length() == 8) {
            int nbChar = 0;
            for (int i = 1; i < txtnumero.getText().trim().length(); i++) {
                char ch = txtnumero.getText().charAt(i);
                if (Character.isLetter(ch)) {
                    nbChar++;
                }
            }

            if (nbChar == 0) {
                telCheckMark.setImage(new Image("image/checkMark.png"));
                return true;
            } else {
                telCheckMark.setImage(new Image("image/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
                return false;

            }
        } else if (txtnumero.getText().trim().length() != 8) {
//            erreur = erreur + ("Il faut saisir 8 chiffres dans le numéro de telephone\n");
            telCheckMark.setImage(new Image("image/erreurcheckMark.png"));
            return false;
        }
             return true;

    }
       
@FXML
    private void ajouterLivreur(ActionEvent event) throws Exception {
          if(!testSaisies())
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
                 projet.utils.JavaMailUtil mail = new projet.utils.JavaMailUtil();
           mail.sendMail(liv.getEmail());
                 setLivreurList();
                }
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
        
 
		SortedList<Livreur> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(TVLivreur.comparatorProperty());
		TVLivreur.setItems(sortedData);
   }
  
    

private Boolean testSaisies() {
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
}
