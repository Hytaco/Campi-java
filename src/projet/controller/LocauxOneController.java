/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import java.io.File;
import java.net.MalformedURLException;
import projet.models.Locaux;
import projet.models.Programmes;
//import Entite.Projet;
import projet.service.ServiceProgrammes;
//import Service.ServiceProjet;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import projet.service.ServiceLocaux;

/**
 * FXML Controller class
 *
 * @author ines
 */
public class LocauxOneController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Label nom;
    @FXML
    private Label adresse;
    @FXML
    private Label note;
    @FXML
    private Label description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
        public void InitLocaux(Locaux ann) throws SQLException {

       ServiceLocaux sA = new ServiceLocaux();
        Locaux A = sA.details(ann.getId());
      
      
      
 
      
    
        nom.setText(A.getNom());
       adresse.setText(A.getAdresse());
        note.setText(String.valueOf(A.getNote()));
            System.out.println(A.getImage_name());
   description.setText(A.getDescription());
           if(A.getImage_name()!=null)
        {
        setImageP(A.getImage_name());
        }
    }

     public void setImageP(String name) {
        File file = new File ("C:\\xampp\\web\\web\\Hytaco\\public\\images\\properties\\"+name);
Image imagel = null ;
        try {
           
            imagel = new Image(file.toURI().toURL().toExternalForm());
        } catch (MalformedURLException ex) {
            Logger.getLogger(AnnonceFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        image.setImage(imagel);

  }
}
