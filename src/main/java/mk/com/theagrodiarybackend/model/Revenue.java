package mk.com.theagrodiarybackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Data
@NoArgsConstructor
public class Revenue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer revenueId;
    @Column(nullable = false)
    private Float revenueSum;
    private Date date;
    @Column(nullable = false)
    private Float seedAmountKg;

    private Date updatedAt;

    @ManyToOne
    private Person person;
    @ManyToOne
    private Seed seed;

    public Revenue(Float revenueSum, Date date, Float seedAmountKg, Date updatedAt, Person person, Seed seed) {
        this.revenueSum = revenueSum;
        this.date = date;
        this.seedAmountKg = seedAmountKg;
        this.updatedAt = updatedAt;
        this.person = person;
        this.seed = seed;
    }
}
