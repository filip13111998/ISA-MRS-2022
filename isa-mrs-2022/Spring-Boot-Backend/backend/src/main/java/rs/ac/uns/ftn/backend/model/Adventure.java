package rs.ac.uns.ftn.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "adventure")
public class Adventure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "adress")
    private String adress;

    @Column(name = "longitude")
    private Long longitude;

    @Column(name = "latitude")
    private Long latitude;

    @Column(name = "description")
    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @Column(name = "max_num")
    private String maxNumPerson;

    @Column(name = "rule_behaviour")
    private String ruleBehavior;

    @Column(name = "more_information")
    private String moreInformation;

    @Column(name = "cancellation_conditions")
    private String cancellationConditions;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<AdventureMark> marks;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<AdventurePricelist> adventurePricelists;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<AdventureReservation> adventureReservations;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<AdventureAction> adventureActions;

    @ManyToMany(mappedBy = "adventures",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MyUser> myUsers;

    public double averageMarks(){
        return marks.stream().mapToDouble(l->l.getMark()).average().orElse(0.0);
    }

    public String getInstructorDescription(){
        return "Instructor name: " + this.getInstructor().getName() + " and Average mark: " + this.getInstructor().averageMarks();
    }

    public boolean checkReservationDate(LocalDate start , LocalDate end){
        for(AdventureReservation ar : adventureReservations){
            if(!(ar.getReservationEnd().isBefore(start) || ar.getReservationStart().isAfter(end))){
                return false;
            }
        }
        return true;
    }

}
