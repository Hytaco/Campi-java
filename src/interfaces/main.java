/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author USER
 */
public class main extends Application {
    private Stage primaryStage;
    private Parent parentPage;
    @Override
   public void start(Stage primaryStage)  
    {
          
        this.primaryStage = primaryStage;
        try { 
        parentPage = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(parentPage);
         primaryStage.initStyle(StageStyle.UNDECORATED);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
         StackPane root = new StackPane();
        new animatefx.animation.BounceIn(root).play();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
}
