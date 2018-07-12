import java.io.*;
import java.util.Scanner;

/**
 * Created by nikit on 2018/01/24.
 */
public class ByIO {

    static String fName;
    static int linesToRead;

    public static void start() {
        System.out.println("Введите название входного файла:");
        fName = new Scanner(System.in).nextLine();
        if (fName.equals("")) {
            System.out.println("Файл не указан, попробуйте еще раз!");
            start();
        }

        try (BufferedReader r = new BufferedReader(new FileReader(new File(fName)))) {

            linesToRead = howManyLines();
            copyToFile(r);

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден, попробуйте еще раз!");
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyToFile(BufferedReader reader) {
        System.out.println("Напишите название файла, в который копировать строки:");

        File outFile = new File(new Scanner(System.in).nextLine());
        try (BufferedWriter w = new BufferedWriter(new FileWriter(outFile))) {
            String line;
            for (int i = 0; i < linesToRead; i++) {
                line = reader.readLine();
                if (line == null) {
                    System.out.println("В исходном файле нет столько строк!");
                    outFile.deleteOnExit();
                    reader.close();
                    w.close();
                    System.exit(0);
                }
                w.write(line);
                w.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int howManyLines() {
        System.out.println("Сколько строк нужно перенести в исходный файл:");
        int toReturn = new Scanner(System.in).nextInt();
        if (toReturn >= 0) {
            return toReturn;
        } else {
            System.out.println("Количество линий меньше 0, попробуйте еще раз!");
            return howManyLines();
        }
    }

}
