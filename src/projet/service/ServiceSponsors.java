/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;

import projet.models.sponsors;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static projet.service.ServiceEvenement.statement;
import projet.utils.Connexionjdbc;

/**
 *
 * @author Firqs
 */
public class ServiceSponsors implements IServiceSponsor {
     private  ResultSet rs;
private Statement st;
public static int loggeduser;

                   Connection cnx ;

    public ServiceSponsors() {
        this.cnx = Connexionjdbc.getInstance().getConnection();
    }
public void ajouter(sponsors s) {
        try {
            String requete = "INSERT INTO sponsors (id,nom,adresse,mail,numero,image_name) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, s.getId());
            pst.setString(2, s.getNom());
            pst.setString(3,s.getAdresse());
            pst.setString(4, s.getMail());
            pst.setInt(5, s.getNumero());
            pst.setString(6, s.getImage_name());
            pst.executeUpdate();
            System.out.println("sponsor ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<sponsors> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
 public void modifierSponsor(sponsors s) {
String req ="UPDATE sponsors SET nom='"+s.getNom()+"',adresse='"+s.getAdresse()+"',mail='"+s.getMail()+"',numero='"+s.getNumero()+"',image_name='"+s.getImage_name()+"' WHERE id="+s.getId();
    try {
        
             PreparedStatement st1 = cnx.prepareStatement(req);
             //String value1 = tf_instru.getText();
             st1.executeUpdate();
            System.out.println("sponsor modifié");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        }    }
public void deleteSponsor(sponsors s) {
        String req = "delete from sponsors where id=" + s.getId();
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

    
public List<sponsors> listerPromotion()
    {
        List<sponsors> myList=new ArrayList();
        try {
        
        String requete4="select * from sponsors";      
     statement = cnx.createStatement();
                ResultSet rs = statement.executeQuery(requete4);
            
            while(rs.next())
            {
              //  Promotion p=new Promotion();
                //p.setNom_promotion(rs.getString(2));
                //p.setId(rs.getInt(1));
                myList.add(new sponsors(rs.getInt(1),rs.getString(2),rs.getString(2),rs.getString(4),rs.getInt(5),rs.getString(6)));
             

            }                                    
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

}
