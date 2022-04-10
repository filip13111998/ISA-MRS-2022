package rs.ac.uns.ftn.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "adventure_reservation")
public class AdventureReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reservation_start")
    private LocalDate reservationStart;

    @Column(name = "reservation_end")
    private LocalDate reservationEnd;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "adventure_pricelist_id")
    private AdventurePricelist pricelistItem;

    //Racuna ukupnu cenu rezervacije kao broj dana pomnozen sa cenom jednog dana
    public Double getPrice(){
        Period period = Period.between(this.getReservationEnd(), this.getReservationStart());
        return period.getDays() * this.getPricelistItem().getPrice();
    }

    //Proveravakoliko dana je ostalo do pocetka rezervacije...ako je iznad 3 onda moze da je otkaze
    public Boolean reservationDeactivated(){
        Period period = Period.between(this.getReservationStart(), LocalDate.now());
        return period.getDays() > 3 ;
    }
}
