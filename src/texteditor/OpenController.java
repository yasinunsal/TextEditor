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
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;
import texteditor.commandpattern.CommandInvoker;
import texteditor.commandpattern.OpenCommand;

/**
 * FXML Controller class
 *
 * @author Egecan, Erdem, Yasin
 */
public class OpenController extends Main implements Initializable {   
    
    private TextField fileName;
    private TextEditor textEditor = TextEditor.getInstance();
    private CommandInvoker cmdinv;
    
    @FXML
    private Pane mainPaneOpen;
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {                 
        fileName = (TextField) mainPaneOpen.lookup("#openFileName");
        cmdinv = new CommandInvoker();
    }    

    @FXML
    private void openFile() throws IOException{
        OpenCommand open = new OpenCommand(fileName.getText());
        cmdinv.execute(open);
        boolean fileOpened = open.getReturnStatus();
        if (fileOpened) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TextMenu.fxml"));
            Parent openPane = loader.load();
            TextArea textArea = (TextArea) openPane.lookup("#editText");            
            textArea.setText(textEditor.getText());
            mainPaneOpen.getChildren().setAll(openPane);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("File cannot be opened.");
            alert.showAndWait();
            
        }
    }
    
    @FXML
    private void returnMainMenu(ActionEvent event) throws IOException {
        Parent openPane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        mainPaneOpen.getChildren().setAll(openPane);
    }
    
}
