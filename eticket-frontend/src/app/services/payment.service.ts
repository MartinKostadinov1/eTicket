import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Injectable({
    providedIn: 'root'
})
export class PaymentService {

    constructor(private http: HttpClient) { }


    async checkForDefaultPaymentMethod() {
        try {
            let result = await (this.http.get(`/api/payment/check/default`, { observe: 'response' }).toPromise());
            return result.status == 200;
        } catch (_) {
            return false;
        }
    }

    async payTicket(ticketId: string, token: string, saveAsDefault: string) {
        let headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'token': token,
            'savePaymentMethod': saveAsDefault
        });
        let options = { headers: headers };

        return this.http.post(`/api/payment/ticket/${ticketId}`, null, options).subscribe(
            (_) => {
                return true;
            },
            (_) => {
                return false;
            })
    }

    async payWithDefault(ticketId: string) {

        return this.http.post(`/api/payment/ticket/default/${ticketId}`, {}).subscribe(
            (_) => {
                return true;
            },
            (_) => {
                return false;
            })
    }

}