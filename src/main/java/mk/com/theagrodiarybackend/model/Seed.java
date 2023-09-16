package mk.com.theagrodiarybackend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Seed {

    @Id
    private Long seedId;
    private String seedName;
    private Integer year;

    @ManyToOne
    private Person person;
}
