/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.models;

/**
 *
 * @author USER
 */
public class produits {
    private int id_produit;
    private int categorie_id;
    private String nom_produit;
    private String description_produit;
    private int quantite_produit;
    private Double prix_produit;
    private String image_name;

    public produits() {}

    public produits(int id_produit, String nom_produit, String description_produit, String image_name, int quantite_produit, Double prix_produit) 
    {
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.description_produit = description_produit;
        this.quantite_produit = quantite_produit;
        this.prix_produit =prix_produit ;
        this.image_name =image_name ;
    }
    
    public produits(int id_produit,int categorie_id, String nom_produit, String description_produit, String image_name, int quantite_produit, Double prix_produit) 
    {
        this.id_produit = id_produit;
        this.categorie_id = categorie_id;
        this.nom_produit = nom_produit;
        this.description_produit = description_produit;
        this.quantite_produit = quantite_produit;
        this.prix_produit =prix_produit ;
        this.image_name =image_name ;
    }
    public produits(String nom_produit, String description_produit, String image_name, int quantite_produit, Double prix_produit) 
    {
        this.nom_produit = nom_produit;
        this.description_produit = description_produit;
        this.quantite_produit = quantite_produit;
        this.prix_produit =prix_produit ;
        this.image_name =image_name ;
    }
    /**
     * @return the id_produit
     */
    public int getId_produit() {
        return id_produit;
    }

        /**
     * @param id_produit the id_produit to set
     */
    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }
    
       /**
     * @return the categorie_id
     */
    public int getCategorie_id() {
        return categorie_id;
    }

        /**
     * @param categorie_id the categorie_id to set
     */
    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }
    
    /**
     * @return the nom_produit
     */
    public String getNom_produit() {
        return nom_produit;
    }

    /**
     * @param nom_produit the nom_produit to set
     */
    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    
    /**
     * @return the description_produit
     */
    public String getDescription_produit() {
        return description_produit;
    }

    /**
     * @param description_produit the description_produit to set
     */
    public void setDescription_produit(String description_produit) {
        this.description_produit = description_produit;
    }

    /**
     * @return the quantite_produit
     */
    public int getQuantite_produit() {
        return quantite_produit;
    }

    /**
     * @param quantite_produit the quantite_produit to set
     */
    public void setQuantite_produit(int quantite_produit) {
        this.quantite_produit = quantite_produit;
    }
    

    
        /**
     * @return the prix_produit
     */
    public Double getPrix_produit() {
        return prix_produit;
    }

    /**
     * @param prix_produit the prix_produit to set
     */
    public void setPrix_produit(Double prix_produit) {
        this.prix_produit = prix_produit;
    }
    
    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }
    
}
