/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import projet.models.Admin;
import projet.models.Client;
import projet.models.Utilisateur;
import projet.service.ServiceUtilisateur;
import projet.service.UserSession;
import animatefx.animation.ZoomIn;
import com.stripe.Stripe;
import static com.stripe.Stripe.apiKey;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.log;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import projet.utils.JavaMailUtilUser;

/**
 * FXML Controller class
 *
 * @author user
 */
public class LoginController implements Initializable {

    @FXML
    private PasswordField password_lo;
    @FXML
    private TextField email_lo;
    @FXML
    private Button login;
    @FXML
    private TextField nom_i;
    @FXML
    private TextField prenom_i;
    @FXML
    private TextField adresse_i;
    @FXML
    private TextField telephone_i;
    @FXML
    private TextField email_i;
    @FXML
    private PasswordField password_i;
    @FXML
    private Button sign_up_l;
    @FXML
    private Button sign_in_l;
    @FXML
    private Button sign_up_s;
    @FXML
    private Button sign_in_s;
    @FXML
    private TextField tf_image;
    @FXML
    private Pane pane_signup;
    @FXML
    private Pane pane_signin;
    @FXML
    private Button closeb;
    @FXML
    private Label e_nom;
    @FXML
    private Label e_prenom;
    @FXML
    private Label e_mail;
    @FXML
    private Label e_password;
    @FXML
    private Label e_telephone;
    private String code ;
    @FXML
    private Button importer;
    @FXML
    private Button inscprition;
    @FXML
    private TextField emailm;
    @FXML
    private Pane pane_code;
    @FXML
    private TextField codem;
    @FXML
    private Button motdepasse;
    @FXML
    private Pane pane_password;
    @FXML
    private PasswordField password_nc;
    @FXML
    private PasswordField password_n;
    @FXML
    private Button modifier;
    @FXML
    private Button back_p;
    @FXML
    private Pane pane_email;
    @FXML
    private Button envoyerm;
    @FXML
    private Button back_a;
    @FXML
    private Button envoyer_c;
    @FXML
    private Button back_c;
    @FXML
    private Label pasword_ne;
    @FXML
    private Label pasword_nec;
    private String email ;

    /**
     * Initializes the controller class.
     */
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      //  Payement();
    //  OpenWeather ow = new OpenWeather();
       
  //    getWeather();
                  Font.loadFont(getClass().getResourceAsStream("/css/DroidSerif-Regular.ttf.ttf"), 14);
 email_i.textProperty().addListener((observable, oldValue, newValue) -> {
                String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
Pattern pattern = Pattern.compile(masque);
Matcher controler = pattern.matcher(newValue);
if (controler.matches()){
e_mail.setText("");
 
}else{
e_mail.setText("*mail invalide ");
}
                });
             
