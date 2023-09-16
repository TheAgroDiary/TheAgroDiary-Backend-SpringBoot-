package mk.com.theagrodiarybackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.ZonedDateTime;


@Entity
@Data
public class Revenue {

    @Id
    private Long revenueId;
    private Float revenueSum;
    private ZonedDateTime date;
    private Integer seedAmountKg;

    @ManyToOne
    private Person person;
    @ManyToOne
    private Seed seed;
}
