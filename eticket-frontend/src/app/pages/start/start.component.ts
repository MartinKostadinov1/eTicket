import { Component, OnInit, OnDestroy, OnChanges } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { environment } from 'src/environments/environment';
import { LocalStorageService } from 'src/app/services/local-storage.service';
import { UserSerivce } from 'src/app/services/user.service';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Router } from '@angular/router';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.scss']
})
export class StartComponent implements OnInit, OnDestroy, OnChanges {

  constructor(public authService: AuthService, private localStorageService: LocalStorageService, private userService: UserSerivce, private router: Router) { }

  get isExistingUser() {
    return this.userService.userExist()
  }


  async ngOnInit() {
    await this.userService.validateUserExistance();
  }


  async ngOnChanges() {
    await this.userService.validateUserExistance();
  }


  ngOnDestroy() {
  }

  isAuthenticated() {
    return this.authService.isAuthenticated();
  }


  get urs() {
    return { backendUrl: environment.backendUrl, url: environment.url };
  }


  toggleRedirect() {
    if (this.isAuthenticated() && this.isExistingUser) {
      this.localStorageService.setValue("redirect_from_start_page", true);
    }
  }

  redirectTo() {
    if (!this.isAuthenticated()) {
      this.authService.login()
    } else if (this.isAuthenticated() && this.isExistingUser) {
      this.router.navigate(['/dashboard']);
    } else {
      this.router.navigate(['/register']);
    }
  }
}
