import { Component, OnInit } from '@angular/core';
import { DashboardService } from 'src/app/services/dashboard.service';
import { IMapTicketModel } from 'src/app/models/map/IMapTicketModel';
import { Router } from '@angular/router';
import { ITicketModel } from 'src/app/models/dashboard/ITicketModel';


@Component({
  selector: 'app-tickets',
  templateUrl: './tickets.component.html',
  styleUrls: ['./tickets.component.scss']
})
export class TicketsComponent implements OnInit {


  constructor(private dashboardService: DashboardService, private route: Router) { }


  public tickets: IMapTicketModel[] = [];

  public itemsPerPage = 7;
  public currentPage = 1;
  public totalPages = 0;
  public pages = [];

  public currentPageTickets: IMapTicketModel[] = [];

  public isPaymentWindow = false;
  public ticketToPayId = '';

  async ngOnInit() {
    this.tickets = await this.dashboardService.getAllTickets()


    this.tickets = this.tickets.sort((a, b) => {
      let c = new Date(a.ticket.createdOn);
      let d = new Date(b.ticket.createdOn);
      if (c < d) return 1;
      if (c > d) return -1;
      return 0;
    })

    this.totalPages = Math.ceil(this.tickets.length / this.itemsPerPage)
    for (let i = 1; i <= this.totalPages; i++) {
      this.pages.push(i)
    }

    this.loadCurrentPageItems()
  }

  getStatus(ticketId: string) {
    let ticket = this.tickets.find(t => t.id == ticketId).ticket;

    return ticket.isPaid ? 'paid' : (ticket.isDeleted ? 'deleted' : 'unpaid');
  }

  loadCurrentPageItems() {
    this.currentPageTickets = this.tickets.slice((this.currentPage - 1) * this.itemsPerPage,
      this.currentPage * this.itemsPerPage)
  }

  increasePages() {
    this.currentPage < this.pages.length ? this.currentPage++ : undefined;
    this.loadCurrentPageItems();
  }

  decreasePages() {
    this.currentPage > 1 ? this.currentPage-- : undefined;
    this.loadCurrentPageItems();
  }

  payTicket(ticketId: string) {
    this.ticketToPayId = ticketId;
    this.isPaymentWindow = true;
  }

}
