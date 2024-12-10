package uz.pdp.testmaster.web.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.testmaster.web.abs.BaseEntity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Admin extends BaseEntity {
    private String gmail;
    private String password;
}
