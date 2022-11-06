/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this wordslate file, choose Tools | wordslates
 * and open the wordslate in the editor.
 */
package texteditor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Set;
import javafx.scene.control.TextArea;
import texteditor.strategyPattern.BruteForceTypo;
import texteditor.strategyPattern.DetailedTypo;
import texteditor.strategyPattern.TypoBehaviour;
/**
 *
 * @author Egecan, Erdem, Yasin
 */
public class TextEditor {

    private static TextEditor txtedt = new TextEditor();
    private Scanner fileScanner;
    private BufferedReader wordsReader;
    private ArrayList<String> wordsList, previousTexts;
    private String text = "";
    protected File file;
    protected FileWriter fr;
    private boolean created;
    private Set wordsSet;

    private TextEditor() {
        try {//Adding the words in words.txt to an arraylist.
            wordsReader = new BufferedReader(new FileReader("words.txt"));
            String wordsLine = wordsReader.readLine();
            wordsSet = new HashSet();
            wordsList = new ArrayList<>();
            previousTexts = new ArrayList<>();
            while (wordsLine != null) {
                wordsSet.add(wordsLine);
                wordsLine = wordsReader.readLine();
            }
            wordsReader.close();
            
            wordsDuplicateRemoval();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void wordsDuplicateRemoval(){
        Iterator<String> wordsSetIterator = wordsSet.iterator();
        while (wordsSetIterator.hasNext())
           wordsList.add(wordsSetIterator.next());
        
        Collections.sort(wordsList);
    }
    
    public static TextEditor getInstance(){
        return txtedt;
    }
    
    public void setCreated(boolean created){
        this.created = created;
    }
    
    public boolean getCreated(){
        return created;
    }
    
    public void setFile(File file){
        this.file = file;
    }
    
    public String getText(){
        return text;
    }
    
    public void setText(String text){
        this.text = text;
    }
    
    public File getFile() {
        return file;
    }

    public boolean createFile(String fileName) {
        try {
            file = new File(fileName + ".txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
                created = true;
            } else {
                System.out.println("File already exists.");
                created = false;
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return created;
    }

    public boolean openFile(String fileName) throws FileNotFoundException {
        file = new File(fileName + ".txt");
        text = "";
        if (file.exists()) {
            fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (fileScanner.hasNextLine()) line += "\n";
                text += line;
            }
            previousTexts.add(text);    
        } else {
            return false;
        }
        
        return true;
    }

    public void closeFile() throws IOException {
        if (fr != null) 
            fr.close();
    }

    public void saveFile(String text) throws IOException {
        fr = new FileWriter(file,false);
        try {
            fr.write(text);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void replaceWord(String existingWord, String replacingWord) {
        String[] words = text.split("(?<=,)|(?=,)|(?<=\\.)|(?=\\.)|(?<=;)|(?=;)|(?<=\\s)|(?=\\s)|(?<=\\n)|(?=\\n)|(?<=!)|(?=!)|(?<=\\?)|(?=\\?)|(?<=\")|(?=\")|(?<=:)|(?=:)|(?<=-)|(?=-)");
        for (int index = 0; index < words.length; ++index) {
            if (words[index].equalsIgnoreCase(existingWord)) {
                char firstLetterExist = words[index].charAt(0);
                if (Character.isUpperCase(firstLetterExist)) {
                    words[index] = replacingWord.substring(0, 1).toUpperCase(Locale.ENGLISH) + replacingWord.substring(1);
                } else {
                    words[index] = replacingWord.substring(0, 1).toLowerCase(Locale.ENGLISH) + replacingWord.substring(1);
                }
            }
        }
        previousTexts.add(text);
        text = String.join("", words);
    }
    
    public void undo() {
        text = previousTexts.remove(previousTexts.size()-1);
    }

    public void typoCorrection() {
        previousTexts.add(this.text);
        TypoBehaviour typo;
        int misSpelledCtr = 0;
        String[] tempArr = text.split("(?<=,)|(?=,)|(?<=\\.)|(?=\\.)|(?<=;)|(?=;)|(?<=\\s)|(?=\\s)|(?<=\\n)|(?=\\n)|(?<=!)|(?=!)|(?<=\\?)|(?=\\?)|(?<=\")|(?=\")|(?<=:)|(?=:)|(?<=-)|(?=-)");
        for (int i = 0; i < tempArr.length; i++) {
            String word = tempArr[i];
            if (!wordsList.contains(word) && word.length() > 1) misSpelledCtr++;
        }
                
        if (misSpelledCtr > 30) typo = new BruteForceTypo(text, wordsList);
        else typo = new DetailedTypo(text, wordsList);
        
        String returnValue = typo.typo();        
        this.text = returnValue;
    }        
    
    public void operation(String oldValue, String newValue){
        previousTexts.add(oldValue);        
        text = newValue;                
    }
    
}
