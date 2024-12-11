package uz.pdp.testmaster.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import uz.pdp.testmaster.bot.DB.TgUserRepo;
import uz.pdp.testmaster.bot.entity.State;
import uz.pdp.testmaster.bot.entity.TgUser;
import uz.pdp.testmaster.bot.factory.Command;

import static uz.pdp.testmaster.bot.Main.telegramBot;


public class SaveFullNameEndShareContact implements Command {
    @Override
    public void execute(Update update, TgUser tgUser) {
        String fullName = update.message().text();
        tgUser.setFullName(fullName);
        TgUserRepo.updateFullName(tgUser);
        SendMessage sendMessage = new SendMessage(tgUser.getChatId(), "Ism-Familyangiz muvaffaqiyatli saqlandi✅");
        SendMessage sendMessage1 = new SendMessage(tgUser.getChatId(), tgUser.getFullName() + " KONTAKT YUBORISH tugmasi yordamida telefon raqamingizni yuboring");
        KeyboardButton contactButton = new KeyboardButton("KONTAKT YUBORISH").requestContact(true);
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup(contactButton)
                .resizeKeyboard(true)
                .oneTimeKeyboard(true);
        tgUser.setState(State.SHARE_CONTACT);
        sendMessage1.replyMarkup(keyboard);
        telegramBot.execute(sendMessage);
        telegramBot.execute(sendMessage1);
    }
}
