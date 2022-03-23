/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;

import projet.models.Locaux;
import java.util.List;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author asus
 *
 */
public interface IServices {
    public void ajouter(Locaux o);
    //public void supprimer(int id);
    public void modifierLocaux(Locaux o);
    public List<Locaux> afficher();
    public void deleteLocaux(Locaux o);
}
