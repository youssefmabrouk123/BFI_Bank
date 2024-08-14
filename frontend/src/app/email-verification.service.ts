import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmailVerificationService {
  private apiUrl = 'http://localhost:8081/api/v1/users/public/email/sendVerificationToken';
  private verifyUrl = 'http://localhost:8081/api/v1/users/public/email/verify'; // Endpoint for token verification

  constructor(private http: HttpClient) { }

  sendVerificationToken(email: string): Observable<HttpResponse<string>> {
    const params = new HttpParams().set('email', email);
    return this.http.get<string>(this.apiUrl, { params, responseType: 'text' as 'json', observe: 'response' });
  }

  verifyToken(token: string): Observable<any> {
    const params = new HttpParams().set('token', token);
    return this.http.get<any>(this.verifyUrl, { params });
  }

  sendOtp(data: { phoneNumber: string }): Observable<any> {
    return this.http.post<any>(`http://localhost:8081/api/v1/users/public/send-otp`, data);
  }

  verifyOtp(body: { phoneNumber: string; otp: string }) {
    return this.http.post('http://localhost:8081/api/v1/users/public/verify-otp', body);
  }
}
