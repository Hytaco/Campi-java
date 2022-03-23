/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;


import projet.models.Locaux;
import projet.models.Programmes;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import projet.utils.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;


/**
 *
 * @author asus
 */

public class ServiceLocaux implements IServices {
 private  ResultSet rs;
private Statement st;
    Connection cnx = DataSource.getInstance().getCnx();   
    public static int loggeduser;

public void ajouter(Locaux o) {
        try {
            String requete = "INSERT INTO locaux (id,nom,adresse,description,image_name,note,google_map) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, o.getId());
            pst.setString(2, o.getNom());
            pst.setString(3, o.getAdresse());
            pst.setString(4, o.getDescription());
            pst.setString(5, o.getImage_name());
           pst.setInt(6, o.getNote());
            pst.setString(7, o.getGoogle_map());
         
            pst.executeUpdate();
            System.out.println("Locale ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Locaux> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 @Override
 public void modifierLocaux(Locaux o) {
String req ="UPDATE locaux SET nom='"+o.getNom()+"',adresse='"+o.getAdresse()+"',description='"+o.getDescription()+"',image_name='"+o.getImage_name()+"',note='"+o.getNote()+"',google_map='"+o.getGoogle_map()+"' WHERE id="+o.getId() ;
    try {
        
             PreparedStatement st1 = cnx.prepareStatement(req);
             //String value1 = tf_instru.getText();
             st1.executeUpdate();
            System.out.println("Locale modifié");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        }    }
public void deleteLocaux(Locaux o) {
        String req = "delete from locaux where id=" + o.getId();
        //if (c != null) {
            try {
            PreparedStatement st1 = cnx.prepareStatement(req);
             //String value1 = tf_instru.getText();
             st1.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(ServiceLocaux.class.getName()).log(Level.SEVERE, null, ex);
            }
        /*} else {
            System.out.println("Service n'existe pas");
        /*} else {
            System.out.println("Service n'existe pas");
        }*/

    }

  public Locaux details(int id) throws SQLException {
       String req = "select * from Locaux where id ='" + id + "'";

        Locaux A = null;

        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                //java.sql.Date sqlDate = java.sql.Date.valueOf(rs.getDate(6).toLocalDate());
     
           A = new Locaux(rs.getInt("id"),rs.getString("nom"),rs.getString("adresse"),rs.getString("description"),rs.getString("image_name"),rs.getInt("note"),rs.getString("google_map"));            
      
           //A= new Offre(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5), sqlDate,rs.getString(7));
            }
        } catch (SQLException ex) {
//            Logger.getLogger(ServiceProjet.class.getName()).log(Level.SEVERE, null, ex);
        }

        return A;
    }    

  public ArrayList<Locaux> DisplayNEW() throws SQLException {
        ArrayList<Locaux> TabA = new ArrayList<>();
        String req = "SELECT * FROM Locaux ";
     
     
       PreparedStatement  p = cnx.prepareStatement(req);
    
        ResultSet rs = p.executeQuery();
        while (rs.next()) {

             TabA.add(new Locaux(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4) , rs.getString(5),rs.getInt(6), rs.getString(7) ));

        }
        return TabA;
    }
  
  public ArrayList<Locaux> DisplayAll() throws SQLException {
        ArrayList<Locaux> TabP = new ArrayList<>();
        String req = "SELECT * FROM Locaux";
        PreparedStatement p;
        p = cnx.prepareStatement(req);
        ResultSet rs = p.executeQuery();
        while (rs.next()) { 

            TabP.add(new Locaux(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4) , rs.getString(5),rs.getInt(6), rs.getString(7) ));
        }
        return TabP;
    }
  

    }