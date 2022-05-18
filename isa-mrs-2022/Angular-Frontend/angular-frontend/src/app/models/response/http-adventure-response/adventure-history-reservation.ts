import { AdventurePricelistDTO } from './adventure-pricelist';
export class AdventureReservationHistoryDTO {
    id: number;

    reservationStart: Date;

    reservationEnd: Date;

    active: boolean;

    pricelistItem: AdventurePricelistDTO;

    adventureID: number;
}