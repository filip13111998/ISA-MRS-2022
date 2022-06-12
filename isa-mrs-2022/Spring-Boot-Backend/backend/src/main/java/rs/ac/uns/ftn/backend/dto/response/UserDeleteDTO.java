package rs.ac.uns.ftn.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDeleteDTO {

    private Long id;

    private String email;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String description;

}