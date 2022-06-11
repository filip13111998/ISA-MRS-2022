import { AdventurePricelistDTO } from './adventure-pricelist';
export class AdventureReservationHistoryDTO {
    id: number;

    reservationStart: Date;

    reservationEnd: Date;

    price: number;

    active: boolean;

    pricelistItem: AdventurePricelistDTO;

    adventureID: number;
}