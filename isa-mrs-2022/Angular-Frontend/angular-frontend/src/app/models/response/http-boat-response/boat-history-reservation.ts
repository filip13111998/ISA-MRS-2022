import { BoatPricelistDTO } from './boat-pricelist';
export class BoatReservationHistoryDTO {
    id: number;

    reservationStart: Date;

    reservationEnd: Date;

    price: number;

    active: boolean;

    pricelistItem: BoatPricelistDTO;

    boatID: number;
}