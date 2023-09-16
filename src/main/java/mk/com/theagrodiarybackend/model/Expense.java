package mk.com.theagrodiarybackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.ZonedDateTime;


@Entity
@Data
public class Expense {

    @Id
    private Long expenseId;
    private Float expenseSum;
    private ZonedDateTime date;
    private Integer seedAmountKg;
    private String expensePurpose;

    @ManyToOne
    private Person person;
    @ManyToOne
    private Seed seed;
}
