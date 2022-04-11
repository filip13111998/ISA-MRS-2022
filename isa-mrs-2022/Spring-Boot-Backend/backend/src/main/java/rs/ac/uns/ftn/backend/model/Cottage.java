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
@Table(name = "cottage")
public class Cottage {

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

    @Column(name = "number_of_room")
    private Long numberOfRoom;

    @Column(name = "number_of_bed_per_room")
    private Long numberOfBedPerRoom;

    @Column(name = "rule_behaviour")
    private String ruleBehavior;

    @Column(name = "more_information")
    private String moreInformation;

    @Column(name = "delete_ctg")
    private Boolean delete;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<CottageImage> cottageImages;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<CottagePricelist> cottagePricelists;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<CottageMark> marks;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<CottageResevation> cottageResevations;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<CottageAction> cottageActions;

    @ManyToMany(mappedBy = "cottages",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MyUser> myUsers;

    public double averageMarks(){
        return marks.stream().mapToDouble(l->l.getMark()).average().orElse(0.0);
    }


    public boolean checkReservationDate(LocalDate start , LocalDate end){
        for(CottageResevation cr : cottageResevations){
            if(!(cr.getReservationEnd().isBefore(start) || cr.getReservationStart().isAfter(end))){
                return false;
            }
        }
        return true;
    }

}
