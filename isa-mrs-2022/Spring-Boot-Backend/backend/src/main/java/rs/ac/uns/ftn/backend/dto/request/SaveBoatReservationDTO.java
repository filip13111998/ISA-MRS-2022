package rs.ac.uns.ftn.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveBoatReservationDTO {
    private String myUsername;

    private Long boatId;

    private LocalDate start;

    private LocalDate end;

    private String description;

    private Double price;
}
