import { BoatActionDTO } from './boat-action';
import { BoatResevationDTO } from './boat-reservation';
import { BoatPricelistDTO } from './boat-pricelist';
import { BoatImageDTO } from './boat-image-dto';

export class BoatProfileDTO {

    id: number;
    name: string;
    type: string;
    lenght: number;
    engineNum: number;
    enginePower: number;
    maxSpeed: number;
    address: string;
    longitude: number;
    latitude: number;
    capacity: string;
    description: string;
    navigation: string;
    ruleBehavior: string;
    fishingEquipment: string;
    moreInformation: string;
    boatImages: BoatImageDTO[];
    boatPricelists: BoatPricelistDTO[];
    averageMark: number;
    boatResevations: BoatResevationDTO[];
    boatActions: BoatActionDTO[];
}