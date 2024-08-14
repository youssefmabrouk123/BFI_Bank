import { Component, OnInit, OnDestroy, AfterViewInit, ViewEncapsulation } from '@angular/core';
import { Chart } from 'chart.js';
//import { UserService } from '../services/user.service'; // Adjust path as necessary
import { Subscription } from 'rxjs';
import { CarteBancaireComponent } from "../carte-bancaire/carte-bancaire.component";
import { YoussefComponent } from '../youssef/youssef.component';
import { ClientService } from '../client.service';

@Component({
  selector: 'app-mon-compte',
  standalone: true,
  imports: [CarteBancaireComponent,YoussefComponent], // Add necessary Angular modules if required
  templateUrl: './mon-compte.component.html',
  styleUrls: ['./mon-compte.component.css'] ,// Corrected 'styleUrls' instead of 'styleUrl'
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
        },
        (error) => {
          console.error('Error fetching client data:', error);
        }
      );
    } else {
      console.error('Client ID not found in localStorage');
    }
  }
}