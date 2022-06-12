package rs.ac.uns.ftn.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.uns.ftn.backend.model.Role;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminProfileDTO {

    private Long id;

    private String email;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

}
