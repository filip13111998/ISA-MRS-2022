import { CottagePricelistDTO } from "./cottage-pricelist";

export class CottageResevationDTO {

    id: number;

    reservationStart: Date;

    reservationEnd: Date;

    pricelistItem: CottagePricelistDTO;

}