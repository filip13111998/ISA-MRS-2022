import { AdventureActionDTO } from './adventure-action';
import { AdventureResevationDTO } from './adventure-reservation';
import { AdventurePricelistDTO } from './adventure-pricelist';
import { AdventureImageDTO } from './adventure-image-dto';
import { InstructorDTO } from '../http-instructore-response/instructor';

export class AdventureProfileDTO {

    id: number;
    name: string;
    address: string;
    longitude: number;
    latitude: number;
    description: string;
    instructor: InstructorDTO;
    maxNumPerson: number;
    ruleBehavior: string;
    moreInformation: string;
    cancellationConditions: string;
    adventureImages: AdventureImageDTO[];
    adventurePricelists: AdventurePricelistDTO[];
    averageMark: number;
    adventureResevations: AdventureResevationDTO[];
    adventureActions: AdventureActionDTO[];
}