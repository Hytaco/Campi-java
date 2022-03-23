/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.models;

import java.sql.Date;

/**
 *
 * @author Hass
 */
public class Programmes {
private int loc;    
private int id;
private String nom;
private Date date;
private int duree;
private String details;
private Locaux locaux;

    public Programmes() {
    }

    public Programmes(int id, String nom, Date date, int duree, String details, Locaux locaux) {
   
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.duree = duree;
        this.details = details;
        this.locaux = locaux;
 
    }
      public Programmes(int id, String nom, Date date, int duree, String details) {
   
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.duree = duree;
        this.details = details;
        this.locaux = locaux;
 
    }
    
 

    public Programmes(int id,int loc, String nom, Date date, int duree, String details) {
        this.id = id;
             this.loc = loc;
        this.nom = nom;
        this.date = date;
        this.duree = duree;
        this.details = details;
    }

   

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Date getDate() {
        return date;
    }

    public int getDuree() {
        return duree;
    }

    public String getDetails() {
        return details;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Locaux getLocaux() {
        return locaux;
    }

    public void setLocaux(Locaux locaux) {
        this.locaux = locaux;
    }

    public int getLoc() {
        return loc;
    }

    public void setLoc(int loc) {
        this.loc = loc;
    }

}
