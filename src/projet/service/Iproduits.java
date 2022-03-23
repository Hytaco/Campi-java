/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;

import java.sql.SQLException;
import java.util.List;
import projet.models.produits;

/**
 *
 * @author Taher
 */
public interface Iproduits 
{
public void ajouteproduits(produits p);
public void supprimerproduits(produits p);
public List<produits> afficherproduits();
public void modifierproduits(produits p);
}