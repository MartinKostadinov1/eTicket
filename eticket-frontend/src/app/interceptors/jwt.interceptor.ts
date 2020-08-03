
import { throwError as observableThrowError, Observable } from 'rxjs';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

import { tap, catchError } from 'rxjs/operators'
import { CookieService } from '../services/cookie.service';
import { environment } from 'src/environments/environment';


@Injectable()
export class JwtHttpInterceptor implements HttpInterceptor {
    constructor(private router: Router, private cookieService: CookieService) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let authorization = localStorage.getItem('id_token');

        let clone: HttpRequest<any>;
        if (authorization) {
            clone = request.clone({
                setHeaders: {
                    "Accept": `application/json`,
                    "Authorization": `Bearer ${authorization}`,
                    "AccessToken": `Bearer ${environment.backendAccessToken}`
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