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
//@Data
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

    @Version
    private Integer version;

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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNumberOfRoom(Long numberOfRoom) {
        this.numberOfRoom = numberOfRoom;
    }

    public void setNumberOfBedPerRoom(Long numberOfBedPerRoom) {
        this.numberOfBedPerRoom = numberOfBedPerRoom;
    }

    public void setRuleBehavior(String ruleBehavior) {
        this.ruleBehavior = ruleBehavior;
    }

    public void setMoreInformation(String moreInformation) {
        this.moreInformation = moreInformation;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    public void setCottageImages(Set<CottageImage> cottageImages) {
        this.cottageImages = cottageImages;
    }

    public void setCottagePricelists(Set<CottagePricelist> cottagePricelists) {
        this.cottagePricelists = cottagePricelists;
    }

    public void setMarks(Set<CottageMark> marks) {
        this.marks = marks;
    }

    public void setCottageResevations(Set<CottageResevation> cottageResevations) {
        this.cottageResevations = cottageResevations;
    }

    public void setCottageActions(Set<CottageAction> cottageActions) {
        this.cottageActions = cottageActions;
    }

    public void setMyUsers(Set<MyUser> myUsers) {
        this.myUsers = myUsers;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Long getLongitude() {
        return longitude;
    }

    public Long getLatitude() {
        return latitude;
    }

    public String getDescription() {
        return description;
    }

    public Long getNumberOfRoom() {
        return numberOfRoom;
    }

    public Long getNumberOfBedPerRoom() {
        return numberOfBedPerRoom;
    }

    public String getRuleBehavior() {
        return ruleBehavior;
    }

    public String getMoreInformation() {
        return moreInformation;
    }

    public Boolean getDelete() {
        return delete;
    }

    public Set<CottageImage> getCottageImages() {
        return cottageImages;
    }

    public Set<CottagePricelist> getCottagePricelists() {
        return cottagePricelists;
    }

    public Set<CottageMark> getMarks() {
        return marks;
    }

    public Set<CottageResevation> getCottageResevations() {
        return cottageResevations;
    }

    public Set<CottageAction> getCottageActions() {
        return cottageActions;
    }

    public Set<MyUser> getMyUsers() {
        return myUsers;
    }
}
