package uz.pdp.testmaster.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.testmaster.web.abs.BaseEntity;

import java.time.LocalDate;
import java.time.LocalTime;

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
}
