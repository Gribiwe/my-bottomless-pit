package study;

import poly.PolyWritter;
import utilities.PathSystem;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by nikit on 2018/02/22.
 */
public class Word implements Comparable<Word> {
    private int id;
    private String word;
    private String meaning;
    private int rememberLVL;

    public int getId() {
        return id;
    }


    public Word(String word, String meaning) {

        this.word = word;
        this.meaning = meaning;

    }

    public int getRememberLVL() {
        return rememberLVL;
    }

    public Word(int id, String word, String meaning, int rememberLVL) {
        this.id = id;
        this.word = word;
        this.meaning = meaning;
        this.rememberLVL = rememberLVL;
    }

    public void ugadal(boolean ansv) {
        if (ansv) {rememberLVL++;}
        else { rememberLVL--;}
    }

    public File getVoice() {

        if(!Files.exists(Paths.get(PathSystem.JAP_AUDIOS_DIR+"/"+this.word+".mp3"))){
            try {
                PolyWritter.createReadingFile(this.word);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        File file = new File(PathSystem.JAP_AUDIOS_DIR+"/"+this.word+".mp3");
        System.out.println("re "+file.getAbsolutePath());
        return file;
    }

    public String getWord() {
        return word;
    }

    public String getMeaning() {
        return meaning;
    }

    @Override
    public int compareTo(Word o) {
        return rememberLVL-o.rememberLVL;
    }
}
