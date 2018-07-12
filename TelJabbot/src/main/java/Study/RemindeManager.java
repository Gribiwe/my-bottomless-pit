package study;

import utilities.PathSystem;
import utilities.WordReader;
import utilities.WordWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by nikit on 2018/02/22.
 */
public class RemindeManager {
    public static HashMap<Integer, Word> activeUsers = new HashMap();
    public static HashMap<Integer, Boolean> whatToCheck = new HashMap();

    public static boolean checkMeaning(int telegramId, String answer) {

        Word word = activeUsers.get(telegramId);
        word.ugadal(answer.equals(word.getMeaning()));
        WordWriter.updateWord(telegramId, word);

        return answer.equals(word.getMeaning());

    }

    public static boolean checkWord(int telegramId, String answer) {

        Word word = activeUsers.get(telegramId);
        word.ugadal(answer.equals(word.getWord()));
        WordWriter.updateWord(telegramId, word);

        return answer.equals(word.getWord());

    }

    public static boolean changeWord(int telegramID) {
        try {
            boolean empty = (Files.readAllLines(Paths.get(PathSystem.USERS_DIR + "/" + telegramID + ".DICT")).size() < 1);
            if (empty) {
                return false;
            }

            activeUsers.replace(telegramID, WordReader.getFirstWord(telegramID));
            whatToCheck.replace(telegramID, new Random().nextInt(20)>7); //true - meaning , false - word

            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static boolean init(int telegramId) {

        if (!Files.exists(Paths.get(PathSystem.USERS_DIR.toAbsolutePath() + "/" + telegramId + ".DICT"))) {
            try {
                Files.createFile(Paths.get(PathSystem.USERS_DIR.toAbsolutePath() + "/" + telegramId + ".DICT"));

            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        return true;

    }

}
