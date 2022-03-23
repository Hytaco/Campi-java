/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import projet.utils.DataSource;

/**
 * FXML Controller class
 *
 * @author malek
 */
public class StatControllerp implements Initializable {
    ObservableList<PieChart.Data> piechartdata;
    Connection cnx = DataSource.getInstance().getCnx();   

    @FXML
    private PieChart stat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            loadDataPie();
        } catch (SQLException ex) {
            Logger.getLogger(StatControllerp.class.getName()).log(Level.SEVERE, null, ex);
        }
        stat.setData(piechartdata);
        
    }  
    public void loadDataPie() throws SQLException
    {
        piechartdata = FXCollections.observableArrayList();
        String requete = "select o.duree,count(o.id) as nbad from programmes o group by o.duree";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
            piechartdata.add(new PieChart.Data(rs.getString("duree"), rs.getInt("nbad")));

        }
    }
    
    
}
