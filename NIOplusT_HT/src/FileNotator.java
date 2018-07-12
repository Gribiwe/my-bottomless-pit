import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/**
 * Created by nikit on 2018/01/30.
 */
public class FileNotator implements Runnable {

    String lastText;
    List<String> data;

    @Override
    public void run() {

        if (!Files.exists(Paths.get("output.txt"))) {
            try {
                Files.createFile(Paths.get("output.txt"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Started FileNotator");
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (Main.changed) {
                lastText = Main.cacheText.get();
                Main.cacheText.set("");
                Main.changed = false;

                if (lastText.endsWith("\n")) {
                    System.out.println("true");
                    lastText = lastText.substring(0, lastText.length() - 1);
                }
                addToFile(lastText);
                System.out.println("added " + lastText);
            }
            if (Main.closed) {
                System.exit(0);
            }
        }
    }

    private void addToFile(String newText) {
        try {
            data = Files.readAllLines(Paths.get("output.txt"));
            data.add(newText);
            Files.write(Paths.get("output.txt"), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
