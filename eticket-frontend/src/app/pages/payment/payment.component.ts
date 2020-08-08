import { Component, OnInit, ViewChild, ElementRef, ChangeDetectorRef, AfterViewInit, OnDestroy, Input } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { PaymentService } from 'src/app/services/payment.service';

@Component({
    selector: 'app-payment',
    templateUrl: './payment.component.html',
    styleUrls: ['./payment.component.scss']
})
export class PaymentComponent implements OnInit, AfterViewInit, OnDestroy {

    @ViewChild('cardInfo') cardInfo: ElementRef;
    card: any;
    cardHandler = this.onChange.bind(this);
    error: string;

    @Input() ticketId: string;

    public savePaymentMethod = false;
    public isDefaultPaymentSaved = false;
    public isLoading = true;

    constructor(private cd: ChangeDetectorRef, private authService: AuthService, private paymentService: PaymentService) { }

    async ngOnInit() {
        this.isDefaultPaymentSaved = await this.paymentService.checkForDefaultPaymentMethod();
        this.isLoading = false;
    }


    ngAfterViewInit() {
        const style = {
            base: {
                lineHeight: '24px',
                fontFamily: 'monospace',
                fontSmoothing: 'antialiased',
                fontSize: '19px',
                '::placeholder': {
                    color: 'purple'
                }
            }
        };

        this.card = elements.create('card', { style });
        this.card.mount(this.cardInfo.nativeElement);

        this.card.addEventListener('change', this.cardHandler);
    }

    ngOnDestroy() {
        this.card.removeEventListener('change', this.cardHandler);
        this.card.destroy();
    }

    onChange({ error }) {
        if (error) {
            this.error = error.message;
        } else {
            this.error = null;
        }
        this.cd.detectChanges();
    }

    async onSubmit(form: NgForm) {
        const { token, error } = await stripe.createToken(this.card, {
            email: this.authService.email
        });
        if (error) {
            console.log('Something is wrong:', error);
        } else {
            this.isLoading = true;
            let success = await this.paymentService.payTicket(this.ticketId, token.id, `${this.savePaymentMethod}`);
            this.isLoading = false;

            if (success) {
                window.location.reload();
            } else {
                alert("Payment failed. Please try again later!");
            }
        }

    }

    async defaultPayment() {
        this.isLoading = true;
        let success = await this.paymentService.payWithDefault(this.ticketId);
        this.isLoading = false;

        if (success) {
            window.location.reload();
        } else {
            alert("Payment failed. Please try again later!");
        }
    }
}
