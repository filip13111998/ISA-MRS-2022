package rs.ac.uns.ftn.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "delete_ins")
    private Boolean delete;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<InstructorMark> marks;

    public double averageMarks(){
        return marks.stream().mapToDouble(l->l.getMark()).average().orElse(0.0);
    }

}