             telephone_i.textProperty().addListener((observable, oldValue, newValue) -> {
                String masque = "[0-9]*";
Pattern pattern = Pattern.compile(masque);
Matcher controler = pattern.matcher(newValue);
if (controler.matches()){
    if (newValue.length()==8){
e_telephone.setText("");
    }
 
}else{
e_telephone.setText("*le numero du telephone est invalid");
}
                });
             nom_i.textProperty().addListener((observable, oldValue, newValue) -> {
                String masque = "[a-zA-Z]*";
Pattern pattern = Pattern.compile(masque);
Matcher controler = pattern.matcher(newValue);
if (controler.matches()){
e_nom.setText(""); 
}else{
e_nom.setText("*votre nom est invalid");
}
                });
             prenom_i.textProperty().addListener((observable, oldValue, newValue) -> {
                String masque = "[a-zA-Z]*";
Pattern pattern = Pattern.compile(masque);
Matcher controler = pattern.matcher(newValue);
if (controler.matches()){
e_prenom.setText("");
 
}else{
e_prenom.setText("*votre prenom est invalid");
}
                });
             password_i.textProperty().addListener((observable, oldValue, newValue) -> {
               if (newValue.length()>=5){
                   e_password.setText("");
               }else {
e_password.setText("*Votre mot de passe est invalid");
               }

                });
   email_lo.setText("taher.bekri@esprit.tn");
   password_lo.setText("stanger");
    }    

    @FXML
    private void LoginUtilisateur(ActionEvent event) throws IOException {
         ServiceUtilisateur us = new ServiceUtilisateur();

            List<Utilisateur> lu =   us.getTtUtilisateur();
          
            lu.forEach(e -> {
            if (e.getEmail().equals(email_lo.getText()) &&  (us.testMotDePasse(password_lo.getText(), e.getPassword()))){
                if (e.getActivation_token()== null){   
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("login");
                    alert.setContentText("Succes");
                    alert.showAndWait();
                    System.out.println(e);
   
                    UserSession.setInstace(e);
                       System.out.println(UserSession.getInstace().getUtilisateur());
                                    try {
                                        Parent root = null ;
                                        if(e instanceof Admin){
                    root = FXMLLoader.load(getClass().getResource("/interfaces/AccueilBack.fxml"));
                                        }else if(e instanceof Client) {
                      root = FXMLLoader.load(getClass().getResource("/interfaces/accueilFGUI.fxml"));
                                        }
                    Stage window = (Stage) login.getScene().getWindow();
                    window.setScene(new Scene(root));
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }else {
                                    Alert alert = new Alert(Alert.AlertType.ERROR);

                  alert.setTitle("login");
                    alert.setContentText("Verifier votre compte ");
                    alert.showAndWait();
                    System.out.println(e);
            }
            }
            });
                       
}
    
    @FXML
    private void InscriptionUtilisateur(ActionEvent event) {
                 ServiceUtilisateur us = new ServiceUtilisateur();
                 Client user = new Client(nom_i.getText(),prenom_i.getText(), Integer.parseInt(telephone_i.getText())
                         ,adresse_i.getText(),password_i.getText(),email_i.getText(),tf_image.getText(),UUID.randomUUID().toString());
                 if(us.Inscription(user)==1){
                  JavaMailUtilUser mail = new JavaMailUtilUser();
        try {
            String content = "Activation de votre compte\n" +
"veuillez clicker sur le lien ci-dessus pour l'activer  votre compte\n" +
"http://127.0.0.1:8000/activation/"+ user.getActivation_token();
            mail.sendMail("Mail Verification",content,email_i.getText());
                     new ZoomIn(pane_signin).play();
        pane_signin.toFront();
        
        } catch (Exception ex) {
            Logger.getLogger(AfficherUtilisateursController.class.getName()).log(Level.SEVERE, null, ex);
        }
            }else {
                           Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Inscription");
                    alert.setContentText("Fail");
                    alert.showAndWait();
                 }
                 
                 
    }

    @FXML
    private void To_signin(ActionEvent event) {
        new ZoomIn(pane_signin).play();
        pane_signin.toFront();
        
    }
    @FXML
 private void importer(ActionEvent event) {
       String fileName = null ;
        FileChooser fc = new FileChooser();
        File SelectedFile = fc.showOpenDialog(null);
         fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", ".jpg", ".png"));
           //  e.setImage_event(SelectedFile.getAbsolutePath());
        if (SelectedFile != null) {

            String fcs = SelectedFile.toString();
            int index = fcs.lastIndexOf('\\');
            if (index > 0) {
                 fileName = fcs.substring(index + 1);
            }

        }
     String key =  UUID.randomUUID().toString();
        String fcs = SelectedFile.getAbsolutePath();
        File source = new File(fcs);
        File destination = new File(System.getProperty("user.dir") + "\\src\\image\\" +key+ fileName);
        String url =  destination.getAbsolutePath();
                        System.out.println(url);

        if (!destination.exists()) {
            try {
                FileUtils.copyFile(source, destination);
            } catch (Exception E) {

            }
         
               tf_image.setText(key+ fileName);
    }
    }

    @FXML
    private void To_signup(ActionEvent event) {
         new ZoomIn(pane_signup).play();
        pane_signup.toFront();
    }

    @FXML
    private void Close(ActionEvent event) {
        Stage window = (Stage) closeb.getScene().getWindow();
        window.close();
    }

    @FXML
    private void To_mot_de_passe(ActionEvent event) {
        new ZoomIn(pane_email).play();
        pane_email.toFront();
    }

    @FXML
    private void ModifierPassword(ActionEvent event) {
         if (password_n.getText().length()>=5){
                   e_password.setText("");
               }else {
pasword_ne.setText("*Votre mot de passe est invalid");
return ;
               }
         if (password_n.getText().equals(password_nc.getText())){
                   e_password.setText("");
               }else {
pasword_nec.setText("*Mot de passe de confirmation est invalide");
return ;
               }
         ServiceUtilisateur suser = new ServiceUtilisateur();
         System.out.println(email);
       if (suser.ModifierMotdePasse(email, password_n.getText())==1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("login");
                    alert.setContentText("Succes");
                    alert.showAndWait();
                    new ZoomIn(pane_signin).play();
        pane_signin.toFront();
       }

    }

    @FXML
    private void BackP(ActionEvent event) {
           new ZoomIn(pane_signin).play();
        pane_signin.toFront();
    }
public static String getRandomNumberString() {
 
    Random rnd = new Random();
    int number = rnd.nextInt(999999);

    return String.format("%06d", number);
}
    @FXML
    private void EnvoyerMail(ActionEvent event) {
                          JavaMailUtilUser mail = new JavaMailUtilUser();

    code =    getRandomNumberString();
         String content = "Modifier le mot de passe\n" +
"une requete du changement de mot passe à eté envoyer \n" +
"ceci est le code du changement : "+ code;
        try {
            mail.sendMail("Changement du mot de passe",content,emailm.getText());
            email = emailm.getText();
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
                     new ZoomIn(pane_code).play();
        pane_code.toFront();
    }

    @FXML
    private void BackA(ActionEvent event) {
           new ZoomIn(pane_signin).play();
        pane_signin.toFront();
    }

    @FXML
    private void EnvoyerCode(ActionEvent event) {
        if (codem.getText().equals(code)){
            new ZoomIn(pane_password).play();
        pane_password.toFront();
        }else {
             Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("login");
                    alert.setContentText("Le code est incorrect !");
                    alert.showAndWait();
        }
    }

    @FXML
    private void BackC(ActionEvent event) {
           new ZoomIn(pane_signin).play();
        pane_signin.toFront();
    }
    
}