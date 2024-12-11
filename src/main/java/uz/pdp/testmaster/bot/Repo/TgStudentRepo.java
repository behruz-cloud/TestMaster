package uz.pdp.testmaster.bot.Repo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import uz.pdp.testmaster.bot.DB.DBConfig;
import uz.pdp.testmaster.bot.entity.TgStudent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static uz.pdp.testmaster.web.util.MyListener.EMF;
public class TgStudentRepo implements ServletContextListener {


    public static TgStudent getUserFromDB(Long chatId) {
        try (
                EntityManager em = EMF.createEntityManager()
        ) {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT s FROM TgStudent s WHERE s.chatId = :chatId").setParameter("chatId", chatId);
            em.getTransaction().commit();
            return (TgStudent) query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static TgStudent save(TgStudent tgStudent) {
        try (
                Connection connection = DBConfig.getConnection()
        ) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into tguser(chatid,state) values (?,?,?) returning chatid,state");
            preparedStatement.setLong(1, tgStudent.getChatId());
            preparedStatement.setString(2, String.valueOf(tgStudent.getState()));
            ResultSet resultSet = preparedStatement.executeQuery();
            return new TgStudent(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
