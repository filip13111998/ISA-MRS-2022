package rs.ac.uns.ftn.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarkAdminDTO {

    private Long id;

    private Long entityID;
//
//    private LocalDate date;
//
//    private Double mark;
}
