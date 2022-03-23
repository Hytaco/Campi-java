/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;

import projet.models.Commande;
import projet.models.CommandeAff;
import projet.models.LigneCommande;
import projet.utils.Connexionjdbc;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author user
 */
public class ServiceCommande {
      Connection cnx ;

    public ServiceCommande() {
        this.cnx = Connexionjdbc.getInstance().getConnection();
    }
    public  int getReduction(int code)  {
          try {
              String sql = "SELECT * FROM coupon WHERE code =?";
              PreparedStatement preparedStatement = (PreparedStatement) cnx.prepareStatement(sql);
              preparedStatement.setInt(1, code);
              ResultSet resultSet = preparedStatement.executeQuery();

              while (resultSet.next()) {
                  return resultSet.getInt("pourcentage");
              }
          } catch (SQLException ex) {
              Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
          }
          return 0 ;
    }

public  HashMap<Date,Double> getStatCommandes()  {
        HashMap<Date,Double> map = new HashMap<Date,Double>();
        try {
            String sql = "SELECT c.date_commande as date,sum(c.prix) as revenue FROM commande c GROUP BY c.date_commande ORDER BY date ASC";
            PreparedStatement preparedStatement = (PreparedStatement) cnx.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
        
                    while (resultSet.next()) {
                        map.put(resultSet.getDate("date"), resultSet.getDouble("revenue"));
                    }

        } catch (SQLException e) {
            e.printStackTrace();

        }
return map;
    }
     public  List<CommandeAff> getTCommandes() {
        List<CommandeAff> list = new ArrayList<CommandeAff>();
        try {
            String sql = "select c.id as id, c.date_commande as date,lc.quantite as quantite,u.nom as cnom"
                    + ", p.nom as pnom , lc.prix as prix , c.statue as statue,l.adresse as adresse,l.datelivraison as dateliv"
                    + " from ligne_commande lc JOIN Commande c on (lc.commande_id=c.id)  "
                    + "JOIN Produits p on (lc.produit_id=p.id) "
                    +"JOIN Utilisateur u on (c.utilisateur_id=u.id)"
                    + "JOIN Livraisons l on (l.id=c.livraison_id)";
            PreparedStatement preparedStatement = (PreparedStatement) cnx.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                             
                CommandeAff commande = new CommandeAff(resultSet.getInt("id"),
                        resultSet.getDate("date"),resultSet.getString("pnom"),resultSet.getString("cnom"),
                        resultSet.getInt("quantite"),resultSet.getDouble("prix"),resultSet.getString("statue"),resultSet.getDate("dateliv"),resultSet.getString("adresse"));
        
                list.add(commande);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return list;

    }
     
     
     public  int SupprimerCommandes(CommandeAff commande) {
  
         
        int status = 0;
        String sql = "DELETE FROM commande where id = ?";
        String sql2 = "DELETE FROM ligne_commande where commande_id  = ?";

        try {
          PreparedStatement  preparedStatement = cnx.prepareStatement(sql);
         PreparedStatement  preparedStatement2 = cnx.prepareStatement(sql2);
            preparedStatement2.setInt(1, commande.getId());
        status = preparedStatement2.executeUpdate();
          preparedStatement.setInt(1, commande.getId());
        status = preparedStatement.executeUpdate();
    
 } catch (SQLException e) {
            e.printStackTrace();
        }
        return status ;
     }
     
     
          public  Commande AjouterCommande(Commande commande) {
                      int status = 0;
        String sql = "INSERT INTO Commande(utilisateur_id,date_commande,prix,statue) VALUES(?,?,?,?)";
         try {
          PreparedStatement  preparedStatement = cnx.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
          preparedStatement.setInt(1, commande.getIdclient());
          preparedStatement.setDate(2, new java.sql.Date(commande.getDate().getTime()) );
          preparedStatement.setDouble(3,commande.getPrix());
          preparedStatement.setString(4,"Non Payé");
          status = preparedStatement.executeUpdate();
           try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                commande.setId(generatedKeys.getInt(1));
            }
            else {
                throw new SQLException("Creating Commande failed, no ID obtained.");
            }
            
        }
 } catch (SQLException e) {
            e.printStackTrace();
        }
        return commande ;
          }

public int ToPayer(int id){
    int status = 0;
            String sql = "UPDATE Commande SET statue = ? WHERE id = ?";
 try {
          PreparedStatement  preparedStatement = cnx.prepareStatement(sql);
          preparedStatement.setString(1, "Payé");
          preparedStatement.setInt(2, id);
           status = preparedStatement.executeUpdate();
                    
 } catch (SQLException e) {
            e.printStackTrace();
        }
        return status ;
          }

public int setLivraison(int idc,int idl){
    int status = 0;
            String sql = "UPDATE Commande SET livraison_id  = ? WHERE id = ?";
 try {
     System.out.println(idc + " "+idl);
          PreparedStatement  preparedStatement = cnx.prepareStatement(sql);
          preparedStatement.setInt(1,idl);
          preparedStatement.setInt(2, idc);
           status = preparedStatement.executeUpdate();
                    
 } catch (SQLException e) {
            e.printStackTrace();
        }
        return status ;
          }


     public  int AjouterLigneCommande(LigneCommande lcommande) {
                      int status = 0;
        String sql = "INSERT INTO ligne_commande(commande_id,produit_id,quantite,prix) VALUES(?,?,?,?)";
         try {
          PreparedStatement  preparedStatement = cnx.prepareStatement(sql);
          preparedStatement.setInt(1, lcommande.getCommande().getId());
          preparedStatement.setInt(2, lcommande.getProduit().getId_produit());
          preparedStatement.setInt(3, lcommande.getQuantite());
          preparedStatement.setDouble(4, lcommande.getPrix());

                    status = preparedStatement.executeUpdate();
                    
 } catch (SQLException e) {
            e.printStackTrace();
        }
        return status ;
          }
}

