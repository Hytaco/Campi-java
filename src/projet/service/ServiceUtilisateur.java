/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;
import projet.models.Admin;
import projet.models.Client;
import projet.models.Fournisseur;
import projet.models.Utilisateur;
import projet.utils.Connexionjdbc;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.mindrot.jbcrypt.BCrypt;

import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author user
 */
public class ServiceUtilisateur {
    Connection cnx ;

    public ServiceUtilisateur() {
        this.cnx = Connexionjdbc.getInstance().getConnection();
    }
    
      public  int Inscription(Utilisateur utilisateur) {
          String role = " ";
        // Define the BCrypt workload to use when generating password hashes. 10-31 is a valid value en regarde le cryptage de fosuserbundle il utilise $2y$13$.
        int workload = 13;
        int status = 0;
        String sql = "INSERT INTO utilisateur(nom,password,email,roles,prenom,adresse,telephone,image_name,activation_token) VALUES(?,?,?,?,?,?,?,?,?)";

        try {
          PreparedStatement  preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.setString(1, utilisateur.getNom());
            String mdp = BCrypt.hashpw(utilisateur.getPassword(), BCrypt.gensalt(workload));
            preparedStatement.setString(2, mdp.replaceFirst("2a", "2y"));
            preparedStatement.setString(3, utilisateur.getEmail());
            preparedStatement.setString(4, utilisateur.RoleToBase());
            preparedStatement.setString(5, utilisateur.getPrenom());
            preparedStatement.setString(6, utilisateur.getAdresse());
            preparedStatement.setInt(7, utilisateur.getTelephone());
            preparedStatement.setString(8, utilisateur.getImage_name());
            preparedStatement.setString(9, utilisateur.getActivation_token());
            
            status = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
      
       public  int ModifierUtilisateur(Utilisateur utilisateur) {
        int workload = 13;
        int status = 0;
        System.out.println(utilisateur.toString());
        String sql = "UPDATE utilisateur SET nom =?,email=?,prenom=?,adresse=?,telephone=?,image_name=? WHERE id=?";

        try {
            PreparedStatement  preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.setString(1, utilisateur.getNom());
            preparedStatement.setString(2, utilisateur.getEmail());
            preparedStatement.setString(3, utilisateur.getPrenom());
            preparedStatement.setString(4, utilisateur.getAdresse());
            preparedStatement.setInt(5, utilisateur.getTelephone());
             preparedStatement.setString(6, utilisateur.getImage_name());
            preparedStatement.setInt(7, utilisateur.getId());

            status = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
   public  int ModifierMotdePasse(String email,String mdp) {
        int workload = 13;
        int status = 0;
        String sql = "UPDATE utilisateur SET password =? WHERE email=?";

        try {
            String pass = BCrypt.hashpw(mdp, BCrypt.gensalt(workload));
            PreparedStatement  preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.setString(1, pass.replaceFirst("2a", "2y"));
            preparedStatement.setString(2, email);
  

            status = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
    public  boolean testMotDePasse(String motDePasseGUI, String motDePasseBD) {
        boolean password_verified = false;
        if (null == motDePasseBD) {
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        }
        password_verified = BCrypt.checkpw(motDePasseGUI, motDePasseBD.replaceFirst("2y", "2a"));
        return (password_verified);
    }

    public  List<Utilisateur> getTtUtilisateur() throws IOException {
        Utilisateur utilisateur =null ;
        List<Utilisateur> list = new ArrayList<Utilisateur>();
        try {
            String sql = "select * from Utilisateur ";
            PreparedStatement preparedStatement = (PreparedStatement) cnx.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
System.out.print(resultSet.getString("roles"));
   if (resultSet.getString("roles").equals("[\"ROLE_ADMIN\"]")){
                        utilisateur = new Admin();
   }else if (resultSet.getString("roles").equals("[\"ROLE_FOUR\"]")){
                        utilisateur = new Fournisseur();
   } else if (resultSet.getString("roles").equals("[\"ROLE_CLIENT\"]")) {
                         utilisateur = new Client();
   } 
                utilisateur.setId(resultSet.getInt("id"));
                utilisateur.setNom(resultSet.getString("nom"));
                utilisateur.setPrenom(resultSet.getString("prenom"));
                utilisateur.setPassword(resultSet.getString("password"));
                utilisateur.setEmail(resultSet.getString("email"));
                utilisateur.setAdresse(resultSet.getString("adresse"));
                utilisateur.setTelephone(resultSet.getInt("telephone"));
                utilisateur.setImage_name(resultSet.getString("image_name"));
                utilisateur.setActivation_token(resultSet.getString("activation_token"));
                                       list.add(utilisateur);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return list;
    }


     public  int SupprimerUtilisateur(Utilisateur utilisateur) {
        int status = 0;
        String sql = "DELETE FROM utilisateur where id = ?";
        try {
          PreparedStatement  preparedStatement = cnx.prepareStatement(sql);
          preparedStatement.setInt(1, utilisateur.getId());
        status = preparedStatement.executeUpdate();
 } catch (SQLException e) {
            e.printStackTrace();
        }
        return status ;
     }
}
