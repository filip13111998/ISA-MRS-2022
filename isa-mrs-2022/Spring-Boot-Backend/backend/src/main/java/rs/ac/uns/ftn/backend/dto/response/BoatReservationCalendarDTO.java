package rs.ac.uns.ftn.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.uns.ftn.backend.dto.request.SimpleActionDTO;
import rs.ac.uns.ftn.backend.model.BoatResevation;
import rs.ac.uns.ftn.backend.model.CottageResevation;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoatReservationCalendarDTO {
    private List<BoatResevation> boatResevations= new ArrayList<>();

    private List<SimpleActionDTO> boatActionsReservated= new ArrayList<>();

    private List<SimpleActionDTO> boatActionsUnReservated= new ArrayList<>();

}
