import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '../../environments/environment';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {

  constructor(private http: HttpClient) {
  }

  setValue(name: string, value: any) {
    localStorage.setItem(name, String(value));
  }

  retreiveBooleanValue(name: string): boolean {
    return (localStorage.getItem(name) == "true");
  }
}
