/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplicationfx;

import java.sql.Connection;
import projet.utils.Connexionjdbc;

/**
 *
 * @author ASUS
 */
public class JavaApplicationFX {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       Connection cnx;
        cnx = Connexionjdbc.getInstance().getConnection();

    }
    
}
