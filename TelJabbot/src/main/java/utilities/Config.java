package utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikit on 2018/02/22.
 */
public class Config {
    private static List<String> lines;

    public static String BOT_USERNAME;
    public static String F_POLY_KEY;
    public static String S_POLY_KEY;
    public static String TELEGRAM_BOT_TOKEN;

    public static void configInit() {
        try {
            lines = Files.readAllLines(PathSystem.CONFIG_FILE);
            BOT_USERNAME = lines.get(0).split("<>")[1];;
            F_POLY_KEY = lines.get(1).split("<>")[1];
            S_POLY_KEY = lines.get(2).split("<>")[1];
            TELEGRAM_BOT_TOKEN = lines.get(3).split("<>")[1];

//            System.out.println(BOT_USERNAME);
//            System.out.println(F_POLY_KEY);
//            System.out.println(S_POLY_KEY);
//            System.out.println(TELEGRAM_BOT_TOKEN);

            System.out.println("Config loaded.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getPublics() {
        ArrayList<String> list = new ArrayList<String>();
        for (String arg: lines.get(4).split("<>")){
            list.add(arg);
        }
        return list;
    }

}
