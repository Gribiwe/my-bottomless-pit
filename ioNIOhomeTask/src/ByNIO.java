import com.sun.org.apache.xml.internal.serialize.LineSeparator;

import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * Created by nikit on 2018/01/24.
 */
public class ByNIO {

    static String fName;
    static int linesToRead;

    public static void start() {
        System.out.println("Введите название входного файла:");
        fName = new Scanner(System.in).nextLine();
        if (fName.equals("")) {
            System.out.println("Файл не указан, попробуйте еще раз!");
            start();
        }

        try {
            FileChannel fileChannel = new FileInputStream(new File(fName)).getChannel();
            linesToRead = howManyLines();
            copyToFile(fName);

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден, попробуйте еще раз!");
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyToFile(String fileChannel) {
        System.out.println("Напишите название файла, в который копировать строки:");

        File outFile = new File(new Scanner(System.in).nextLine());
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileChannel));
            if (linesToRead > lines.size()) {
                System.out.println("В исходном файле нет столько строк!");
                System.exit(0);
            }
            FileChannel outFileChannel1 = new FileOutputStream(outFile).getChannel();
            ByteBuffer buf;
            for (int i = 0; i < linesToRead; i++) {
                buf = ByteBuffer.allocate(256);
                buf.put((lines.get(i) + "\n").getBytes());
                buf.flip();
                outFileChannel1.write(buf);
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
