package uz.pdp.testmaster.bot;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;

public class Bot {
    public static void main(String[] args) {
        // Botni yaratish
        TelegramBot bot = new TelegramBot("7138723780:AAGy5HxzXxOVPUSEJElXXUZlF-bFbyUz1JU");

        // Yangilanishlarni tinglash
        bot.setUpdatesListener(updates -> {
            for (Update update : updates) {
                if (update.message() != null && update.message().text() != null) {
                    String chatId = update.message().chat().id().toString();
                    String messageText = update.message().text();

                    // Agar foydalanuvchi /start bilan kelsa
                    if (messageText.startsWith("/start")) {
                        String[] parts = messageText.split(" ");
                        if (parts.length > 1) {
                            String examId = parts[1]; // Parametrni olamiz
                            bot.execute(new com.pengrad.telegrambot.request.SendMessage(chatId,
                                    "Sizning test ID: " + examId));
                        } else {
                            bot.execute(new com.pengrad.telegrambot.request.SendMessage(chatId,
                                    "Xush kelibsiz! QR kodni skanerlash orqali testga ulaning."));
                        }
                    }
                }
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
