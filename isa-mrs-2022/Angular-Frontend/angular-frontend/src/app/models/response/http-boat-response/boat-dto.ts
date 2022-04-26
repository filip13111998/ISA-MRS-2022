import { BoatImageDTO } from "./boat-image-dto";

export interface BoatDTO {
    id: number;

    name: string;

    bi: BoatImageDTO;

    address: string;

    type: number;

    averageMark: number;
}
