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
@Table(name = "cottage")
public class Cottage {

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
    @Fetch(FetchMode.JOIN)
    private Set<CottageImage> cottageImages =new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<CottagePricelist> cottagePricelists= new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<CottageMark> marks = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @Fetch(FetchMode.JOIN)
    private Set<CottageResevation> cottageResevations = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<CottageAction> cottageActions = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<MyUser> myUsers = new HashSet<MyUser>();

    public double averageMarks(){
        return marks.stream().mapToDouble(l->l.getMark()).average().orElse(0.0);
    }

    //public Integer sumBedNumer(){ return Integer.parseInt(String.valueOf(numberOfRoom))*Integer.parseInt(String.valueOf(numberOfBedPerRoom));}

    public boolean checkReservationDate(LocalDate start , LocalDate end){
        for(CottageResevation cr : cottageResevations){
            if(!(cr.getReservationEnd().isBefore(start) || cr.getReservationStart().isAfter(end))){
                return false;
            }
        }
        return true;
    }

}
