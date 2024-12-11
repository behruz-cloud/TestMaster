package uz.pdp.testmaster.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.testmaster.bot.DB.CourseRepo;
import uz.pdp.testmaster.bot.DB.GroupsRepo;
import uz.pdp.testmaster.web.abs.BaseEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Groups extends BaseEntity {
    private String groupName;
    @ManyToOne
    private Course course;

    public Groups(ResultSet resultSet) throws SQLException {
        resultSet.next();
        int courseId = resultSet.getInt("course_id");
        Optional<Course> courseOptional = CourseRepo.getById(courseId);

        this.setId(resultSet.getInt("id"));
        this.setCourse(courseOptional.get());
        this.setGroupName(resultSet.getString("groupname"));
    }

    public Groups() {

    }
}
