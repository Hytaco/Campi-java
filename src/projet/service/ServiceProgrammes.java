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
import projet.service.ServiceLocaux;
/**
 *
 * @author Hass
 */
public class ServiceProgrammes implements IService2 {
  
    
    
    
    
    
 private  ResultSet rs;
private Statement st;
    Connection cnx = DataSource.getInstance().getCnx();   
    public static int loggeduser;
 public  Programmes getProgramme(int id)  {
     Programmes p = null ;
          try {
              
              String sql = "SELECT p.id as pid,transporteur_id,p.nom as pnom,date,duree,details,l.* FROM programmes p JOIN locaux l on(p.locale_id=l.id)WHERE p.id= ?";
              PreparedStatement preparedStatement = (PreparedStatement) cnx.prepareStatement(sql);
              preparedStatement.setInt(1, id);

              ResultSet rs = preparedStatement.executeQuery();

              while (rs.next()) {
          Locaux locaux = new Locaux(rs.getInt("pid"),rs.getString("pnom"),rs.getString("adresse"),rs.getString("description"),rs.getString("image_name"),rs.getInt("note"),rs.getString("google_map"));            
              p =  new Programmes(rs.getInt("id"),rs.getString("nom"),rs.getDate("date"),rs.getInt("duree"),rs.getString("details"),locaux);
                  return p;
              }
          } catch (SQLException ex) {
              Logger.getLogger(ServiceProgrammes.class.getName()).log(Level.SEVERE, null, ex);
          }
          return p ;
    }
 
 public int Participer(int idp , int idu){
     int status = 0 ;
     try {
            String requete = "INSERT INTO programmes_utilisateur(programmes_id ,utilisateur_id ) VALUES (?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, idp);
            pst.setInt(2, idu);
           status = pst.executeUpdate();
                   
      } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
                return status ;
 }
 
 public void ajouter2(Programmes p) {
        try {
            String requete = "INSERT INTO programmes(id,locale_id ,nom,date,duree,details) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, p.getId());
            pst.setInt(2, p.getLoc());
            pst.setString(3, p.getNom());
             pst.setDate(4,(Date) p.getDate());
            pst.setInt(5, p.getDuree());
            pst.setString(6, p.getDetails());

            pst.executeUpdate();
            System.out.println("Programme ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<Programmes> afficher2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 public void modifierProgrammes(Programmes p) {
String req ="UPDATE programmes SET nom='"+p.getNom()+"',nom_categorie_id='"+p.getLoc()+"',date='"+p.getDate()+"',duree='"+p.getDuree()+"',details='"+p.getDetails()+"' WHERE id="+p.getId() ;    try {
        
             PreparedStatement st1 = cnx.prepareStatement(req);
             //String value1 = tf_instru.getText();
             st1.executeUpdate();
            System.out.println("Programme modifié");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        }    
 }
 
public void deleteProgrammes(Programmes p) {
        String req = "delete from programmes where id=" + p.getId();
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
    
    
    
    
 public ArrayList<Programmes> DisplayNEW() throws SQLException {
        ArrayList<Programmes> TabA = new ArrayList<>();
        String req = "SELECT * FROM Programmes ";
       System.out.println("fff");
     
       PreparedStatement  p = cnx.prepareStatement(req);
         System.out.println("aaaa");
        ResultSet rs = p.executeQuery();
        while (rs.next()) {

             TabA.add(new Programmes(rs.getInt(1), rs.getString(4) , rs.getDate(5),rs.getInt(6), rs.getString(7) ));

        }
        return TabA;
    }
    public Programmes details(int id) throws SQLException {
       String req = "select * from Programmes where id ='" + id + "'";

        Programmes A = null;

        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                //java.sql.Date sqlDate = java.sql.Date.valueOf(rs.getDate(6).toLocalDate());
     
           A = new Programmes(rs.getInt("id"),rs.getString("nom"),rs.getDate("date"),rs.getInt("duree"),rs.getString("details"));            
      
           //A= new Offre(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5), sqlDate,rs.getString(7));
            }
        } catch (SQLException ex) {
//            Logger.getLogger(ServiceProjet.class.getName()).log(Level.SEVERE, null, ex);
        }

        return A;
    }    
      public ArrayList<Programmes> DisplayAll() throws SQLException {
        ArrayList<Programmes> TabP = new ArrayList<>();
        String req = "SELECT * FROM Programmes";
        PreparedStatement p;
        p = cnx.prepareStatement(req);
        ResultSet rs = p.executeQuery();
        while (rs.next()) { 

            TabP.add(new Programmes(rs.getInt(1), rs.getString(4) , rs.getDate(5),rs.getInt(6), rs.getString(7)));
        }
        return TabP;
    }
    
}
