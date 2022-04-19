package rs.ac.uns.ftn.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "cottage_owner_complaint")
public class CottageOwnerComplaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "my_user_id")
    private MyUser myUser;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cottage_owner_id")
    private CottageOwner cottageOwner;
}
