package uz.pdp.testmaster.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import uz.pdp.testmaster.bot.entity.State;
import uz.pdp.testmaster.bot.entity.TgUser;
import uz.pdp.testmaster.bot.factory.Command;

import static uz.pdp.testmaster.bot.Main.telegramBot;

public class ShareExam implements Command {
    @Override
    public void execute(Update update, TgUser tgUser) {
        if (update.message().document() != null) {
            SendMessage sendMessage = new SendMessage(tgUser.getChatId(),
                    tgUser.getFullName() + " javoblaringiz qabul qilindi✅"
            );
            SendMessage sendMessage1 = new SendMessage(tgUser.getChatId(),
                    "Yangi imtihon uchun sizga berilgan QR-kodni skanerlang \uD83D\uDCF2 !"
            );
            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
            inlineKeyboardMarkup.addRow(new InlineKeyboardButton("YANGI IMTIHON").callbackData("exam"));
            sendMessage1.replyMarkup(inlineKeyboardMarkup);
            tgUser.setState(State.START);
            telegramBot.execute(sendMessage);
            telegramBot.execute(sendMessage1);
        } else {
            SendMessage sendMessage = new SendMessage(tgUser.getChatId(),
                    tgUser.getFullName() + " javoblaringizni fayl ko'rinishida yuborishingiz kerak!\n" +
                            "Noto'g'ri formatda yuboryapsiz. E'tiborli bo'ling❗"
            );
            telegramBot.execute(sendMessage);
        }
    }
}
