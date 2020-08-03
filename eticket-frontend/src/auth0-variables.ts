interface AuthConfig {
    clientID: string;
    domain: string;
    callbackURL: string;
    apiUrl: string;
    redirectUrl: string;
  }
  
  export const AUTH_CONFIG: AuthConfig = {
    clientID: 'qvVTugtC1lJqUDpViaQ3ZfAdkMTqtExZ',
    domain: 'eticket.eu.auth0.com',
    callbackURL: 'http://localhost:4200/callback',
    apiUrl: '',
    redirectUrl: 'http://localhost:4200/'
  };