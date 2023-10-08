package mk.com.theagrodiarybackend.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Yield {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long yieldId;
    @Column(nullable = false)
    private Float amountKg;
    private String type;
    @Column(nullable = false)
    private Integer year;

    @ManyToOne
    private Person person;

    @ManyToOne
    private Seed seed;

    public Yield(Float amountKg, String type, Integer year, Person person, Seed seed) {
        this.amountKg = amountKg;
        this.type = type;
        this.year = year;
        this.person = person;
        this.seed = seed;
    }
}
