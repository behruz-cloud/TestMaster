package uz.pdp.testmaster.bot.DB;

import uz.pdp.testmaster.web.entity.Exam;
import uz.pdp.testmaster.web.entity.Groups;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class GroupsRepo {

    public static Optional<Groups> getById(int groupId) {
        try (
                Connection connection = DBConfig.getConnection()
        ) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from groups where id = ?");
            preparedStatement.setLong(1, groupId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return Optional.empty();
            } else {
                Groups groups = new Groups(resultSet);
                return Optional.of(groups);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
