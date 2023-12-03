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
    private Integer expenseId;
    @Column(nullable = false)
    private Float expenseSum;
    private ZonedDateTime date;
    @Column(nullable = false)
    private Float seedAmountKg;
    private String description;

    @ManyToOne
    private Person person;
    @ManyToOne
    private Seed seed;

    @ManyToOne
    private Category category;

    public Expense(Float expenseSum, ZonedDateTime date, Float seedAmountKg,
                   String description, Person person, Seed seed, Category category) {
        this.expenseSum = expenseSum;
        this.date = date;
        this.seedAmountKg = seedAmountKg;
        this.description = description;
        this.person = person;
        this.seed = seed;
        this.category = category;
    }
}
