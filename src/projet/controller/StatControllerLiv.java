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
import projet.utils.Connexionjdbc;

/**
 * FXML Controller class
 *
 * @author malek
 */
public class StatControllerLiv implements Initializable {
          Connection cnx ;

    ObservableList<PieChart.Data> piechartdata;
    public StatControllerLiv() {
        this.cnx = Connexionjdbc.getInstance().getConnection();
    }
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
            Logger.getLogger(StatControllerLiv.class.getName()).log(Level.SEVERE, null, ex);
        }
        stat.setData(piechartdata);
        
    }  
    public void loadDataPie() throws SQLException
    {
        piechartdata = FXCollections.observableArrayList();
        String requete = "select o.adresse,count(o.id) as nbad from livraisons o group by o.adresse";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
            piechartdata.add(new PieChart.Data(rs.getString("adresse"), rs.getInt("nbad")));

        }
    }
    

    
}
