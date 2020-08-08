import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';
import { DashboardService } from 'src/app/services/dashboard.service';
import { IMapTicketModel } from 'src/app/models/map/IMapTicketModel';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-maps',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})
export class MapsComponent implements OnInit {


  lat: number = null;
  lng: number = null;
  zoom: number = 15;

  constructor(private dashboradService: DashboardService, private route: ActivatedRoute) {
    this.route.queryParams.subscribe(params => {
      this.lat = Number(params['lat']);
      this.lng = Number(params['lng']);
      this.route.queryParams = null
    });
  }

  public markers: IMapTicketModel[] = [];

  async ngOnInit() {
    let userCurrentLocation = await this.getPosition();

    if (!this.lat && !this.lng) {
      this.lat = userCurrentLocation.lat
      this.lng = userCurrentLocation.lng
    }
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
