package mk.com.theagrodiarybackend.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeId;
    private String typeName;
    private Float amountKg;

    @ManyToOne
    private Seed seed;
}
