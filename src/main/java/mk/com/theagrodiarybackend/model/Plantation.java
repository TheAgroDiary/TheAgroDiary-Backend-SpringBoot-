package mk.com.theagrodiarybackend.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Plantation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer plantationId;
    @Column(nullable = false)
    private Float amountKg;
    private String type;
    @Column(nullable = false)
    private Integer year;

    private Date updatedAt;
    @ManyToOne
    private Person person;

    @ManyToOne
    private Seed seed;

    public Plantation(Float amountKg, String type, Integer year, Date updatedAt, Person person, Seed seed) {
        this.amountKg = amountKg;
        this.type = type;
        this.year = year;
        this.updatedAt = updatedAt;
        this.person = person;
        this.seed = seed;
    }
}
