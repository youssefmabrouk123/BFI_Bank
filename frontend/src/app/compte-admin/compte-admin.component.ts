import { Component, OnInit } from '@angular/core';
import { Demandes, DemandesService } from '../demandes.service';
import { CommonModule } from '@angular/common';
import { YoussefComponent } from '../youssef/youssef.component';
import { ZainebComponent } from '../zaineb/zaineb.component';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-compte-admin',
  standalone: true,
  imports: [YoussefComponent,ZainebComponent, CommonModule],
  templateUrl: './compte-admin.component.html',
  styleUrls: ['./compte-admin.component.css']
})
export class CompteAdminComponent implements OnInit {
  demandes: Demandes[] = [];
  selectedDemande: Demandes | null = null;
  showModal: boolean = false;
  cinBackUrl: string | null = null;
  cinFrontUrl: string | null = null;


  constructor(private demandesService: DemandesService) {}

  ngOnInit(): void {
    this.loadDemandes();
  }

  loadDemandes(): void {
    this.demandesService.getDemandes().subscribe(demandes => this.demandes = demandes);
  }

  acceptDemande(demande: Demandes): void {
    if (demande.email) {
      this.demandesService.acceptDemande(demande.email,demande.id).subscribe(() => this.loadDemandes());
      alert("demande Accepter avec succés");
      this.rejectDemande(demande.id);
    }
  }

  rejectDemande(id: number): void {
    this.demandesService.rejectDemande(id).subscribe(() => this.loadDemandes());
  }

  openDetailModal(demande: Demandes): void {
    this.selectedDemande = demande;
    console.log(demande);
    if (demande.id) {
      this.demandesService.getCinBack(demande.id).subscribe(response => {
        this.cinBackUrl = `data:image/png;base64,${response.cinBack}`;
      });
      this.demandesService.getCinFront(demande.id).subscribe(response => {
        this.cinFrontUrl = `data:image/png;base64,${response.cinFront}`;
      });
    }
    this.showModal = true;
  }

  closeDetailModal(): void {
    this.showModal = false;
    this.selectedDemande = null;
    this.cinBackUrl = null;
  }
}