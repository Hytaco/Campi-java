/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import projet.models.Locaux;
import projet.models.Programmes;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author ines
 */
public class AnnonceOneController implements Initializable {

    @FXML
    private Label date;
    @FXML
    private ImageView image;
    @FXML
    private Label nom;
    @FXML
    private Label details;
    @FXML
    private Label duree;
    @FXML
    private ImageView qrcode;
    @FXML
    private Button btnparticiper;
    private Programmes prog = new Programmes();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
        public void InitAnnonce(Programmes ann) throws SQLException {
       ServiceProgrammes sA = new ServiceProgrammes();
        Programmes A = sA.getProgramme(ann.getId());
        Locaux P = A.getLocaux();
      
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String Date = dateFormat.format(A.getDate());

        prog.setId(ann.getId());
        date.setText(Date);
    
        nom.setText(P.getNom());
        details.setText(A.getDetails());
        duree.setText(String.valueOf(A.getDuree()));
            System.out.println(P.getImage_name());

           if(P.getImage_name()!=null)
        {
        setImageP(P.getImage_name());
        }
                String data = P.getGoogle_map();

            String path = System.getProperty("user.dir") + "\\src\\image\\demo"
            +String.valueOf(ann.getId())+".png";
        int size = 400;
     
        String charset = "UTF-8";
 
              BitMatrix bitMatrix = generateMatrix(data, size);

        String imageFormat = "png";
        String outputFileName = path;
      
       writeImage(outputFileName, imageFormat, bitMatrix);
        setImage("demo"+String.valueOf(ann.getId())+".png");
    }
 private static void writeImage(String outputFileName, String imageFormat, BitMatrix bitMatrix)  {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(outputFileName));
            MatrixToImageWriter.writeToStream(bitMatrix, imageFormat, fileOutputStream);
            fileOutputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(AnnonceFrontController.class.getName()).log(Level.SEVERE, null, ex);
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
    public void setImage(String name) {
        File file = new File (System.getProperty("user.dir") +"\\src\\image\\"+name);
Image image = null ;
        try {
           
            image = new Image(file.toURI().toURL().toExternalForm());
        } catch (MalformedURLException ex) {
            Logger.getLogger(AnnonceFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        qrcode.setImage(image);

  }
    
  private static BitMatrix generateMatrix(String data, int size) {
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = new QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, size, size);
        } catch (WriterException ex) {
            Logger.getLogger(AnnonceFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bitMatrix;
    }   

    @FXML
    private void Participer(ActionEvent event) {
               ServiceProgrammes sA = new ServiceProgrammes();
               int id = 1;
              //  id = UserSession.getInstance().getUtilisateur().getId();
if (sA.Participer(prog.getId(), id)==1){
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Participation");
                    alert.setContentText("votre participation est ajouté avec succes");
                    alert.showAndWait();
}else {
         Alert alert = new Alert(Alert.AlertType.ERROR);

      alert.setTitle("Participation");
                    alert.setContentText("FAIL");
                    alert.showAndWait();
}
    }
    
}
