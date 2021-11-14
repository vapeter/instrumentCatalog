package eu.vargapeter.instrumentcatalog.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import eu.vargapeter.instrumentcatalog.util.Constans;
import lombok.Data;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "rental")
@Data
public class Rental implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private Integer rentalId;

    @NotNull
    @JsonFormat(pattern = Constans.JSON_DATE_PATTERN)
    @Column(name = "start_of_rental")
    private LocalDate startOfRental;

    @JsonFormat(pattern = Constans.JSON_DATE_PATTERN)
    @Column(name = "end_of_rental")
    private LocalDate endOfRental;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "instrument_id")
    private Instrument instrument;

}
