package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;


/**
 * The type Curve point.
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "curve_point")
public class CurvePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "curve id is mandatory")
    @Min(value = 0,message = "curve id must be a positive number")
    @Digits(fraction = 0, integer = 10, message ="curve id must be an integer")
    @Column(name = "curve_id")
    private Integer curveId;

    @Column(name = "as_of_date")
    private LocalDateTime asOfDate;

    @NotNull(message = "term is mandatory")
    @Min(value = 0,message = "term must be a positive number")
    @Digits(integer = 8, fraction = 2, message = "term should be a number")
    @Column(name = "term")
    private double term;

    @NotNull(message = "value is mandatory")
    @Min(value = 0,message = "value must be a positive number")
    @Digits(integer = 8, fraction = 2, message = "value should be a number")
    @Column(name = "value")
    private double value;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    /**
     * Instantiates a new Curve point.
     *
     * @param curveId the curve id
     * @param term    the term
     * @param value   the value
     */
    public CurvePoint(Integer curveId, double term, double value) {
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }

    /**
     * Instantiates a new Curve point.
     */
    public CurvePoint() {

    }
}
