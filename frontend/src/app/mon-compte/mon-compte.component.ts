import { Component, OnInit, OnDestroy, AfterViewInit, ViewEncapsulation } from '@angular/core';
import { Chart } from 'chart.js';
//import { UserService } from '../services/user.service'; // Adjust path as necessary
import { Subscription } from 'rxjs';
import { CarteBancaireComponent } from "../carte-bancaire/carte-bancaire.component";
import { YoussefComponent } from '../youssef/youssef.component';

@Component({
  selector: 'app-mon-compte',
  standalone: true,
  imports: [CarteBancaireComponent,YoussefComponent], // Add necessary Angular modules if required
  templateUrl: './mon-compte.component.html',
  styleUrls: ['./mon-compte.component.css'] ,// Corrected 'styleUrls' instead of 'styleUrl'
  encapsulation: ViewEncapsulation.Emulated
})
export class MonCompteComponent  {

}
