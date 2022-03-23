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

/**
 *
 * @author Taher
 */
public class CategorieService implements Icategories{
       Connection cnx;
        static Statement statement = null;

 public CategorieService() {
        cnx = Connexionjdbc.getInstance().getConnection();
    }
    
    
    @Override
    public int ajoutercategories(categories c) {
  
 int status =0;
        try { 
            Statement stm = cnx.createStatement();
            String query = "INSERT INTO `categories`(`nom`, `description`,`image_name`) VALUES ('"+c.getNom_categorie()+"','"+c.getDescription_categorie()+"','"+c.getImage_name()+"')";
      System.out.println(query);
            status =    stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status; 

 
    }

    
    @Override
    public void supprimercategories(categories p) {
        String req = "delete from categories where id=" + p.getId_categorie();
        
            try {
            PreparedStatement st1 = cnx.prepareStatement(req);
             st1.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    

    @Override
    public List<categories> affichercategories()throws SQLException
    {
       Statement stm = cnx.createStatement();
            String query = "SELECT * FROM `categories`";
            ResultSet rst = stm.executeQuery(query);
            List<categories> CategorieService;
            CategorieService = new ArrayList<>();
            while (rst.next())
            {
                categories categories = new categories();
                categories.setId_categorie(rst.getInt("id"));
                categories.setNom_categorie(rst.getString("nom"));
                categories.setDescription_categorie(rst.getString("description"));
                categories.setImage_name(rst.getString("image_name"));
                CategorieService.add(categories);
            }
        return CategorieService;
    }

    @Override
    public void modifiercategories(categories p) 
    {
   
String req ="UPDATE categories SET nom='"+p.getNom_categorie()+"',description='"+p.getDescription_categorie()+"', image_name='"+p.getImage_name()+"' WHERE id="+p.getId_categorie() ;
    try {
             PreparedStatement st1 = cnx.prepareStatement(req);
             //String value1 = tf_instru.getText();
             st1.executeUpdate();
            System.out.println("categories modifié");     
        } 
    catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        }    }
   public List<categories> listerPromotion()
    {
        List<categories> myList=new ArrayList();
        try {
        
        String requete4="select * from categorie";      
     statement = cnx.createStatement();
                ResultSet rs = statement.executeQuery(requete4);
            
            while(rs.next())
            {
              //  Promotion p=new Promotion();
                //p.setNom_promotion(rs.getString(2));
                //p.setId(rs.getInt(1));
                myList.add(new categories(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4)));
             

            }                                    
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
  public ArrayList<categories> DisplayAll() throws SQLException {
        ArrayList<categories> TabP = new ArrayList<>();
        String req = "SELECT * FROM categories";
        PreparedStatement p;
        p = cnx.prepareStatement(req);
        ResultSet rs = p.executeQuery();
        while (rs.next()) { 

            TabP.add(new categories(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4)));
        }
        return TabP;
    }
}

