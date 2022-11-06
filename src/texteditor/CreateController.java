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
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import texteditor.commandpattern.CommandInvoker;
import texteditor.commandpattern.CreateCommand;

/**
 * FXML Controller class
 *
 * @author Egecan, Erdem, Yasin
 */
public class CreateController extends Main implements Initializable {
    
    private TextField fileName;
    private TextEditor textEditor = TextEditor.getInstance();
    private CommandInvoker cmdinv;
    
    @FXML
    private Pane mainPaneCreate;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fileName = (TextField) mainPaneCreate.lookup("#createFileName");
        cmdinv = new CommandInvoker();
    }    

    @FXML
    private void createFile() throws IOException{
        CreateCommand crtcmd = new CreateCommand(fileName.getText());
        cmdinv.execute(crtcmd);
        boolean fileCreated = crtcmd.getReturnStatus();
        if (fileCreated) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TextMenu.fxml"));
            Parent createPane = loader.load();
            mainPaneCreate.getChildren().setAll(createPane);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("File already exists.");
            alert.showAndWait();
            
        }
        
    }
    
    @FXML
    private void returnMainMenu(ActionEvent event) throws IOException {
        Parent createPane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        mainPaneCreate.getChildren().setAll(createPane);
    }
    
}
