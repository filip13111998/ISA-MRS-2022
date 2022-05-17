package rs.ac.uns.ftn.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteAdventureReservationDTO {
    private String myUsername;

    private Long adventureId;

    private Long reservationId;
}
