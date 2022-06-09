package rs.ac.uns.ftn.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDTO {

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String adresa;

    private String grad;

    private String drzava;

}
