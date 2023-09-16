package mk.com.theagrodiarybackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;


@Entity
@Data
public class Revenue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long revenueId;
    private Float revenueSum;
    private ZonedDateTime date;
    private Integer seedAmountKg;

    @ManyToOne
    private Person person;
    @ManyToOne
    private Seed seed;
}
