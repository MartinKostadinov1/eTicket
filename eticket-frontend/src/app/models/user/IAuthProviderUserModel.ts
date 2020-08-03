export default interface IAuthProviderUserModel {
    authId: string;
    createdAt: Date;
    lastLogin: Date;
    updatedAt: Date;
    lastIp: string;
    loginsCount: number;
    username: string;
    picture: string;
    email: string;
}