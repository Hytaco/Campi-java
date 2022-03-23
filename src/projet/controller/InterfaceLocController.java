/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;
import animatefx.animation.SlideInLeft;
import projet.models.Programmes;
import projet.models.Locaux;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
import projet.utils.DataSource;
import projet.utils.JavaMailUtilProg;

/**
 * FXML Controller class
 *
 * @author malek
 */
public class InterfaceLocController implements Initializable {
    ObservableList<Locaux> oblist = FXCollections.observableArrayList();
String path;

   
    @FXML
    private TableColumn<Locaux, String> cl_image;
    @FXML
    private TextField tf_image;
    private String fileName ,fcs;
    @FXML
    private TableColumn<?, ?> cl_nom;
    @FXML
    private TableColumn<?, ?> cl_adr;
    @FXML
    private TableColumn<?, ?> cl_des;
    @FXML
    private TableColumn<?, ?> cl_note;
    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_adr;
    @FXML
    private TextField tf_des;
    @FXML
    private TextField tf_note;
    @FXML
    private TextField tf_lien;
    @FXML
    private TableView<Locaux> tv_locaux;
    @FXML
    private TableColumn<?, ?> cl_lien;
    @FXML
    private Button btn_ajout;
    @FXML
    private Button btn_supprimer;
    @FXML
    private Button btn_modifier;
    @FXML
    private Button btn_import;
    @FXML
    private Button btn_stat;
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
    private ImageView DescriptionCheckmark;
    @FXML
    private ImageView MapsCheckmark;
    @FXML
    private ImageView AdresseCheckmark;
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
        // TODO
        
