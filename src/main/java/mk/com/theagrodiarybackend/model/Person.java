package mk.com.theagrodiarybackend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Person {

    @Id
    private Long personId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String confirmPassword;
}
