/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;

import projet.models.evenements;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import projet.utils.Connexionjdbc;

/**
 *
 * @author Firqs
 */
public class ServiceEvenement implements IService {
     private  ResultSet rs;
private Statement st;
    public static int loggeduser;
    static Statement statement = null;
    Connection cnx ;   


    public ServiceEvenement() {
        this.cnx = Connexionjdbc.getInstance().getConnection();
    }
public void ajouter(evenements e) {
        try {
            String requete = "INSERT INTO evenements (id,nom,date,image_name,nbrplace,datef,lieu,description) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, e.getId());
            pst.setString(2, e.getNom());
            pst.setDate(3,(Date) e.getDate());
            pst.setString(4, e.getImage_name());
            pst.setInt(5, e.getNbrplace());
            pst.setDate(6,(Date) e.getDatef());
            pst.setString(7, e.getLieu());
            pst.setString(8, e.getDescription());
            pst.executeUpdate();
            System.out.println("évènement ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<evenements> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
 public void modifierEvenement(evenements e) {
String req ="UPDATE evenements SET nom='"+e.getNom()+"',date='"+e.getDate()+"',image_name='"+e.getImage_name()+"',nbrplace='"+e.getNbrplace()+"',datef='"+e.getDatef()+"',lieu='"+e.getLieu()+"',description='"+e.getDescription()+"' WHERE id="+e.getId();
    try {
        
             PreparedStatement st1 = cnx.prepareStatement(req);
             //String value1 = tf_instru.getText();
             st1.executeUpdate();
            System.out.println("évènement modifié");
            
        } catch (SQLException ex) {
            System.out.println("Problème");
            System.out.println(ex.getMessage());
            
        }    }
public void deleteEvenement(evenements e) {
        String req = "delete from evenements where id=" + e.getId();
        //if (c != null) {
            try {
            PreparedStatement st1 = cnx.prepareStatement(req);
             //String value1 = tf_instru.getText();
             st1.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
            }
        /*} else {
            System.out.println("Service n'existe pas");
        }*/

    }

public List<evenements> listerPromotion()
    {
        List<evenements> myList=new ArrayList();
        try {
        
        String requete4="select * from evenements";      
     statement = cnx.createStatement();
                ResultSet rs = statement.executeQuery(requete4);
            
            while(rs.next())
            {
              //  Promotion p=new Promotion();
                //p.setNom_promotion(rs.getString(2));
                //p.setId(rs.getInt(1));
                myList.add(new evenements(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getString(4),rs.getInt(5),rs.getDate(6),rs.getString(7),rs.getString(8)));
             

            }                                    
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }


public void Rating(evenements e){
try {  
    System.out.println(e.getRating());
            
          //  String requete = "update user set username=? ,last_name=?, email=?, adresse=?, age=? , telephone=?,image=?,password=?  WHERE id=?";
            String requete = "update evenements set rating=?  WHERE id=?";
            PreparedStatement st = cnx.prepareStatement(requete);
            
            st.setInt(2, e.getId());
            System.out.println(st);
            st.setDouble(1,e.getRating());
        
            
            st.executeUpdate();
            System.out.println("rating ajouter ");
              
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
}
}
