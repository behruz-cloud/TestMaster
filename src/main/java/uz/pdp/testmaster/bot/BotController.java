package uz.pdp.testmaster.bot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import uz.pdp.testmaster.bot.Repo.TgStudentRepo;
import uz.pdp.testmaster.bot.entity.TgStudent;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BotController {
    ExecutorService executorService = Executors.newFixedThreadPool(20);

    public void start() {
        BotService.telegramBot.setUpdatesListener(updates -> {
            for (Update update : updates) {
                executorService.execute(() -> {
                    try {
                        if (update.message() != null || update.callbackQuery() != null) {
                            handleUpdates(update);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    private void handleUpdates(Update update) {
        if (update.message() != null) {
            handleMessage(update.message());
        }
    }

    private void handleMessage(Message message) {
        Long chatId = message.chat().id();
        TgStudent tgStudent=getTgStudent(chatId);
        if (message.text() != null) {
            if (message.text().startsWith("/start")) {
                String[] commandParts = message.text().split(" ");
                if (commandParts.length > 1) {
                    String examId = commandParts[1];
                    SendMessage sendMessage = new SendMessage(tgStudent.getChatId(), examId);
                    BotService.telegramBot.execute(sendMessage);
                }
            }
        } else if (message.contact() != null) {
//            BotService.acceptContactAndChooseMenu(tgStudent, message.contact());
            System.out.println(message.text());
        }
    }

    private TgStudent getTgStudent(Long chatId) {
        Optional<TgStudent> user = BotService.getUserFromDB(chatId);
        if (user.isPresent()) {
            return user.get();
        } else {
            TgStudent tgStudent=new TgStudent(chatId);
            return TgStudentRepo.save(tgStudent);
        }
    }
}
