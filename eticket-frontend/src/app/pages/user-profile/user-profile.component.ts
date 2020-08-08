import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { IUserModel } from 'src/app/models/user/IUserModel';
import { UserSerivce } from 'src/app/services/user.service';
import { AuthService } from 'src/app/services/auth.service';
import { IUpdateUserModel } from 'src/app/models/user/IUpdateUserModel';
import { Router } from '@angular/router';
import { DashboardService } from 'src/app/services/dashboard.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  constructor(private userService: UserSerivce, protected authService: AuthService, private router: Router, private dashboardService: DashboardService
  ) { }

  currentUser: Partial<IUserModel> = { authorities: null, authProviderUser: { username: '' } };

  get backgroundPicture() {
    return this.currentUser.profileBackgroundPictureUrl || 'assets/img/theme/profile-background-default.svg';
  }

  isLoading: boolean = true;


  rolesMapping: { [key: string]: string } = {
    "ROLE_ADMIN": "Administartor",
    "ROLE_USER": "User",
  }

  profileImageFile: File = null;

  ticketsCount: number = 0;
  vehiclesCount: number = 0;

  errors: string[] = []

  async ngOnInit() {
    this.currentUser = await this.userService.getCurrentUser().toPromise() || null;
    this.isLoading = false;

    let dashboradData = await this.dashboardService.getDashBoardStatusWidget();

    this.ticketsCount = dashboradData.allPaidTickets;
    this.vehiclesCount = dashboradData.vehicleCount;
  }

  private triggetValidations(errors: any[]) {
    if (errors) {
      for (const e of errors) {
        this.errors[e.field] = e.defaultMessage;
      }
    }
  }


  async update() {

    this.isLoading = true;

    let userToUpdate: IUpdateUserModel = {
      username: this.currentUser.authProviderUser.username,
      firstName: this.currentUser.firstName,
      lastName: this.currentUser.lastName,
      country: this.currentUser.country,
      city: this.currentUser.city,
      address: this.currentUser.address,
      postCode: this.currentUser.postCode,
      description: this.currentUser.description,
      phoneNumber: this.currentUser.phoneNumber
    }


    try {
      let result = await this.userService.updateProfile(userToUpdate);

      if (result.status && result.status != 200) {
        this.triggetValidations(result.errors)
      } else {
        this.currentUser = await this.userService.getCurrentUser().toPromise() || null;
      }
      this.isLoading = false;
    } catch (e) {
      this.isLoading = false;
      alert("Something went wrong. Please try again later")
    }

  }

  async importProfilePicture(event) {
    this.isLoading = true;

    if (event.target.files.length == 0) {
      alert("No file selected!");
      return
    }

    try {
      let updatedUser = await this.userService.updateProfilePicture(event.target.files[0]);
      this.currentUser = updatedUser;
      this.userService.setUser(updatedUser);
      this.isLoading = false;
      window.location.reload();
    } catch (e) {
      this.isLoading = false;
      alert("Something went wrong.  Please try again later")
    }


  }

  async importBackgroundPicture(event) {
    this.isLoading = true;

    if (event.target.files.length == 0) {
      alert("No file selected!");
      return
    }

    try {
      let updatedUser = await this.userService.updateProfileBackgroundPicture(event.target.files[0]);
      this.currentUser = updatedUser;
      this.userService.setUser(updatedUser);
      this.isLoading = false;
    } catch (e) {
      this.isLoading = false;
      alert("Something went wrong.  Please try again later")
    }

  }

}
