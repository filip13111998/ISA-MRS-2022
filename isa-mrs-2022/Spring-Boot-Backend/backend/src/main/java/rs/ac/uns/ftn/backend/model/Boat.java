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
@Table(name = "boat")
public class Boat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "lenght")
    private String lenght;

    @Column(name = "engine_num")
    private String engineNum;

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

    @Column(name = "delete")
    private Boolean delete;

    @OneToMany
    private Set<BoatMark> marks;

    @OneToMany
    private Set<BoatPricelist> boatPricelists;

    @OneToMany
    private Set<BoatResevation> boatResevations;

    @OneToMany
    private Set<BoatAction> boatActions;

    @ManyToMany
    private Set<MyUser> myUsers;

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
