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
    
    private Date date;
    @Column(nullable = false)
    private Float seedAmountKg;
    private String description;
    private Date updatedAt;

    @ManyToOne
    private Person person;
    @ManyToOne
    private Seed seed;

    @ManyToOne
    private Category category;

    public Expense(Float expenseSum, Date date, Float seedAmountKg, String description,
                   Date updatedAt, Person person, Seed seed, Category category) {
        this.expenseSum = expenseSum;
        this.date = date;
        this.seedAmountKg = seedAmountKg;
        this.description = description;
        this.updatedAt = updatedAt;
        this.person = person;
        this.seed = seed;
        this.category = category;
    }
}
