/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Egecan, Erdem, Yasin
 */
public class MainController extends Main implements Initializable{
                   
    @FXML
    private AnchorPane rootPane;
    private Pane openPane;
    private Pane createPane;
    
    @Override   
    public void initialize(URL url, ResourceBundle rb) { 
        
    } 
    

    @FXML
    private void openFile(ActionEvent event) throws IOException {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OpenMenu.fxml"));
            Parent openPane = loader.load();
            rootPane.getChildren().setAll(openPane);

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void createFile(ActionEvent event) throws IOException {
        try {
            Parent createPane = FXMLLoader.load(getClass().getResource("CreateMenu.fxml"));
            rootPane.getChildren().setAll(createPane);
        } catch(Exception e){
            e.printStackTrace();
        }
    }


    
}
