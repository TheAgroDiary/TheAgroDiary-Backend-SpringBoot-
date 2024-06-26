package mk.com.theagrodiarybackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

    private Integer personId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
