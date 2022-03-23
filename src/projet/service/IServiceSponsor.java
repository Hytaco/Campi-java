/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;

import projet.models.sponsors;
import java.util.List;

/**
 *
 * @author Firqs
 */
public interface IServiceSponsor {
     public void ajouter(sponsors s);
    //public void supprimer(int id);
    public void modifierSponsor(sponsors s);
    public List<sponsors> afficher();
    public void deleteSponsor(sponsors s);
}
