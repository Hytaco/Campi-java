/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.models;

/**
 *
 * @author Taher
 */
public class publicite {
    
    private int id_publicite ;
    private String nom_publicite;
    private String lien_publicite;
    private String image_name;
    
    public publicite(){}
    
    public publicite(int id_publicite, String nom_publicite, String lien_publicite, String image_name) 
    {
        this.id_publicite = id_publicite;
        this.nom_publicite = nom_publicite;
        this.lien_publicite = lien_publicite;
        this.image_name =image_name ;
    }


    
    /**
     * @return the id_publicite
     */
    public int getId_publicite() {
        return id_publicite;
    }

        /**
     * @param id_publicite the id_produit to set
     */
    public void setId_publicite(int id_publicite) {
        this.id_publicite = id_publicite;
    }
    
    /**
     * @return the nom_publicite
     */
    public String getNom_publicite() {
        return nom_publicite;
    }

    /**
     * @param nom_publicite the nom_produit to set
     */
    public void setNom_publicite(String nom_publicite) {
        this.nom_publicite = nom_publicite;
    }

    
    /**
     * @return the lien_publicite
     */
    public String getLien_publicite() 
    {
        return lien_publicite;
    }

    /**
     * @param lien_publicite the lien_publicite to set
     */
    public void setLien_publicite(String lien_publicite) 
    {
        this.lien_publicite = lien_publicite;
    }
    
    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }
    
}
