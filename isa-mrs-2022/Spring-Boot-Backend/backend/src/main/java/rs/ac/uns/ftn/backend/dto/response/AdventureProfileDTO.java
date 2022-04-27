package rs.ac.uns.ftn.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.uns.ftn.backend.model.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdventureProfileDTO {

    private Long id;

    private String name;

    private String address;

    private Long longitude;

    private Long latitude;

    private String description;

    private InstructorDTO instructor;

    private Integer maxNumPerson;

     private String ruleBehavior;

    private String moreInformation;

    private String cancellationConditions;

    private Set<AdventureImage> adventureImages;

    private Set<AdventurePricelist> adventurePricelists;

    private double averageMark;

    private Set<AdventureReservation> adventureReservations;

    private Set<AdventureAction> adventureActions;

}
