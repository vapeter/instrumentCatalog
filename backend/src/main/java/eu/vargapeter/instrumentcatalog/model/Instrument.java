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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "instruments")
@Data
public class Instrument implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instrument_id")
    private Integer instrumentId;

    @NotNull
    @NotBlank
    @Column(name = "catalog_number")
    private String catalogNumber;

    @NotBlank
    @NotNull
    @Column(name = "brand")
    private String brand;

    @NotBlank
    @Column(name = "model")
    private String model;

    @NotBlank
    @Column(name = "instrument_type")
    private String instrumentType;

    @NotNull
    @NotBlank
    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "location")
    private String location;

    @Column(name = "comment")
    private String comment;

    @Column(name = "rented")
    private Boolean rented;

    @Column(name = "high_value")
    private Boolean highValue;

    @Column(name = "waste")
    private Boolean waste;

    @DateTimeFormat(pattern = Constans.SQL_DATE_PATTERN)
    @JsonFormat(pattern = Constans.JSON_DATE_PATTERN)
    @Column(name = "last_showing_date")
    private LocalDate lastShowingDate;


}
