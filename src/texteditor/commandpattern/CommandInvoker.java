/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor.commandpattern;

import java.util.ArrayList;

/**
 *
 * @author erdem
 */
public class CommandInvoker {
    private ArrayList<Command> commandHistory;
    
    public CommandInvoker(){
        commandHistory = new ArrayList<>();
    }
    
    public void execute(Command command){
        if(command.undoable())
            commandHistory.add(command);
        command.execute();
        
    }
    
    public void undo(){
        if(!commandHistory.isEmpty()){
            Command tempCommand = commandHistory.remove(commandHistory.size()-1);
            tempCommand.undo();
        }
        
    }
    
}
