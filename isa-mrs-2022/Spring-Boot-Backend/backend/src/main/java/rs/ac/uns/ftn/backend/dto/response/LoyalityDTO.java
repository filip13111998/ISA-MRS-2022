package rs.ac.uns.ftn.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoyalityDTO {
    public  Long appProfit ;

    public  Long silverPercent ;

    public  Long goldPercent;

    public  Long silverPoints;

    public  Long goldPoints;
}
