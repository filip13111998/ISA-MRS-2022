package rs.ac.uns.ftn.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoatSearchSortDTO {

    private String name;

    private String type;

    private Double lenghtFrom;

    private Double lenghtTo;

    private Integer engineNumFrom;

    private Integer engineNumTo;

    private Double enginePowerFrom;

    private Double enginePowerTo;

    private Double maxSpeedFrom;

    private Double maxSpeedTo;

    private String address;

    private Double averageMark;

}
