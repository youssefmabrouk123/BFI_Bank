import {ChangeDetectionStrategy, Component} from '@angular/core';
import {FormBuilder, Validators, FormsModule, ReactiveFormsModule, FormGroup } from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatStepperModule} from '@angular/material/stepper';
import {MatButtonModule} from '@angular/material/button';
import { StepperModule } from 'primeng/stepper';
import { ButtonModule } from 'primeng/button';
import { CommonModule } from '@angular/common';
import { MatSelectModule } from '@angular/material/select';
import {provideNativeDateAdapter} from '@angular/material/core';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatIconModule} from '@angular/material/icon';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormDataService } from '../form-data.service';
//import { FormDataService } from '../form-data.service';





@Component({
  selector: 'app-ouvrir-compte',
  templateUrl: './ouvrir-compte.component.html',
  styleUrl: './ouvrir-compte.component.css',
  standalone: true,
  imports: [
    MatButtonModule,
    MatStepperModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,StepperModule, ButtonModule , CommonModule, MatSelectModule,
    MatFormFieldModule, MatDatepickerModule, MatIconModule,

  ],
  providers: [provideNativeDateAdapter(), FormDataService],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class  OuvrirCompteComponent {

  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  thirdFormGroup: FormGroup;
  fourthFormGroup: FormGroup;
  fifthFormGroup: FormGroup;
  sixthFormGroup: FormGroup;
  
  
  isLinear = false;
  constructor(private _formBuilder: FormBuilder,    private formDataService: FormDataService
  ) {
    
   

    this.firstFormGroup = this._formBuilder.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      phoneNumber: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      confirmPhoneNumber: ['', Validators.required],
      confirmEmail: ['', [Validators.required, Validators.email]],
      dateNaissance: ['', Validators.required]
    });

    this.secondFormGroup = this._formBuilder.group({
    adresse: ['', Validators.required],
    pay: ['', Validators.required],
    gouvernorat: ['', Validators.required],
    codePostal: ['', Validators.required],
    });

    this.thirdFormGroup = this._formBuilder.group({

      nationalité: ['', Validators.required],
      statutCivil: ['', Validators.required],
      nombreEnfants: ['', Validators.required]

    });

    this.fourthFormGroup = this._formBuilder.group({
      categorieSocioPro: ['', Validators.required],
      revenuNetMensuel: ['', Validators.required],
      natureActivite: ['', Validators.required],
      secteurActivite: ['', Validators.required]
    });

    
    this.fifthFormGroup = this._formBuilder.group({
      cinFront: ['', Validators.required],
      cinBack: ['', Validators.required],
      numeroCin: ['', Validators.required],
      cinDate: ['', Validators.required],
    });
    
    this.sixthFormGroup = this._formBuilder.group({
      password: ['', [Validators.required, Validators.minLength(8)]],
      confirmPassword: ['', Validators.required],
    });

  }

  cinFrontPreview: string | ArrayBuffer | null = null;
  cinBackPreview: string | ArrayBuffer | null = null;

  onFileSelected(event: Event, field: string): void {
    const fileInput = event.target as HTMLInputElement;
    if (fileInput.files && fileInput.files.length > 0) {
      const file = fileInput.files[0];

      // Valider le type de fichier (image)
      const allowedTypes = ['image/jpeg', 'image/png', 'image/gif'];
      if (!allowedTypes.includes(file.type)) {
        alert('Seuls les fichiers JPEG, PNG et GIF sont autorisés');
        return;
      }

      // Valider la taille du fichier (par exemple, max 5MB)
      const maxSizeInBytes = 5 * 1024 * 1024;
      if (file.size > maxSizeInBytes) {
        alert('La taille du fichier doit être inférieure à 5MB');
        return;
      }

      const reader = new FileReader();
      reader.onload = () => {
        if (field === 'cinFront') {
          this.cinFrontPreview = reader.result;
        } else if (field === 'cinBack') {
          this.cinBackPreview = reader.result;
        }
      };
      reader.readAsDataURL(file);
    }
  }


 logFormValues(): void {
    console.log('First Form Group:', this.firstFormGroup.value);
    console.log('Second Form Group:', this.secondFormGroup.value);
    console.log('Third Form Group:', this.thirdFormGroup.value);
    console.log('Fourth Form Group:', this.fourthFormGroup.value);
    console.log('Fifth Form Group:', this.fifthFormGroup.value);
    console.log('Sixth Form Group:', this.sixthFormGroup.value);
  }
  logForm(): void {
    
    const formValues = {
      email: this.firstFormGroup.get('email')?.value,
      motDePasse: this.sixthFormGroup.get('password')?.value,
      nom: this.firstFormGroup.get('nom')?.value,
      prenom: this.firstFormGroup.get('prenom')?.value,
      phoneNumber: this.firstFormGroup.get('phoneNumber')?.value,
      adresse: this.secondFormGroup.get('adresse')?.value,
      pay: this.secondFormGroup.get('pay')?.value,
      gouvernorat: this.secondFormGroup.get('gouvernorat')?.value,
      codePostal: this.secondFormGroup.get('codePostal')?.value,
      offre: this.thirdFormGroup.get('offre')?.value,
      categorieSocioPro: this.fourthFormGroup.get('categorieSocioPro')?.value,
      revenuNetMensuel: this.fourthFormGroup.get('revenuNetMensuel')?.value,
      natureActivite: this.fourthFormGroup.get('natureActivite')?.value,
      secteurActivite: this.fourthFormGroup.get('secteurActivite')?.value,
      numeroCIN: this.fifthFormGroup.get('numeroCin')?.value,
      dateDelivranceCIN: this.fifthFormGroup.get('cinDate')?.value,
      // photoCINAvant: this.cinFrontPreview ? (this.cinFrontPreview as string).split(',')[1] : '', // Base64 encoded string
      // photoCINArriere: this.cinBackPreview ? (this.cinBackPreview as string).split(',')[1] : '', // Base64 encoded string
      statut: this.thirdFormGroup.get('statut')?.value,
      documents: [
        {
          nom: 'Document1', // You can customize or dynamically generate this if needed
          cinFront: this.cinFrontPreview ? (this.cinFrontPreview as string).split(',')[1] : '', // Base64 encoded string
          cinBack: this.cinBackPreview ? (this.cinBackPreview as string).split(',')[1] : '', // Base64 encoded string
          numeroCin: this.fifthFormGroup.get('numeroCin')?.value,
          dateDelivrance: this.fifthFormGroup.get('cinDate')?.value
        }
      ]
    };
    console.log(formValues);

    //Envoi des données au backend
    this.formDataService.sendFormData(formValues).subscribe({
      next: (response) => {
        console.log('Données envoyées avec succès', response);
      },
      error: (error) => {
        console.error('Erreur lors de l\'envoi des données', error);
      }
    });
  }
  
}