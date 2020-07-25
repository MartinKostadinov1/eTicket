import { Component, OnInit } from '@angular/core';
import Chart from 'chart.js';

// core components
import {
  chartOptions,
  parseOptions,
  chartExample1,
  chartExample2
} from "../../variables/charts";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  public datasets: any;
  public data: any;
  public salesChart;
  public clicked: boolean = true;
  public clicked1: boolean = false;

  public monthlyTrips: number = 12;
  public unpaidTickets: number = 0;
  public allTimePaidTickets: number = 0;
  public virtualBalance: number = 0;
  public virtualBalanceCurrency: string = 'EUR';

  public previous: any = { monthlyTrips: 100 };

  constructor() { }

  ngOnInit() {

    this.datasets = [
      [0, 20, 10, 30, 15, 40, 20, 60, 60],
      [0, 20, 5, 25, 10, 30, 15, 40, 40]
    ];
    this.data = this.datasets[0];


    var chartOrders = document.getElementById('chart-orders');

    parseOptions(Chart, chartOptions());


    var ordersChart = new Chart(chartOrders, {
      type: 'bar',
      options: chartExample2.options,
      data: chartExample2.data
    });

    var chartSales = document.getElementById('chart-sales');

    this.salesChart = new Chart(chartSales, {
			type: 'line',
			options: chartExample1.options,
			data: chartExample1.data
		});
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





  public updateOptions() {
    this.salesChart.data.datasets[0].data = this.data;
    this.salesChart.update();
  }

}
