package rs.ac.uns.ftn.backend.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.uns.ftn.backend.model.Role;

import javax.persistence.Access;
import javax.persistence.Column;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyUserDTO {

    private Long id;

    private String email;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private List<Role> roles;

    private Long point;

    private String phoneNumber;

   private String adresa;

   private String grad;

   private String drzava;

}
