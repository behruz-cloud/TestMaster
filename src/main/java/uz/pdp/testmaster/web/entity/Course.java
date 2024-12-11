package uz.pdp.testmaster.web.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.testmaster.web.abs.BaseEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Course extends BaseEntity {
    private String courseName;

    public Course(ResultSet resultSet) throws SQLException {
        resultSet.next();
        this.setId(resultSet.getInt("id"));
        this.courseName = resultSet.getString("coursename");
    }

    public Course() {

    }
}
