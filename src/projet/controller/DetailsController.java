/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import projet.models.evenements;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.*;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import projet.service.ServiceEvenement;

/**
 * FXML Controller class
 *
 * @author Firqs
 */
public class DetailsController implements Initializable {

    @FXML
    private ImageView image;
private evenements e;
    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_nombre;
    @FXML
    private TextField tf_lieu;
    @FXML
    private Button parler;
     private static final String VOICENAME="kevin16";
    @FXML
    private Label label_desc;
    @FXML
    private Button events;
    @FXML
    private Label label_rating;
    @FXML
    private Rating rating_etoile;
    @FXML
    private DatePicker tf_df;
    @FXML
    private DatePicker tf_dd;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        rating_etoile.ratingProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            label_rating.setText("Rating : "+newValue);
             });
    } 
    
   public void initData (evenements e){
    
    this.e=e;
    
         // cl_datedeb.setValue(o.getDatedeb().toLocalDate());
            // cl_datedeb.setText(dateFormat.format(o.getDatedeb())); 
         
     /*
            public void initData (offre o ){
    
    this.o=o;
          cl_mail.setText(o.getMailen());
          cl_noms.setText(o.getNoms());
         // cl_datedeb.setValue(o.getDatedeb().toLocalDate());
            // cl_datedeb.setText(dateFormat.format(o.getDatedeb())); 
         
      
          cl_description.setText(o.getDescription());
            */
     
    
   // tf_datedeb.setValue(e.getDate().toLocalDate());
    tf_name.setText(e.getNom());
    tf_nombre.setText(e.getNbrplace()+"");
     tf_lieu.setText(e.getLieu());
     label_desc.setText(e.getDescription());
     label_desc.setWrapText(true);
        
              tf_dd.setValue(e.getDate().toLocalDate());
             tf_df.setValue(e.getDatef().toLocalDate());
         
           // lb_datedeb.(o.getDatefin().toLocalDate());
    /*Image image = new Image(this.o.getImage_name());
      Circle c = new Circle(90, 90, 120);
        c.setFill(new ImagePattern(image));
        mv.getChildren().add(c);*/
     File file = new File ("C:/xampp/web/web/Hytaco/public/images/properties/"+e.getImage_name());
    Image imageForFile = null ;
        try {
            System.out.println(file.toURI().toURL());
            imageForFile = new Image(file.toURI().toURL().toExternalForm());
        } catch (MalformedURLException ex) {
            Logger.getLogger(DetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
   image.setImage(imageForFile);

        
    }

    @FXML
   
    private void jButtonSpeak(ActionEvent event) {
        Voice voice;
        VoiceManager vm=VoiceManager.getInstance();
        voice =vm.getVoice(VOICENAME);
        
        voice.allocate();
        try{
           // voice.speak("the name is");
            //voice.speak(tf_name.getText());
            //voice.speak("It has");
              // voice.speak(tf_nombre.getText());
               //voice.speak("places");
               //voice.speak("And it is in");
             //voice.speak(tf_lieu.getText());
             voice.speak(label_desc.getText());
           
        }catch(Exception e){ 
            
        }
    }

    @FXML
    private void goToEvent(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/views/front.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void addRating(ActionEvent event) {
        
        this.e=e;
        System.out.println(rating_etoile.getRating());
            ServiceEvenement sc = new ServiceEvenement();
            evenements o1 = new evenements(e.getId(),rating_etoile.getRating());
             sc.Rating(o1);
    }
    
}
