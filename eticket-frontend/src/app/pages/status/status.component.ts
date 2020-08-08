import { Component, OnInit } from '@angular/core';
import { DashboardService } from 'src/app/services/dashboard.service';
import { IDashboardStatusWidget } from 'src/app/models/dashboard/IDashboardStatusWidget';

@Component({
  selector: 'app-stats',
  templateUrl: './status.component.html',
  styleUrls: ['./status.component.scss']
})
export class StatusComponent implements OnInit {


  dashboardWidget: IDashboardStatusWidget = { monthlyTrips: 0, previousMonthTrips: 0, unpaidTickets: 0,  allPaidTickets: 0,vehicleCount: 0 };

constructor(private dashboardService: DashboardService) { }

async ngOnInit() {
  this.dashboardWidget = await this.dashboardService.getDashBoardStatusWidget();
}

//Calculates the trips percantabge compared to the previus month
get calTripsPercentage() {
  if (this.dashboardWidget) {

    if (this.dashboardWidget.previousMonthTrips == 0 && this.dashboardWidget.monthlyTrips == 0) {
      return '0';
    }

    if (this.dashboardWidget.monthlyTrips == 0) {
      return -100;
    }

    if (this.dashboardWidget.previousMonthTrips == 0) {
      return 100;
    }

    if (this.dashboardWidget.monthlyTrips == this.dashboardWidget.previousMonthTrips) {
      return 0;
    }

    if(this.dashboardWidget.monthlyTrips > this.dashboardWidget.previousMonthTrips) {
      let gross = 100 - ((this.dashboardWidget.previousMonthTrips / this.dashboardWidget.monthlyTrips) * 100)
      return gross.toFixed(2)
    } else {
      let gross = -100 + ((this.dashboardWidget.previousMonthTrips / this.dashboardWidget.monthlyTrips) * 100)
      return gross.toFixed(2)
    }
  }

}

}
