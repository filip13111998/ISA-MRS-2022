import { CottagePricelistDTO } from './cottage-pricelist';
export class CottageReservationHistoryDTO {
    id: number;

    reservationStart: Date;

    reservationEnd: Date;

    active: boolean;

    pricelistItem: CottagePricelistDTO;

    cottageID: number;
}