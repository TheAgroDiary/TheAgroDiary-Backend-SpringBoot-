package mk.com.theagrodiarybackend.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Seed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seedId;
    @Column(nullable = false)
    private String seedName;

    public Seed(String seedName) {
        this.seedName = seedName;
    }
}
