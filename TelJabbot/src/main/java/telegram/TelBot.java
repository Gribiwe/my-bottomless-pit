package telegram;

import study.Word;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendVoice;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import study.RemindeManager;
import utilities.Config;
import utilities.PathSystem;
import utilities.WordReader;
import utilities.WordWriter;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by nikit on 2018/02/22.
 */
public class TelBot extends TelegramLongPollingBot {

    HashMap<Integer, String[]> waitAnswer = new HashMap<>();

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        User user = message.getFrom();
        String[] args = message.getText().toLowerCase().split(" ");

        if (waitAnswer.containsKey(user.getId()) && message.getText().equals("/cancel")) {
            waitAnswer.remove(user.getId());
            sendMsg(message.getChatId(), "Отмененно");

        }

        if (waitAnswer.containsKey(user.getId()) && waitAnswer.get(user.getId())[0].equals("!W")) {
            if ((message.getText().length() < 1 || message.getText().length() > 20) && !message.getText().equals("!W")) {
                sendMsg(message.getChatId(), "Твое слово либо слишком долгое либо это вообще не слово" +
                        "\n/cancel, если передумал.");

            } else {
                String[] buf = waitAnswer.get(user.getId());
                buf[0] = message.getText();
                waitAnswer.replace(user.getId(), buf);
                sendMsg(message.getChatId(), "Введи значение слова" +
                        "\n/cancel, если передумал.");
            }
        } else if (waitAnswer.containsKey(user.getId()) && waitAnswer.get(user.getId())[1].equals("!M")) {
            if ((message.getText().length() < 1 || message.getText().length() > 20) && !message.getText().equals("!M")) {
                sendMsg(message.getChatId(), "Твое слово либо слишком долгое либо это вообще не слово" +
                        "\n/cancel, если передумал.");
            } else {
                String[] buf = waitAnswer.get(user.getId());
                buf[1] = message.getText();
                waitAnswer.replace(user.getId(), buf);

                Word word = new Word(waitAnswer.get(user.getId())[0], waitAnswer.get(user.getId())[1]);
                WordWriter.add(user.getId(), word);
                sendMsg(message.getChatId(), "Твое слово было добавлено: " + word.getWord() + ": " + word.getMeaning());

            }
        } else if (args.length > 0 && args[0].equals("/start")) {
            if (RemindeManager.init(user.getId())) {
                if (RemindeManager.activeUsers.containsKey(user.getId())) {
                    sendMsg(message.getChatId(), "Уже запущено");
                } else {
                    RemindeManager.activeUsers.put(user.getId(), null);
                    RemindeManager.whatToCheck.put(user.getId(), false);
                    if (!RemindeManager.changeWord(user.getId())) {
                        sendMsg(message.getChatId(), "Твой словарь пуст" +
                                "\n/add чтобы добавить слово");
                        RemindeManager.activeUsers.remove(user.getId());
                        RemindeManager.whatToCheck.remove(user.getId());
                    } else {
                        if (RemindeManager.whatToCheck.get(user.getId())) {

                            if (new Random().nextBoolean()) {
                                SendVoice audio = new SendVoice();
                                audio.setChatId(message.getChatId());
                                audio.setNewVoice(RemindeManager.activeUsers.get(user.getId()).getVoice());
                                try {
                                    sendVoice(audio);
                                } catch (TelegramApiException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                sendMsg(message.getChatId(), RemindeManager.activeUsers.get(user.getId()).getWord()
                                        + " (" + RemindeManager.activeUsers.get(user.getId()).getId() + ")");
                            }
                        } else {
                            sendMsg(message.getChatId(), RemindeManager.activeUsers.get(user.getId()).getMeaning()
                                    + " (" + RemindeManager.activeUsers.get(user.getId()).getId() + ")");
                        }
                    }
                }
            } else {
                sendMsg(message.getChatId(), "Впервые тут? Небольшая экскурсия." +
                        "\nЯ - бот, который поможет тебе в изучении языка, а именно в изучении новых слов." +
                        "\nКак пользоваться мною? Легко" +
                        "\n/add " +
                        "\nЭта команда позволяет добавить новое слово. Параметры вводить не нужно" +
                        "\nя сам попрошу всю инфу, которая мне нужна. Попробуй добавить какое-нибудь слово." +
                        "\nчтобы запустить процесс напоминания, напиши /start" +
                        "\nА вообще, мне лень тебе что-то еще объяснять, напиши /help и там все наглядно.");
            }
        } else if (args.length > 0 && args[0].equals("/add")) {
            waitAnswer.put(user.getId(), new String[]{"!W", "!M"});
            sendMsg(message.getChatId(), "Введи слово на японском, которое ты хочешь добавить" +
                    "\n/cancel, если передумал.");
        } else if (args.length > 1 && args[0].equals("/remove")) {
            if (!RemindeManager.activeUsers.containsKey(user.getId())&&(args[1].equals("dictionary"))) {
                WordWriter.deleteFile(user.getId());
                sendMsg(message.getChatId(), "Словарь очищен.");
            }
            else if (!RemindeManager.activeUsers.containsKey(user.getId())) {
                WordWriter.removeWord(user.getId(), Integer.parseInt(args[1]));
                sendMsg(message.getChatId(), "Удалено! ");
            } else {
                sendMsg(message.getChatId(), "Сначала останови ремайнд /stop");
            }
        } else if (args.length == 1 && args[0].equals("/remove")) {

            sendMsg(message.getChatId(), "/remove (id)　－　удалить слово из словаря" +
                    "\n/remove dictionary　－　полностью очистить словарь");

        } else if (args.length > 0 && args[0].equals("/stop")) {
            if (!RemindeManager.activeUsers.containsKey(user.getId())) {
                sendMsg(message.getChatId(), "Ничего не запущено. Запустить ремайндер -  /start ");
            } else {
                RemindeManager.activeUsers.remove(user.getId());
                RemindeManager.whatToCheck.remove(user.getId());
            }
        } else if (args.length > 0 && args[0].equals("/help")) {
            sendMsg(message.getChatId(), "/start　－　запустить ремайндер\n" +
                    "/stop　－　остановить ремайндер\n" +
                    "/add　－　добавить слово в свой словарь\n" +
                    "/remove　－　команды удаления\n" +
                    "/words　－　просмотреть твой списк слов в словаре\n" +
                    "/import　－　команды импортирования словарей\n");

        } else if (args.length > 0 && args[0].equals("/words")) {
            StringBuilder sb = new StringBuilder();
            sb.append("ーーーーー Твои слова ーーーーー");
            int count = 0;
            for (Word word : WordReader.getWordsArray(user.getId())) {
                sb.append("\n" + word.getId() + ". " + word.getWord() + "　―　" + word.getMeaning());
                count++;
            }
            if (count == 0) sb.append("\nПусто :c" +
                    "\nКакой-то ты не разговорчивый.");
            sb.append("\nーーーーー Всего слов: " + count + "ーーーーー");
            sendMsg(message.getChatId(), sb.toString());

        } else if (args.length > 1 && args[0].equals("/import")) {

            if(args[1].equals("list")) {
                StringBuilder sb = new StringBuilder();

                sb.append("ーーーー  Список публичных словарей  ーーーー");
                int counter = 0;
                for (String pub : Config.getPublics()) {
                    sb.append("\n" + counter + ". " + pub);
                    counter++;
                }
                sb.append("\nーーーー Всего публичных словарей: " + counter + " ーーーー");
                sendMsg(message.getChatId(), sb.toString());
            }
            else if (args[1].equals("info")) {
                sendMsg(message.getChatId(), "Твой словарь можно заимпортить с помощью \n/import " + user.getId());
            }
            else {

                String dir = "";
                if (Config.getPublics().contains(args[1])) {
                    dir = PathSystem.PUBLIC_DIR + "/";
                } else {
                    dir = PathSystem.USERS_DIR + "/";
                }
                if (WordWriter.importDictionary(user.getId(), dir + args[1])) {

                    sendMsg(message.getChatId(), "Импорт удался.");
                    if ((user.getId() + "").equals(args[1])) {
                        sendMsg(message.getChatId(), "Только я не понимаю, зачем себя импортить?");
                    }
                } else {
                    sendMsg(message.getChatId(), "Импорт не удался, вероятно, указана ошибка в номере, " +
                            "\nлучше глянь публичные словари: /importlist.");
                }
            }
        } else if (args.length == 1 && args[0].equals("/import")) {
            {
                sendMsg(message.getChatId(), "/import (id)　－　испортировать другой словарь по id" +
                        "\n/import (name)　－　импортировать публичный словарь" +
                        "\n/import list　－　посмотреть публичные словари)"+
                        "\n/import info　－　узнать айди импорта твоего словаря");
            }

        } else {
            if (RemindeManager.activeUsers.containsKey(user.getId())) {
                if (RemindeManager.whatToCheck.get(user.getId())) {
                    if (!RemindeManager.checkMeaning(user.getId(), message.getText())) {

                        sendMsg(message.getChatId(), "Ошибся! " + RemindeManager.activeUsers.get(user.getId()).getWord() + " ー " +
                                RemindeManager.activeUsers.get(user.getId()).getMeaning());
                    }

                } else {
                    if (!RemindeManager.checkWord(user.getId(), message.getText())) {

                        sendMsg(message.getChatId(), "Ошибся! " + RemindeManager.activeUsers.get(user.getId()).getWord() + " ー " +
                                RemindeManager.activeUsers.get(user.getId()).getMeaning());
                    }

                }
                RemindeManager.changeWord(user.getId());
                if (RemindeManager.whatToCheck.get(user.getId())) {

                    if (new Random().nextInt(20)>7) {
                        SendVoice audio = new SendVoice();
                        audio.setChatId(message.getChatId());
                        audio.setNewVoice(RemindeManager.activeUsers.get(user.getId()).getVoice());
                        try {
                            sendVoice(audio);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    } else {
                        sendMsg(message.getChatId(), RemindeManager.activeUsers.get(user.getId()).getWord()
                                + " (" + RemindeManager.activeUsers.get(user.getId()).getId() + ")");
                    }
                } else {
                    sendMsg(message.getChatId(), RemindeManager.activeUsers.get(user.getId()).getMeaning()
                            + " (" + RemindeManager.activeUsers.get(user.getId()).getId() + ")");
                }

            }
        }
    }

    @Override
    public String getBotUsername() {
        return Config.BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return Config.TELEGRAM_BOT_TOKEN;
    }

    private void sendMsg(long chatId, String text) {
        SendMessage s = new SendMessage();
        s.setChatId(chatId);
        s.setText(text);
        try {
            sendMessage(s);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
