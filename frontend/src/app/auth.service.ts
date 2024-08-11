// auth.service.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8081/api/v1/users/auth/signin';
  private userDetailsUrl = 'http://localhost:8081/api/v1/users/public/user';
  private loggedIn = new BehaviorSubject<boolean>(this.hasToken());
  private user = new BehaviorSubject<any>(null); // Add a BehaviorSubject to store user details

  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<any> {
    return this.http.post<any>(this.apiUrl, { email, password }).pipe(
      tap(response => {
        if (response.token) {
          this.setToken(response.token);
          this.fetchUserDetails(response.token).subscribe(); // Fetch user details after setting the token
          this.loggedIn.next(true);
          console.log(this.user);
        }
      }),
      catchError(error => {
        this.loggedIn.next(false);
        throw error;
      })
    );
  }

  fetchUserDetails(token: string): Observable<any> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<any>(this.userDetailsUrl, { headers }).pipe(
      tap(userDetails => this.user.next(userDetails)), // Store user details in BehaviorSubject
      catchError(error => {
        console.error('Failed to fetch user details:', error);
        throw error;
      })
    );
  }

  getUserDetails(): Observable<any> {
    return this.user.asObservable(); // Expose the user details as an observable
  }

  logout(): void {
    localStorage.removeItem('token');
    this.loggedIn.next(false);
    this.user.next(null); // Clear user details on logout
  }

  isAuthenticated(): Observable<boolean> {
    return this.loggedIn.asObservable();
  }

  setToken(token: string): void {
    localStorage.setItem('token', token);
  }

  hasToken(): boolean {
    return !!localStorage.getItem('token');
  }
}
