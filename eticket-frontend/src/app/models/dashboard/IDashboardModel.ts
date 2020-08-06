import { IVehicleModel } from "./IVehicleModel";

export interface IDashboardModel {

    driverLicenseId: String;

    authProviderId: String;

    email: String;

    vehicles: Set<IVehicleModel>;
}