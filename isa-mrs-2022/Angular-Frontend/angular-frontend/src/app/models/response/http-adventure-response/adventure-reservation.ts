import { AdventurePricelistDTO } from './adventure-pricelist';

export class AdventureResevationDTO {

    id: number;

    reservationStart: Date;

    reservationEnd: Date;

    pricelistItem: AdventurePricelistDTO;

}