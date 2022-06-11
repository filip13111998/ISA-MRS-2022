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
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
//@Data
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
    private Double lenght;

    @Column(name = "engine_num")
    private Integer engineNum;

    @Column(name = "engine_power")
    private Double enginePower;

    @Column(name = "max_speed")
    private Double maxSpeed;

    @Column(name = "address")
    private String address;

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
//    @Fetch(FetchMode.JOIN)
    private Set<BoatResevation> boatResevations = new HashSet<>();





    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<BoatAction> boatActions= new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "my_user_boats", joinColumns = @JoinColumn(name = "boats_id"), inverseJoinColumns = @JoinColumn(name = "my_users_id"))
    @Fetch(FetchMode.JOIN)
    private Set<MyUser> myUsers = new HashSet<MyUser>();









    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLenght(Double lenght) {
        this.lenght = lenght;
    }

    public void setEngineNum(Integer engineNum) {
        this.engineNum = engineNum;
    }

    public void setEnginePower(Double enginePower) {
        this.enginePower = enginePower;
    }

    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
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

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNavigation(String navigation) {
        this.navigation = navigation;
    }

    public void setRuleBehavior(String ruleBehavior) {
        this.ruleBehavior = ruleBehavior;
    }

    public void setFishingEquipment(String fishingEquipment) {
        this.fishingEquipment = fishingEquipment;
    }

    public void setMoreInformation(String moreInformation) {
        this.moreInformation = moreInformation;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    public void setBoatImages(Set<BoatImage> boatImages) {
        this.boatImages = boatImages;
    }

    public void setMarks(Set<BoatMark> marks) {
        this.marks = marks;
    }

    public void setBoatPricelists(Set<BoatPricelist> boatPricelists) {
        this.boatPricelists = boatPricelists;
    }

    public void setBoatResevations(Set<BoatResevation> boatResevations) {
        this.boatResevations = boatResevations;
    }

    public void setBoatActions(Set<BoatAction> boatActions) {
        this.boatActions = boatActions;
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

    public String getType() {
        return type;
    }

    public Double getLenght() {
        return lenght;
    }

    public Integer getEngineNum() {
        return engineNum;
    }

    public Double getEnginePower() {
        return enginePower;
    }

    public Double getMaxSpeed() {
        return maxSpeed;
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

    public String getCapacity() {
        return capacity;
    }

    public String getDescription() {
        return description;
    }

    public String getNavigation() {
        return navigation;
    }

    public String getRuleBehavior() {
        return ruleBehavior;
    }

    public String getFishingEquipment() {
        return fishingEquipment;
    }

    public String getMoreInformation() {
        return moreInformation;
    }

    public Boolean getDelete() {
        return delete;
    }

    public Set<BoatImage> getBoatImages() {
        return boatImages;
    }

    public Set<BoatMark> getMarks() {
        return marks;
    }

    public Set<BoatPricelist> getBoatPricelists() {
        return boatPricelists;
    }

    public Set<BoatResevation> getBoatResevations() {
        return boatResevations;
    }

    public Set<BoatAction> getBoatActions() {
        return boatActions;
    }

    public Set<MyUser> getMyUsers() {
        return myUsers;
    }

    public double averageMarks(){
        List<BoatMark> lsita = marks.stream().filter(m-> m.getEnable()).collect(Collectors.toList());

        return lsita.stream().mapToDouble(l->l.getMark()).average().orElse(0.0);
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
