/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.models;

import java.util.Date;

/**
 *
 * @author user
 */
public class CommandeAff {
    Integer id;
    Date date;
    String nom_produit ;
    String nom_client ;
    Integer quantite;
    Double prix;
    String statue ;
    Date dateliv ;
    String adresse ;

    public CommandeAff(Integer id, Date date, String nom_produit, String nom_client, Integer quantite, Double prix, String statue, Date dateliv, String adresse) {
        this.id = id;
        this.date = date;
        this.nom_produit = nom_produit;
        this.nom_client = nom_client;
        this.quantite = quantite;
        this.prix = prix;
        this.statue = statue;
        this.dateliv = dateliv;
        this.adresse = adresse;
    }



    public CommandeAff() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public String getNom_client() {
        return nom_client;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Double getPrix() {
        return round(prix,2);
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getStatue() {
        return statue;
    }

    public void setStatue(String statue) {
        this.statue = statue;
    }

    public Date getDateliv() {
        return dateliv;
    }

    public void setDateliv(Date dateliv) {
        this.dateliv = dateliv;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

  

 
public static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    long factor = (long) Math.pow(10, places);
    value = value * factor;
    long tmp = Math.round(value);
    return (double) tmp / factor;
}
   
}