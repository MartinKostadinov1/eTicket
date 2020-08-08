import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { IUserRegisterModel } from '../models/user/IUserRegisterModel';
import { IUserModel } from '../models/user/IUserModel';
import { of, Observable } from 'rxjs';
import 'rxjs/add/operator/do'; import 'rxjs/add/operator/catch';
import { AuthService } from './auth.service';
import { IUpdateUserModel } from '../models/user/IUpdateUserModel';




@Injectable({
    providedIn: 'root'
})
export class UserSerivce {

    private currentUser: IUserModel;

    get user() {
        return this.currentUser;
    }

    setUser(currentUser: IUserModel) {
        this.currentUser = currentUser;
    }


    constructor(private http: HttpClient, private router: Router, private authService: AuthService) { }

    handleError(error: any, type: string): Observable<void> {
        if (type == 'currentUser') {
            this.authService.logout();
        }

        return;
    }

    async validateUserExistance(): Promise<boolean> {
        let result = await (this.http.get(`/api/users/check`, { observe: 'response' }).toPromise()).catch(_ => null);

        if (result && result?.status == 200) {
            localStorage.setItem('user_exists', 'true')
            return true;
        }

        localStorage.setItem('user_exists', 'false')

        return false;
    }


    userExist() {
        return localStorage.getItem('user_exists') == 'true';
    }

    async register(userData: IUserRegisterModel): Promise<any> {
        let result = await (this.http.post<IUserModel>(`/api/users/register`, userData, { observe: 'response' }).toPromise()).catch(e => e);

        return result;
    }

    getCurrentUser(): Observable<IUserModel | void> {
        return this.currentUser
            ? of(this.currentUser) // wrap cached value for consistent return value
            : this.http.get<IUserModel>('/api/users/current')
                .do(data => { this.currentUser = data }) // cache it for next call
                .catch(error => this.handleError(error, 'currentUser'));
    }

    async updateProfile(userData: IUpdateUserModel) {
        let result = await this.http.put<IUserModel>('/api/users/profile', userData, { observe: 'response' }).toPromise().catch(e => e);
        if(result && result.status == 200) {
            this.setUser(result.body);
        }
        return result;
    }

    async updateProfilePicture(imageFile: File): Promise<IUserModel> {
        let formData: FormData = new FormData();
        formData.set("image", imageFile)
        let result = await this.http.put<IUserModel>('/api/users/profile/upload/profile-picture', formData).toPromise();

        return result;

    }

    async updateProfileBackgroundPicture(imageFile: File): Promise<IUserModel> {
        let formData: FormData = new FormData();
        formData.set("image", imageFile)
        let result = await this.http.put<IUserModel>('/api/users/profile/upload/profile-background-picture', formData).toPromise();

        return result;
    }



}