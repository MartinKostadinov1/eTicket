import { ITicketModel } from "../dashboard/ITicketModel";

export interface IMapTicketModel {
    lat: number; 
    lng: number;
    ticket: ITicketModel;
}