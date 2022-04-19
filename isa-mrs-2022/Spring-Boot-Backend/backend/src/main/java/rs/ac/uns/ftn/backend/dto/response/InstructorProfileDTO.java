package rs.ac.uns.ftn.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.uns.ftn.backend.model.AdventureAction;
import rs.ac.uns.ftn.backend.model.AdventureReservation;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstructorProfileDTO {
    private Long id;

    private String name;

    private String email;

    private String addressInstructor;

    private String description;

    private double averageMark;

    private Set<AdventureReservation> adventureReservations;

    private Set<AdventureAction> adventureActions;

}
