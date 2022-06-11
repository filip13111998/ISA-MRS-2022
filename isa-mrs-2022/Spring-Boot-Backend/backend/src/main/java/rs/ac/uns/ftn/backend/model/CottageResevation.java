package rs.ac.uns.ftn.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "cottage_reservation")
@Where(clause = "active=true")
public class CottageResevation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reservation_start")
    private LocalDate reservationStart;

    @Column(name = "reservation_end")
    private LocalDate reservationEnd;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "price")
    private Long price;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cottage_pricelist_id")
    private CottagePricelist pricelistItem;

    //Racuna ukupnu cenu rezervacije kao broj dana pomnozen sa cenom jednog dana
//    public Double getPrice(){
//        Period period = Period.between( this.getReservationStart(),this.getReservationEnd());
//        return period.getDays() * this.getPricelistItem().getPrice();
//    }

    //Proveravakoliko dana je ostalo do pocetka rezervacije...ako je iznad 3 onda moze da je otkaze
    public Boolean reservationDeactivated(){
        Period period = Period.between(this.getReservationStart(), LocalDate.now());
        return period.getDays() > 3 ;
    }

}
