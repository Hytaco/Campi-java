/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Haboub
 */
public class Connexionjdbc {
    final static String URL = "jdbc:mysql://127.0.0.1:3306/hytaco";
    final static String LOGIN = "root";
    final static String PWD = "";
    static Connexionjdbc instance = null;
    private Connection cnx;
    
    private Connexionjdbc ()
    {
        try{
        cnx = DriverManager.getConnection(URL,LOGIN,PWD);
            System.out.println("Connection established.");
        }catch(SQLException ex){
            System.out.println("Could not establish connection");
        }
    }
    
    public  static  Connexionjdbc getInstance()
    {
        if(instance == null)
        {
            instance = new Connexionjdbc();
        }
      return instance;  
    }
    public Connection getConnection()
    {
        return cnx;
    }
}
