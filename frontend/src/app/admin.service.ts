import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  
  private apiUrl = 'http://localhost:8999/api/v1/Account/demandes'; // API URL

  constructor(private http: HttpClient) {}

  // Method to fetch data
  getDemandes(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
}

