/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.models;

import java.util.List;

/**
 *
 * @author Hassene
 */
public class Fournisseur extends Utilisateur {

    List<Produit> produits ;
    public Fournisseur() {
    }

    public Fournisseur(String nom, String prenom, int telephone, String adresse, String password, String email, String image_name, String activation_token) {
        super(nom, prenom, telephone, adresse, password, email, image_name, activation_token);
    }

    public Fournisseur(int id, String nom, String prenom, int telephone, String adresse, String email, String image_name) {
        super(id, nom, prenom, telephone, adresse, email, image_name);
    }
public String  getRole(){
    return "Fournisseur" ;
}
    public String RoleToBase(){
      return "[\"ROLE_FOUR\"]";
   }
    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }
     
}
