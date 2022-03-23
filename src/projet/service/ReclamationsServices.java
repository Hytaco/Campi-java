/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;

import projet.models.Reclamations; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import projet.utils.Connexionjdbc;
/**
 *
 * @author ASUS
 */
public class ReclamationsServices {
    
    Connection cnx;
    PreparedStatement ste;
    
    public ReclamationsServices(){
        this.cnx = Connexionjdbc.getInstance().getConnection();
    }
    
    public void ajouterReclamations(Reclamations c){
         try {
        String sql = "insert into reclamations(type,description,email)"+"values(?,?,?)";
        ste = cnx.prepareStatement(sql);
        ste.setString(1, c.getType());
        ste.setString(2, c.getDescription());
        ste.setString(3, c.getEmail());
        ste.executeUpdate();
             System.out.println("Réclamation ajoutée!");
         }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void modifierreclamations(Reclamations c){
        String req = "UPDATE reclamations  SET type='"+c.getType()+"',description='"+c.getDescription()+"',email='"+c.getEmail()+"'WHERE id="+c.getId();
        try {
            PreparedStatement st1 = cnx.prepareStatement(req);
             
             st1.executeUpdate();
            System.out.println("Reclamation modifié");

        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());

        }

    }
    
    public void supprimerReclamations(Reclamations c){
        String requete = "DELETE FROM reclamations WHERE id = ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, c.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        System.out.println("Réclamations supprimée!");
    }
    
     public List<Reclamations> getReclamations(){
        List<Reclamations> list = new ArrayList<>();
        String requete = "SELECT * FROM reclamations ";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Reclamations c = new Reclamations(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));                
                list.add(c);
            }
            System.out.println("List des reclamations à été crée!");
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        return list;
    }
    
}
