import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;

/**
 * Created by nikit on 2018/02/13.
 */
public class Bot {
    public Bot(String token) {
        try {
            JDA bot = new JDABuilder(AccountType.BOT).setToken(token).buildBlocking();
            bot.addEventListener(new BotEventListener(bot.getSelfUser().getName()));
            for(Guild guild: bot.getGuilds()){
                for(TextChannel textChannel: guild.getTextChannels()) {
                    if (textChannel.getName().toLowerCase().equals("general")){
                        //textChannel.sendMessage("Я жив!!!").queue();
                    }
                }
            }

        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RateLimitedException e) {
            e.printStackTrace();
        }
    }

}
