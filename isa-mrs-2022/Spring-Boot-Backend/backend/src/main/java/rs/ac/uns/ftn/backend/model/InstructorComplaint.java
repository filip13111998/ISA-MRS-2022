package rs.ac.uns.ftn.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "instructor_complaint")
public class InstructorComplaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "my_user_id")
    private MyUser myUser=null;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor=null;

}
