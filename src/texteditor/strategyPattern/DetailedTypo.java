/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor.strategyPattern;

import java.util.ArrayList;

/**
 *
 * @author egecan
 */
public class DetailedTypo implements TypoBehaviour{
    
    private ArrayList<String> wordsList;
    private String text;
    
    public DetailedTypo(String text, ArrayList<String> wordsList) {
        this.text = text;
        this.wordsList = wordsList;
    }
    
    @Override
    public String typo() {                        
        String[] tempArr = text.split("(?<=,)|(?=,)|(?<=\\.)|(?=\\.)|(?<=;)|(?=;)|(?<=\\s)|(?=\\s)|(?<=\\n)|(?=\\n)|(?<=!)|(?=!)|(?<=\\?)|(?=\\?)|(?<=\")|(?=\")|(?<=:)|(?=:)|(?<=-)|(?=-)");
        String returnValue = "";
        for (int i = 0; i < tempArr.length; i++) {
            String temp = tempArr[i].toLowerCase();
            if (!wordsList.contains(temp) && temp.length() > 1) {
                int similarMax = 0;                
                char firstLetterExist = tempArr[i].charAt(0);
                String closestWord = temp;

                for (int j = 0; j < wordsList.size(); j++) {
                    if (wordsList.get(j).length() == temp.length()) {
                        int asciiTotal1 = 0;
                        int asciiTotal2 = 0;
                        boolean done = false;
                        for (int k = 0; k < temp.length(); k++) {
                            if (!wordsList.get(j).contains(String.valueOf(temp.charAt(k)))) {
                                done = true;
                                break;
                            }
                            asciiTotal1 += wordsList.get(j).charAt(k);
                            asciiTotal2 += temp.charAt(k);                            
                        }
                        if (!done) {
                            if (asciiTotal1 == asciiTotal2) {
                                int differentCtr = 0;
                                int transposControl = 0;
                                boolean cont = true;
                                for (int k = 0; k < temp.length(); k++) {
                                    if (temp.charAt(k) != wordsList.get(j).charAt(k)) {
                                        if (differentCtr == 0) transposControl -= k;
                                        else transposControl += k;
                                        differentCtr++;
                                    }
                                    if (differentCtr > 2) {
                                        cont = false;
                                        break;
                                    }
                                }
                                if (cont && transposControl == 1) {
                                    closestWord = wordsList.get(j);
                                    break;
                                }                            
                            }   
                        }
                    }
                }

                if (Character.isUpperCase(firstLetterExist)) {
                    closestWord = closestWord.substring(0, 1).toUpperCase() + closestWord.substring(1);
                    tempArr[i] = closestWord;
                } else tempArr[i] = closestWord;
            }     
        }
                
        returnValue = String.join("", tempArr);                
        return returnValue;
    }
    
}
