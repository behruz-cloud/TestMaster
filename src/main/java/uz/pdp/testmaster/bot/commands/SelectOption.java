package uz.pdp.testmaster.bot.commands;
import com.pengrad.telegrambot.model.Update;
import uz.pdp.testmaster.bot.entity.TgUser;
import uz.pdp.testmaster.bot.factory.Command;
import uz.pdp.testmaster.bot.util.FixNumber;

public class SelectOption implements Command {
    @Override
    public void execute(Update update, TgUser user) {
        String phoneNumber = update.message().contact().phoneNumber();
        System.out.println(FixNumber.fix(phoneNumber));
    }

}