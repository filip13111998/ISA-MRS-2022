package rs.ac.uns.ftn.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.uns.ftn.backend.model.BoatPricelist;
import rs.ac.uns.ftn.backend.model.CottagePricelist;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoatReservationHistoryDTO {
    private Long id;

    private LocalDate reservationStart;

    private LocalDate reservationEnd;

    private Long price;

    private Boolean active;

    private BoatPricelist pricelistItem;

    private Long boatID;
}
