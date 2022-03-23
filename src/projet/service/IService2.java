/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;

import projet.models.Programmes;
import java.util.List;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author asus
 *
 */
public interface IService2 {
  
      public void ajouter2(Programmes p);
 
    public void modifierProgrammes(Programmes p);
    public List<Programmes> afficher2();
    public void deleteProgrammes(Programmes p);
}
