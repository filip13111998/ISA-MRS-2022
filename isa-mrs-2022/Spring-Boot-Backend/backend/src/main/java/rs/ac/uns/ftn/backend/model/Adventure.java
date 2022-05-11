package rs.ac.uns.ftn.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
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

    @Column(name = "address")
    private String address;

    @Column(name = "longitude")
    private Long longitude;

    @Column(name = "latitude")
    private Long latitude;

    @Column(name = "description")
    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @Column(name = "max_num")
    private Integer maxNumPerson;

    @Column(name = "rule_behaviour")
    private String ruleBehavior;

    @Column(name = "more_information")
    private String moreInformation;

    @Column(name = "cancellation_conditions")
    private String cancellationConditions;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<AdventureImage> adventureImages = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<AdventureMark> marks = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<AdventurePricelist> adventurePricelists = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @Fetch(FetchMode.JOIN)
    private Set<AdventureReservation> adventureReservations = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<AdventureAction> adventureActions = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<MyUser> myUsers = new HashSet<MyUser>();

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
