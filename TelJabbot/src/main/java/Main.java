import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import poly.PolyWritter;
import telegram.TelBot;
import utilities.BootInit;
import utilities.Config;

import java.util.Scanner;

public class Main {


    public static void main(String args[]) throws Exception {

        if(!BootInit.initFiles()){
            System.out.println("Files created. Please, look config.yml. Than - press Enter");
            new Scanner(System.in).nextLine();
        }
        Config.configInit();


        PolyWritter polyWritter = new PolyWritter();


        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new TelBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
} 