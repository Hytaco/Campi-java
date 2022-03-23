package projet.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projet.utils.Connexionjdbc;
import projet.models.categories;
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
import projet.models.publicite;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Taher
 */



public class PubliciteService {
       Connection cnx;
          PreparedStatement ste;

       public PubliciteService() {
        cnx = Connexionjdbc.getInstance().getConnection();
    }
       
    
    public void ajoutepublicite(publicite c){
         try {
        String sql = "insert into publicite(nom,lien,image_name)"+"values(?,?,?)";
        ste = cnx.prepareStatement(sql);
        ste.setString(1, c.getNom_publicite());
        ste.setString(2, c.getLien_publicite());
        ste.setString(3, c.getImage_name());
        ste.executeUpdate();
             System.out.println("publicite ajoutée!");
         }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void modifierpublicite(publicite a){
        String req = "UPDATE publicite SET nom='"+a.getNom_publicite()+"',lien="+a.getLien_publicite()+", image_name="+a.getImage_name()+" WHERE id="+a.getId_publicite() ;
        try {
            PreparedStatement st1 = cnx.prepareStatement(req);
             
             st1.executeUpdate();
            System.out.println("publicite modifié");

        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());

        }

    }
    
    public void supprimerpublicite(publicite c){
        String requete = "DELETE FROM publicite WHERE id = ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, c.getId_publicite());
            pst.executeUpdate();
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        System.out.println("publicite supprimée!");
    }
    
    
    
     public List<publicite> afficherpublicite(){
        List<publicite> list = new ArrayList<>();
        String requete = "SELECT * FROM publicite ";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                publicite a = new publicite(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4));                
                list.add(a);
            }
            System.out.println("List des publicite à été crée!");
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        return list;
    }
     
    
}