        loadTable();
        
    }   
    public void loadTable(){
        
        try {
            
            oblist.clear();
            Connection cnx = DataSource.getInstance().getCnx();
            
            ResultSet rs = cnx.createStatement().executeQuery("Select id,nom,adresse,description,image_name,note,google_map from locaux");
            
            while(rs.next()){
                
                oblist.add(new Locaux(rs.getInt("id"),rs.getString("nom"),rs.getString("adresse"),rs.getString("description"),rs.getString("image_name"),rs.getInt("note"),rs.getString("google_map")));
           
            }
            
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceLocController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        cl_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cl_adr.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        cl_des.setCellValueFactory(new PropertyValueFactory<>("description"));
        cl_image.setCellValueFactory(new PropertyValueFactory<>("image_name"));
        cl_note.setCellValueFactory(new PropertyValueFactory<>("note"));
        cl_lien.setCellValueFactory(new PropertyValueFactory<>("google_map"));
 
        tv_locaux.setItems(oblist);
        FilteredList<Locaux> filteredData = new FilteredList<>(oblist,b-> true);
        
          search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Locaux -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Locaux.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Locaux.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}else if (Locaux.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                              else if (String.valueOf(Locaux.getNote()).indexOf(lowerCaseFilter)!=-1){
                  return true;}
                            
                                
                              
                                
				else if (String.valueOf(Locaux.getId()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
          // 3. Wrap the FilteredList in a SortedList. 
		SortedList<Locaux> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tv_locaux.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		
               tv_locaux.setItems(sortedData);

    
    }

    @FXML
    private void ajouter(ActionEvent event) throws Exception {
          if(!testSaisie())
       // if(txtnomentreprise.getText().isEmpty()txtsalaire.getText().isEmpty()txtdescription.getText().isEmpty()txtlocalisation.getText().isEmpty()txtnbrheure.getText().isEmpty()txtnivetude.getText().isEmpty()txtlangue.getText().isEmpty()||date.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Verifier vos champs");
        }
        String nom=tf_nom.getText();
        String adresse =tf_adr.getText();
        String description =tf_des.getText();
      
   
        int note;
        note = Integer.parseInt(tf_note.getText());
        String google_map = tf_lien.getText();
        String image_name = tf_image.getText();

        Locaux o = new Locaux(0 , nom, adresse, description ,image_name ,note, google_map);
        ServiceLocaux sc = new ServiceLocaux();
               sc.ajouter(o);
               JavaMailUtilProg mail = new JavaMailUtilProg();
           mail.sendMail(o.getDescription());
               loadTable();
     
    }
    

    @FXML
    private void delete(ActionEvent event) {
        
   
       Locaux o=tv_locaux.getSelectionModel().getSelectedItem();
     
        ServiceLocaux sc = new ServiceLocaux();
               sc.deleteLocaux(o);
               loadTable();
    }

        
    @FXML
    private void modifier(ActionEvent event) {
        
    
         Locaux cl=tv_locaux.getSelectionModel().getSelectedItem();
          int id =cl.getId();
           String nom=tf_nom.getText();
           String adresse =tf_adr.getText();
           String description =tf_des.getText();
      
    
           String image = tf_image.getText();
          int note;
        note = Integer.parseInt(tf_note.getText());
           String google_map = tf_lien.getText();
            
            ServiceLocaux sc = new ServiceLocaux();
            Locaux o = new Locaux(id, nom, adresse, description ,image, note, google_map);
           //Cours cc = new Cours(id, instru , niveau, vid);
            sc.modifierLocaux(o);
            
            loadTable(); 
            
        
        
        
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
     String key = UUID.randomUUID().toString();
        
            fcs=SelectedFile.getAbsolutePath();
        File source = new File(fcs);
        File destination = new File("C:\\xampp\\web\\web\\Hytaco\\public\\images\\properties\\" + key + fileName);
        String url = "" + key +fileName;
        if (!destination.exists()) {
            try {
                FileUtils.copyFile(source, destination);
            } catch (Exception E) {

            }
         
            
               tf_image.setText(url);
            
       
        
        
        
    }
    }
    
    
    
    
    
    
    
    @FXML
    private void handleMouseButton(javafx.scene.input.MouseEvent event) {
        Locaux  o= tv_locaux.getSelectionModel().getSelectedItem();
         
            
            tf_nom.setText(o.getNom());
            tf_adr.setText(o.getAdresse());
            tf_des.setText(o.getDescription());

           tf_note.setText(String.valueOf(o.getNote() ));
            
            tf_lien.setText(o.getGoogle_map());
            tf_image.setText(o.getImage_name());
            
            
        }

    @FXML
    private void Onclick_watch(ActionEvent event) throws IOException {
        
        Locaux  o= tv_locaux.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/image.fxml"));
            Scene scene = new Scene(loader.load());
            ImageController dp=loader.getController();
               dp.initData(o);
         

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
              
    }

    @FXML
    private void stat(MouseEvent event) throws IOException {
       Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/views/stat.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

        
 private Boolean testnom() {
       
        if (tf_nom.getText().trim().length() >= 1) {
            nomCheckmark.setImage(new Image("image/checkMark.png"));
            return true;
        } else {
            nomCheckmark.setImage(new Image("image/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }


private Boolean testdescription() {
       

        if (tf_des.getText().trim().length() >= 1) {
            DescriptionCheckmark.setImage(new Image("image/checkMark.png"));
            return true;
        } else {
            DescriptionCheckmark.setImage(new Image("image/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }

private Boolean testadresse() {
     
        if (tf_adr.getText().trim().length() >= 1) {
            AdresseCheckmark.setImage(new Image("image/checkMark.png"));
            return true;
        } else {
            AdresseCheckmark.setImage(new Image("image/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }


private Boolean testmaps() {
       

        if (tf_lien.getText().trim().length() >= 1) {
           MapsCheckmark.setImage(new Image("image/checkMark.png"));
            return true;
        } else {
           MapsCheckmark.setImage(new Image("image/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }


  private Boolean testSaisie() {
        erreur = "";
       
       
     
          if(!testadresse())
        {
            erreur = erreur + ("Veuillez verifier le champ adresse \n");
        }
          
         if(!testdescription())
        {
            erreur = erreur + ("Veuillez verifier le champ déscription \n");
        }
          
                if(!testnom())
        {
            erreur = erreur + ("Veuillez verifier le champ nom \n");
        }
                
         if(!testmaps())
        {
            erreur = erreur + ("Veuillez verifier votre champ google maps \n");
        }
        if (!testnom() ||!testadresse()||!testdescription() ||!testmaps() ) {
            n = Notifications.create()
                    .title("Erreur de format")
                    .text(erreur)
                    .graphic(null)
                    .position(Pos.TOP_CENTER);
                    //.hideAfter(Duration.ofSeconds(6));
            n.showInformation();
        }

        return testnom()&& testadresse()&& testdescription()&& testmaps() ;
      
    }    

    private void Programmes(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/views/interfacep.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void Locaux(ActionEvent event) throws IOException {   Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/views/interface.fxml"));/* Exception */
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
