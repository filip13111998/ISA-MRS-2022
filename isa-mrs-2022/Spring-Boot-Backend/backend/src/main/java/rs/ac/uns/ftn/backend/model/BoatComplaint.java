package rs.ac.uns.ftn.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "boat_complaint")
public class BoatComplaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "enable")
    private Boolean enable;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "entity")
    private Long entity;


//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "my_user_id")
//    private MyUser myUser;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "boat_id")
//    private Boat boat;


}
