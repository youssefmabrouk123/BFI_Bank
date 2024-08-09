// auth.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8081/api/v1/users/auth/signin';
  private loggedIn = new BehaviorSubject<boolean>(this.hasToken());

  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<any> {
    return this.http.post<any>(this.apiUrl, { email, password }).pipe(
      tap(response => {
        if (response.token) { // Assuming the response contains a token
          this.setToken(response.token);
          this.loggedIn.next(true);
        }
      }),
      catchError(error => {
        // Handle error properly here, e.g., display an error message
        this.loggedIn.next(false);
        throw error;
      })
    );
  }

  logout(): void {
    localStorage.removeItem('token');
    this.loggedIn.next(false);
  }

  isAuthenticated(): Observable<boolean> {
    return this.loggedIn.asObservable();
  }

  private setToken(token: string): void {
    localStorage.setItem('token', token);
  }

  private hasToken(): boolean {
    return !!localStorage.getItem('token');
  }
}
