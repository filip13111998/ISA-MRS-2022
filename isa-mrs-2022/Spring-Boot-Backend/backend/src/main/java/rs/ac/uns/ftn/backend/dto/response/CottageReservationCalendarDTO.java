package rs.ac.uns.ftn.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.uns.ftn.backend.dto.request.SimpleActionDTO;
import rs.ac.uns.ftn.backend.model.CottagePricelist;
import rs.ac.uns.ftn.backend.model.CottageResevation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CottageReservationCalendarDTO {

    private List<CottageResevation> cottageResevations= new ArrayList<>();

    private List<SimpleActionDTO> cottageActionsReservated= new ArrayList<>();

    private List<SimpleActionDTO> cottageActionsUnReservated= new ArrayList<>();

}
