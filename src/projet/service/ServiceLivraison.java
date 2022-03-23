/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import projet.models.Livraison;
import projet.utils.Connexionjdbc;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author dell
 */
public class ServiceLivraison {
                   Connection cnx ;

    public ServiceLivraison() {
        this.cnx = Connexionjdbc.getInstance().getConnection();
    }
    
    public  int Ajouter(Livraison livraison) {
   
        // Define the BCrypt workload to use when generating password hashes. 10-31 is a valid value en regarde le cryptage de fosuserbundle il utilise $2y$13$.
        int workload = 13;
        int status = 0;
        String sql = "INSERT INTO livraisons (datelivraison,adresse) VALUES(?,?)";

        try {
          PreparedStatement  preparedStatement = cnx.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, new java.sql.Date(livraison.getDatelivraison().getTime()));
            preparedStatement.setString(2, livraison.getAdresse());

            status = preparedStatement.executeUpdate();
             try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                livraison.setId(generatedKeys.getInt(1));
            }
            else {
                throw new SQLException("Creating Livraison failed, no ID obtained.");
            }
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livraison.getId();
    }
    
       public  List<Livraison> AfficheLivraison()  {
        List<Livraison> list = new ArrayList<Livraison>();
        try {
            String sql = "select * from livraisons ";
            PreparedStatement preparedStatement = (PreparedStatement) cnx.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                             
                Livraison livraison = new Livraison();
                livraison.setId(resultSet.getInt("id"));
                livraison.setDatelivraison( resultSet.getDate("datelivraison"));
                livraison.setAdresse(resultSet.getString("adresse"));
              //  livraison.setLivreur(resultSet.getInt("livreur_id"));

                list.add(livraison);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return list;

    }

       public  int ModifierLivraison(Livraison livraison) {
        // Define the BCrypt workload to use when generating password hashes. 10-31 is a valid value en regarde le cryptage de fosuserbundle il utilise $2y$13$.
        int workload = 13;
        int status = 0;
        System.out.println(livraison.toString());
        String sql = "UPDATE livraisons SET datelivraison=?,adresse=? WHERE id=?";

        try {
                        PreparedStatement  preparedStatement = cnx.prepareStatement(sql);

            preparedStatement.setDate(1, new java.sql.Date(livraison.getDatelivraison().getTime()));
            preparedStatement.setString(2, livraison.getAdresse());
            preparedStatement.setInt(3, livraison.getId());

            status = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }


        public  int SupprimerLivraison(Livraison liv) {
  
         
        int status = 0;
        String sql = "DELETE FROM livraisons where id = ?";
        try {
          PreparedStatement  preparedStatement = cnx.prepareStatement(sql);
          preparedStatement.setInt(1, liv.getId());
        status = preparedStatement.executeUpdate();
 } catch (SQLException e) {
            e.printStackTrace();
        }
        return status ;
     }


}
