/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor.commandpattern;

import javafx.scene.control.TextArea;
import texteditor.TextEditor;

/**
 *
 * @author erdem
 */
public class OperationCommand implements Command {

    private String oldValue, newValue;
    private TextEditor texteditor;
    
    public OperationCommand(String oldValue, String newValue){
        this.oldValue = oldValue;
        this.newValue = newValue;
        texteditor = TextEditor.getInstance();
    }
            
    @Override
    public void execute() {
        texteditor.operation(oldValue, newValue);
    }

    @Override
    public void undo() {
        texteditor.undo();
    }

    @Override
    public boolean undoable() {
        return true;
    }
    
}
