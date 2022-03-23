/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import animatefx.animation.SlideInLeft;
import projet.models.evenements;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.scene.input.KeyCode.S;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import projet.service.ServiceEvenement;
import org.apache.commons.io.FileUtils;
import org.controlsfx.control.Notifications;
import projet.utils.Connexionjdbc;

/**
 * FXML Controller class
 *
 * @author Firqs
 */
public class EvenementsController implements Initializable {
    
 ObservableList<evenements> oblist = FXCollections.observableArrayList();
String path;
   

    @FXML
    private TableView<evenements> tv_evenement;
    @FXML
    private TableColumn<evenements, Integer> cl_id;
    @FXML
    private TableColumn<evenements, String> cl_nom;
    @FXML
    private TableColumn<evenements, Date> cl_dated;
    @FXML
    private TableColumn<evenements, String> cl_image;
    @FXML
    private TableColumn<evenements, String> cl_nbrp;
    @FXML
    private TableColumn<evenements, Date> cl_datef;
    @FXML
    private TableColumn<evenements, String> cl_lieu;
    @FXML
    private TextField tf_image;
    @FXML
    private TextField tf_nbrp;
    @FXML
    private TextField tf_lieu;
    @FXML
    private TextField tf_nom;
    @FXML
    private DatePicker tf_datedeb;
    @FXML
    private Button btn_import;
    @FXML
    private DatePicker tf_datefin;
    @FXML
    private Button btn_ajout;
    @FXML
    private Button btn_modifier;
    @FXML
    private Button btn_supprimer;
    @FXML
    private TextField txtRech;
    private PreparedStatement preparedStatement;
    private String fileName ,fcs;
    private PieChart piechart;
    ObservableList<PieChart.Data>data=FXCollections.observableArrayList();
    private String query;
    @FXML
    private TableColumn<evenements, String> cl_desc;
    @FXML
    private TextField tf_desc;
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
    @FXML
    private Button Statistiques;
    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadTable();
    } 
    
    
    public void loadTable(){
               Connection cnx;

        try {
            
          oblist.clear();
        cnx = Connexionjdbc.getInstance().getConnection();
            
            
            ResultSet rs = cnx.createStatement().executeQuery("Select id,nom,date,image_name,nbrplace,datef,lieu,description from evenements");
            
            while(rs.next()){
                
                oblist.add(new evenements(rs.getInt("id"),rs.getString("nom"),rs.getDate("date"),rs.getString("image_name"),rs.getInt("nbrplace"),rs.getDate("datef"),rs.getString("lieu"),rs.getString("description")));
           
            }
            
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(EvenementsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        cl_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cl_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cl_dated.setCellValueFactory(new PropertyValueFactory<>("date"));
        cl_image.setCellValueFactory(new PropertyValueFactory<>("image_name"));
        cl_nbrp.setCellValueFactory(new PropertyValueFactory<>("nbrplace"));
        cl_datef.setCellValueFactory(new PropertyValueFactory<>("datef"));
        cl_lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
       cl_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
      
       
     
       tv_evenement.setItems(oblist);
       
        FilteredList<evenements> filteredData = new FilteredList<>(oblist,b-> true);
        
          txtRech.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(evenements -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (evenements.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (evenements.getLieu().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}else if (evenements.getImage_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                
                                   //tf_datedeb.setValue(o.getDatedeb().toLocalDate());
                                
                                else if (String.valueOf(evenements.getDate()).indexOf(lowerCaseFilter)!=-1) {
					return true; // Filter matches last name.
				}
                                  else if (String.valueOf(evenements.getDatef()).indexOf(lowerCaseFilter)!=-1) {
					return true; // Filter matches last name.
				}
                                
				else if (String.valueOf(evenements.getId()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
          // 3. Wrap the FilteredList in a SortedList. 
		SortedList<evenements> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tv_evenement.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		
               tv_evenement.setItems(sortedData);


       
       
    
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
        String url = "" + fileName;
      
         
            
               tf_image.setText(url);
            
       
        
        
        
    
    }

    @FXML
    private void ajouter(ActionEvent event) {
        
        String nom=tf_nom.getText();
        Date date =java.sql.Date.valueOf(tf_datedeb.getValue());
        String image_name =tf_image.getText();
      
       int nbrplace = Integer.parseInt(tf_nbrp.getText());
        Date datef=java.sql.Date.valueOf(tf_datefin.getValue());
       String lieu=tf_lieu.getText();
        String description=tf_desc.getText();
       
       
  if ((image_name.isEmpty())&& (nom.isEmpty())&& (lieu.isEmpty())  ) {
            JOptionPane.showMessageDialog(null, "il faut remplir tous les champs  ");
        }
        else if (nom.equals("")) {
            JOptionPane.showMessageDialog(null, "la saisi du nom est obligatoire ");
        } else if (lieu.equals("")) {
            JOptionPane.showMessageDialog(null, "la saisi du nom est obligatoire");
        } else if (image_name.equals("")) {
            JOptionPane.showMessageDialog(null, "image est obligatoire ");
        } else if (date.after(datef)) {
            JOptionPane.showMessageDialog(null, "il faut que date fin soit superieur a date debut ");
        }else if ((image_name.equals(""))&& (nom.equals(""))&& (lieu.equals(""))  ) {
            JOptionPane.showMessageDialog(null, "il faut remplir tous les champs  ");
        }
       else{
        evenements e = new evenements(0 , nom, date, image_name ,nbrplace , datef, lieu,description);
        ServiceEvenement sc = new ServiceEvenement();
               sc.ajouter(e);
               loadTable();
            double s = 1;
            //double h = 1;
               
             
  Notifications notificationBuilder =Notifications.create()
          .title("L'evenement a ete ajoute")
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

    @FXML
    private void modifier(ActionEvent event) {
        
       evenements cl=tv_evenement.getSelectionModel().getSelectedItem();
          int id =cl.getId();
           String nom=tf_nom.getText();
          Date date=java.sql.Date.valueOf(tf_datedeb.getValue());
            String image_name = tf_image.getText();
           
        int nbrplace = Integer.parseInt(tf_nbrp.getText());
          
           Date datef=java.sql.Date.valueOf(tf_datefin.getValue());
         
           String lieu = tf_lieu.getText();
         String description = tf_desc.getText();
            ServiceEvenement sc = new ServiceEvenement();
            evenements e = new evenements(id,nom,date,image_name,nbrplace,datef,lieu,description);
           //Cours cc = new Cours(id, instru , niveau, vid);
            sc.modifierEvenement(e);
            
            loadTable();
            
            
            double s = 1;
            //double h = 1;
               
             
  Notifications notificationBuilder =Notifications.create()
          .title("Cet evenement a ete modifie")
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

    @FXML
    private void delete(ActionEvent event) {
        evenements e=tv_evenement.getSelectionModel().getSelectedItem();
       //tv_evenement.getItems().removeAll(tv_evenement.getSelectionModel().getSelectedItem());
        ServiceEvenement sc = new ServiceEvenement();
               sc.deleteEvenement(e);
               loadTable();
              // JOptionPane.showMessageDialog(null, "Voulez-vous vraiment supprimer cet evenement ");
                double s = 1;
            //double h = 1;
               
             
  Notifications notificationBuilder =Notifications.create()
          .title("cet evenement a ete supprime")
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
    
    @FXML
    private void handleMouseButton(javafx.scene.input.MouseEvent event) {
        evenements  e= tv_evenement.getSelectionModel().getSelectedItem();
         
            
            tf_nom.setText(e.getNom());
             tf_datedeb.setValue(e.getDate().toLocalDate());
            tf_image.setText(e.getImage_name());
              //Date datedeb=java.sql.Date.valueOf(tf_datedeb.getValue());
        //Date datefin=java.sql.Date.valueOf(tf_datefin.getValue());
            //tf_datedeb.setDate(o.getDatedeb());
          // tf_datefin.setDate(o.getDatedeb());
           // p.setDate_debut(java.sql.Date.valueOf(date_debut.SetValue()));
        //p.setDate_fin(java.sql.Date.valueOf(date_fin.getValue()));
           // tf_nbrp.Integer.parseInt(e.getNbrplace().toString());
            tf_datefin.setValue(e.getDatef().toLocalDate());
            tf_lieu.setText(e.getLieu());
         
             tf_nbrp.setText(e.getNbrplace()+"");
             tf_desc.setText(e.getDescription());
            
        }

   /* @FXML
    private void rechercher(KeyEvent event) throws SQLException {
          try {
            oblist.clear();
           //Connection cnx = DataSource.getInstance().getCnx();
            
           // ResultSet rs = cnx.prepareStatement().executeQuery("select * from evenements where nom='"+txtRech.getText()+"'");
            Connection cnx = DataSource.getInstance().getCnx();
           preparedStatement=cnx.prepareStatement("select * from evenements where  id='"+txtRech.getText()+"'" );
           ResultSet rs=preparedStatement.executeQuery();
            while(rs.next()){
                
                oblist.add(new evenements(rs.getInt("id"),rs.getString("nom"),rs.getDate("date"),rs.getString("image_name"),rs.getInt("nbrplace"),rs.getDate("datef"),rs.getString("lieu")));
           
            
                      
                        
                  }
             } catch (SQLException ex) {
            Logger.getLogger(InterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
tv_evenement.setItems(oblist);
         if(txtRech.getText().trim().isEmpty())
         {
            loadTable();
         
         }
    }*/
    
    private void stat()
    {
         ObservableList<PieChart.Data>data=FXCollections.observableArrayList();
           data = FXCollections.observableArrayList();
                          Connection cnx;

    try {
            cnx = Connexionjdbc.getInstance().getConnection();
            query = "SELECT COUNT(*),nbrplace FROM evenements GROUP BY nbrplace";
            preparedStatement = cnx.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();           
             while (rs.next()){   
                 String d= rs.getString(2) +" : "+rs.getString(1);

               data.add(new PieChart.Data(d,rs.getInt(1)));
            }  
        } catch (SQLException ex) {
            Logger.getLogger(EvenementsController.class.getName()).log(Level.SEVERE, null, ex);
        }         
        piechart.setTitle("Statistques des Evenements");
        //piechart.setLegendSide(Side.LEFT);
        piechart.setData(data);
    
    }

    @FXML
    private void rechercher(KeyEvent event) {
    }

    private void onClick(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/sponors.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void goFront(ActionEvent event) throws IOException {
         Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/front.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void goProposition(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/propositionback.fxml"));/* Exception */
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

    @FXML
    private void Statistiques(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/statEvenements.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
