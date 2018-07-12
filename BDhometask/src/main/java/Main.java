import java.util.Scanner;

/**
 * Created by nikit on 2018/02/03.
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("1 - из файла в бд\n2 - из бд в файл");
        switch (new Scanner(System.in).nextInt()) {
            case 1:
                FileWorker.initFileToDB();
                DataBase.initFileToDataBase();
                DataBase.transToDB(FileWorker.getFileToSQL());
                break;
            case 2:
                DataBase.initDataBaseToFile();
                FileWorker.transDBtoFile(DataBase.getSQLToFile());

                break;
            default:
                System.exit(0);
        }

    }
}
