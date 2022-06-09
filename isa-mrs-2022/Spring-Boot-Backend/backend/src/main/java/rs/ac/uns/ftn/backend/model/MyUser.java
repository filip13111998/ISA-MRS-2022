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
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
//@Data
@Entity
//@Table(name = "my_user")
public class MyUser extends User{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long id;

//    @Column(name = "name_user")
//    private String nameUser;

    @Column(name = "point")
    private Long point;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "adresa")
    private String adresa;

    @Column(name = "grad")
    private String grad;

    @Column(name = "drzava")
    private String drzava;

    @Column(name = "register_token")
    private String registerToken;

//    @Column(name = "email")
//    private String email;

//    @Column(name = "username")
//    private String username;
//
//    @Column(name = "password")
//    private String password;

//    @Column(name = "active")
//    private Boolean active;

    @Column(name = "activate_usr")
    private Boolean activate;

    @ManyToMany(mappedBy="myUsers",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @Fetch(FetchMode.JOIN)
    private Set<Cottage> cottages = new HashSet<>();

    @ManyToMany(mappedBy="myUsers",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<Boat> boats = new HashSet<Boat>();

    @ManyToMany(mappedBy="myUsers",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<Adventure> adventures = new HashSet<>();


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<CottageResevation> cottageResevations = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<BoatResevation> boatResevations = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<AdventureReservation> adventureResevations = new HashSet<>();

//    public void setNameUser(String nameUser) {
//        this.nameUser = nameUser;
//    }

//    public void setDelete(Boolean delete) {
//        this.delete = delete;
//    }


    public String getRegisterToken() {
        return registerToken;
    }

    public void setRegisterToken(String registerToken) {
        this.registerToken = registerToken;
    }

    public Boolean getActivate() {
        return activate;
    }

    public void setActivate(Boolean activate) {
        this.activate = activate;
    }

    public void setCottages(Set<Cottage> cottages) {
        this.cottages = cottages;
    }

    public void setBoats(Set<Boat> boats) {
        this.boats = boats;
    }

    public void setAdventures(Set<Adventure> adventures) {
        this.adventures = adventures;
    }

    public void setCottageResevations(Set<CottageResevation> cottageResevations) {
        this.cottageResevations = cottageResevations;
    }

    public void setBoatResevations(Set<BoatResevation> boatResevations) {
        this.boatResevations = boatResevations;
    }

    public void setAdventureResevations(Set<AdventureReservation> adventureResevations) {
        this.adventureResevations = adventureResevations;
    }

//    public String getNameUser() {
//        return nameUser;
//    }

//    public Boolean getDelete() {
//        return delete;
//    }

    public Set<Cottage> getCottages() {
        return cottages;
    }

    public Set<Boat> getBoats() {
        return boats;
    }

    public Set<Adventure> getAdventures() {
        return adventures;
    }

    public Set<CottageResevation> getCottageResevations() {
        return cottageResevations;
    }

    public Set<BoatResevation> getBoatResevations() {
        return boatResevations;
    }

    public Set<AdventureReservation> getAdventureResevations() {
        return adventureResevations;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public Long getPoint() {
        return point;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getGrad() {
        return grad;
    }

    public String getDrzava() {
        return drzava;
    }
}
