import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {
  }


  isAuthenticated() {
    return localStorage.getItem('Authorization') != null;
  }

  get username() {
    return "Martin";
  }

  async logout() {
    await localStorage.removeItem('Authorization');
  }

  parseJwt(token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    return JSON.parse(window.atob(base64));
  };

  async checkTokenValidity() {
    return this.http.get("/api/token/valid").toPromise();
  }

}
