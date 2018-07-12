import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by nikit on 2018/02/13.
 */
public class BotEventListener extends ListenerAdapter {

    int factCount = 0;
    String botName;

    private FactUploader factUploader;
    ExecutorService executorService;

    BotEventListener(String botName) {
        this.botName = botName;

        System.out.println("Bot name: " + botName);
        executorService = Executors.newCachedThreadPool();
        factUploader = new FactUploader();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        factCount++;

        if(factCount == 150) {
            factCount = 0;
            event.getTextChannel().sendMessage("Прикиииньте\n"+factUploader.getFact()).queue();
        }
        System.out.println(factCount);
        executorService.submit(new MessageReceivedReaction(event, botName));
    }
}
