import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RdvService {

  private apiUrl = 'http://localhost:8999/api/v1/Account/rendezvous/available-slots';
  private bookUrl = 'http://localhost:8999/api/v1/Account/rendezvous/book';

  constructor(private http: HttpClient) { }

  getAvailableSlots(): Observable<Date[]> {
    return this.http.get<Date[]>(this.apiUrl).pipe(
      map(slots => slots.filter(slot => new Date(slot) > new Date()))
    );
  }
  bookSlot(selectedDate: Date | string, email: string): Observable<any> {
    // Ensure selectedDate is a Date object
    const date = (typeof selectedDate === 'string') ? new Date(selectedDate) : selectedDate;

    // Convert date to ISO string format
    const body = {
      selectedDate: date.toISOString(),
      email: email
    };
    
    return this.http.post(this.bookUrl, body);
  }
}
