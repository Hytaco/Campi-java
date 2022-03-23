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
 * @author USER
 */
public class StatevenementsController implements Initializable {

    @FXML
    private PieChart piechart;
 ObservableList<PieChart.Data>data=FXCollections.observableArrayList();

      private PreparedStatement preparedStatement;
    private String query;


    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        stat();
    }
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
    private void retour(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/interfaces/evenements.fxml")); /* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
