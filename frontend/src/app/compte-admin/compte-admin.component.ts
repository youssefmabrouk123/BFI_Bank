import { Component, OnInit, OnDestroy, AfterViewInit, ViewEncapsulation } from '@angular/core';
import { ZainebComponent } from "../zaineb/zaineb.component";
import { CommonModule } from '@angular/common';
import { Demandes, DemandesService } from '../demandes.service';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-compte-admin',
  standalone: true,
  imports: [ZainebComponent,CommonModule,HttpClientModule],
  templateUrl: './compte-admin.component.html',
  styleUrl: './compte-admin.component.css',
  encapsulation: ViewEncapsulation.Emulated
})
export class CompteAdminComponent  {
  // demandes: Demandes[] = [];

  // constructor(private demandesService: DemandesService) {}

  // ngOnInit(): void {
  //   this.loadDemandes();
  // }

  // loadDemandes(): void {
  //   this.demandesService.getDemandes().subscribe(demandes => this.demandes = demandes);
  // }

  // acceptDemande(id: number): void {
  //   this.demandesService.acceptDemande(id).subscribe(() => this.loadDemandes());
  // }

  // rejectDemande(id: number): void {
  //   this.demandesService.rejectDemande(id).subscribe(() => this.loadDemandes());
  // }

}
