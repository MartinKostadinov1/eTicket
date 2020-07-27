import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';
declare const google: any;

@Component({
  selector: 'app-maps',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})
export class MapsComponent implements OnInit {

  constructor() { }


  public markers: any = [{ lat: 51.347680, lng: 22.661402780001, eticket: { id: "voj09wejoe", type: "PARKING" } },
  { lat: 52.347680, lng: 23.661402780001, eticket: { id: "fiejrwo0ef", type: "HIGHWAY" } }];

  lat: number = 51.247680;
  lng: number = 22.561402780001;
  zoom: number = 15;
  ngOnInit() {

  }

  get appUrl() {
    return environment.url;
  }

}
