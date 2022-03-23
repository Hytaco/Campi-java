/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;

import projet.models.evenements;
import java.util.List;

/**
 *
 * @author Firqs
 */
public interface IService {
     public void ajouter(evenements e);
    //public void supprimer(int id);
    public void modifierEvenement(evenements e);
    public List<evenements> afficher();
    public void deleteEvenement(evenements e);
    public void Rating (evenements e);
}
