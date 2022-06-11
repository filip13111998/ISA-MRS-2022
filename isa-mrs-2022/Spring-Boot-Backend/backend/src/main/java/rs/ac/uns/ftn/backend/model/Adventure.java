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
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
//@Data
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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<MyUser> myUsers = new HashSet<MyUser>();

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

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public void setMaxNumPerson(Integer maxNumPerson) {
        this.maxNumPerson = maxNumPerson;
    }

    public void setRuleBehavior(String ruleBehavior) {
        this.ruleBehavior = ruleBehavior;
    }

    public void setMoreInformation(String moreInformation) {
        this.moreInformation = moreInformation;
    }

    public void setCancellationConditions(String cancellationConditions) {
        this.cancellationConditions = cancellationConditions;
    }

    public void setAdventureImages(Set<AdventureImage> adventureImages) {
        this.adventureImages = adventureImages;
    }

    public void setMarks(Set<AdventureMark> marks) {
        this.marks = marks;
    }

    public void setAdventurePricelists(Set<AdventurePricelist> adventurePricelists) {
        this.adventurePricelists = adventurePricelists;
    }

    public void setAdventureReservations(Set<AdventureReservation> adventureReservations) {
        this.adventureReservations = adventureReservations;
    }

    public void setAdventureActions(Set<AdventureAction> adventureActions) {
        this.adventureActions = adventureActions;
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

    public Instructor getInstructor() {
        return instructor;
    }

    public Integer getMaxNumPerson() {
        return maxNumPerson;
    }

    public String getRuleBehavior() {
        return ruleBehavior;
    }

    public String getMoreInformation() {
        return moreInformation;
    }

    public String getCancellationConditions() {
        return cancellationConditions;
    }

    public Set<AdventureImage> getAdventureImages() {
        return adventureImages;
    }

    public Set<AdventureMark> getMarks() {
        return marks;
    }

    public Set<AdventurePricelist> getAdventurePricelists() {
        return adventurePricelists;
    }

    public Set<AdventureReservation> getAdventureReservations() {
        return adventureReservations;
    }

    public Set<AdventureAction> getAdventureActions() {
        return adventureActions;
    }

    public Set<MyUser> getMyUsers() {
        return myUsers;
    }

    public double averageMarks(){

        List<AdventureMark> lsita = marks.stream().filter(m-> m.getEnable()).collect(Collectors.toList());

        return lsita.stream().mapToDouble(l->l.getMark()).average().orElse(0.0);
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
