/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import animatefx.animation.SlideInLeft;
import projet.controller.InterfaceLocController;
import projet.models.Locaux;
import projet.models.Programmes;
import java.io.File;
import java.io.IOException;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;
import org.controlsfx.control.Notifications;
import projet.service.ServiceLocaux;
import projet.service.ServiceProgrammes;
import projet.utils.DataSource;

/**
 * FXML Controller class
 *
 * @author Hass
 */
public class InterfacepController implements Initializable {
    ObservableList<Programmes> oblist = FXCollections.observableArrayList();

    @FXML
    private Button btn_ajoutp;
    @FXML
    private Button btn_supprimerp;
    @FXML
    private Button btn_modifierp;
    @FXML
    private TableView<Programmes> tv_prog;
   
    @FXML
    private TableColumn<?, ?> cl_nomp;
    @FXML
    private TableColumn<?, ?> cl_datep;
    @FXML
    private TableColumn<?, ?> cl_dureep;
    @FXML
    private TableColumn<?, ?> cl_detp;
    @FXML
    private TextField tf_nomp;
    @FXML
    private TextField tf_dureep;
    @FXML
    private TextField tf_detp;
    @FXML
    private Button btn_statp;
    @FXML
    private DatePicker tf_datep;
    private Label tf_image;
    @FXML
    private TextField search;
    Notifications n;
        String erreur;
    @FXML
    private Label test;
    @FXML
    private Label e_nom;
    @FXML
    private Label e_mail;
    @FXML
    private Label e_password;
    @FXML
    private Label e_telephone;
    @FXML
    private ImageView nomCheckmark;
    @FXML
    private ImageView detailsCheckmark;
    @FXML
    private ImageView dateCheckmark;
    @FXML
    private ComboBox<Locaux> id_projet;
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

   @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
        // TODO
        
