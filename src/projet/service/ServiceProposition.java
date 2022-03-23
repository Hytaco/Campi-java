/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;

import projet.models.proposition;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import projet.utils.Connexionjdbc;

/**
 *
 * @author Firqs
 */
public class ServiceProposition implements IServiceProposition {
    
    
     private  ResultSet rs;
private Statement st;

public static int loggeduser;
                   Connection cnx ;

    public ServiceProposition() {
        this.cnx = Connexionjdbc.getInstance().getConnection();
    }
public void ajouter(proposition p) {
        try {
            String requete = "INSERT INTO proposition (id,nom,date,nombre_place,email,mail) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, p.getId());
            pst.setString(2, p.getNom());
             pst.setDate(3,(Date) p.getDate());
            pst.setInt(4, p.getNombre_place());
            pst.setString(5, p.getEmail());
            pst.setString(6, p.getMail());
            pst.executeUpdate();
            System.out.println("Proposition ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

  public List<proposition> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
  public void deletePropo(proposition p) {
        String req = "delete from proposition where id=" + p.getId();
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
    
}
