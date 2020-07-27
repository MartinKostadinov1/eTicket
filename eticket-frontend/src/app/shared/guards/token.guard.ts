import { Injectable } from '@angular/core';

import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { HttpClient } from '@angular/common/http';
import { CookieService } from 'src/app/services/cookie.service';


@Injectable()
export class TokenGuard implements CanActivate {

    constructor(private authService: AuthService, private http: HttpClient, private cookieService: CookieService, private router: Router) {
    }

    async canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<boolean> {

        if (this.cookieService.getCookie("Authorization") || localStorage.getItem("Authorization")) {
            try {
                let response = await (this.http.get<any>("/api/token/valid", { observe: 'response' }).toPromise());

                if (response.status != 200) {
                    this.earaseData()
                }
            } catch (_) {
                this.earaseData()
            }

        }

        return true;
    }

    private earaseData() {
        this.cookieService.deleteCookie("Authorization");
        localStorage.removeItem("Authorization");
        this.router.navigate(['/']);
    }

}
