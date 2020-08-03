import { Injectable } from '@angular/core';

import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { Observable } from 'rxjs/index';
import { environment } from 'src/environments/environment';
import { StartComponent } from 'src/app/pages/start/start.component';
import { LocalStorageService } from 'src/app/services/local-storage.service';
import { UserSerivce } from 'src/app/services/user.service';


@Injectable({
  providedIn: 'root'
})
export class GuestGuard implements CanActivate {

  constructor(private auth: AuthService, private router: Router, private localStorageService: LocalStorageService, private userService: UserSerivce) {
  }

  async canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<boolean> {
    if (!this.auth.isAuthenticated()) {
      if (state.url == '/register') {
        this.auth.login()
      }else{
        return true;
      }
    } else {
      if (state.url == '/register') {
        let exist = localStorage.getItem('user_exists') == 'true' || await this.userService.validateUserExistance();
        if (exist) {
          localStorage.setItem('user_exists', 'true');
          if (this.localStorageService.retreiveBooleanValue("redirect_from_start_page")) {
            this.router.navigate(['/dashboard']);
          } else {
            this.router.navigate(['/']);
          }
        } else {
          return true;
        }
      } else {
        if (this.localStorageService.retreiveBooleanValue("redirect_from_start_page")) {
          this.router.navigate(['/dashboard']);
        } else {
          return true;
        }
      }

    }

  }



}
