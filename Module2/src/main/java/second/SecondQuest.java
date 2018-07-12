package second;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by nikit on 17.02.2018.
 */
public class SecondQuest {

    private static String typed;
    private static boolean gameOver;
    private static Scanner scanner;

    public void start() {
        typed = null;
        gameOver = false;
        scanner = new Scanner (System.in);

        System.out.println("Игра начинается");
        ExecutorService comp = Executors.newFixedThreadPool(1);
        comp.execute(new Compudahter());

        while (!gameOver) {
            typed = scanner.nextLine();
        }
        comp.shutdown();
    }

    public static void newCycle() {
        typed = null;
    }

    public static void gameOver() {
        gameOver = true;
        System.out.println("game over!");
        System.out.println("Напишите что-нибудь, чтобы вернуться в меню выбора проверки задания");
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    public static boolean isTyped() {
        return typed!=null;
    }
}
