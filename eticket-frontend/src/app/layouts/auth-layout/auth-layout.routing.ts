import { Routes } from '@angular/router';

import { StartComponent } from '../../pages/start/start.component';
import { TokenGuard } from 'src/app/shared/guards/token.guard';

export const AuthLayoutRoutes: Routes = [
    { path: '', component: StartComponent, canActivate: [TokenGuard] },
];
