package uz.pdp.testmaster.bot.DB;

import uz.pdp.testmaster.bot.entity.TgUser;
import uz.pdp.testmaster.web.entity.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CourseRepo {
    public static Optional<Course> getById(int courseId) {
        try (
                Connection connection = DBConfig.getConnection()
        ) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from course where id = ?");
            preparedStatement.setLong(1, courseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return Optional.empty();
            } else {
                Course course = new Course(resultSet);
                return Optional.of(course);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
