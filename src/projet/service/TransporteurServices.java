/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;

import projet.models.Transporteur; 
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
public class TransporteurServices {
    
    Connection cnx;
    PreparedStatement ste;
    
    public TransporteurServices(){
        this.cnx = Connexionjdbc.getInstance().getConnection();
    }
    
    public void ajouterTransporteur(Transporteur t){
         try {
        String sql = "insert into transporteur(type,numero,nom,adresse,mail)"+"values(?,?,?,?,?)";
        ste = cnx.prepareStatement(sql);
        ste.setString(1, t.getType());
        ste.setInt(2, t.getNumero());
        ste.setString(3, t.getNom());
        ste.setString(4, t.getAdresse());
        ste.setString(5, t.getMail());
        ste.executeUpdate();
             System.out.println("Transporteur ajoutée!");
         }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
   public void modifiertransporteur(Transporteur t){
        String req ="UPDATE transporteur SET type='"+t.getType()+"',numero='"+t.getNumero()+"',nom='"+t.getNom()+"',adresse='"+t.getAdresse()+"',mail='"+t.getMail()+"' WHERE id="+t.getId() ;
    try {

             PreparedStatement st1 = cnx.prepareStatement(req);
             
             st1.executeUpdate();
            System.out.println("Transporteur modifié");

        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());

        }

    }
    
    public void supprimerTransporteur(Transporteur c){
        String requete = "DELETE FROM transporteur WHERE id = ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, c.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        System.out.println("Transporteur supprimée!");
    }
    
     public List<Transporteur> getTransporteur(){
        List<Transporteur> list = new ArrayList<>();
        String requete = "SELECT * FROM transporteur ";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Transporteur t = new Transporteur(rs.getInt(1), rs.getString(2),  rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6));                
                list.add(t);
            }
            System.out.println("List des TRANSPORTEURS à été crée!");
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        return list;
        
    }
    
}
