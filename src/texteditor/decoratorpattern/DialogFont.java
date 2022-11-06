/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor.decoratorpattern;

/**
 *
 * @author yasin
 */
public class DialogFont implements FontChanger {
    @Override
    public String changeFont(){
        return "Dialog";
    }
    
}
