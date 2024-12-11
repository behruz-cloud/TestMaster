package uz.pdp.testmaster.bot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.testmaster.bot.DB.TgUserRepo;

import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
@NoArgsConstructor
public class TgUser {
    @Getter
    private Integer id;
    @Setter
    @Getter
    private Long chatId;
    @Setter
    @Getter
    private String fullName;
    @Getter
    private State state = State.START;
@Setter
@Getter
    private String temp_category_name;

    public TgUser(Long chatId, String fullName) {
        this.chatId = chatId;
        this.fullName = fullName;
    }

    public TgUser(ResultSet resultSet) throws SQLException {
        resultSet.next();
        this.id = resultSet.getInt("id");
        this.chatId = resultSet.getLong("chatid");
        this.fullName = resultSet.getString("fullname");
        this.state = State.valueOf(resultSet.getString("state"));
    }

    public void setState(State state) {
        this.state = state;
        TgUserRepo.updateState(this.id, state)
        ;
    }

}
