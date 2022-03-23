/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.models;

/**
 *
 * @author Hassene
 */
public class Client extends Utilisateur {

    public Client() {
    }

    public Client(String nom, String prenom, int telephone, String adresse, String password, String email, String image_name, String activation_token) {
        super(nom, prenom, telephone, adresse, password, email, image_name, activation_token);
    }

   

    public Client(int id, String nom, String prenom, int telephone, String adresse, String email, String image_name) {
        super(id, nom, prenom, telephone, adresse, email, image_name);
    }
    public  String  getRole(){
    return "Client" ;
}
     public String RoleToBase(){
        return "[\"ROLE_CLIENT\"]" ; 
   }
}
