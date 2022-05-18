import { SimpleActionDTO } from './../../util/SimpleActionDTO';
import { CottageResevationDTO } from "./cottage-reservation";

export class CottageReservationCalendarDTO {

    cottageResevations: CottageResevationDTO[];

    cottageActionsReservated: SimpleActionDTO[];

    cottageActionsUnReservated: SimpleActionDTO[];

}