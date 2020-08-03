import IAuthProviderUserModel from "./IAuthProviderUserModel";
import IAuthorityModel from "./IAuthorityModel";


export interface IUserModel {
    id: string;

    authProviderId: string;

    driverLicenseId: string;

    firstName: string;

    lastName: string;

    dateBorn: Date;

    phoneNumber: string;

    email: string;

    description: string;

    country: string;

    city: string;

    postCode: string;
    
    address: string;

    authProviderUser: Partial<IAuthProviderUserModel>;

    authorities: Set<IAuthorityModel>;

    profilePictureUrl: string;

    profileBackgroundPictureUrl: string;
}