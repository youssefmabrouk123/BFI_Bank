import { Component, OnInit, OnDestroy, AfterViewInit, ViewEncapsulation } from '@angular/core';
import { Chart } from 'chart.js';
//import { UserService } from '../services/user.service'; // Adjust path as necessary
import { Subscription } from 'rxjs';
import { CarteBancaireComponent } from "../carte-bancaire/carte-bancaire.component";
import { YoussefComponent } from '../youssef/youssef.component';
import { ClientService } from '../client.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-mon-compte',
  standalone: true,
  imports: [CarteBancaireComponent,YoussefComponent,CommonModule,], // Add necessary Angular modules if required
  templateUrl: './mon-compte.component.html',
  styleUrls: ['./mon-compte.component.css'] ,
  providers: [ClientService],
  // Corrected 'styleUrls' instead of 'styleUrl'
  encapsulation: ViewEncapsulation.Emulated
})
export class MonCompteComponent implements OnInit {
  clientData: any;

  constructor(private clientService: ClientService) {}

  ngOnInit(): void {
    const clientId = localStorage.getItem('Id');
    if (clientId) {
      this.clientService.getClientData(clientId).subscribe(
        (data) => {
          this.clientData = data;
          console.log('Client Data:', this.clientData);
          localStorage.setItem("idCompte", this.clientData.id);
          localStorage.setItem("nom", this.clientData.carte.nomTitulaire);
          localStorage.setItem("contractSignature", this.clientData.carte.contractSignature);


        },
        (error) => {
          console.error('Error fetching client data:', error);
        }
      );
    } else {
      console.error('Client ID not found in localStorage');
    }
  }

  formatDate(dateString: string): string {
    const date = new Date(dateString);
  
    // Extract the month and year
    const month = (date.getMonth() + 1).toString().padStart(2, '0'); // getMonth() is zero-based
    const year = date.getFullYear().toString().slice(-2); // Get the last two digits of the year
  
    // Return the formatted date as MM/YY
    return `${month}/${year}`;
  }

  formatCardNumber(cardNumber: string): string {
    return cardNumber.replace(/(\d{4})(?=\d)/g, '$1 ');
  }

  formatFrenchDate(dateString: string): string {
    const date = new Date(dateString);
  
    // Define the month names in French
    const monthNames = [
      'Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin',
      'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre'
    ];
  
    const day = date.getDate();
    const month = monthNames[date.getMonth()];
    const year = date.getFullYear();
  
    // Return the formatted date as "DD Mois YYYY"
    return `${day} ${month} ${year}`;
  }
  
}