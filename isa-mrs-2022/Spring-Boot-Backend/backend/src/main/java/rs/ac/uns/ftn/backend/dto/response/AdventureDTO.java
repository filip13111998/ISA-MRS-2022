package rs.ac.uns.ftn.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.uns.ftn.backend.model.AdventureImage;
import rs.ac.uns.ftn.backend.model.BoatImage;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdventureDTO {

    private Long id;

    private String name;

    private AdventureImage bi;

    private String address;

    private String maxNumPerson;

    private double averageMark;
}
