/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor.commandpattern;

import texteditor.TextEditor;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author erdem
 */
public class CreateCommand implements Command {
    private String fileName;
    private TextEditor textEditor;
    private boolean returnStatus;
    
    public CreateCommand(String fileName){
        this.fileName = fileName;
        this.textEditor = TextEditor.getInstance();
    }
    
    @Override
    public void execute() {
        returnStatus = textEditor.createFile(fileName);
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
