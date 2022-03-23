/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;

import projet.models.Livreur;
import java.sql.Connection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import projet.utils.Connexionjdbc;

/**
 *
 * @author dell
 */
public class ServiceLivreur {
                Connection cnx ;

    public ServiceLivreur() {
        this.cnx = Connexionjdbc.getInstance().getConnection();
    }
          public  int Ajouter(Livreur livreur) {
   
        // Define the BCrypt workload to use when generating password hashes. 10-31 is a valid value en regarde le cryptage de fosuserbundle il utilise $2y$13$.
        int workload = 13;
        int status = 0;
        String sql = "INSERT INTO livreurs (telephone,adresse,mail,nom)   VALUES(?,?,?,?)";

        try {

            
          PreparedStatement  preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.setInt(1,livreur.getTelephone());
            preparedStatement.setString(2, livreur.getAdresse());
            preparedStatement.setString(3,livreur.getEmail());
            preparedStatement.setString(4,livreur.getNom());


            status = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
       public  List<Livreur> AfficheLivreur() throws IOException {
        List<Livreur> list = new ArrayList<Livreur>();
        try {
            String sql = "select * from livreurs ";
            PreparedStatement preparedStatement = (PreparedStatement) cnx.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                             
                Livreur livreur = new Livreur();
                livreur.setId(resultSet.getInt("id"));
                livreur.setTelephone(resultSet.getInt("telephone"));
                livreur.setAdresse(resultSet.getString("adresse"));
                livreur.setEmail(resultSet.getString("mail"));
                livreur.setNom(resultSet.getString("nom"));
                list.add(livreur);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return list;

    }

       public  int ModifierLivreur(Livreur livreur) {
        int workload = 13;
        int status = 0;
        String sql = "UPDATE livreurs SET telephone=?,adresse=?,mail=?,nom=? WHERE id=?";

        try {
                        PreparedStatement  preparedStatement = cnx.prepareStatement(sql);
             preparedStatement.setInt(1,livreur.getTelephone());
            preparedStatement.setString(2, livreur.getAdresse());
            preparedStatement.setString(3,livreur.getEmail());
            preparedStatement.setString(4,livreur.getNom());
             preparedStatement.setInt(5,livreur.getId());

            status = preparedStatement.executeUpdate();
                    System.out.println(status);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }



        public  int SupprimerLivreur(Livreur liv) {
  
         
        int status = 0;
        String sql = "DELETE FROM livreurs where id = ?";
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
