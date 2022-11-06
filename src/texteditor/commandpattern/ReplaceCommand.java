/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor.commandpattern;

import java.io.File;
import java.util.Locale;
import javafx.scene.control.TextArea;
import texteditor.TextEditor;

/**
 *
 * @author erdem
 */
public class ReplaceCommand implements Command {
    private String existingWord, replacingWord;
    private TextEditor texteditor;

    
    public ReplaceCommand(String existingWord, String replacingWord){
        this.existingWord = existingWord;
        this.replacingWord = replacingWord;
        texteditor = TextEditor.getInstance();
    }
    
    @Override
    public void execute() {
        texteditor.replaceWord(existingWord, replacingWord);
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
