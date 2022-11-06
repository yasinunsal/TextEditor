/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import texteditor.commandpattern.CloseCommand;

import texteditor.commandpattern.CommandInvoker;
import texteditor.commandpattern.OperationCommand;
import texteditor.commandpattern.ReplaceCommand;
import texteditor.commandpattern.SaveCommand;
import texteditor.commandpattern.TypoCommand;
import texteditor.decoratorpattern.ArielFont;
import texteditor.decoratorpattern.DialogFont;
import texteditor.decoratorpattern.FontChanger;
import texteditor.decoratorpattern.MonospacedFont;
import texteditor.decoratorpattern.SansSerifFont;
import texteditor.decoratorpattern.SerifFont;

/**
 * FXML Controller class
 *
 * @author Egecan, Erdem, Yasin
 */
public class TextController extends Main implements Initializable {
    
    private TextArea textArea;
    private TextEditor textEditor = TextEditor.getInstance();
    private TextField oldWord, newWord;
    private CommandInvoker cmdinv;
    private boolean operationBool;
    private int fontCtr = 0;

    @FXML
    private Pane mainTextPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textArea = (TextArea) mainTextPane.lookup("#editText");       
        oldWord = (TextField) mainTextPane.lookup("#oldWordText");
        newWord = (TextField) mainTextPane.lookup("#newWordText");
        cmdinv = new CommandInvoker();
        operationBool = false;
        textArea.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
            if(operationBool){
                OperationCommand operation = new OperationCommand(oldValue, newValue);
                cmdinv.execute(operation);
                operationBool = false;
            }
        }
    });
       
    }
    
    @FXML
    private void returnMainMenu() throws IOException {        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent textPane = loader.load();        
        mainTextPane.getChildren().setAll(textPane);
        CloseCommand close = new CloseCommand();
        cmdinv.execute(close);
    }

    @FXML
    private void keyPressed(){
        operationBool = true;
    }
    

    
    @FXML
    private void undoLetter(ActionEvent event) {
        cmdinv.undo();
        textArea.setText(textEditor.getText());
    }

    @FXML
    private void fixTypo(ActionEvent event) {
        TypoCommand typo = new TypoCommand();
        cmdinv.execute(typo);
        textArea.setText(textEditor.getText());
    }
    
    @FXML
    private void save(ActionEvent event) throws IOException {
        SaveCommand save = new SaveCommand(textArea.getText());
        cmdinv.execute(save);
    }
    
    @FXML
    private void replaceWord(ActionEvent event) {
        ReplaceCommand replace = new ReplaceCommand(oldWord.getText(), newWord.getText());
        cmdinv.execute(replace);
        textArea.setText(textEditor.getText());
        
    }
    @FXML
    public void changeFont(){
        ArrayList<FontChanger> fontList = new ArrayList<>();
        fontList.add(new SerifFont());
        fontList.add(new SansSerifFont());
        fontList.add(new MonospacedFont());
        fontList.add(new DialogFont());
        fontList.add(new ArielFont());
        textArea.setFont(Font.font(fontList.get(fontCtr%fontList.size()).changeFont(),12));
        fontCtr++;
    }

    

}
