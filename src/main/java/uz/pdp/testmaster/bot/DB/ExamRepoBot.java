package uz.pdp.testmaster.bot.DB;

import uz.pdp.testmaster.bot.entity.TgUser;
import uz.pdp.testmaster.web.entity.Exam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ExamRepoBot {
    public static Optional<Exam> getById(int examId) {
        try (
                Connection connection = DBConfig.getConnection()
        ) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from exam where id = ?");
            preparedStatement.setLong(1, examId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return Optional.empty();
            } else {
                Exam exam = new Exam(resultSet);
                return Optional.of(exam);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
