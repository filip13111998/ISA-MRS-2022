package rs.ac.uns.ftn.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdventureSearchSortDTO {

    private String name;

    private String address;

    private Integer maxNumPersonFrom;

    private Integer maxNumPersonTo;

    private Double averageMark;

}
