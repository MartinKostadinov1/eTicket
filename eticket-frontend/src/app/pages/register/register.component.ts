import { Component, OnInit, ViewChild, ElementRef, ChangeDetectorRef } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { UserSerivce } from 'src/app/services/user.service';
import { IUserRegisterModel } from 'src/app/models/user/IUserRegisterModel';
import { NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  constructor(public auth: AuthService, private userService: UserSerivce, private http: HttpClient, private router: Router, private cd: ChangeDetectorRef) { }

  async ngOnInit() {
  }

  countryList = ["Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Anguilla", "Antigua &amp; Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia &amp; Herzegovina", "Botswana", "Brazil", "British Virgin Islands", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Cape Verde", "Cayman Islands", "Chad", "Chile", "China", "Colombia", "Congo", "Cook Islands", "Costa Rica", "Cote D Ivoire", "Croatia", "Cruise Ship", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Estonia", "Ethiopia", "Falkland Islands", "Faroe Islands", "Fiji", "Finland", "France", "French Polynesia", "French West Indies", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guam", "Guatemala", "Guernsey", "Guinea", "Guinea Bissau", "Guyana", "Haiti", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Isle of Man", "Israel", "Italy", "Jamaica", "Japan", "Jersey", "Jordan", "Kazakhstan", "Kenya", "Kuwait", "Kyrgyz Republic", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Mauritania", "Mauritius", "Mexico", "Moldova", "Monaco", "Mongolia", "Montenegro", "Montserrat", "Morocco", "Mozambique", "Namibia", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Norway", "Oman", "Pakistan", "Palestine", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russia", "Rwanda", "Saint Pierre &amp; Miquelon", "Samoa", "San Marino", "Satellite", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "South Africa", "South Korea", "Spain", "Sri Lanka", "St Kitts &amp; Nevis", "St Lucia", "St Vincent", "St. Lucia", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Timor L'Este", "Togo", "Tonga", "Trinidad &amp; Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks &amp; Caicos", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "Uruguay", "Uzbekistan", "Venezuela", "Vietnam", "Virgin Islands (US)", "Yemen", "Zambia", "Zimbabwe"];

  userData: IUserRegisterModel = {
    driverLicenseId: null,

    firstName: null,

    lastName: null,

    dateBorn: null,

    phoneNumber: null,

    description: null,

    country: null,

    city: null,

    postCode: null,

    address: null
  };


  dateBornPick: any = null;

  error: string;

  errors: any = {};

  isLoading = false;


  agreeWithTermsOfUse: boolean = false;

  private triggetValidations(errors: any[]) {
    if (errors) {
      for (const e of errors) {
        this.errors[e.field] = e.defaultMessage;
      }
    }



  }

  async onSubmit(form: NgForm) {
    if (this.agreeWithTermsOfUse) {
      this.errors = {};
      try {
        this.isLoading = true;

        let tempDate = new Date();
        tempDate.setFullYear(this.dateBornPick.year);
        tempDate.setMonth(this.dateBornPick.month);
        tempDate.setDate(this.dateBornPick.day);

        this.userData.dateBorn = tempDate.toISOString().
          replace(/\..+/, '');

        let result = await this.userService.register(this.userData);

        if (result.status && result.status != 201) {
          this.triggetValidations(result.errors)
        } else {
          this.router.navigate(['/'])
        }

        this.isLoading = false;
      } catch (error) {
        this.error = error.message;
        this.isLoading = false;
      }
    } else {
      this.error = 'Please agree with the tearms of use';
    }

  }

  acceptTermsOfUse() {
    if (this.agreeWithTermsOfUse) {
      this.error = '';
    } else {
      this.error = 'Please agree with the tearms of use';
    }
  }

}











// for (const key in form.controls) {
//   console.log(key)
//   if (key == 'driverLicenseId') {
//     if (form.controls[key].value && form.controls[key].value.lenght != 10) {
//       this.errors[key] = this.prebuildErrors[key][1];
//     } else {
//       this.errors[key] = this.prebuildErrors[key][0];
//     }
//   } else if (key == 'phoneNumber') {
//     if (form.controls[key].value && form.controls[key].value.lenght < 5) {
//       this.errors[key] = this.prebuildErrors[key][1];
//     } else {
//       this.errors[key] = this.prebuildErrors[key][0];
//     }
//   } else if (key == 'country') {
//     if (!form.controls[key].value) {
//       this.errors[key] = this.prebuildErrors[key][0];
//     }
//   } else if (key == 'city') {
//     if (form.controls[key].value && form.controls[key].value.lenght < 2 || form.controls[key].value.lenght > 20) {
//       this.errors[key] = this.prebuildErrors[key][1];
//     } else {
//       this.errors[key] = this.prebuildErrors[key][0];
//     }
//   } else if (key == 'postCode') {
//     if (form.controls[key].value && form.controls[key].value.lenght < 4 || form.controls[key].value.lenght > 6) {
//       this.errors[key] = this.prebuildErrors[key][1];
//     } else {
//       this.errors[key] = this.prebuildErrors[key][0];
//     }
//   } else if (key == 'address') {
//     if (form.controls[key].value && form.controls[key].value.lenght < 3 || form.controls[key].value.lenght > 30) {
//       this.errors[key] = this.prebuildErrors[key][1];
//     } else {
//       this.errors[key] = this.prebuildErrors[key][0];
//     }
//   } else if (key == 'description') {
//     continue;
//   } else {
//     if (!form.controls[key].value || form.controls[key].value == '') {
//       this.errors[key] = this.prebuildErrors[key][0];
//     } else if (form.controls[key].value.lenght < 2 || form.controls[key].value.lenght > 20) {
//       this.errors[key] = this.prebuildErrors[key][1];
//     }
//   }



//   console.log(this.errors);

// prebuildErrors: any = {
//   driverLicenseId: ['Driver license ID is required!', 'Driver License ID must be exactly 10 numbers'],

//   firstName: ['First names required!', 'First name must be between 2 and 20 characters'],

//   surName: ['Sur name is required!', 'Sur name must be between 2 and 20 characters'],

//   familyName: ['Family name is required!', 'Family name must be between 2 and 20 characters'],

//   phoneNumber: ['Phone number is required!', 'Phone number must be at between 5 and 12 digits'],

//   country: ['Country is required!', 'Country name must be at between 2 and 20 character'],

//   city: ['City is required!', 'City name must be at between 2 and 20 character'],

//   postCode: ['Post code is required!', 'Post code must be at between 4 and 6 digits'],

//   address: ['Address is required!', 'Address must be at between 2 and 30 character'],

//   description: [],
// };