import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by nikit on 2018/02/05.
 */
public class FileWorker {

    public static String fileName;

    public static void initFileToDB() {
        System.out.println("Введите название или путь файла расширения CSV");
        fileName = new Scanner(System.in).next().replaceAll(".csv", "").replaceAll(".CSV", "");

        if (Files.exists(Paths.get(fileName + ".csv"))) {
            System.out.println(fileName + " найден.");
            return;
        } else {
            System.out.println(fileName + " не найден.");
            initFileToDB();
            return;
        }
    }

    public static void initDBToFile() {
        System.out.println("Введите название или путь файла расширения CSV");
        fileName = new Scanner(System.in).next().replaceAll(".csv", "").replaceAll(".CSV", "");

        try {
            if (!Files.exists(Paths.get(fileName))) Files.createFile(Paths.get(fileName));
            System.out.println(fileName + " найден.");
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getFileToSQL() {
        ArrayList<String> list = new ArrayList<>();

        try {

            List<String> lines = Files.readAllLines(Paths.get(fileName + ".csv"));
            String valuesFields = " (id " + lines.get(0).substring(lines.get(0).indexOf(',')) + ") ".replaceAll(" ", "");

            System.out.println(valuesFields);

            list.add("CREATE TABLE IF NOT EXISTS " + fileName + " (id int, " +
                    lines.get(0).substring(lines.get(0).indexOf(',') + 1).
                            replaceAll(",", " varchar(40), ") + " varchar(40));");

            for (int i = 1; i < lines.size(); i++) {
                list.add("INSERT INTO " + fileName + valuesFields + "VALUES ("+ lines.get(i).replaceAll(",", "','").replaceFirst("'", "").replaceAll(" ", "") + "');");

            }

            for (String line : list) {
                System.out.println("Added " + line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void transDBtoFile(ArrayList<String> rows) {

         try {
            if(!Files.exists(Paths.get(DataBase.tableName+".csv"))) Files.createFile(Paths.get(DataBase.tableName+".csv"));
            Files.write(Paths.get(DataBase.tableName+".csv"), rows);
            System.out.println("Done");
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
