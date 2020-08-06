import { ITicketModel } from "./ITicketModel";

export interface IVehicleModel {

    id: String;

    registrationNumber: String;

    addedOn: Date;

    blocked: boolean;

    tickets: ITicketModel[];

}