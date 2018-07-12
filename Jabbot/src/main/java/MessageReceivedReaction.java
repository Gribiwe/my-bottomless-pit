import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.List;
import java.util.Random;

/**
 * Created by nikit on 2018/02/15.
 */
public class MessageReceivedReaction implements Runnable {
    private MessageReceivedEvent event;
    private int counter,ranLim,factCount;
    private String botName;

    public MessageReceivedReaction(MessageReceivedEvent event, String botName) {
        this.event = event;
        counter = 0;
        ranLim = 0;

        this.botName = botName;

    }
    @Override
    public void run() {

        System.out.println(event.getMessage().getAuthor().getName()+": "+event.getMessage().getContent());

        if (counter >= ranLim+3) {
            ranLim = new Random().nextInt(3);
            counter = 0;
        }
        String text = event.getMessage().getContent().toLowerCase();

        if (!event.getMessage().getAuthor().isBot() && (text.contains(botName.toLowerCase()) || text.contains("grib-pc"))) {
            List<Message> list = event.getTextChannel().getIterableHistory().complete();
            event.getTextChannel().sendMessage(event.getAuthor().getAsMention() + " " + list.get((new Random().nextInt(12))).getContent()).queue();
        }
        else if (!event.getMessage().getAuthor().isBot() && counter == 0) {

            if ((text.endsWith("да") || text.endsWith("da") || text.endsWith("だ"))&& new Random().nextInt(5)==1) {
                switch (new Random().nextInt(3)) {
                    case 1:
                        event.getTextChannel().sendMessage(event.getAuthor().getAsMention() + "Я бы рифманул").queue();
                        break;
                    case 2:
                        event.getTextChannel().sendMessage("Согласен").queue();
                        break;
                    case 3:
                        event.getTextChannel().sendMessage("Да").queue();
                        break;
                    default:
                        break;
                }
                counter++;
            } else if ((text.contains("привет") || text.contains("здарова") || text.contains("коничива")
                    || text.contains("конничива") || text.contains("こんにちは") || text.contains("おはよう")
                    || text.contains("こんばんは") || text.equals("ちわ")) && new Random().nextInt(2)==1) {
                switch (new Random().nextInt(5)) {
                    case 1:
                        event.getTextChannel().sendMessage("Zdaroは").queue();
                        break;
                    case 2:
                        event.getTextChannel().sendMessage("И тебе привет").queue();
                        break;
                    case 3:
                        event.getTextChannel().sendMessage("конничива").queue();
                        break;
                    case 4:
                        event.getTextChannel().sendMessage("こんにちは").queue();
                        break;
                    default:
                        event.getTextChannel().sendMessage("ちわ").queue();
                        break;
                }
                counter++;
            } else if ((text.contains("ночи") || text.contains("ночь") || text.contains("спать")
                    || text.contains("おやすみ"))&& new Random().nextInt(2)==1) {
                counter++;
                event.getTextChannel().sendMessage("zｚZＺZｚzｚＺZｚ").queue();
            } else if (text.contains("hello") || text.contains("english") || text.contains("английский")
                    || text.contains("инглиш") || text.contains("英")) {
                counter++;
                event.getTextChannel().sendMessage("O, sir, da vy iz Anglii.").queue();
            }
            if ((text.contains(")))") || text.contains(")0")) && new Random().nextInt(3)==1) {
                String scobochki = ")";
                for (int i = 0; i < new Random().nextInt(5) + 5; i++) {
                    if (new Random().nextInt(2) % 2 == 0) {
                        scobochki += ")";
                    } else scobochki += "0";
                }
                event.getTextChannel().sendMessage(scobochki).queue();
            } else if ((text.endsWith("хд") || text.endsWith("xd"))&& new Random().nextInt(4)==1) {
                event.getTextChannel().sendMessage("хд").queue();
            }
            else if ((text.endsWith("?") || text.endsWith("？"))&& new Random().nextInt(5)==1) {
                switch (new Random().nextInt(3)) {
                    case 1:
                        event.getTextChannel().sendMessage("Кстати да, тоже хочу знать").queue();
                        break;
                    case 2:
                        event.getTextChannel().sendMessage("Я бы погуглил").queue();
                        break;
                    case 3:
                        event.getTextChannel().sendMessage("Мой программист мне не говорит ответ на этот вопрос, он ばか＞：（").queue();
                        break;
                    default:
                        event.getTextChannel().sendMessage("Если ты не против, я могу ответить\nСорри, не знаю(").queue();
                        break;
                }
            }
            else if ((text.contains("прости") || text.contains("сорри") || text.contains("прошу прощения"))&& new Random().nextInt(4)==1) {
                switch (new Random().nextInt(3)) {
                    case 1:
                        event.getTextChannel().sendMessage("Нет, это ты прости моего программиста, за то, что создал меня").queue();
                        break;
                    case 2:
                        event.getTextChannel().sendMessage("Ну, миннасан, прощаем же нашего друга?").queue();
                        break;
                    case 3:
                        event.getTextChannel().sendMessage("Да че ты, норм все, ой, это не мне, ну лан").queue();
                        break;
                    default:
                        event.getTextChannel().sendMessage("И меня простите").queue();
                        break;
                }
            }
        }
        else if (!event.getMessage().getAuthor().isBot() && counter>0) counter++;


    }
}
