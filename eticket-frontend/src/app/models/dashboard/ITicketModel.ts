export interface ITicketModel {

    id: string;

    locationName: string;

    locationCoordinates: string;

    createdOn: Date;

    paidOn: Date;

    isPaid: boolean;

    isDeleted: boolean;

    amount: number;

    ticketType: string;

    description: string;

    vehicleRegistrationNumber: string;

}