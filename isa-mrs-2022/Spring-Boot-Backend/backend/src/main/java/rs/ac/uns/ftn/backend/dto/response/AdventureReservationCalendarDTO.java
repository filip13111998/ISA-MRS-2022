package rs.ac.uns.ftn.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.uns.ftn.backend.dto.request.SimpleActionDTO;
import rs.ac.uns.ftn.backend.model.Adventure;
import rs.ac.uns.ftn.backend.model.AdventureReservation;
import rs.ac.uns.ftn.backend.model.BoatResevation;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdventureReservationCalendarDTO {
    private List<AdventureReservation> adventureResevations= new ArrayList<>();

    private List<SimpleActionDTO> adventureActionsReservated= new ArrayList<>();

    private List<SimpleActionDTO> adventureActionsUnReservated= new ArrayList<>();
}
