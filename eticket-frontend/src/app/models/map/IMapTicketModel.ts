import { ITicketModel } from "../dashboard/ITicketModel";

export interface IMapTicketModel {
    id: string;
    lat: number; 
    lng: number;
    ticket: ITicketModel;
}