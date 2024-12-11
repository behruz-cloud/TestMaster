package uz.pdp.testmaster.bot;

import com.pengrad.telegrambot.TelegramBot;
import uz.pdp.testmaster.bot.DB.DBConfig;
import uz.pdp.testmaster.bot.Repo.TgStudentRepo;
import uz.pdp.testmaster.bot.entity.TgStudent;
import uz.pdp.testmaster.bot.servlet.SaveStudentServlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


public class BotService {
    public static TelegramBot telegramBot = new TelegramBot("7138723780:AAGy5HxzXxOVPUSEJElXXUZlF-bFbyUz1JU");

    public static Optional<TgStudent> getUserFromDB(Long chatID) {
        try (
                Connection connection = DBConfig.getConnection()
        ) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from tgstudent where chatid = ?");
            preparedStatement.setLong(1, chatID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return Optional.empty();
            } else {
                TgStudent tgUser = new TgStudent(resultSet);
                return Optional.of(tgUser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
