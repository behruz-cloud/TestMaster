package uz.pdp.testmaster.bot.DB;
import uz.pdp.testmaster.bot.entity.State;
import uz.pdp.testmaster.bot.entity.TgUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
public class TgUserRepo {

    public static TgUser save(TgUser tgUser) {
        try (
                Connection connection = DBConfig.getConnection()
        ) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into tguser(chatid, fullname, state) values (?,?,?) returning chatid,fullname,state");
            preparedStatement.setLong(1, tgUser.getChatId());
            preparedStatement.setString(2, tgUser.getFullName());
            preparedStatement.setString(3, String.valueOf(tgUser.getState()));
            ResultSet resultSet = preparedStatement.executeQuery();
            return new TgUser(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Optional<TgUser> getByChatID(Long chatID) {
        try (
                Connection connection = DBConfig.getConnection()
        ) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from tgUser where chatid = ?");
            preparedStatement.setLong(1, chatID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return Optional.empty();
            } else {
                TgUser tgUser = new TgUser(resultSet);
                return Optional.of(tgUser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateState(Integer id, State state) {
        try (
                Connection connection = DBConfig.getConnection()
        ) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update tguser set state = ? where id = ?");
            preparedStatement.setString(1, state.name());
            preparedStatement.setObject(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateFullName(TgUser tgUser) {
        try (
                Connection connection = DBConfig.getConnection()
        ) {
            String fullName = tgUser.getFullName();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update tguser set fullname = ? where id = ?");
            preparedStatement.setString(1, fullName);
            preparedStatement.setObject(2, tgUser.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void savePhoneNumber(String fixPhoneNumber, TgUser user) {
        try (
                Connection connection = DBConfig.getConnection()
        ) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update tguser set phonenumber = ? where chatid = ?");
            preparedStatement.setString(1, fixPhoneNumber);
            preparedStatement.setObject(2, user.getChatId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveExam(TgUser tgUser) {
        try (
                Connection connection = DBConfig.getConnection()
        ) {
            Integer examId = tgUser.getExamId();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update tguser set examId = ? where chatid = ?");
            preparedStatement.setString(1, examId.toString());
            preparedStatement.setObject(2, tgUser.getChatId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
