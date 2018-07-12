import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        System.out.println("Выполнение задачи\n1 - C помощью IO\n2 - С помощью NIO");

        switch (new Scanner(System.in).nextInt()) {
            case 1:
                ByIO.start();
                break;
            case 2:
                ByNIO.start();
                break;
            default:
                System.out.println("Какие-то мы неопределенные З:");
                System.exit(0);
        }
    }
}
