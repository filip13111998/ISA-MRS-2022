package rs.ac.uns.ftn.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ValidationAccountTokenDTO {

    private String token;

    private Boolean activate;

}
