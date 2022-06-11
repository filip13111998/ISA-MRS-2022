import { CottagePricelistDTO } from './cottage-pricelist';
export class CottageReservationHistoryDTO {
    id: number;

    reservationStart: Date;

    reservationEnd: Date;

    active: boolean;

    price: number;

    pricelistItem: CottagePricelistDTO;

    cottageID: number;
}