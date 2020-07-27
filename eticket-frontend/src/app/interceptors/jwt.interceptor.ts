
import { throwError as observableThrowError, Observable } from 'rxjs';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

import { tap, catchError } from 'rxjs/operators'
import { CookieService } from '../services/cookie.service';


@Injectable()
export class JwtHttpInterceptor implements HttpInterceptor {
    constructor(private router: Router, private cookieService: CookieService) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let authorization = this.cookieService.getCookie('Authorization');
        this.cookieService.deleteCookie('Authorization');

        console.log(authorization)
        if(authorization) {
            localStorage.setItem('Authorization', authorization);
        }

        // const cookie = `ajs_group_id=${this.cookieService.getCookie("ajs_group_id")}; ajs_anonymous_id=${this.cookieService.getCookie("ajs_anonymous_id")}; JSESSIONID=${localStorage.getItem('JSESSIONID')};`
        authorization = localStorage.getItem('Authorization')
        let clone: HttpRequest<any>;
        if (authorization) {
            clone = request.clone({
                setHeaders: {
                    "Accept": `application/json`,
                    "Authorization": `Bearer ${authorization}`
                }
            });
        } else {
            clone = request;
        }
        return next.handle(clone).pipe(tap((event: HttpEvent<any>) => { }, (response: any) => {
            if (response instanceof HttpErrorResponse) {
                if (response.status == 401) {
                    sessionStorage.clear();
                    this.router.navigateByUrl('/');
                }
            }
        }), catchError(response => {
            if (response.error)
                return observableThrowError(response.error);
            else
                return observableThrowError(response);
        }));
    }
    
}