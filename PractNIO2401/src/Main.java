import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static String checkingFile;

    public static void main(String[] args) {
        System.out.println("Enter dir name:");
        checkingFile = new Scanner(System.in).nextLine();
        showMeDeWay();
    }

    static String level;
    static int leveling;
    static boolean first;

    public static void showMeDeWay() {
        StringBuilder toOut = new StringBuilder();
        //level="";
        leveling = 0;
        first = true;
        AtomicInteger counterFiles = new AtomicInteger(0);
        AtomicInteger counterDirs = new AtomicInteger(0);
        try {
            if (!Files.exists(Paths.get("output.txt"))) {
                Files.createFile(Paths.get("output.txt"));
            }
            Files.walkFileTree(Paths.get(checkingFile), new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    level = "";
                    for (int i = 1; i < leveling; i++) {
                        level += "      ";
                    }
                    leveling++;
                    if (first) {
                        first = false;
                        System.out.println(level + dir.getFileName());
                        toOut.append(level + dir.getFileName() + "\n");

                    } else {
                        System.out.println(level + "+---- " + dir.getFileName());
                        toOut.append(level + "+---- " + dir.getFileName() + "\n");
                    }

                    counterDirs.getAndIncrement();
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    level = "";
                    for (int i = 1; i < leveling; i++) {
                        level += "      ";
                    }
                    System.out.println(level + "+---- " + file.getFileName());
                    toOut.append(level + "+---- " + file.getFileName() + "\n");

                    counterFiles.getAndIncrement();
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    leveling--;
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Count of files: " + counterFiles + "\nCount of dirs: " + counterDirs);
        try (FileChannel fileChannel = new FileOutputStream(new File("output.txt")).getChannel()) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(toOut.length() + 128);
            byteBuffer.put(toOut.toString().getBytes());
            byteBuffer.put(("\nCount of files: " + counterFiles + " Count of dirs: " + counterDirs).getBytes());
            byteBuffer.flip();
            fileChannel.write(byteBuffer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
