import { AdventureImageDTO } from './adventure-image-dto';
export interface AdventureDTO {
    id: number;

    name: string;

    ai: AdventureImageDTO;

    address: string;

    maxNumPerson: number;

    averageMark: number;
}
