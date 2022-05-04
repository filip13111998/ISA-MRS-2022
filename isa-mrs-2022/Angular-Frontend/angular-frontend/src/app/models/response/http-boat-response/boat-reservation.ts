import { BoatPricelistDTO } from './boat-pricelist';


export class BoatResevationDTO {

    id: number;

    reservationStart: Date;

    reservationEnd: Date;

    pricelistItem: BoatPricelistDTO;

}