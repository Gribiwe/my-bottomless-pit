package third;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by nikit on 17.02.2018.
 */
public class GFileReader {
    private String fileName;

    public void initFileToDB() {
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

    public String getFileName() {
        return fileName;
    }

    public ArrayList<ClassInfo> getClasses() {

        ArrayList<ClassInfo> classes = new ArrayList<>();

        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(fileName + ".csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(lines.size()>20) {
            System.err.println("Слишком много классов, их не должно превышать 20. Классов: "+lines.size());
            System.exit(0);
        }

        String values[];

        for (String line : lines) {
            values = line.split(",");
            if (values[0].length()>3) {
                System.err.println("Нереальный класс: "+values[1]);
                return null;
            }
            classes.add(new ClassInfo(values[0], Integer.parseInt(values[1]), values[2]));
        }

        return classes;
    }

}