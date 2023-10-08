package mk.com.theagrodiarybackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;


@Entity
@Data
@NoArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;
    @Column(nullable = false)
    private Float expenseSum;
    private ZonedDateTime date;
    @Column(nullable = false)
    private Float seedAmountKg;
    @Column(nullable = false)
    private String expenseType;
    private String description;

    @ManyToOne
    private Person person;
    @ManyToOne
    private Seed seed;

    public Expense(Float expenseSum, ZonedDateTime date, Float seedAmountKg, String expenseType,
                   String description, Person person, Seed seed) {
        this.expenseSum = expenseSum;
        this.date = date;
        this.seedAmountKg = seedAmountKg;
        this.expenseType = expenseType;
        this.description = description;
        this.person = person;
        this.seed = seed;
    }
}
