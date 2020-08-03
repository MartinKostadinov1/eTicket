import { Validator, AbstractControl, NG_VALIDATORS } from '@angular/forms';
import { Directive } from '@angular/core';


/**  This validator actually works on checkboxes as opposite to the required and requiredTrue built-in validators */
@Directive({
    selector: '[validateTrue]',
    providers: [{ provide: NG_VALIDATORS, useExisting: TrueValidatorDirective, multi: true }]
})
export class TrueValidatorDirective implements Validator {

    validate(control: AbstractControl): { [key: string]: any } {
        if (control.value)
            return null;

        return { 'validateMatch': { value: control.value } };
    }
}

