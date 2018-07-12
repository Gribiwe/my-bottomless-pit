package third;

import java.util.Scanner;

/**
 * Created by nikit on 17.02.2018.
 */
public class ThirdQuest {

    public void start() {
        GFileReader reader = new GFileReader();
        reader.initFileToDB();

        for (ClassInfo classInfo: reader.getClasses()) {
            classInfo.sout();

        }
        Database database = new Database(reader.getFileName());
        database.flushClasses(reader.getClasses(), reader.getFileName());

        System.out.println("nenenen");
        database.getClassInfo("a12", reader.getFileName()).sout();

        System.out.println("Напишите что-нибудь, чтобы вернуться в меню выбора проверки задания");
        new Scanner(System.in).nextLine();
    }
}
