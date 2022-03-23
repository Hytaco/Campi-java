/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;

import projet.models.categories;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Taher
 */
public interface Icategories {

public int ajoutercategories(categories c);
public void supprimercategories(categories c);
public List<categories> affichercategories()throws SQLException;
public void modifiercategories(categories c);
    
}
