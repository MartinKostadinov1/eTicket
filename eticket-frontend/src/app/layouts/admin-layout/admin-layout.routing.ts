import { Routes } from '@angular/router';

import { DashboardComponent } from '../../pages/dashboard/dashboard.component';
import { IconsComponent } from '../../pages/icons/icons.component';
import { MapsComponent } from '../../pages/map/map.component';
import { UserProfileComponent } from '../../pages/user-profile/user-profile.component';
import { DocumentionComponent } from '../../pages/documentation/documention.component';
import { TicketsComponent } from '../../pages/tickets/tickets.component';
import { AuthGuard } from 'src/app/shared/guards/auth.guard';

export const AdminLayoutRoutes: Routes = [
    { path: 'dashboard',      component: DashboardComponent, canActivate: [AuthGuard] },
    { path: 'user-profile',   component: UserProfileComponent, canActivate: [AuthGuard] },
    { path: 'etickets',         component: TicketsComponent, canActivate: [AuthGuard] },
    { path: 'icons',          component: IconsComponent, canActivate: [AuthGuard] },
    { path: 'map',            component: MapsComponent, canActivate: [AuthGuard] },
    { path: 'documentation',  component: DocumentionComponent, canActivate: [AuthGuard] }
];
