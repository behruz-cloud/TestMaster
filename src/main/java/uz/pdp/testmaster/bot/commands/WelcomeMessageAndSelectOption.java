package uz.pdp.testmaster.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import uz.pdp.testmaster.bot.DB.ExamRepoBot;
import uz.pdp.testmaster.bot.DB.TgUserRepo;
import uz.pdp.testmaster.bot.entity.State;
import uz.pdp.testmaster.bot.entity.TgUser;
import uz.pdp.testmaster.bot.factory.Command;
import uz.pdp.testmaster.web.entity.Exam;

import java.util.Optional;

import static uz.pdp.testmaster.bot.Main.telegramBot;


public class WelcomeMessageAndSelectOption implements Command {

    @Override
    public void execute(Update update, TgUser tgUser) {
        try {
            if (update.message()==null) {
                SendMessage sendMessage = new SendMessage(tgUser.getChatId(),
                        "Xush kelibsiz! QR kodni skanerlash orqali testga ulaning."
                );
                telegramBot.execute(sendMessage);
            }
            if (update.message().text() != null) {
                String messageText = update.message().text();
                String[] parts = messageText.split(" ");
                if (parts.length > 1) {
                    String examId = parts[1];
                    tgUser.setExamId(Integer.parseInt(examId));
                    TgUserRepo.saveExam(tgUser);
                    Optional<Exam> exam = ExamRepoBot.getById(Integer.parseInt(examId));
                    String info =
                            "ASSALOMU ALAYKUM !\n\n" +
                                    "Imtihon sanasi : " + exam.get().getDate() + "\n" +
                                    "Yo'nalish : " + exam.get().getCourse().getCourseName() + "\n" +
                                    "Guruh : " + exam.get().getGroup().getGroupName() + " guruhi\n" +
                                    "Modul : " + exam.get().getModuleNumber() + " - modul\n" +
                                    "Boshlanish vaqti : " + exam.get().getStartTime() + "\n" +
                                    "Tugash vaqti : " + exam.get().getEndTime() + "\n";
                    SendMessage sendMessage = new SendMessage(tgUser.getChatId(), info);
                    SendMessage sendMessage1 = new SendMessage(tgUser.getChatId(), "Ism - Familyangizni kiriting!\n(Namuna: Eshmat Toshmatov)");

                    Thread.sleep(4000);

                    tgUser.setState(State.SAVE_FULLNAME);
                    telegramBot.execute(sendMessage);
                    telegramBot.execute(sendMessage1);
                } else if (messageText.equals("exam")) {
                    SendMessage sendMessage = new SendMessage(tgUser.getChatId(),
                            "Xush kelibsiz! QR kodni skanerlash orqali testga ulaning."
                    );
                    telegramBot.execute(sendMessage);
                } else {
                    SendMessage sendMessage = new SendMessage(tgUser.getChatId(),
                            "Xush kelibsiz! QR kodni skanerlash orqali testga ulaning."
                    );
                    telegramBot.execute(sendMessage);
                }
            } else if (update.callbackQuery() != null) {
                SendMessage sendMessage = new SendMessage(tgUser.getChatId(),
                        "Xush kelibsiz! QR kodni skanerlash orqali testga ulaning."
                );
                telegramBot.execute(sendMessage);
            } else {
                SendMessage sendMessage = new SendMessage(tgUser.getChatId(),
                        "Xush kelibsiz! QR kodni skanerlash orqali testga ulaning."
                );
                telegramBot.execute(sendMessage);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
