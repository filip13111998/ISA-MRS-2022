package rs.ac.uns.ftn.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.uns.ftn.backend.model.CottageImage;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CottageDTO {

    private Long id;

    private String name;

    private CottageImage cm;

    private String address;

    private Integer bedNums;

    private double averageMark;

}
