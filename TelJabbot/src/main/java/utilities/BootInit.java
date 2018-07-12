package utilities;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by nikit on 2018/02/22.
 */
public class BootInit {

    public static boolean initFiles() {
        try {
            Files.createDirectory(PathSystem.BIN_DIR);
            Files.createDirectory(PathSystem.LANGUAGES_DIR);
            Files.createDirectory(PathSystem.JAP_DIR);
            Files.createDirectory(PathSystem.JAP_AUDIOS_DIR);
            Files.createDirectory(PathSystem.USERS_DIR);
            Files.createDirectory(PathSystem.PUBLIC_DIR);

            Files.createFile(PathSystem.CONFIG_FILE);
            defaultConfigCreate();

            return false;

        } catch (FileAlreadyExistsException ignored) {
            System.out.println("Files found.");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private static void defaultConfigCreate() throws IOException {
        List<String> lines = new ArrayList<>();

        lines.add("Bot username<>null");
        lines.add("First Polly key<>null");
        lines.add("Second Polly key<>null");
        lines.add("Tegram bot token<>null");
        lines.add("Publics<>null");

        Files.write(PathSystem.CONFIG_FILE, lines);

    }
}
