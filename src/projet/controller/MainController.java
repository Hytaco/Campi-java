/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import projet.models.Livraison;
import projet.models.Livreur;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import projet.service.ServiceLivraison;
import projet.service.ServiceLivreur;

/**
 *
 * @author dell
 */
public class MainController implements Initializable {
    
    private Label label;
    @FXML
    private DatePicker tfDATELIVRAISON;
    @FXML
    private TextField tfADRESSE;
    @FXML
    private TableView<Livraison> TVLivraison;
    @FXML
    private TableColumn<Livraison, Integer> colID;
    @FXML
    private TableColumn<Livraison, Integer> colDateLivraison;
    @FXML
    private TableColumn<Livraison, String> colAdresse;
    @FXML
    private TableColumn<Livraison, String> colLivreur;
    @FXML
    private Button btnM;
    @FXML
    private Button btnA;
    @FXML
    private Button btnS;
             ObservableList<Livraison> observableList = FXCollections.observableArrayList();
                          ObservableList<Livreur> observableList_l = FXCollections.observableArrayList();

    @FXML
    private Label idl;
    @FXML
    private TableView<Livreur> TVLivreur;
    @FXML
    private TableColumn<Livreur, Integer> colTelephone;
    @FXML
    private TableColumn<Livreur, String> colMail;
    @FXML
    private TableColumn<Livreur, String> colNom;
    @FXML
    private Button ajoutliv;
    @FXML
    private Button mofliv;
    @FXML
    private Button suplivr;
    @FXML
    private TextField noml;
    @FXML
    private TextField adressel;
    @FXML
    private TextField maill;
    @FXML
    private TextField telephonel;
    @FXML
    private TableColumn<Livreur, String> colAdressel;
    @FXML
    private Label idll;

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setLivraisonList();
        setLivreurList();
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
   }
    
   public void setLivraisonList(){
        ServiceLivraison us = new ServiceLivraison();
observableList.clear();
       colID.setCellValueFactory(new PropertyValueFactory<>("id"));
       colDateLivraison.setCellValueFactory(new PropertyValueFactory<>("datelivraison"));
       colAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
       colLivreur.setCellValueFactory(new PropertyValueFactory<>("livreur"));
    List<Livraison> lu = null;
    lu = us.AfficheLivraison();
        lu.forEach(e -> {
                observableList.add(e);
            });
        TVLivraison.setItems(observableList);
   }
    @FXML
    private void ajouterLivraison(ActionEvent event) {
                         Livraison comSelect = TVLivraison.getSelectionModel().getSelectedItem();
         ServiceLivraison us = new ServiceLivraison();
         
                 Livraison liv;
                 if (comSelect!=null){
        liv = new Livraison(Integer.parseInt(idl.getText()),java.sql.Date.valueOf(tfDATELIVRAISON.getValue()),tfADRESSE.getText());
          us.ModifierLivraison(liv);

                 }else {
        liv = new Livraison(java.sql.Date.valueOf(tfDATELIVRAISON.getValue()),tfADRESSE.getText());
                 if(us.Ajouter(liv)==1){
                       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("login");
                 alert.setContentText("Succes");
                 alert.showAndWait();
                 setLivraisonList();
            }else {
                         Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("login");
                 alert.setContentText("Echec");
                 alert.show();
                 }      
                 }
                 setLivraisonList();
    }
   public void showLivraison(){

       
   }    
    @FXML
   private void supprimerLivraison(ActionEvent event) {
        //récuprer la ligne selectionné
        
        Livraison comSelect = TVLivraison.getSelectionModel().getSelectedItem();
        if (comSelect == null) {
           
            return;
        }
        ServiceLivraison us = new ServiceLivraison();

        us.SupprimerLivraison(comSelect);
        //pour refraichir le tableView
        observableList.remove(comSelect);
        setLivraisonList();

    }
       @FXML

      private void modifierLivraison(ActionEvent event) {
                 Livraison comSelect = TVLivraison.getSelectionModel().getSelectedItem();
       tfDATELIVRAISON.setValue(Instant.ofEpochMilli(comSelect.getDatelivraison().getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
       tfADRESSE.setText(comSelect.getAdresse());
       idl.setText(String.valueOf(comSelect.getId()));
                  
                         
                  
      }

    @FXML
    private void ajouterLivreur(ActionEvent event) {
                Livreur comSelect = TVLivreur.getSelectionModel().getSelectedItem();
         ServiceLivreur us = new ServiceLivreur();
         
                 Livreur liv;
                 if (comSelect!=null){
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
                 setLivreurList();
    }

    @FXML
    private void modifierLivreur(ActionEvent event) {
         Livreur comSelect = TVLivreur.getSelectionModel().getSelectedItem();
         telephonel.setText(String.valueOf(comSelect.getTelephone()));
         adressel.setText(comSelect.getAdresse());
         maill.setText(comSelect.getEmail());
         noml.setText(comSelect.getNom());
         idll.setText(String.valueOf(comSelect.getId()));
    }

    @FXML
    private void supprimerLivreur(ActionEvent event) {
        Livreur comSelect = TVLivreur.getSelectionModel().getSelectedItem();
        if (comSelect == null) {
           
            return;
        }
        ServiceLivreur us = new ServiceLivreur();

        us.SupprimerLivreur(comSelect);
        //pour refraichir le tableView
        observableList_l.remove(comSelect);
        setLivreurList();
    }

}
