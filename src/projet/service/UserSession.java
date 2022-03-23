/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;

import projet.models.Produit;
import projet.models.Utilisateur;
import java.util.HashMap;
import projet.models.produits;

/**
 *
 * @author user
 */

        public final class UserSession {

    private static UserSession instance;

    private Utilisateur utilisateur ;
    private HashMap<produits,Integer> panier ;

    public UserSession(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        this.panier =  new HashMap<produits,Integer>();
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

   public void AddLignePanier(produits produit){
       panier.put(produit,1);
   }
     public void RemoveLignePanier(produits produit){
       panier.remove(produit);
   }
     public void SetLignePanier(produits produit,Integer quantite){
       panier.put(produit,quantite);
   }

    public HashMap<produits, Integer> getPanier() {
        return panier;
    }

    public static UserSession getInstace() {
        return instance;
    }
    public static void setInstace(Utilisateur utilisateur) {
            instance = new UserSession(utilisateur);
        
    }
    
  
}