        loadTable();
        
    }   
    public void loadTable(){
        
        try {
            
            oblist.clear();
            Connection cnx = DataSource.getInstance().getCnx();
            
            ResultSet rs = cnx.createStatement().executeQuery("Select id,nom,date,duree,details from programmes");
            
            while(rs.next()){
                oblist.add(new Programmes(rs.getInt("id"),rs.getString("nom"),rs.getDate("date"),rs.getInt("duree"),rs.getString("details")));
            }
            
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceLocController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
      
        cl_nomp.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cl_datep.setCellValueFactory(new PropertyValueFactory<>("date"));
        cl_detp.setCellValueFactory(new PropertyValueFactory<>("details"));
        cl_dureep.setCellValueFactory(new PropertyValueFactory<>("duree"));

 
        tv_prog.setItems(oblist);
          FilteredList<Programmes> filteredData = new FilteredList<>(oblist,b-> true);
        
          search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Programmes -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Programmes.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Programmes.getDetails().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                              else if (String.valueOf(Programmes.getDuree()).indexOf(lowerCaseFilter)!=-1){
                  return true;}
                            
                                
                                else if (String.valueOf(Programmes.getDate()).indexOf(lowerCaseFilter)!=-1) {
					return true; // Filter matches last name.
				}
                                
				else if (String.valueOf(Programmes.getId()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
          // 3. Wrap the FilteredList in a SortedList. 
		SortedList<Programmes> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tv_prog.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		
               tv_prog.setItems(sortedData);

    
    }

    @FXML
    private void ajouter(ActionEvent event) {
        if(!testSaisie())
       // if(txtnomentreprise.getText().isEmpty()txtsalaire.getText().isEmpty()txtdescription.getText().isEmpty()txtlocalisation.getText().isEmpty()txtnbrheure.getText().isEmpty()txtnivetude.getText().isEmpty()txtlangue.getText().isEmpty()||date.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Verifier vos champs");
        }
        else { 
        String nom=tf_nomp.getText();
        String details =tf_detp.getText();
           Date date=java.sql.Date.valueOf(tf_datep.getValue());
      
       
        int duree;
        duree = Integer.parseInt(tf_dureep.getText());
       

        Programmes o = new Programmes(0 ,id_projet.getValue().getId(), nom, date, duree ,details);
        ServiceProgrammes sc = new ServiceProgrammes();
               sc.ajouter2(o);
               loadTable();
        }
     
    }
    

    @FXML
    private void delete(ActionEvent event) {
        
   
       Programmes o=tv_prog.getSelectionModel().getSelectedItem();
      
        ServiceProgrammes sc = new ServiceProgrammes();
               sc.deleteProgrammes(o);
               loadTable();
    }

        
    @FXML
    private void modifier(ActionEvent event) {
        
    
         Programmes cl=tv_prog.getSelectionModel().getSelectedItem();
          int id =cl.getId();
            String nom=tf_nomp.getText();
        String details =tf_detp.getText();
           Date date=java.sql.Date.valueOf(tf_datep.getValue());
      
   
        int duree;
        duree = Integer.parseInt(tf_dureep.getText());            
            ServiceProgrammes sc = new ServiceProgrammes();
            Programmes o = new Programmes(id,id_projet.getValue().getId(), nom, date, duree, details);
          
            sc.modifierProgrammes(o);
            
            loadTable(); 
            
        
        
        
    }

    private void importer(ActionEvent event) {
        String   fileName = null ;
        FileChooser fc = new FileChooser();
        File SelectedFile = fc.showOpenDialog(null);
         fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", ".jpg", ".png"));
           //  e.setImage_event(SelectedFile.getAbsolutePath());
        if (SelectedFile != null) {

          String  fcs = SelectedFile.toString();
            int index = fcs.lastIndexOf('\\');
            if (index > 0) {
                fileName = fcs.substring(index + 1);
                //System.out.println(fileName);
            }
        }
     String key = UUID.randomUUID().toString();
        
          String  fcs=SelectedFile.getAbsolutePath();
        File source = new File(fcs);
        File destination = new File("C:\\xampp\\web\\web\\Hytaco\\public\\images\\properties\\" + key + fileName);
        String url = "/image/" + key +fileName;
        if (!destination.exists()) {
            try {
                FileUtils.copyFile(source, destination);
            } catch (Exception E) {

            }
         
            
               tf_image.setText(url);
            
       
        
        
        
    }
}

    @FXML
    private void handleMouseButton(MouseEvent event) {
        
          Programmes  o= tv_prog.getSelectionModel().getSelectedItem();
         
            
            tf_nomp.setText(o.getNom());
            tf_dureep.setText(String.valueOf(o.getDuree() ));
            tf_detp.setText(o.getDetails());
           tf_datep.setValue(o.getDate().toLocalDate());

    }

    
 @FXML
    private void stat(MouseEvent event) throws IOException {
       Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/statp.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }    
    
 
private Boolean testnom() {
       

        if (tf_nomp.getText().trim().length() >= 1) {
            nomCheckmark.setImage(new Image("image/checkMark.png"));
            return true;
        } else {
            nomCheckmark.setImage(new Image("image/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }


private Boolean testDetails() {
      

        if (tf_detp.getText().trim().length() >= 1) {
            detailsCheckmark.setImage(new Image("image/checkMark.png"));
            return true;
        } else {
            detailsCheckmark.setImage(new Image("image/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }




private Boolean testDate() {

     Date date=java.sql.Date.valueOf(tf_datep.getValue());
     java.util.Date aujourdhui = new java.util.Date();
    if (tf_datep.getValue().lengthOfMonth()<1)
    {
       dateCheckmark.setImage(new Image("image/erreurCheckMark.png"));
           return false;  
    }
     if (tf_datep.getValue() != null) {
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

    private void Locaux(ActionEvent event) throws IOException {
        
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/views/interface.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void Programmes(ActionEvent event) throws IOException {   Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/views/interfacep.fxml"));/* Exception */
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
    private void Close(ActionEvent event) {
                        Stage window = (Stage) closeb.getScene().getWindow();
        window.close();
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
