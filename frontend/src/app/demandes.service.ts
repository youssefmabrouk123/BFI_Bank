import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

export interface Demandes {
  id: number;
  nom: string;
  prenom: string;
  dateNaissance: Date | null;
  email: string;
  phoneNumber: string;
  adresse: string;
  pay: string;
  gouvernorat: string;
  codePostal: string;
  nombreEnfants: number | null;
  statutCivil: string | null;
  nationalite: string | null;
  categorieSocioPro: string;
  revenuNetMensuel: string;
  natureActivite: string;
  secteurActivite: string;
  statut: string;
  numeroCin: number;
  motDePasse: string;
  dateDelivrance: Date | null;
  cinFront: string | null;
  cinBack: string | null;
  dateCreation: string; // Or Date depending on your needs
}

export interface DemandeImageResponse {
  cinBack: string;
}

export interface DemandeImageResponseFront {
  cinFront: string;
}

@Injectable({
  providedIn: 'root'
})
export class DemandesService {

  private baseUrl = 'http://localhost:8999/api/v1/Account/demandes'; // URL de votre API
  private apiUrl = "http://localhost:8999/api/v1/Account/demandes/files";


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
  acceptDemande(email: string,id: number): Observable<void> {
    return     this.http.put<void>(`http://localhost:8999/api/v1/Account/demandes/unblock?email=${email}`, {});


  }

  rejectDemande(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/refuse/${id}`, {});
  }

  // Méthode pour gérer les erreurs
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

  getCinBack(idDemande: number): Observable<DemandeImageResponse> {
    return this.http.get<DemandeImageResponse>(`${this.apiUrl}/${idDemande}`);
  }

  getCinFront(idDemande: number): Observable<DemandeImageResponseFront> {
    return this.http.get<DemandeImageResponseFront>(`${this.apiUrl}/front/${idDemande}`);
  }
}
