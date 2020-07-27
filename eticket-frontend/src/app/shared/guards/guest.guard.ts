import {Injectable} from '@angular/core';

import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {AuthService} from '../../services/auth.service';
import {Observable} from 'rxjs/index';
import { environment } from 'src/environments/environment';
import { StartComponent } from 'src/app/pages/start/start.component';
import { LocalStorageService } from 'src/app/services/local-storage.service';


@Injectable()
export class GuestGuard implements CanActivate {

  constructor(private auth: AuthService, private router: Router, private localStorageService: LocalStorageService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | Observable<boolean> | Promise<boolean> {
    if (!this.auth.isAuthenticated()) {
      return true;
    } else {
      if(this.localStorageService.retreiveBooleanValue("redirectFromStartPage")) {
        this.router.navigate(['/dashboard']);
      }else{
        return true
      }
      
    }


  }

}
