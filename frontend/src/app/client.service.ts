import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private apiUrl = 'http://localhost:8999/api/v1/Account/comptes';

  constructor(private http: HttpClient) { }

  getClientData(id: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/${id}`);
  }
}
