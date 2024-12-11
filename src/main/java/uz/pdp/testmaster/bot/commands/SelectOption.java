package uz.pdp.testmaster.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.Setter;
import uz.pdp.testmaster.bot.DB.TgUserRepo;
import uz.pdp.testmaster.bot.Main;
import uz.pdp.testmaster.bot.entity.State;
import uz.pdp.testmaster.bot.entity.TgUser;
import uz.pdp.testmaster.bot.factory.Command;
import uz.pdp.testmaster.bot.util.FixNumber;

import static uz.pdp.testmaster.bot.Main.telegramBot;

public class SelectOption implements Command {
    @Override
    public void execute(Update update, TgUser user) {
        String phoneNumber = update.message().contact().phoneNumber();
        String fixPhoneNumber = FixNumber.fix(phoneNumber);
        TgUserRepo.savePhoneNumber(fixPhoneNumber, user);
        SendMessage sendMessage = new SendMessage(user.getChatId(),
                "Telefon raqamingiz muvaffaqiyatli saqlandi✅"
        );
        SendMessage sendMessage1 = new SendMessage(user.getChatId(),
                "Hurmatli " + user.getFullName() + " !\n" +
                        "Imtihon javoblarini topshiringiz mumkin !\n\n" +
                        "Eslatib o'tamiz ⁉️ Birinchi tashlangan javobni qabul qilamiz, e'tiborli bo'ling !"
        );
        user.setState(State.SHARE_EXAM);
        telegramBot.execute(sendMessage);
        telegramBot.execute(sendMessage1);
    }

}