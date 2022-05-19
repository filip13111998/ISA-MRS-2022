import { SimpleActionDTO } from "../../util/SimpleActionDTO";
import { AdventureResevationDTO } from "./adventure-reservation";

export class AdventureReservationCalendarDTO {

    adventureResevations: AdventureResevationDTO[];

    adventureActionsReservated: SimpleActionDTO[];

    adventureActionsUnReservated: SimpleActionDTO[];

}