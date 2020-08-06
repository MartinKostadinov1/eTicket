import { Component, OnInit } from '@angular/core';
import Chart from 'chart.js';

// core components
import {
  chartOptions,
  parseOptions,
  chartExample1,
  chartExample2
} from "../../variables/charts";
import { ITicketModel } from 'src/app/models/dashboard/ITicketModel';
import { IVehicleModel } from 'src/app/models/dashboard/IVehicleModel';
import { DashboardService } from 'src/app/services/dashboard.service';

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

  public vehicles: any[] = []

  public locations: any[] = []

  public totalTicketsMonthly = 12;


  constructor(private dashboardService: DashboardService) { }

  async ngOnInit() {

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

    
    await this.mostVisitedPlaces();
    this.totalTicketsMonthly = (await this.dashboardService.getDashboardStatusWidget()).monthlyTrips;
    this.mostUsedVehicles();
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
    if (result <= 25) {
      background = 'bg-gradient-danger';
    } else if (result <= 35) {
      background = 'bg-gradient-warning';
    } else if (result <= 55) {
      background = 'bg-gradient-primary';
    } else if (result <= 75) {
      background = 'bg-gradient-info';
    } else {
      background = 'bg-gradient-success';
    }

    return [{ result: result.toFixed(0), background: background, fixedResult: result.toFixed(0) }];
  }

  private userVehicles: IVehicleModel[] = [];
  private tickets: any = {}

  private async mostVisitedPlaces() {
    this.tickets = await this.dashboardService.getAllTickets();
    let locations: { [locationName: string]: { tickets: ITicketModel[], vehicles: IVehicleModel[] } } = {};
    this.userVehicles = Array.from(await this.dashboardService.getVehicles());

    for (const ticket of this.tickets) {
      if (!locations[ticket.ticket.locationName]) {
        locations[ticket.ticket.locationName] = { tickets: [], vehicles: [] }
      }

      locations[ticket.ticket.locationName].tickets.push(ticket.ticket);
    }

    let topLocations = Object.keys(locations).sort((a, b) => locations[a].tickets.length - locations[b].tickets.length)

    if(topLocations.length >= 5) topLocations = topLocations.slice(0, 4);
    
    let count = 1;
    for (const location of topLocations) {
      let locationResult = {
        id: count,
        name: location,
        tickets: locations[location].tickets,
        vehiclesBeenThere: this.userVehicles.filter(v => v.tickets.find(t => t.locationName == location))
      }

      this.locations.push(locationResult);
      count++;
    }
  }

  private mostUsedVehicles() {
    for (const vehicle of this.userVehicles) {
      this.vehicles.push({
        id: vehicle.id,
        registrationNumber: vehicle.registrationNumber,
        tickets: Array.from(vehicle.tickets)
      },)
    }

    this.vehicles = this.vehicles.sort((a, b) => a.tickets - b.tickets);
  }

}
