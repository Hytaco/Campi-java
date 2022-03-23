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
import projet.models.produits;

/**
 *
 * @author Taher
 */
public class ProduitsService implements Iproduits{
       Connection cnx;
       PreparedStatement ste;
        static Statement statement = null;
       public ProduitsService() {
        cnx = Connexionjdbc.getInstance().getConnection();
    }
       
        
    @Override
    public void ajouteproduits(produits p) {
    try {
        String sql = "insert into produits(categorie_id,nom,description,image_name,quantite,prix)"+"values(?,?,?,?,?,?)";
        ste = cnx.prepareStatement(sql);
        ste.setInt(1, p.getCategorie_id());
        ste.setString(2, p.getNom_produit());
        ste.setString(3, p.getDescription_produit());       
        ste.setString(4, p.getImage_name());
        ste.setInt(5, p.getQuantite_produit());
        ste.setDouble(6, p.getPrix_produit());
        ste.executeUpdate();
             System.out.println("prdouuit ajoutée!");
         }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerproduits(produits p) {
        String req = "delete from produits where id=" + p.getId_produit();
        
            try {
            PreparedStatement st1 = cnx.prepareStatement(req);
             st1.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(ProduitsService.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    

    @Override
    public List<produits> afficherproduits()
    {
         List<produits> list = new ArrayList<>();
                 String requete = "SELECT * FROM produits ";       
        
         try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                produits a = new produits(rs.getInt("id"),rs.getString("nom"), rs.getString("description"),rs.getString("image_name"),rs.getInt("quantite"),rs.getDouble("prix"));                
                list.add(a);
            }
            System.out.println("List des produits à été crée!");
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        return list;
    }

    public produits readbyId(int id){
        produits a=null;
        String req ="SELECT * FROM produits WHERE id=?";
        try {
            PreparedStatement st=cnx.prepareStatement(req);
            st.setInt(1, id);
            ResultSet rs=st.executeQuery();
            if(rs.next())
            {
                a=new produits(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getInt(5),rs.getDouble(6));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return a;


    }
    @Override
    public void modifierproduits(produits p) 
    {
   
String req ="UPDATE produits SET categorie_id='"+p.getCategorie_id()+"',nom='"+p.getNom_produit()+"',description='"+p.getDescription_produit()+"',quantite="+p.getQuantite_produit()+", prix="+p.getPrix_produit()+", image_name='"+p.getImage_name()+"' WHERE id="+p.getId_produit() ;
    try {
             PreparedStatement st1 = cnx.prepareStatement(req);
             //String value1 = tf_instru.getText();
             st1.executeUpdate();
            System.out.println("produits modifié");     
        } 
    catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        }    }

    public List<categories> affichercategories() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public List<produits> listerPromotion()
    {
        List<produits> myList=new ArrayList();
        try {
        
        String requete4="select * from produits";      
     statement = cnx.createStatement();
                ResultSet rs = statement.executeQuery(requete4);
            
            while(rs.next())
            {
              //  Promotion p=new Promotion();
                //p.setNom_promotion(rs.getString(2));
                //p.setId(rs.getInt(1));
                myList.add(new produits(rs.getInt("id"),rs.getString("nom"), rs.getString("description"),rs.getString("image_name"),rs.getInt("quantite"),rs.getDouble("prix")));
             

            }                                    
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
}
