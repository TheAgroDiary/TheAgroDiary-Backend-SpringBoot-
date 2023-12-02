package mk.com.theagrodiarybackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;


@Entity
@Data
@NoArgsConstructor
public class Revenue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer revenueId;
    @Column(nullable = false)
    private Float revenueSum;
    private ZonedDateTime date;
    @Column(nullable = false)
    private Float seedAmountKg;

    @ManyToOne
    private Person person;
    @ManyToOne
    private Seed seed;

    public Revenue(Float revenueSum, ZonedDateTime date, Float seedAmountKg, Person person, Seed seed) {
        this.revenueSum = revenueSum;
        this.date = date;
        this.seedAmountKg = seedAmountKg;
        this.person = person;
        this.seed = seed;
    }
}
