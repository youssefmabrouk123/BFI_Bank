import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FormDataService {
  private apiUrl = 'http://localhost:8999/api/v1/Account/demandes/create';
  private formDataStorage = new BehaviorSubject<any>(null);  // Variable to store form data

  constructor(private http: HttpClient) { }

  // Store form data in the service
  storeFormData(formData: any): void {
    console.log('Storing form data:', formData);  // Add this line
    this.formDataStorage.next(formData);
    localStorage.setItem('email', formData.email);
    localStorage.setItem('phoneNumber', formData.phoneNumber);

    //localStorage.setItem('phone', formData.email);


  }

  // Retrieve stored form data
  getStoredFormData(): Observable<any> {
    console.log('Retrieving stored form data:', this.formDataStorage.getValue());  // Add this line
    return this.formDataStorage.asObservable();
  }

  submitFormData(formData: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, formData);
  }
}
