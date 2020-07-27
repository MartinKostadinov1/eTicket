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

  public vehicles: any[] = [
    {
      id: "1",
      registrationNumber: "BP 3549 BP",
      tickets: [{}, {}, {}]
    },
    {
      id: "2",
      registrationNumber: "BP 4512 KO",
      tickets: [{}, {}, {}, {}, {}, {}, {}, {}, {}]
    }
  ]

  public locations: any[] = [
    {
      id: "1",
      name: "Nebraska",
      tickets: ['girehiewr', 'sidhiojui9jrnwj'],
      vehiclesBeenThere: ['1', '2']
    },
    {
      id: "2",
      name: "ReedView parking slot",
      tickets: ['90i09jo9neiorg'],
      vehiclesBeenThere: ['1']
    },
    {
      id: "3",
      name: "OWms highway Hungary",
      tickets: ['0oi0weio kw'],
      vehiclesBeenThere: ['2']
    }
  ]

  public totalTicketsMonthly = 12;

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

  public updateOptions() {
    this.salesChart.data.datasets[0].data = this.data;
    this.salesChart.update();
  }

  // Claculates what part of all tickets is taken by the tickets of this vehicle
  //id - vehicle id
  public calcVehicleUsage(id: string) {
    let vehicle = this.vehicles.find(v => v.id == id);

    let result = (vehicle.tickets.length / this.totalTicketsMonthly) * 100;
    let background = '';
    if(result <= 25) {
      background = 'bg-gradient-danger';
    } else if(result <= 35) {
      background = 'bg-gradient-warning';
    } else if(result <= 55) {
      background = 'bg-gradient-primary';
    } else if(result <= 75) {
      background = 'bg-gradient-info';
    } else {
      background = 'bg-gradient-success';
    }

    return [{result: result.toFixed(2).replace(".", ","), background: background, fixedResult: result.toFixed(0)}];
  }

}
