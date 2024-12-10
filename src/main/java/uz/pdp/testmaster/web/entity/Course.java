package uz.pdp.testmaster.web.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.testmaster.web.abs.BaseEntity;
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Course extends BaseEntity {
    private String courseName;
}
