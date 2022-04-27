package rs.ac.uns.ftn.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.uns.ftn.backend.model.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoatProfileDTO {

    private Long id;

    private String name;

    private String type;

    private Double lenght;

    private Integer engineNum;

    private Double enginePower;

    private Double maxSpeed;

    private String address;

    private Long longitude;

    private Long latitude;

    private String capacity;

    private String description;

    private String navigation;

    private String ruleBehavior;

    private String fishingEquipment;

    private String moreInformation;

    private Set<BoatImage> boatImages;

    private Set<BoatPricelist> boatPricelists;

    private double averageMark;

    private Set<BoatResevation> boatResevations;

    private Set<BoatAction> boatActions;

}
