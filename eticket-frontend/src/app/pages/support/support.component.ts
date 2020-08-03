import { Component, OnInit } from '@angular/core';
declare const google: any;

@Component({
  selector: 'app-support',
  templateUrl: './support.component.html',
  styleUrls: ['./support.component.scss']
})
export class SupportComponent implements OnInit {
  constructor() { }

  lat: number = 42.667286;
  lng: number = 23.324846;
  zoom: number = 15;

  public marker: any = { lat: 42.667286, lng: 23.324846, markerName: 'eTicket Central Office - Sofia'};

  ngOnInit() {
  }
  
  goBack() {
    window.history.back();
  }
}
