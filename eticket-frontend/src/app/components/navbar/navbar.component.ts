import { Component, OnInit, ElementRef } from '@angular/core';
import { ROUTES } from '../sidebar/sidebar.component';
import { Location, LocationStrategy, PathLocationStrategy } from '@angular/common';
import { Router } from '@angular/router';
import { LocalStorageService } from 'src/app/services/local-storage.service';
import { AuthService } from 'src/app/services/auth.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  public focus;
  public listTitles: any[];
  public location: Location;
  constructor(location: Location,  private element: ElementRef, private router: Router, private localStorageService: LocalStorageService, private authService: AuthService) {
    this.location = location;
  }

  ngOnInit() {
    this.listTitles = ROUTES.filter(listTitle => listTitle);
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
        
        if(this.listTitles[item].path === title){
            return this.listTitles[item].title;
        }
    }
    return 'E-Dashboard';
  }

  goToStart() {
    this.localStorageService.setValue("redirectFromStartPage", false);
  }

  get backendUrl() {
    return environment.backendUrl;
  }

  doLogout() {
    this.authService.logout()
  }

}
