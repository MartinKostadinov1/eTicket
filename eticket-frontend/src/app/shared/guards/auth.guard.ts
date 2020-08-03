import { AuthService } from '../../services/auth.service';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, CanActivate, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { UserSerivce } from 'src/app/services/user.service';


@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private auth: AuthService, private userService: UserSerivce, private router: Router) {
  }

  async canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<boolean> {
    if (this.auth.isAuthenticated()) {
      let exist = localStorage.getItem('user_exists') == 'true' || await this.userService.validateUserExistance();
      if (exist) {
        localStorage.setItem('user_exists', 'true')
        return exist;
      } else {
        this.router.navigate(['/register']);
      }
    } else {
      this.router.navigate(['/']);
    }


  }

}
