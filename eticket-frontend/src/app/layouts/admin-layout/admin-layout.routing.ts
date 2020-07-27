import { Routes } from '@angular/router';

import { DashboardComponent } from '../../pages/dashboard/dashboard.component';
import { IconsComponent } from '../../pages/icons/icons.component';
import { MapsComponent } from '../../pages/map/map.component';
import { UserProfileComponent } from '../../pages/user-profile/user-profile.component';
import { DocumentionComponent } from '../../pages/documentation/documention.component';
import { TablesComponent } from '../../pages/tables/tables.component';
import { TokenGuard } from 'src/app/shared/guards/token.guard';

export const AdminLayoutRoutes: Routes = [
    { path: 'dashboard',      component: DashboardComponent, canActivate: [TokenGuard] },
    { path: 'user-profile',   component: UserProfileComponent, canActivate: [TokenGuard] },
    { path: 'tables',         component: TablesComponent, canActivate: [TokenGuard] },
    { path: 'icons',          component: IconsComponent, canActivate: [TokenGuard] },
    { path: 'map',            component: MapsComponent, canActivate: [TokenGuard] },
    { path: 'documentation',  component: DocumentionComponent, canActivate: [TokenGuard] }
];
