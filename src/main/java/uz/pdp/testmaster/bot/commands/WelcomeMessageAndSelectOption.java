package uz.pdp.testmaster.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import uz.pdp.testmaster.bot.DB.ExamRepoBot;
import uz.pdp.testmaster.bot.entity.State;
import uz.pdp.testmaster.bot.entity.TgUser;
import uz.pdp.testmaster.bot.factory.Command;
import uz.pdp.testmaster.web.entity.Exam;

import java.util.Optional;

import static uz.pdp.testmaster.bot.Main.telegramBot;


public class WelcomeMessageAndSelectOption implements Command {

    @Override
    public void execute(Update update, TgUser tgUser) {
        String messageText = update.message().text();
        String[] parts = messageText.split(" ");
        if (parts.length > 1) {
            String examId = parts[1];
            Optional<Exam> exam = ExamRepoBot.getById(Integer.parseInt(examId));
            String info =
                    "ASSALOMU ALAYKUM !\n\n"+
                    "Imtihon sanasi : "+ exam.get().getDate()+"\n"+
                    "Yo'nalish : "+ exam.get().getCourse().getCourseName() + "\n" +
                    "Guruh : "+ exam.get().getGroup().getGroupName() + " guruhi\n" +
                    "Modul : "+ exam.get().getModuleNumber() + " - modul\n" +
                    "Boshlanish vaqti : " + exam.get().getStartTime() + "\n" +
                    "Tugash vaqti : " + exam.get().getEndTime() + "\n";
            SendMessage sendMessage = new SendMessage(tgUser.getChatId(),info);
            SendMessage sendMessage1 = new SendMessage(tgUser.getChatId(),"Ism - Familyangizni kiriting!\n(Namuna: Eshmat Toshmatov");
            tgUser.setState(State.SAVE_FULLNAME);
            telegramBot.execute(sendMessage);
            telegramBot.execute(sendMessage1);
        }
    }
}
