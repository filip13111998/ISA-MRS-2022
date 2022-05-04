import { CottagePricelistDTO } from './cottage-pricelist';
import { CottageImageDTO } from './cottage-image-dto';
import { CottageResevationDTO } from './cottage-reservation';
import { CottageActionDTO } from './cottage-action';
export class CottageProfileDTO {

    id: number;
    name: string;
    address: string;
    longitude: number;
    latitude: number;
    description: string;
    numberOfRoom: number;
    numberOfBedPerRoom: number;
    ruleBehavior: string;
    moreInformation: string;
    cottageImages: CottageImageDTO[];
    cottagePricelists: CottagePricelistDTO[];
    averageMark: number;
    cottageResevations: CottageResevationDTO[];
    cottageActions: CottageActionDTO[];
}