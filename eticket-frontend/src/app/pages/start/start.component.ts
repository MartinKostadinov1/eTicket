import { Component, OnInit, OnDestroy } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { environment } from 'src/environments/environment';
import { LocalStorageService } from 'src/app/services/local-storage.service';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.scss']
})
export class StartComponent implements OnInit, OnDestroy {
  constructor(private authService: AuthService, private localStorageService: LocalStorageService) {}

  ngOnInit() {
  }
  ngOnDestroy() {
  }

  isAuthenticated() {
    return this.authService.isAuthenticated();
  }

  get principalUsername() {
    return this.authService.username;
  }

  get urs() {
    return { backendUrl: environment.backendUrl, url: environment.url };
  }

  toogleRedirect() {
    if(this.isAuthenticated()) {
      this.localStorageService.setValue("redirectFromStartPage", true);
    }
  }
}
