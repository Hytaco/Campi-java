/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import animatefx.animation.BounceInDown;
import animatefx.animation.FadeOutUp;
import animatefx.animation.SlideInLeft;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import projet.models.produits;
import projet.service.ProduitsService;
import projet.utils.SendEmail;
import static projet.utils.print.printNode;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javax.swing.JOptionPane;
import projet.models.categories;
import projet.service.CategorieService;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Taher
 */
public class AjoutProduitsGUIController implements Initializable {

    @FXML
    private TextField nom_produit;
    @FXML
    private TextField description_produit;
    @FXML
    private TextField prix_produit;
    @FXML
    private Button ajoutproduit;
    @FXML
    private TableView<produits> afficheprod;
    @FXML
    private TableColumn<produits, String> nomprod;
    @FXML
    private TableColumn<produits, String> descprod;
    @FXML
    private TextField recherchess;
    @FXML
    private TableColumn<produits, Integer> quantiteprod;
    @FXML
    private TableColumn<produits, Double> prixprod;
    @FXML
    private Button modifprod;
    @FXML
    private Button deleteprod;
    @FXML
    private Button Importer;
    private TableColumn<produits, String> imagee;
    @FXML
    private TextField image_name;
    private String fileName ,fcs;
    @FXML
    private TableColumn<produits, String> imagepp;
    @FXML
    private Button print;
    private AnchorPane AnO;
    @FXML
    private AnchorPane AnOs;
    @FXML
    private TextField quantite;
    @FXML
    private Button Statistiques;
    @FXML
    private ComboBox<categories> categorie_id;
int idp =0;
    @FXML
    private Button ajouter;
    @FXML
    private Pane blacks;
    @FXML
    private Pane pane_ajout;
    @FXML
    private Button back;
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
    private PieChart stat;
    @FXML
    private LineChart<String, Double> statcourbe;
    
    
     
    ObservableList<PieChart.Data> piechartdata;
    XYChart.Series<String, Double> linechartdata = new XYChart.Series();
  
    Connection cnx;
    ResultSet rs;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    ProduitsService ps = new ProduitsService();
    ArrayList<produits> publiciteList =  (ArrayList<produits>) ps.afficherproduits() ;
    ObservableList<produits> data = FXCollections.observableArrayList(publiciteList); 
    afficheprod.setItems(data);
        nomprod.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
        descprod.setCellValueFactory(new PropertyValueFactory<>("description_produit"));
        quantiteprod.setCellValueFactory(new PropertyValueFactory<>("quantite_produit"));
        prixprod.setCellValueFactory(new PropertyValueFactory<>("prix_produit"));
        imagepp.setCellValueFactory(new PropertyValueFactory<>("image_name"));

