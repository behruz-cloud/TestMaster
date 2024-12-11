package uz.pdp.testmaster.bot;
import com.pengrad.telegrambot.model.Update;
import lombok.SneakyThrows;
import uz.pdp.testmaster.bot.DB.TgUserRepo;
import uz.pdp.testmaster.bot.entity.TgUser;
import uz.pdp.testmaster.bot.factory.Command;
import uz.pdp.testmaster.bot.factory.CommandFactory;
import java.util.Optional;
public class UpdateHandler {
    @SneakyThrows
    public static void handle(Update update) {

        if (update.message() != null) {
            String name = update.message().chat().firstName();
            Long chatID = update.message().chat().id();
            TgUser tgUser = getTgUser(chatID, name);
            Command command = CommandFactory.getCommand(tgUser.getState());
            command.execute(update, tgUser);

        } else if (update.callbackQuery() != null) {
            Long chatId = update.callbackQuery().from().id();
            String name = update.callbackQuery().from().firstName();
            TgUser tgUser = getTgUser(chatId, name);
            Command command = CommandFactory.getCommand(tgUser.getState());
            command.execute(update, tgUser);
        }
    }

    private static TgUser getTgUser(Long chatId, String name) {
        Optional<TgUser> tgUserOptional = TgUserRepo.getByChatID(chatId);
        if (tgUserOptional.isPresent()) {
            return tgUserOptional.get();
        } else {
            TgUser tgUser = new TgUser(chatId, name);
            return TgUserRepo.save(tgUser);
        }
    }

//    public static void getAndSaveFileId(Update update) {
//        if (update.message() != null && update.message().photo() != null) {
//            PhotoSize[] photos = update.message().photo();
//            PhotoSize largestPhoto = photos[photos.length - 1];
//            String fileId = largestPhoto.fileId();
//            System.out.println(fileId);
//        }
//    }


}
