package uz.pdp.testmaster.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.testmaster.bot.DB.GroupsRepo;
import uz.pdp.testmaster.bot.entity.State;
import uz.pdp.testmaster.web.abs.BaseEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Exam extends BaseEntity {
    @ManyToOne
    private Course course;
    @ManyToOne
    private Groups group;
    private Integer moduleNumber;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public Exam(ResultSet resultSet) throws SQLException {
        resultSet.next();
        int groupId = Integer.parseInt(resultSet.getString("group_id"));
        Optional<Groups> groups= GroupsRepo.getById(groupId);

        this.setId(resultSet.getInt("id"));
        this.setGroup(groups.get());
        this.setModuleNumber(resultSet.getInt("modulenumber"));
        this.setCourse(groups.get().getCourse());
        this.setDate(LocalDate.parse(resultSet.getString("date")));
        this.setStartTime(LocalTime.parse(resultSet.getString("starttime")));
        this.setEndTime(LocalTime.parse(resultSet.getString("endtime")));
    }


    public Exam() {
    }
}
