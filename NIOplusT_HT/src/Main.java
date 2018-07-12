import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class Main {

    public static boolean closed, changed;
    public static AtomicReference<String> cacheText;
    public static ExecutorService service;

    public static void main(String[] args) {
        cacheText = new AtomicReference<>();
        cacheText.set("");
        changed = false;
        closed = false;
        service = Executors.newFixedThreadPool(2);

        System.out.println("Здравствуйте, вводите что хотите, exit - выход.");

        service.submit(new FileNotator());
        service.submit(new ConsoleReader());

    }
}
