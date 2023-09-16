package mk.com.theagrodiarybackend.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignupDto {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
