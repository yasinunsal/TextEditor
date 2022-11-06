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
public abstract class ChangeFontDecorator implements FontChanger {
    
    protected FontChanger fontChanger;
    
    protected ChangeFontDecorator(FontChanger fontChanger){
        this.fontChanger = fontChanger;
    }
    public String changeFont(){
        return fontChanger.changeFont();
    }
    
}
