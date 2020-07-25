import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-status',
  templateUrl: './status.component.html',
  styleUrls: ['./status.component.scss']
})
export class StatusComponent implements OnInit {

  public monthlyTrips: number = 12;
  public unpaidTickets: number = 0;
  public allTimePaidTickets: number = 0;
  public virtualBalance: number = 0;
  public virtualBalanceCurrency: string = 'EUR';

  public previous: any = { monthlyTrips: 100 };

  constructor() { }

  ngOnInit() {
  }

  //Calculates the trips percantabge compared to the previus month
  get calTripsPercentage() {
    if(this.monthlyTrips == 0) {
      return -100;
    }

    if(this.monthlyTrips == this.previous.monthlyTrips) {
      return 0;
    }

    if(this.monthlyTrips > this.previous.monthlyTrips) {
      let diff = ((this.monthlyTrips/this.previous.monthlyTrips) * 100).toFixed(2)
      return diff;
    } else {
      let diff = (((this.previous.monthlyTrips-this.monthlyTrips)/this.previous.monthlyTrips) * -100).toFixed(2)
      return diff;
    }

  }

}
