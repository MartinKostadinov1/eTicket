import { Component, OnInit, ElementRef, OnChanges, SimpleChanges, ChangeDetectorRef } from '@angular/core';
import { ROUTES } from '../sidebar/sidebar.component';
import { Location, LocationStrategy, PathLocationStrategy } from '@angular/common';
import { Router } from '@angular/router';
import { LocalStorageService } from 'src/app/services/local-storage.service';
import { AuthService } from 'src/app/services/auth.service';
import { environment } from 'src/environments/environment';
import { IUserModel } from 'src/app/models/user/IUserModel';
import { UserSerivce } from 'src/app/services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  public focus;
  public listTitles: any[];
  public location: Location;
  currentUser: Partial<IUserModel> = { authorities: null, authProviderUser: { username: '' } };
  
  constructor(location: Location,  private element: ElementRef, private localStorageService: LocalStorageService, public authService: AuthService, private userService: UserSerivce) {
    this.location = location;
  }

  async ngOnInit() {
    this.listTitles = ROUTES.filter(listTitle => listTitle);
    this.currentUser = await this.userService.getCurrentUser().toPromise() || null;
  
  }



  getTitle(){
    var title = this.location.prepareExternalUrl(this.location.path());
    if(title.charAt(0) === '#'){
      title = title.slice( 1 );
    }

    for(var item = 0; item < this.listTitles.length; item++){
        if(title == '/dashboard') {
          return 'E-Dashboard';
        }

        if(title == '/map') {
          return 'E-Map';
        }

        if(title == '/etickets') {
          return 'E-Tickets';
        }
        
        if(this.listTitles[item].path === title){
            return this.listTitles[item].title;
        }
    }
    return 'E-Dashboard';
  }

  goToStart() {
    this.localStorageService.setValue("redirect_from_start_page", false);
  }

  get backendUrl() {
    return environment.backendUrl;
  }

}
