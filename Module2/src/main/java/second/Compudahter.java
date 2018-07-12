package second;

/**
 * Created by nikit on 17.02.2018.
 */
public class Compudahter implements  Runnable {
    @Override
    public void run() {
        System.out.println("BUMN");
        while (!SecondQuest.isGameOver()) {
            try {
                Thread.sleep(2000);
                if (!SecondQuest.isTyped()) {
                    SecondQuest.gameOver();
                    break;
                }

                System.out.println("BUMN");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            SecondQuest.newCycle();
        }
    }
}
