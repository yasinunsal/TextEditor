/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor.commandpattern;

import texteditor.TextEditor;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author erdem
 */
public class OpenCommand implements Command{
    private String fileName;
    private TextEditor textEditor;
    private boolean returnStatus;
    
    public OpenCommand(String fileName){
        this.fileName = fileName;
        this.textEditor = TextEditor.getInstance();
    }

    @Override
    public void execute() {
        try {
            returnStatus = textEditor.openFile(fileName);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OpenCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void undo() {
        
    }

    @Override
    public boolean undoable() {
        return false;
    }
    
    public boolean getReturnStatus(){
        return this.returnStatus;
    }
    
}
