package mk.com.theagrodiarybackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Data
@NoArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer expenseId;
    @Column(nullable = false)
    private Float expenseSum;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date date;
    @Column(nullable = false)
    private Float seedAmountKg;
    private String description;

    @ManyToOne
    private Person person;
    @ManyToOne
    private Seed seed;

    @ManyToOne
    private Category category;

    public Expense(Float expenseSum, Date date, Float seedAmountKg,
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
