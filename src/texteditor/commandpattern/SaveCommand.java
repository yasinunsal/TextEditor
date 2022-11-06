/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor.commandpattern;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import texteditor.TextEditor;

/**
 *
 * @author erdem
 */
public class SaveCommand implements Command {

    private String text;
    private TextEditor texteditor;
    
    public SaveCommand(String text){
        this.text = text;
        texteditor = TextEditor.getInstance();
    }
    
    @Override
    public void execute() { 
        try {
            texteditor.saveFile(text);
        } catch (IOException ex) {
            Logger.getLogger(SaveCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void undo() {
        
    }

    @Override
    public boolean undoable() {
        return false;
    }
    
}
