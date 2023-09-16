package mk.com.theagrodiarybackend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Type {

    @Id
    private Long typeId;
    private String typeName;
    private Float amountKg;

    @ManyToOne
    private Seed seed;
}
