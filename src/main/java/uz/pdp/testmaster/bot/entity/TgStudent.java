package uz.pdp.testmaster.bot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.testmaster.web.abs.BaseEntity;
import uz.pdp.testmaster.web.entity.Exam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class TgStudent extends BaseEntity {
    private Long chatId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private State state = State.START;
    @ManyToOne
    private Exam exam;

    public TgStudent(ResultSet resultSet) throws SQLException {
        resultSet.next();
        this.setId(resultSet.getInt("id"));
        this.chatId = resultSet.getLong("chatid");
        this.firstName = resultSet.getString("firstname");
        this.lastName = resultSet.getString("lastname");
        this.state = State.valueOf(resultSet.getString("state"));
    }

    public TgStudent(Long chatId) {
        this.chatId = chatId;
    }
}
