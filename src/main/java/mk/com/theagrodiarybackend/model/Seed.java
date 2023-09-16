package mk.com.theagrodiarybackend.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Seed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seedId;
    private String seedName;
    private Integer year;

    @ManyToOne
    private Person person;
}
