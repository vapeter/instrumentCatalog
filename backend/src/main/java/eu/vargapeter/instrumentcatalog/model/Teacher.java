package eu.vargapeter.instrumentcatalog.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "teachers")
@Data
public class Teacher implements Serializable {

    @Id
    @Column(name = "teacher_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teacherId;

    @NotNull
    @Column(name = "educational_id")
    private Long educationalId;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private Boolean isActive;

}
