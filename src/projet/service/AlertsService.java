/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projet.utils.Connexionjdbc;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Iterator;
import java.util.Properties;
import java.util.*;
import javafx.scene.control.Alert;
import projet.models.Alerts;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

/**
 *
 * @author Taher
 */
public class AlertsService {
       Connection cnx;
          PreparedStatement ste;

       public AlertsService() {
        cnx = Connexionjdbc.getInstance().getConnection();
    }
       
    
    public void ajouteAlerts(Alerts c){
         try {
        String sql = "insert into alerts(programme_id,localisation,date,rapport,telephone,mail)"+"values(?,?,?,?,?,?)";
        ste = cnx.prepareStatement(sql);
        ste.setInt(1, c.getProgramme_id());
        ste.setString(2, c.getLocalisation());
        ste.setDate(3,(Date) c.getDate());
        ste.setString(4, c.getRapport());
        ste.setInt(5, c.getTelephone());
        ste.setString(6, c.getMail());
        ste.executeUpdate();
             System.out.println("Alert ajoutée!");
         }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void modifierAlerts(Alerts a){
        String req = "UPDATE alerts SET programme_id='"+a.getProgramme_id()+"',localisation='"+a.getLocalisation()+"',date='"+a.getDate()+"',rapport="+a.getRapport()+", telephonne="+a.getTelephone()+", mail='"+a.getMail()+"' WHERE id="+a.getId() ;
        try {
            PreparedStatement st1 = cnx.prepareStatement(req);
             
             st1.executeUpdate();
            System.out.println("Alerts modifié");

        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());

        }

    }
    
    public void supprimerAlerts(Alerts c){
        String requete = "DELETE FROM alerts WHERE id = ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, c.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        System.out.println("Alerts supprimée!");
    }
    
     public List<Alerts> afficherAlerts(){
        List<Alerts> list = new ArrayList<>();
        String requete = "SELECT * FROM alerts ";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                Alerts a = new Alerts(rs.getInt("id"),rs.getString("localisation"),rs.getDate("date"),rs.getString("rapport"),rs.getInt("telephone"),rs.getString("mail"));                
                list.add(a);
            }
            System.out.println("List des alerts à été crée!");
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        return list;
    }
     
    
}