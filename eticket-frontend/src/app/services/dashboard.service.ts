import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IDashboardModel } from '../models/dashboard/IDashboardModel';
import { ITicketModel } from '../models/dashboard/ITicketModel';
import { IMapTicketModel } from '../models/map/IMapTicketModel';
import { IDashboardStatusWidget } from '../models/dashboard/IDashboardStatusWidget';

@Injectable({
    providedIn: 'root'
})
export class DashboardService {

    private userDashboard: IDashboardModel;

    private statusWidget: IDashboardStatusWidget;

    get dahsboard() {
        return this.userDashboard;
    }

    setDashboard(userDashboard: IDashboardModel) {
        this.userDashboard = userDashboard;
    }

    get dashboardStatusWidget() {
        return this.statusWidget;
    }

    setDahsboardStatusWidget(statusWidget: IDashboardStatusWidget) {
        this.statusWidget = statusWidget;
    }

    constructor(private http: HttpClient) { }

    async loadUserDashboard() {
        let result = await (this.http.get<IDashboardModel>(`/api/dashboard`, { observe: 'response' }).toPromise()).catch(_ => null);
        this.setDashboard(result.body);
    }

    async getAllTickets() {
        if(!this.dahsboard) {
            await this.loadUserDashboard();
        }

        let tickets: IMapTicketModel[] = [];
        for (const vehiclce of Array.from(this.dahsboard.vehicles)) {

            tickets = tickets.concat(Array.from(vehiclce.tickets).map(t => {
                let coordinates = t.locationCoordinates.split(", ");
        
                return {lat: Number(coordinates[0]), lng: Number(coordinates[1]), ticket: t};
            }));
        }

        return tickets;
    }

    async getDashBoardStatusWidget() {
        this.setDahsboardStatusWidget(await this.http.get<IDashboardStatusWidget>('/api/dashboard/status-widget').toPromise());
        return this.dashboardStatusWidget;
    }

    async getVehicles() {
        if(!this.dahsboard) {
            await this.loadUserDashboard();
        }

        return this.dahsboard.vehicles;
    }


    async getDashboardStatusWidget() {
        if(!this.dashboardStatusWidget) {
            await this.getDashBoardStatusWidget();
        }

        return this.dashboardStatusWidget;
    }

}