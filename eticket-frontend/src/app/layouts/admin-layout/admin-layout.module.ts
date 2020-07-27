import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ClipboardModule } from 'ngx-clipboard';

import { AdminLayoutRoutes } from './admin-layout.routing';
import { DashboardComponent } from '../../pages/dashboard/dashboard.component';
import { StatusComponent } from '../../pages/status/status.component';
import { IconsComponent } from '../../pages/icons/icons.component';
import { MapsComponent } from '../../pages/map/map.component';
import { UserProfileComponent } from '../../pages/user-profile/user-profile.component';
import { DocumentionComponent } from '../../pages/documentation/documention.component';
import { TablesComponent } from '../../pages/tables/tables.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AgmCoreModule } from '@agm/core';
import { environment } from 'src/environments/environment';
// import { ToastrModule } from 'ngx-toastr';


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    FormsModule,
    HttpClientModule,
    NgbModule,
    ClipboardModule,
    AgmCoreModule.forRoot({
      apiKey: environment.googleApiKey
    })
  ],
  declarations: [
    DashboardComponent,
    UserProfileComponent,
    DocumentionComponent,
    TablesComponent,
    IconsComponent,
    MapsComponent,
    StatusComponent
  ]
})

export class AdminLayoutModule {}
