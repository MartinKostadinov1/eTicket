import { Routes } from '@angular/router';

import { StartComponent } from '../../pages/start/start.component';
import { GuestGuard } from 'src/app/shared/guards/guest.guard';
import { AuthGuard } from 'src/app/shared/guards/auth.guard';
import { RegisterComponent } from 'src/app/pages/register/register.component';

export const AuthLayoutRoutes: Routes = [
    { path: '', component: StartComponent, canActivate: [GuestGuard] },
    { path: 'register', component: RegisterComponent, canActivate: [GuestGuard] }
];
