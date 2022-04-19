package rs.ac.uns.ftn.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
@Table(name = "boat")
//@JsonIgnoreProperties(value= {"boatImages","marks","boatPricelists","boatResevations","boatActions"})
public class Boat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type_boat")
    private String type;

    @Column(name = "lenght")
    private Integer lenght;

    @Column(name = "engine_num")
    private Integer engineNum;

    @Column(name = "engine_power")
    private String enginePower;

    @Column(name = "max_speed")
    private String maxSpeed;

    @Column(name = "adress")
    private String adress;

    @Column(name = "longitude")
    private Long longitude;

    @Column(name = "latitude")
    private Long latitude;

    @Column(name = "capacity")
    private String capacity;

    @Column(name = "description")
    private String description;

    @Column(name = "navigation")
    private String navigation;

    @Column(name = "rule_behaviour")
    private String ruleBehavior;

    @Column(name = "fishing_equipment")
    private String fishingEquipment;

    @Column(name = "more_information")
    private String moreInformation;

    @Column(name = "delete_bt")
    private Boolean delete;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<BoatImage> boatImages = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<BoatMark> marks = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<BoatPricelist> boatPricelists = new HashSet<BoatPricelist>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<BoatResevation> boatResevations = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<BoatAction> boatActions= new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "my_user_boats", joinColumns = @JoinColumn(name = "boats_id"), inverseJoinColumns = @JoinColumn(name = "my_users_id"))
    @Fetch(FetchMode.JOIN)
    private Set<MyUser> myUsers = new HashSet<MyUser>();

    public double averageMarks(){
        return marks.stream().mapToDouble(l->l.getMark()).average().orElse(0.0);
    }


    public boolean checkReservationDate(LocalDate start , LocalDate end){
        for(BoatResevation br : boatResevations){
            if(!(br.getReservationEnd().isBefore(start) || br.getReservationStart().isAfter(end))){
                return false;
            }
        }
        return true;
    }

    public void addNewAction(BoatAction ba){
        this.boatActions.add(ba);
        //send mail to all users
//        this.myUsers.stream().forEach(u-> Mail.sendMail(u.getMail()));
    }
}
