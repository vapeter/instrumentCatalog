package eu.vargapeter.instrumentcatalog.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import eu.vargapeter.instrumentcatalog.util.Constans;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "students")
@Data
public class Student implements Serializable {

    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;

    @NotNull
    @Column(name = "educational_id")
    private Long educationalId;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "birth_name")
    private String birthName;

    @NotNull
    @Column(name = "mothers_name")
    private String mothersName;

    @NotNull
    @Column(name = "birth_place")
    private String birthPlace;

    @NotNull
    @DateTimeFormat(pattern = Constans.SQL_DATE_PATTERN)
    @JsonFormat(pattern = Constans.JSON_DATE_PATTERN)
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "active")
    private Boolean isActive;

}
