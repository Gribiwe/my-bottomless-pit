import java.util.Scanner;

/**
 * Created by nikit on 2018/01/30.
 */
public class ConsoleReader implements Runnable {
    @Override
    public void run() {
        System.out.println("Started ConsoleReader");
        Scanner scanner = new Scanner(System.in);
        String buf;
        while (true) {
            buf = scanner.next();
            if (buf.equals("exit") || buf.equals("Exit")) {
                Main.closed = true;
                return;
            }
            Main.cacheText.set(Main.cacheText.get() + buf + "\n");
            Main.changed = true;
        }
    }
}