    javafx.util.Callback<ListView<categories>, ListCell<categories>> cellFactory = new javafx.util.Callback<ListView<categories>, ListCell<categories>>() {
            @Override
            public ListCell<categories> call(ListView<categories> l) {
                return new ListCell<categories>() {

                    @Override
                    protected void updateItem(categories item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.getNom_categorie());
                        }
                    }
                };
            }
        };
          categorie_id.setButtonCell(cellFactory.call(null));
        categorie_id.setCellFactory(cellFactory);
         CategorieService sp = new CategorieService();
        List<categories> arr = new ArrayList<>();
        try {
            arr = sp.DisplayAll();
        } catch (SQLException ex) {
            Logger.getLogger(AjoutProduitsGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (categories p : arr) {
            String titre = p.getNom_categorie();
     
            categorie_id.getItems().add(p);
        }
        
         try {
            loadDataPie();
        } catch (SQLException ex) {
            Logger.getLogger(StatevenementsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stat.setData(piechartdata);
        try {
            loadDataLine();
        } catch (SQLException ex) {
            Logger.getLogger(StatevenementsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        statcourbe.getData().add(linechartdata);
    }    
        public void loadDataPie() throws SQLException{
        piechartdata = FXCollections.observableArrayList();
        String dbUsername = "root";
        String dbPassword = "";
        String dbURL = "jdbc:mysql://127.0.0.1:3306/hytaco";
        cnx = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        PreparedStatement pst = cnx.prepareStatement("SELECT * from produits");
        rs=pst.executeQuery();
        
        while(rs.next()){
            piechartdata.add(new PieChart.Data(rs.getString("nom"),rs.getInt("quantite")));

        } 
    }
        
        
           public void loadDataLine() throws SQLException{
       String dbUsername = "root";
        String dbPassword = "";
        String dbURL = "jdbc:mysql://127.0.0.1:3306/hytaco";
        
        cnx = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        PreparedStatement pst = cnx.prepareStatement("SELECT * from produits");
        rs=pst.executeQuery();
        
        while(rs.next()){

            linechartdata.getData().add(new XYChart.Data<String, Double>((rs.getString("nom")),rs.getDouble("prix")));
        } 
    }
    @FXML
    private void ajoutproduit(ActionEvent event) 
    {
      
        String lp = nom_produit.getText();
        String np = description_produit.getText();
        String ip = image_name.getText();
        Integer qp = Integer.parseInt(quantite.getText());
        Double pp = Double.parseDouble(prix_produit.getText());

        ProduitsService sc = new ProduitsService();
        if (idp==0){
        produits a = new produits(5,categorie_id.getValue().getId_categorie(),lp,np,ip,qp,pp);
        sc.ajouteproduits(a);
                            JOptionPane.showMessageDialog(null, "ajout avec succes");

        }else {
        produits p = new produits(idp,categorie_id.getValue().getId_categorie(),lp,np,ip,qp,pp);

            sc.modifierproduits(p);
            idp = 0 ;
                                JOptionPane.showMessageDialog(null, "Modfication avec succes");

        }
        nom_produit.clear();
        description_produit.clear();      
        quantite.clear();
        prix_produit.clear();
        image_name.clear();
       
         ArrayList<produits> publiciteList =  (ArrayList<produits>) sc.afficherproduits() ; 
    ObservableList<produits> data = FXCollections.observableArrayList(publiciteList);
    
    afficheprod.setItems(data);
       nomprod.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
        descprod.setCellValueFactory(new PropertyValueFactory<>("description_produit"));
        quantiteprod.setCellValueFactory(new PropertyValueFactory<>("quantite_produit"));
        prixprod.setCellValueFactory(new PropertyValueFactory<>("prix_produit"));
        imagepp.setCellValueFactory(new PropertyValueFactory<>("image_name"));

        
    }

    private void handleMouseButton(MouseEvent event) {
        produits p = afficheprod.getSelectionModel().getSelectedItem();

        nom_produit.setText(p.getNom_produit());
        description_produit.setText(p.getDescription_produit());
        quantite.setText(String.valueOf(p.getQuantite_produit()));
        prix_produit.setText(String.valueOf(p.getPrix_produit()));
        image_name.setText(p.getImage_name());
    }


    @FXML
    private void modifprod(ActionEvent event) {
          blacks.setVisible(true);
        new BounceInDown(pane_ajout).play();
        pane_ajout.setVisible(true);
        produits r=afficheprod.getSelectionModel().getSelectedItem();
           idp =r.getId_produit();
          nom_produit.setText(r.getNom_produit());
          description_produit.setText(r.getDescription_produit());
          quantite.setText(String.valueOf(r.getQuantite_produit()));
          image_name.setText(r.getNom_produit());
          prix_produit.setText(String.valueOf(r.getPrix_produit()));
          
     
    }

    @FXML
    private void delete(ActionEvent event) {
           produits selectedItem = afficheprod.getSelectionModel().getSelectedItem();
        if(selectedItem!=null){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setContentText("Etes vous sure de supprimer cette element ?");   
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
        ProduitsService ps = new ProduitsService() ; 
        afficheprod.getItems().remove(selectedItem);
        ps.supprimerproduits(selectedItem);
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

    @FXML
    private void Importer(ActionEvent event) {
        
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
      
         image_name.setText(url);        
    
    }


    private void categories(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/CategoriesGUI.fxml"));/* Exception */
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


    private void publicite(ActionEvent event) throws IOException {
        
                Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/publicite.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void print(ActionEvent event) {
        
        try {
            printNode(AnOs);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(AjoutProduitsGUIController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(AjoutProduitsGUIController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AjoutProduitsGUIController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(AjoutProduitsGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Close(ActionEvent event) {
              Stage window = (Stage) closeb.getScene().getWindow();
        window.close();
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
    private void Statistiques(ActionEvent event) throws IOException {
        
                Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/statProduits.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void To_Ajouter(ActionEvent event) {
          blacks.setVisible(true);
        new BounceInDown(pane_ajout).play();
        pane_ajout.setVisible(true);
    }

    @FXML
    private void To_back(ActionEvent event) {
        blacks.setVisible(false);
        new FadeOutUp(pane_ajout).play();
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
