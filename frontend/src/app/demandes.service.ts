import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

export interface Demandes {
  id: number;
  name: string;
  email: string;
  phone_number?: string;
  request_date: string;
  status: string;
  category?: string;
  nationality?: string;
  governorate?: string;
  address?: string;
  activity_nature?: string;
  sector?: string;
}
@Injectable({
  providedIn: 'root'
})
export class DemandesService {

  private baseUrl = 'http://localhost:8080/api/demandes'; // URL de votre API

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) {}

  // Méthode pour récupérer toutes les demandes
  getDemandes(): Observable<Demandes[]> {
    return this.http.get<Demandes[]>(this.baseUrl).pipe(
      catchError(this.handleError<Demandes[]>('getDemandes', []))
    );
  }

  // Méthode pour accepter une demande
  acceptDemande(id: number): Observable<Demandes> {
    const url = `${this.baseUrl}/${id}/accept`;
    return this.http.put<Demandes>(url, {}, this.httpOptions).pipe(
      catchError(this.handleError<Demandes>('acceptDemande'))
    );
  }

  // Méthode pour rejeter une demande
  rejectDemande(id: number): Observable<Demandes> {
    const url = `${this.baseUrl}/${id}/reject`;
    return this.http.put<Demandes>(url, {}, this.httpOptions).pipe(
      catchError(this.handleError<Demandes>('rejectDemande'))
    );
  }

  // Méthode pour gérer les erreurs
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
}
