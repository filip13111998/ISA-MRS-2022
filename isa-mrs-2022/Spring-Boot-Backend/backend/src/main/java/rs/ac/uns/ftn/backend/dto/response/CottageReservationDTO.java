package rs.ac.uns.ftn.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.uns.ftn.backend.model.CottagePricelist;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CottageReservationDTO {

    private Long id;

    private LocalDate reservationStart;

    private LocalDate reservationEnd;

    private Boolean active;

    private CottagePricelist pricelistItem;
}
