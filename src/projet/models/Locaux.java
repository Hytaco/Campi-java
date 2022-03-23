/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.models;

/**
 *
 * @author Hass
 */
public class Locaux {
private int id;
private String nom;
private String adresse;
private String description;
private String image_name;
private int note;
private String google_map;

    public Locaux() {
    }

    public Locaux(int id, String nom, String adresse, String description, String image_name, int note, String google_map) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.description = description;
        this.image_name = image_name;
        this.note = note;
        this.google_map = google_map;
    }






    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getDescription() {
        return description;
    }

    public String getImage_name() {
        return image_name;
    }

    public int getNote() {
        return note;
    }

    public String getGoogle_map() {
        return google_map;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public void setGoogle_map(String google_map) {
        this.google_map = google_map;
    }

    @Override
    public String toString() {
        return "Locaux{" + "nom=" + nom + ", adresse=" + adresse + ", description=" + description + ", image_name=" + image_name + ", note=" + note + ", google_map=" + google_map + "}\n";
    }





}
