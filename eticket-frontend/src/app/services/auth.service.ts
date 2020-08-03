import { Injectable } from '@angular/core';
import { AUTH_CONFIG } from '../../auth0-variables';
import { Router } from '@angular/router';
import auth0 from 'auth0-js';
import { UserSerivce } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  userProfile: any;
  requestedScopes: string = 'openid profile read:timesheets create:timesheets';

  auth0 = new auth0.WebAuth({
    clientID: AUTH_CONFIG.clientID,
    domain: AUTH_CONFIG.domain,
    responseType: 'token id_token',
    audience: AUTH_CONFIG.apiUrl,
    redirectUri: AUTH_CONFIG.callbackURL,
    scope: this.requestedScopes
  });

  constructor(public router: Router) { }

  public login(): void {
    this.auth0.authorize();
  }

  public async handleAuthentication(): Promise<void> {
    this.auth0.parseHash((err, authResult) => {
      if (authResult && authResult.accessToken && authResult.idToken) {
        window.location.hash = '';
        this.setSession(authResult);
        this.getProfile((err, profile) => {
          if (profile != null)
            this.userProfile = profile;
        });
        this.router.navigate(['/register']);
        
      } else if (err) {
        this.router.navigate(['/']);
        alert('Error: ${err.error}. Check the console for further details.');
      }
    });
  }

  private setSession(authResult): void {
    // Set the time that the Access Token will expire at
    const expiresAt = JSON.stringify((authResult.expiresIn * 1000) + new Date().getTime());

    // If there is a value on the scope param from the authResult,
    // use it to set scopes in the session for the user. Otherwise
    // use the scopes as requested. If no scopes were requested,
    // set it to nothing
    const scopes = authResult.scope || this.requestedScopes || '';

    localStorage.setItem('access_token', authResult.accessToken);
    localStorage.setItem('id_token', authResult.idToken);
    localStorage.setItem('expires_at', expiresAt);
    localStorage.setItem('scopes', JSON.stringify(scopes));
  }

  public changePassword() {
    this.auth0.changePassword({
      connection: 'Username-Password-Authentication',
      email: this.userProfile.name
    }, function (err, resp) {
      if (err) {
        return;
      } else {
        alert(resp);
      }
    });
  }

  public logout(): void {
    this.auth0.logout({
      returnTo: AUTH_CONFIG.redirectUrl,
      clientID: AUTH_CONFIG.clientID
    });
    // Remove tokens and expiry time from localStorage
    localStorage.removeItem('access_token');
    localStorage.removeItem('id_token');
    localStorage.removeItem('expires_at');
    localStorage.removeItem('scopes');
    localStorage.removeItem('redirect_from_start_page');
    localStorage.removeItem('user_exists');
    // Go back to the home route
    this.router.navigate(['/']);
  }

  public isAuthenticated(): boolean {
    // Check whether the current time is past the
    // Access Token's expiry time
    const expiresAt = JSON.parse(localStorage.getItem('expires_at'));
    return new Date().getTime() < expiresAt;
  }

  public userHasScopes(scopes: Array<string>): boolean {
    const grantedScopes = JSON.parse(localStorage.getItem('scopes')).split(' ');
    return scopes.every(scope => grantedScopes.includes(scope));
  }

  public getProfile(cb): void {
    const accessToken = localStorage.getItem('access_token');
    if (!accessToken) {
      throw new Error('Access Token must exist to fetch profile');
    }

    const self = this;
    this.auth0.client.userInfo(accessToken, (err, profile) => {
      if (profile) {
        self.userProfile = profile;
      }
      cb(err, profile);
    });
  }

  get userName() {
    if (this.userProfile) {
      return this.userProfile.nickname;
    }
  }

}