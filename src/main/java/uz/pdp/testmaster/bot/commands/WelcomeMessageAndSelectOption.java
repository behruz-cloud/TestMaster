package uz.pdp.testmaster.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import uz.pdp.testmaster.bot.DB.ExamRepoBot;
import uz.pdp.testmaster.bot.DB.TgUserRepo;
import uz.pdp.testmaster.bot.entity.State;
import uz.pdp.testmaster.bot.entity.TgUser;
import uz.pdp.testmaster.bot.factory.Command;
import uz.pdp.testmaster.web.entity.Exam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static uz.pdp.testmaster.bot.Main.telegramBot;


public class WelcomeMessageAndSelectOption implements Command {

    @Override
    public void execute(Update update, TgUser tgUser) {
        try {
            if (update.message() == null) {
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
                            "\uD83D\uDCCC ASSALOMU ALAYKUM !\n\n" +
                                    "\uD83D\uDCC5 Imtihon sanasi : " + exam.get().getDate() + "\n" +
                                    "\uD83C\uDF10 Yo'nalish : " + exam.get().getCourse().getCourseName() + "\n" +
                                    "\uD83D\uDC65 Guruh : " + exam.get().getGroup().getGroupName() + " guruhi\n" +
                                    "\uD83D\uDD22 Modul : " + exam.get().getModuleNumber() + " - modul\n" +
                                    "\uD83D\uDD50 Boshlanish vaqti : " + exam.get().getStartTime() + "\n" +
                                    "\uD83D\uDD60 Tugash vaqti : " + exam.get().getEndTime() + "\n";
                    SendMessage sendMessage = new SendMessage(tgUser.getChatId(), info);
                    SendMessage sendMessage1 = new SendMessage(tgUser.getChatId(), "Ism - Familyangizni kiriting!\n(Namuna: Eshmat Toshmatov)");

                    boolean checkTime = CheckDateTime(exam.get());
                    telegramBot.execute(sendMessage);
                    if (checkTime) {
                        Thread.sleep(4000);
                        tgUser.setState(State.SAVE_FULLNAME);
                        telegramBot.execute(sendMessage1);
                    } else {
                        SendMessage sendMessage2 = new SendMessage(tgUser.getChatId(),
                                "\uD83D\uDCCC Hurmatli " + tgUser.getFullName() + " ❗\n" +
                                        "\uD83D\uDCC5 Imtihon sanasi: " + exam.get().getDate() + "\n" +
                                        "\uD83D\uDD60 Imtihon tugash vaqti: " + exam.get().getEndTime() + "\n" +
                                        "❌ Uzr, sizning javoblaringizni qabul qila olmaymiz. Imtihon belgilangan vaqtda yakunlangan.");
                        telegramBot.execute(sendMessage2);
                    }
                } else if (messageText.equals("exam")) {
                    SendMessage sendMessage = new SendMessage(tgUser.getChatId(),
                            "Xush kelibsiz! QR kodni skanerlash orqali testga ulaning \uD83D\uDCF2."
                    );
                    telegramBot.execute(sendMessage);
                } else {
                    SendMessage sendMessage = new SendMessage(tgUser.getChatId(),
                            "Xush kelibsiz! QR kodni skanerlash orqali testga ulaning \uD83D\uDCF2."
                    );
                    telegramBot.execute(sendMessage);
                }
            } else if (update.callbackQuery() != null) {
                SendMessage sendMessage = new SendMessage(tgUser.getChatId(),
                        "Xush kelibsiz! QR kodni skanerlash orqali testga ulaning \uD83D\uDCF2."
                );
                telegramBot.execute(sendMessage);
            } else {
                SendMessage sendMessage = new SendMessage(tgUser.getChatId(),
                        "Xush kelibsiz! QR kodni skanerlash orqali testga ulaning \uD83D\uDCF2."
                );
                telegramBot.execute(sendMessage);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean CheckDateTime(Exam exam) {
        LocalDate examDate = exam.getDate();
        LocalTime examEndTime = exam.getEndTime();

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        if (currentDate.isAfter(examDate) || (currentDate.equals(examDate) && currentTime.isAfter(examEndTime))) {
            return false;
        }

        return true;
    }
}
