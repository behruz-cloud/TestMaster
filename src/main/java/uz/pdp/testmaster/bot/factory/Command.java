package uz.pdp.testmaster.bot.factory;
import com.pengrad.telegrambot.model.Update;
import uz.pdp.testmaster.bot.entity.TgUser;

public interface Command {
    void execute(Update update, TgUser tgUser) throws InterruptedException;
}
