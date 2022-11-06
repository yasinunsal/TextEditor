/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor.commandpattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javafx.scene.control.TextArea;
import texteditor.TextEditor;
import texteditor.strategyPattern.BruteForceTypo;
import texteditor.strategyPattern.DetailedTypo;
import texteditor.strategyPattern.TypoBehaviour;

/**
 *
 * @author erdem
 */
public class TypoCommand implements Command {
    private TextEditor texteditor;
    
    public TypoCommand(){
        texteditor = TextEditor.getInstance();
    }

    @Override
    public void execute() {
        texteditor.typoCorrection();
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
