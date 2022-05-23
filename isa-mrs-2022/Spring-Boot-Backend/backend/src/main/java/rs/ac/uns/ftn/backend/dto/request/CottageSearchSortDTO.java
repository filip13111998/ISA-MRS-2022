package rs.ac.uns.ftn.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CottageSearchSortDTO {

    private String name;

    private String address;

    private Long numberOfRoom;

    private Double averageMark;

    private LocalDate start;

    private LocalDate end;

}
