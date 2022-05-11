package rs.ac.uns.ftn.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.uns.ftn.backend.model.CottageAction;
import rs.ac.uns.ftn.backend.model.CottageImage;
import rs.ac.uns.ftn.backend.model.CottagePricelist;
import rs.ac.uns.ftn.backend.model.CottageResevation;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CottageProfileDTO {

    private Long id;

    private String name;

    private String address;

    private Long longitude;

    private Long latitude;

    private String description;

    private Long numberOfRoom;

    private Long numberOfBedPerRoom;

    private String ruleBehavior;

    private String moreInformation;

    private Set<CottageImage> cottageImages;

    private Set<CottagePricelist> cottagePricelists;

    private double averageMark;

    private Set<CottageReservationDTO> cottageResevations;

    private Set<CottageAction> cottageActions;
}
