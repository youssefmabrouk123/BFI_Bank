import { Component,  ElementRef,  OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
@Component({
  selector: 'app-details-client',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatInputModule,
    MatButtonModule,
    MatFormFieldModule,
     
     
  ],
  templateUrl: './details-client.component.html',
  styleUrls: ['./details-client.component.css']
})
export class DetailsClientComponent implements OnInit {
  clientForm: FormGroup;
  isCardContainerVisible = false;

  constructor(private fb: FormBuilder) {
    this.clientForm = this.fb.group({
      nom: ['abderrazak'],
      prenon: ['zaineb'],
      statutCivil: ['célèbataire'],
      email: ['zainebabderrazak6@gmail.com'],
      phoneNumber: ['58448680'],
      adresse: ['rue belhsen jrad'],
      pay: ['tunisie'],
      nationalité: ['tunisienne'],
      gouvernorat: ['nabeul'],
      codePostal: ['8011'],
      categorieSocioPro: ['etudiant'],
      revenuNetMensuel: ['---'],
      natureActivite: ['---'],
      secteurActivite: ['---']
    });
  }

  ngOnInit(): void {}

  toggleCardContainer(): void {
    this.isCardContainerVisible = !this.isCardContainerVisible;
  }

  onSubmit(): void {
    console.log(this.clientForm.value);
 
  } 
  
  @ViewChild('card', { static: true }) cardElement!: ElementRef;

  toggleFlip(): void {
    const card = this.cardElement.nativeElement;
    card.classList.toggle('flip');
  }

}
