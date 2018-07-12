import first.FirstQuest;
import second.SecondQuest;
import third.ThirdQuest;

import java.util.Scanner;

/**
 * Created by nikit on 17.02.2018.
 */
public class Main {
    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("------------------------\n" +
                    "Какое задание прочекать?" +
                    "\n1 - первое задание" +
                    "\n2 - второе задание" +
                    "\n3 - третье задание" +
                    "\n4 - четвертое задание" +
                    "\nДругое - выйти с отвращением на лице.");
            switch (new Scanner(System.in).nextInt()) {
                case 1:
                    FirstQuest quest1 = new FirstQuest();
                    quest1.start();
                    break;
                case 2:
                    SecondQuest quest2 = new SecondQuest();
                    quest2.start();
                    break;
                case 3:
                    ThirdQuest quest3 = new ThirdQuest();
                    quest3.start();
                    break;
                case 4:
                    System.out.println("Дома сделаю, необязательно же, дедлайн не горит)0");
                    break;
                default:
                    exit = true;
                    break;
            }
        }
    }
}
