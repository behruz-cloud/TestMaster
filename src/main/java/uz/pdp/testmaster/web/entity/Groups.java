package uz.pdp.testmaster.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.testmaster.web.abs.BaseEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Groups extends BaseEntity {
    private String groupName;
    @ManyToOne
    private Course course;
}
