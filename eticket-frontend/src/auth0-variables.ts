interface AuthConfig {
    clientID: string;
    domain: string;
    callbackURL: string;
    apiUrl: string;
    redirectUrl: string;
  }
  
  export const AUTH_CONFIG: AuthConfig = {
    clientID: '',
    domain: '',
    callbackURL: '',
    apiUrl: '',
    redirectUrl: ''
  };