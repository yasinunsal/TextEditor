/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor.strategyPattern;

import java.util.ArrayList;
import java.util.Locale;
import javafx.scene.control.TextArea;

/**
 *
 * @author egecan
 */
public class BruteForceTypo implements TypoBehaviour{
      
    private String text;
    private ArrayList<String> wordsList;
    
    public BruteForceTypo(String text, ArrayList<String> wordsList) {        
        this.text = text;
        this.wordsList = wordsList;
    }

    @Override
    public String typo() {                  
        // We take all the words as an array
        String[] tempArr = text.split("(?<=,)|(?=,)|(?<=\\.)|(?=\\.)|(?<=;)|(?=;)|(?<=\\s)|(?=\\s)|(?<=\\n)|(?=\\n)|(?<=!)|(?=!)|(?<=\\?)|(?=\\?)|(?<=\")|(?=\")|(?<=:)|(?=:)|(?<=-)|(?=-)");
        String returnValue = "";        
        for (int i = 0; i < tempArr.length; i++) {
            String temp = tempArr[i].toLowerCase(Locale.ENGLISH);
            char firstLetterExist = tempArr[i].charAt(0);
            
            if (!wordsList.contains(temp)) {
                /* 
                 * Main idea here is to find all the miswritten words and then 
                 * create each word's all single transpositions starting from
                 * first two letters. If current transposition exists in wordsList,
                 * we change the current miswritten word to it's correct form.
                 */
                int tempLength = temp.length();
                for (int j = 0; j < tempLength - 1; j++) {
                    char[] chars = new char[tempLength];
                    temp.getChars(0, tempLength, chars, 0);                    
                    
                    char tempChar = chars[j];
                    chars[j] = chars[j + 1];
                    chars[j + 1] = tempChar;
                    String fixedWord = new String(chars);
                    
                    if (wordsList.contains(fixedWord)) {                        
                        /* 
                         * We check whether the word that has been changed starts
                         * with upper case letter or lower case letter. If the word
                         * starts with an upper case letter, we turn the first letter
                         * upper case again.
                         */
                        if (Character.isUpperCase(firstLetterExist)) {
                            fixedWord = fixedWord.substring(0, 1).toUpperCase(Locale.ENGLISH) + fixedWord.substring(1);
                            tempArr[i] = fixedWord;
                        } else tempArr[i] = fixedWord;
                        break;
                    }
                }                 
            }            
        }
        returnValue = String.join("", tempArr);        
        return returnValue;
    }
    
}
