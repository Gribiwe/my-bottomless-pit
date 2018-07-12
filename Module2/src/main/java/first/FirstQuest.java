package first;

import java.util.Scanner;

/**
 * Created by nikit on 17.02.2018.
 */
public class FirstQuest {

    public void start() {
        GArrayList<Integer> list = new GArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(255, 3);

        for (int i = 0; i<list.size; i++) {
            System.out.println(list.get(i));
        }
        System.out.println("----------");
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(255, 3);
        list.remove(3);

        for (int i = 0; i<list.size; i++) {
            System.out.println(list.get(i));
        }

        System.out.println("Напишите что-нибудь, чтобы вернуться в меню выбора проверки задания");
        new Scanner(System.in).nextLine();
    }
}
