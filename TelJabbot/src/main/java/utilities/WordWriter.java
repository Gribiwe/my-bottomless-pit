package utilities;

import study.Word;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by nikit on 2018/02/22.
 */
public class WordWriter {

    public static void add(int telegaID, Word word) {
        JSONObject obj = new JSONObject();

        obj.put("word", word.getWord());
        obj.put("meaning", word.getMeaning());

        try {
            List<String> lines = Files.readAllLines(Paths.get(PathSystem.USERS_DIR + "/" + telegaID + ".DICT"));
            obj.put("rlvl", 0);
            lines.add(obj.toJSONString());

            Files.write(Paths.get(PathSystem.USERS_DIR + "/" + telegaID + ".DICT"), lines);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static boolean importDictionary(int telegaID, String fromPath) {
        try {
            if(Files.exists(Paths.get(fromPath+ ".DICT"))) {
                List<String> fromLines = Files.readAllLines(Paths.get(fromPath + ".DICT"));
                List<String> toLines = Files.readAllLines(Paths.get(PathSystem.USERS_DIR + "/" + telegaID + ".DICT"));

                for (String line : fromLines) {

                    if (!toLines.contains(line))
                        toLines.add(new StringBuilder().append(line).replace(8, 9, "0").toString());
                }

                Files.write(Paths.get(PathSystem.USERS_DIR + "/" + telegaID + ".DICT"), toLines);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void deleteFile(int telegaID) {
        try {
            Files.deleteIfExists(Paths.get(PathSystem.USERS_DIR+"/"+telegaID+".DICT"));
            Files.createFile(Paths.get(PathSystem.USERS_DIR+"/"+telegaID+".DICT"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeWord(int telegaID, int ID) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(PathSystem.USERS_DIR + "/" + telegaID + ".DICT"));
            lines.remove(ID);
            Files.write(Paths.get(PathSystem.USERS_DIR + "/" + telegaID + ".DICT"), lines);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void updateWord(int telegaID, Word word) {

        try {
            List<String> lines = Files.readAllLines(Paths.get(PathSystem.USERS_DIR + "/" + telegaID + ".DICT"));
            JSONObject obj = new JSONObject();
            obj.put("word", word.getWord());
            obj.put("meaning", word.getMeaning());
            //obj.put("id", word.getId());
            obj.put("rlvl", word.getRememberLVL());

            lines.set(word.getId(), obj.toJSONString());
            Files.write(Paths.get(PathSystem.USERS_DIR + "/" + telegaID + ".DICT"), lines);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
