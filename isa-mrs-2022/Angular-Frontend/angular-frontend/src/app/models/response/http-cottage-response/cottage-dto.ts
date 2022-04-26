import { CottageImageDTO } from './cottage-image-dto';
export interface CottageDTO {

    id: number;

    name: string;

    cm: CottageImageDTO;

    address: string;

    bedNums: number;

    averageMark: number;

}
