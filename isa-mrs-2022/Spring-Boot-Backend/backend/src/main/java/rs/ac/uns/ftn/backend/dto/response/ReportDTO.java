package rs.ac.uns.ftn.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO {

    private Long numberCottageReservation;

    private Long numberBoatReservation;

    private Long numberAdventureReservation;

    private Long priceCottageReservation;

    private Long priceBoatReservation;

    private Long priceAdventureReservation;

    private Long sum;

    private Long appProfit;

    private Long finishSum;

}
