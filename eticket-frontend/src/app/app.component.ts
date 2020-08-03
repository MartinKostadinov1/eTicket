import { Component, OnInit } from '@angular/core';
import { AuthService } from './services/auth.service';
import { UserSerivce } from './services/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'eticket-frontend';

  constructor(private authService: AuthService, private userService: UserSerivce) {
  }

  async ngOnInit() {
    await this.authService.handleAuthentication();
    this.authService.getProfile((err, profile) => {
      if (profile != null)
      this.authService.userProfile = profile;
    });

    await (this.userService.getCurrentUser().toPromise())
  }
}
