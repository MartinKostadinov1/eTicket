import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';
import { DashboardService } from 'src/app/services/dashboard.service';
import { IMapTicketModel } from 'src/app/models/map/IMapTicketModel';
declare const google: any;

@Component({
  selector: 'app-maps',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})
export class MapsComponent implements OnInit {

  constructor(private dashboradService: DashboardService) { }


  public markers: IMapTicketModel[] = [];

  lat: number = 51.247680;
  lng: number = 22.561402780001;
  zoom: number = 15;

  async ngOnInit() {
    let userCurrentLocation = await this.getPosition();
    this.lat = userCurrentLocation.lat
    this.lng = userCurrentLocation.lng

    this.markers = await this.dashboradService.getAllTickets()

  }

  get appUrl() {
    return environment.url;
  }

  async getPosition(): Promise<any> {
    return new Promise((resolve, reject) => {

      navigator.geolocation.getCurrentPosition(resp => {

        resolve({ lng: resp.coords.longitude, lat: resp.coords.latitude });
      },
        err => {
          reject(err);
        });
    });

  }

}
