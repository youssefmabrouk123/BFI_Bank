// // auth.service.ts
// import { Injectable } from '@angular/core';
// import { HttpClient, HttpHeaders } from '@angular/common/http';
// import { Observable, BehaviorSubject } from 'rxjs';
// import { catchError, tap } from 'rxjs/operators';

// @Injectable({
//   providedIn: 'root'
// })
// export class AuthService {
//   private apiUrl = 'http://localhost:8081/api/v1/users/auth/signin';
//   private userDetailsUrl = 'http://localhost:8081/api/v1/users/public/user';
//   private loggedIn = new BehaviorSubject<boolean>(this.hasToken());
//   private user = new BehaviorSubject<any>(null); // Add a BehaviorSubject to store user details

//   constructor(private http: HttpClient) {}

//   login(email: string, password: string): Observable<any> {
//     return this.http.post<any>(this.apiUrl, { email, password }).pipe(
//       tap(response => {
//         if (response.token) {
//           this.setToken(response.token);
//           this.fetchUserDetails(response.token).subscribe(); // Fetch user details after setting the token
//           this.loggedIn.next(true);
//           console.log(this.user);
//         }
//       }),
//       catchError(error => {
//         this.loggedIn.next(false);
//         throw error;
//       })
//     );
//   }

//   fetchUserDetails(token: string): Observable<any> {
//     const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
//     return this.http.get<any>(this.userDetailsUrl, { headers }).pipe(
//       tap(userDetails => this.user.next(userDetails)), // Store user details in BehaviorSubject
//       catchError(error => {
//         console.error('Failed to fetch user details:', error);
//         throw error;
//       })
//     );
//   }

//   getUserDetails(): Observable<any> {
//     console.log(this.user.asObservable());
//     return this.user.asObservable(); // Expose the user details as an observable
//   }

//   logout(): void {
//     localStorage.removeItem('token');
//     this.loggedIn.next(false);
//     this.user.next(null); // Clear user details on logout
//   }

//   isAuthenticated(): Observable<boolean> {
//     return this.loggedIn.asObservable();
//   }

//   setToken(token: string): void {
//     localStorage.setItem('token', token);
//   }

//   hasToken(): boolean {
//     return !!localStorage.getItem('token');
//   }
// }








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
  private user = new BehaviorSubject<any>(null);
  private profilePhotoUrl = 'http://localhost:8081/api/v1/users/public'; // Base URL for profile photo


  constructor(private http: HttpClient) {
    if (this.hasToken()) {
      this.fetchUserDetails(localStorage.getItem('token')!).subscribe(); // Fetch user details on service initialization if token exists
    }
  }

  login(email: string, password: string): Observable<any> {
    return this.http.post<any>(this.apiUrl, { email, password }).pipe(
      tap(response => {
        if (response.token) {
          this.setToken(response.token);
          this.fetchUserDetails(response.token).subscribe(() => {
            this.loggedIn.next(true);
          });
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
      tap(userDetails => this.user.next(userDetails)),
      catchError(error => {
        console.error('Failed to fetch user details:', error);
        throw error;
      })
    );
  }

  getUserDetails(): Observable<any> {
    return this.user.asObservable();
  }

  logout(): void {
    localStorage.removeItem('token');
    this.loggedIn.next(false);
    this.user.next(null);
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


  getProfilePhoto(userId: number): Observable<Blob> {
    return this.http.get(`${this.profilePhotoUrl}/${userId}/profile-photo`, { responseType: 'blob' });
  }

  uploadProfilePhoto(userId: number, file: File): Observable<any> {
    const formData = new FormData();
    formData.append('photo', file);

    return this.http.post(`${this.profilePhotoUrl}/${userId}/profile-photo`, formData);
  }
}

