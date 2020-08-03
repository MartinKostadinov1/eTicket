import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { AuthService } from 'src/app/services/auth.service';
import { LocalStorageService } from 'src/app/services/local-storage.service';
import { IUserModel } from 'src/app/models/user/IUserModel';
import { UserSerivce } from 'src/app/services/user.service';

declare interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}
export const ROUTES: RouteInfo[] = [
    { path: '/dashboard', title: 'eDashboard',  icon: 'ni-active-40 text-primary', class: '' },
    // { path: '/icons', title: 'Icons',  icon:'ni-planet text-blue', class: '' },
    { path: '/map', title: 'eMap',  icon:'ni-pin-3 text-orange', class: '' },
    { path: '/user-profile', title: 'My profile',  icon:'ni-single-02 text-yellow', class: '' },
    // { path: '/tables', title: 'Tables',  icon:'ni-bullet-list-67 text-red', class: '' },
    // { path: '/login', title: 'Login',  icon:'ni-key-25 text-info', class: '' },
    // { path: '/register', title: 'Register',  icon:'ni-circle-08 text-pink', class: '' }
];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  public menuItems: any[];
  public isCollapsed = true;
  currentUser: Partial<IUserModel> = { authorities: null, authProviderUser: { username: '' } };

  constructor(private router: Router, public authService: AuthService, private localStorageService: LocalStorageService,  private userService: UserSerivce) { }

  async ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
    this.router.events.subscribe((event) => {
      this.isCollapsed = true;
   });
   this.currentUser = await this.userService.getCurrentUser().toPromise() || null;
  }



  goToStart() {
    this.localStorageService.setValue("redirect_from_start_page", false);
  }
}
