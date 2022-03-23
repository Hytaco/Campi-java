/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import animatefx.animation.SlideInLeft;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;
import projet.models.produits;
import projet.models.publicite;
import projet.service.PubliciteService;
import static projet.utils.print.printNode;

/**
 * FXML Controller class
 *
 * @author Taher
 */
public class PubliciteController implements Initializable {

    @FXML
    private Button deletepub;
    @FXML
    private TextField image_name;
    @FXML
    private Button Importer;
    @FXML
    private Button print;
    @FXML
    private AnchorPane AnOs;
    @FXML
    private TableView<publicite> affichepub;
    @FXML
    private TableColumn<publicite, String> imagep;
    @FXML
    private Button ajoutpublicite;
    @FXML
    private TextField lien_publicite;
    @FXML
    private TextField nom_publicite;
    @FXML
    private Button modifpub;
    private String fileName ,fcs;
    @FXML
    private TextField recherche;
    @FXML
    private TableColumn<?, ?> nompubs;
    @FXML
    private TableColumn<?, ?> lienps;
    @FXML
    private Button closeb;
    @FXML
    private Circle circle;
    @FXML
    private Button nom_u;
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
    aff();

    }

    public void aff() {
            PubliciteService ps = new PubliciteService();
    ArrayList<publicite> publiciteList =  (ArrayList<publicite>) ps.afficherpublicite() ;
    ObservableList<publicite> data = FXCollections.observableArrayList(publiciteList); 
    affichepub.setItems(data);
        nompubs.setCellValueFactory(new PropertyValueFactory<>("nom"));
        lienps.setCellValueFactory(new PropertyValueFactory<>("lien"));
        imagep.setCellValueFactory(new PropertyValueFactory<>("image_name"));

                }
    private void categories(ActionEvent event) throws IOException {
        
                Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/CategoriesGUI.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

    private void produits(ActionEvent event) throws IOException {
        
                Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/AjoutproduitsGUI.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }


    private void alerts(ActionEvent event) throws IOException {
        
                Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/AlertsGUI.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }



    @FXML
    private void print(ActionEvent event) {
                    try {
            printNode(AnOs);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(AjoutCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(AjoutCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AjoutCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(AjoutCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Close(ActionEvent event) {
              Stage window = (Stage) closeb.getScene().getWindow();
        window.close();
    }

    @FXML
    private void ajoutpublicite(ActionEvent event) {        
        if(nom_publicite.getText().isEmpty()||lien_publicite.getText().isEmpty()||image_name.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "verifer les champs");  
        }
        else
        {
        String lp = nom_publicite.getText();
        String np = lien_publicite.getText();
        String ip = image_name.getText();
        publicite a = new publicite(5,lp,np,ip);
        PubliciteService sc = new PubliciteService();
        sc.ajoutepublicite(a);
                    JOptionPane.showMessageDialog(null, "ajout avec succes");
        nom_publicite.clear();
        lien_publicite.clear();
        image_name.clear();
       
         ArrayList<publicite> publiciteList =  (ArrayList<publicite>) sc.afficherpublicite() ; 
    ObservableList<publicite> data = FXCollections.observableArrayList(publiciteList);
    
    affichepub.setItems(data);
        nompubs.setCellValueFactory(new PropertyValueFactory<>("nom_publicite"));
        lienps.setCellValueFactory(new PropertyValueFactory<>("lien_publicite"));
        imagep.setCellValueFactory(new PropertyValueFactory<>("image_name"));
        }
    }
    

    @FXML
    private void importer(ActionEvent event) {
        
           
        FileChooser fc = new FileChooser();
        File SelectedFile = fc.showOpenDialog(null);
         fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", ".jpg", ".png"));
           //  e.setImage_event(SelectedFile.getAbsolutePath());
        if (SelectedFile != null) {

            fcs = SelectedFile.toString();
            int index = fcs.lastIndexOf('\\');
            if (index > 0) {
                fileName = fcs.substring(index + 1);
                //System.out.println(fileName);
            }

        }
        
            fcs=SelectedFile.getAbsolutePath();
        File source = new File(fcs);
        File destination = new File("C:\\xampp\\web\\web\\Hytaco\\public\\images\\properties\\" + fileName);
        String url = "/image/" + fileName;
        if (!destination.exists()) {
            try {
                FileUtils.copyFile(source, destination);
            } catch (Exception E) {

            }
         image_name.setText(url);        
    }
    }
    @FXML
    private void modifpub(ActionEvent event) {
        
        
                publicite cl=affichepub.getSelectionModel().getSelectedItem();
          int id =cl.getId_publicite();
          
        String lp = nom_publicite.getText();
        String np = lien_publicite.getText();
        String ip = image_name.getText();
        PubliciteService sc = new PubliciteService();
        publicite a = new publicite(5,lp,np,ip);
        sc.modifierpublicite(a);
            ArrayList<publicite> publiciteList =  (ArrayList<publicite>) sc.afficherpublicite() ;
    ObservableList<publicite> donnee = FXCollections.observableArrayList(publiciteList); 
    affichepub.setItems(donnee);
        nompubs.setCellValueFactory(new PropertyValueFactory<>("nom_publicite"));
        lienps.setCellValueFactory(new PropertyValueFactory<>("lien_publicite"));
        imagep.setCellValueFactory(new PropertyValueFactory<>("image_name"));

    }

    @FXML
    private void deleteprod(ActionEvent event) {
        
          publicite selectedItem = affichepub.getSelectionModel().getSelectedItem();
        if(selectedItem!=null){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setContentText("Etes vous sure de supprimer cette element ?");   
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
        PubliciteService ps = new PubliciteService() ; 
        affichepub.getItems().remove(selectedItem);
        ps.supprimerpublicite(selectedItem);
        }
        
        }
        else {
        
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un element à supprimer.");

        alert.showAndWait();
    }
    
}

      private void campi(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/ProduitsFGUI.fxml"));/* Exception */
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
