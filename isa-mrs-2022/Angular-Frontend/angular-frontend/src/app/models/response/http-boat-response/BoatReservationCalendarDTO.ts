import { SimpleActionDTO } from "../../util/SimpleActionDTO";
import { BoatResevationDTO } from "./boat-reservation";

export class BoatReservationCalendarDTO {

    boatResevations: BoatResevationDTO[];

    boatActionsReservated: SimpleActionDTO[];

    boatActionsUnReservated: SimpleActionDTO[];

}