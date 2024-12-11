package uz.pdp.testmaster.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static TelegramBot telegramBot = new TelegramBot("7138723780:AAGy5HxzXxOVPUSEJElXXUZlF-bFbyUz1JU");
    public static ExecutorService executorService = Executors.newFixedThreadPool(20);

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("uz.pdp.testmaster.bot.DB.DBConfig");
        telegramBot.setUpdatesListener(updates -> {
            for (Update update : updates) {
                try {
                    executorService.execute(() -> {
                        UpdateHandler.handle(update);
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
