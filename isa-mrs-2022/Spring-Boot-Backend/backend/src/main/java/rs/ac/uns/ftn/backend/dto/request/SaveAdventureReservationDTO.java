package rs.ac.uns.ftn.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveAdventureReservationDTO {
    private String myUsername;

    private Long adventureId;

    private LocalDate start;

    private LocalDate end;

    private String description;

    private Double price;

}
