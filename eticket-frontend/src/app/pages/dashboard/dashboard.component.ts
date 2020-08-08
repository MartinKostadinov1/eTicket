import { Component, OnInit } from '@angular/core';
import Chart from 'chart.js';

// core components
import {
  chartOptions,
  parseOptions,
  expensesChart,
  ticketsChart
} from "../../variables/charts";
import { ITicketModel } from 'src/app/models/dashboard/ITicketModel';
import { IVehicleModel } from 'src/app/models/dashboard/IVehicleModel';
import { DashboardService } from 'src/app/services/dashboard.service';
import { IDashboardChartsModel } from 'src/app/models/dashboard/IDashboardChartsModel';
import { Month } from 'src/app/models/enums/month.enum';
import { toTypeScript } from '@angular/compiler';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  public datasets: any;

  public vehicles: any[] = []

  public locations: any[] = []

  public totalTicketsMonthly = 0;

  private chartsData: IDashboardChartsModel = { expenses: new Map(), tickets: new Map() };


  constructor(private dashboardService: DashboardService) { }

  async ngOnInit() {

    await this.loadCharts();
    await this.mostVisitedPlaces();
    this.totalTicketsMonthly = (await this.dashboardService.getDashboardStatusWidget()).monthlyTrips;
    this.mostUsedVehicles();

    this.vehicles = this.vehicles.sort((a, b) => b.tickets.length - a.tickets.length)

  }

  private async loadCharts() {

    this.chartsData = await this.dashboardService.getDashboardCharts();

    let ticketsData = [];
    let expensesData = [];

    Object.keys(Month).forEach(m => {
      ticketsData[Number(Month[m])] = Number(this.chartsData.tickets[m]) || 0;
      expensesData[Number(Month[m])] = Number(this.chartsData.expenses[m]) || 0;
    })

    ticketsChart.data.datasets.push({
      label: "Tickets",
      data: ticketsData
    })

    expensesChart.data.datasets.push({
      label: 'Expenses',
      data: expensesData
    })

    parseOptions(Chart, chartOptions());

    var chartTickets = document.getElementById('chart-tickets');

    new Chart(chartTickets, {
      type: 'bar',
      options: ticketsChart.options,
      data: ticketsChart.data
    });



    var chartExpenses = document.getElementById('chart-expenses');

    new Chart(chartExpenses, {
      type: 'line',
      options: expensesChart.options,
      data: expensesChart.data
    });

  }

  // Claculates what part of all tickets is taken by the tickets of this vehicle
  //id - vehicle id
  public calcVehicleUsage(id: string) {
    let vehicle = this.vehicles.find(v => v.id == id);

    let dateNow = new Date();

    let tickets = vehicle.tickets.filter(t => !t.isDeleted && new Date(t.createdOn).getMonth() == dateNow.getMonth());

    if (tickets.length == 0) {
      return [{ result: 0, background: '', fixedResult: 0 }];
    }

    let result = (tickets.length / this.totalTicketsMonthly) * 100;
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
    this.tickets = this.tickets.filter(t => !t.ticket.isDeleted);
    let locations: { [locationName: string]: { tickets: ITicketModel[], vehicles: IVehicleModel[] } } = {};
    this.userVehicles = Array.from(await this.dashboardService.getVehicles());

    for (const ticket of this.tickets) {
      if (!locations[ticket.ticket.locationName]) {
        locations[ticket.ticket.locationName] = { tickets: [], vehicles: [] }
      }

      locations[ticket.ticket.locationName].tickets.push(ticket.ticket);
    }

    let topLocations = Object.keys(locations).sort((a, b) => locations[b].tickets.length -locations[a].tickets.length)

    if (topLocations.length >= 5) topLocations = topLocations.slice(0, 4);

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
      })
    }

    this.vehicles = this.vehicles.sort((a, b) => a.tickets - b.tickets);
  }

}
