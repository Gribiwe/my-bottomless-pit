package utilities;

import study.Word;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by nikit on 2018/02/22.
 */
public class WordReader {

    public static Word getFirstWord(int telegaID) {

        return  getWords(telegaID).first();
    }

    public static TreeSet<Word> getWords(int telegaID){
        try {
            TreeSet words = new TreeSet(new WordComparator());

            JSONObject object;
            Word word;
            int i = 0;
            for (String line: Files.readAllLines(Paths.get(PathSystem.USERS_DIR+"/"+telegaID+".DICT"))) {
                object = (JSONObject) JSONValue.parse(line);
                words.add(new Word(i, object.get("word").toString(),
                        object.get("meaning").toString(),Integer.parseInt(object.get("rlvl").toString())));
                i++;
            }

            return words;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Word> getWordsArray(int telegaID){
        try {
            ArrayList<Word> words = new ArrayList<Word>();

            JSONObject object;
            Word word;
            int i = 0;
            for (String line: Files.readAllLines(Paths.get(PathSystem.USERS_DIR+"/"+telegaID+".DICT"))) {
                object = (JSONObject) JSONValue.parse(line);
                words.add(new Word(i, object.get("word").toString(),
                        object.get("meaning").toString(),Integer.parseInt(object.get("rlvl").toString())));
                i++;
            }

            return words;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
