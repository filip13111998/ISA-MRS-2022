package rs.ac.uns.ftn.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
//@Table(name = "administrator")
public class Administrator extends User{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long id;

    @Column(name = "name_admin")
    private String nameAdmin;

//    @Column(name = "password")
//    private String password;
//
//    @Column(name = "active")
//    private Boolean active;

    @Column(name = "activate_acc")
    private Boolean activateAccount;

    @Column(name = "delete_adm")
    private Boolean delete;

}
