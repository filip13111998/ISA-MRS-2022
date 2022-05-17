package rs.ac.uns.ftn.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteBoatReservationDTO {
    private String myUsername;

    private Long boateId;

    private Long reservationId;
}
