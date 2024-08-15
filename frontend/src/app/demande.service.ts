import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Demandes {
  id: number;
  nom: string;
  prenom: string;
  dateNaissance: string | null;
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
  dateDelivrance: string | null;
  cinFront: string | null;
  cinBack: string | null;
  dateCreation: string;
}

@Injectable({
  providedIn: 'root'
})
export class DemandesService {
  private apiUrl = 'http://localhost:8999/api/v1/Account/demandes';

  constructor(private http: HttpClient) {}

  getDemandes(): Observable<Demandes[]> {
    return this.http.get<Demandes[]>(this.apiUrl);
  }

  acceptDemande(id: number): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/${id}/accept`, {});
  }

  rejectDemande(id: number): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/${id}/reject`, {});
  }
}
