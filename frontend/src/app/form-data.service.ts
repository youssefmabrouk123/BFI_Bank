import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FormDataService {
  private apiUrl = 'http://localhost:8999/api/v1/Account/demandes/create';

  constructor(private http: HttpClient) { }

  sendFormData(data: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, data);
  }
}
