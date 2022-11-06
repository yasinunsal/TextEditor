/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import texteditor.commandpattern.CloseCommand;
import texteditor.commandpattern.CommandInvoker;

/**
 *
 * @author Egecan, Erdem, Yasin
 */
public class Main extends Application{
                
    @Override
    public void start(Stage primaryStage) throws Exception {                         
        try{            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Text Editor");
            primaryStage.show();
            TextEditor textEditor = TextEditor.getInstance();
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){
                public void handle(WindowEvent we){
                if(textEditor.fr != null) {
                    CloseCommand close = new CloseCommand();
                    CommandInvoker cmdinv = new CommandInvoker();
                    cmdinv.execute(close);
                    }
                }
            });
        
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        launch(args);        
    }
    
}
