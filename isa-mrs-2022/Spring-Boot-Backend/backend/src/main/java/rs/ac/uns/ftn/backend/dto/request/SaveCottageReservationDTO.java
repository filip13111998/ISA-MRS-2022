package rs.ac.uns.ftn.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.uns.ftn.backend.model.CottagePricelist;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveCottageReservationDTO {

    private String myUsername;

    private Long cottageId;

    private LocalDate start;

    private LocalDate end;

    private String description;

    private Double price;

}
