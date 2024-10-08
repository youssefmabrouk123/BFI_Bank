import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
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
import { FormDataService } from '../form-data.service';
import { HeaderComponent } from '../header/header.component';
import { FooterComponent } from '../footer/footer.component';
//import { FormDataService } from '../form-data.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router, RouterLink } from '@angular/router'; // Import Router
import { ValidatorFn, AbstractControl } from '@angular/forms';
import { OnDestroy } from '@angular/core';


export function ageValidator(minimumAge: number): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } | null => {
    if (!control.value) return null;

    const selectedDate = new Date(control.value);
    const today = new Date();
    let age = today.getFullYear() - selectedDate.getFullYear();
    const monthDiff = today.getMonth() - selectedDate.getMonth();

    if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < selectedDate.getDate())) {
      age--;
    }

    return age >= minimumAge ? null : { ageError: true };
  };
}

@Component({
  selector: 'app-ouvrir-compte',
  templateUrl: './ouvrir-compte.component.html',
  styleUrls: ['./ouvrir-compte.component.css'],
  standalone: true,
  imports: [
    MatButtonModule,
    MatStepperModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,StepperModule, ButtonModule , CommonModule, MatSelectModule,
    MatFormFieldModule, MatDatepickerModule, MatIconModule,HeaderComponent,FooterComponent,RouterLink
  ],
  providers: [provideNativeDateAdapter(), FormDataService],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class OuvrirCompteComponent implements OnInit, OnDestroy {
  isLinear = true;
  firstFormGroup: FormGroup = new FormGroup({});
  secondFormGroup: FormGroup = new FormGroup({});
  thirdFormGroup: FormGroup = new FormGroup({});
  fourthFormGroup: FormGroup = new FormGroup({});
  fifthFormGroup: FormGroup = new FormGroup({});
  sixthFormGroup: FormGroup = new FormGroup({});

  cinFrontPreview: string | ArrayBuffer | null = null;
  cinBackPreview: string | ArrayBuffer | null = null;

  constructor(private _formBuilder: FormBuilder,    private formDataService: FormDataService,    private snackBar: MatSnackBar,    private router: Router, // Inject Router here


  ) {  }
  ngOnInit(): void {
    this.initializeFormGroups();
  }

  ngOnDestroy(): void {
    // Cleanup logic if needed
  }

  initializeFormGroups(): void {
    this.firstFormGroup = this._formBuilder.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      phoneNumber: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      confirmPhoneNumber: ['', Validators.required],
      confirmEmail: ['', [Validators.required, Validators.email]],
     dateNaissance: ['', [Validators.required, ageValidator(18)]]
    }, { validator: this.phoneEmailMatchValidator });

    this.secondFormGroup = this._formBuilder.group({
      adresse: ['', Validators.required],
      pay: ['', Validators.required],
      gouvernorat: ['', Validators.required],
      codePostal: ['', Validators.required]
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
      numeroCin: ['', Validators.required],
      dateDelivrance: ['', Validators.required],
      cinFront: [null],
      cinBack: [null]
    });

    this.sixthFormGroup = this._formBuilder.group({
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required]
    }, { validator: this.passwordMatchValidator });

    this.subscribeToPasswordChanges();
  }

  private subscribeToPasswordChanges(): void {
    const passwordControl = this.sixthFormGroup.get('password');
    const confirmPasswordControl = this.sixthFormGroup.get('confirmPassword');

    if (passwordControl) {
      passwordControl.valueChanges.subscribe(() => this.sixthFormGroup.updateValueAndValidity());
    }

    if (confirmPasswordControl) {
      confirmPasswordControl.valueChanges.subscribe(() => this.sixthFormGroup.updateValueAndValidity());
    }
  }

  private passwordMatchValidator(group: FormGroup): { [key: string]: boolean } | null {
    const password = group.get('password')?.value;
    const confirmPassword = group.get('confirmPassword')?.value;
    return password === confirmPassword ? null : { mismatch: true };
  }

  private phoneEmailMatchValidator(group: FormGroup): { [key: string]: boolean } | null {
    const phoneNumber = group.get('phoneNumber')?.value;
    const confirmPhoneNumber = group.get('confirmPhoneNumber')?.value;
    const email = group.get('email')?.value;
    const confirmEmail = group.get('confirmEmail')?.value;
    return phoneNumber === confirmPhoneNumber && email === confirmEmail ? null : { phoneEmailMismatch: true };
  }

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
          this.cinFrontPreview = reader.result as string;
        } else if (field === 'cinBack') {
          this.cinBackPreview = reader.result as string;
        }
      };
      reader.readAsDataURL(file);
    }
  }
  

  submitForm(): void {
    
    const formValues = {
       email: this.firstFormGroup.get('email')?.value,
       nationalite: this.thirdFormGroup.get('nationalité')?.value,
       statutCivil: this.thirdFormGroup.get('statutCivil')?.value,
       nombreEnfants: this.thirdFormGroup.get('nombreEnfants')?.value,
       motDePasse: this.sixthFormGroup.get('password')?.value,
       nom: this.firstFormGroup.get('nom')?.value,
       prenom: this.firstFormGroup.get('prenom')?.value,
       phoneNumber: this.firstFormGroup.get('phoneNumber')?.value,
       adresse: this.secondFormGroup.get('adresse')?.value,
       pay: this.secondFormGroup.get('pay')?.value,
       gouvernorat: this.secondFormGroup.get('gouvernorat')?.value,
       codePostal: this.secondFormGroup.get('codePostal')?.value,
       //offre: this.thirdFormGroup.get('offre')?.value,
       categorieSocioPro: this.fourthFormGroup.get('categorieSocioPro')?.value,
       revenuNetMensuel: this.fourthFormGroup.get('revenuNetMensuel')?.value,
       natureActivite: this.fourthFormGroup.get('natureActivite')?.value,
       secteurActivite: this.fourthFormGroup.get('secteurActivite')?.value,
       numeroCin: this.fifthFormGroup.get('numeroCin')?.value,
       dateDelivrance: this.fifthFormGroup.get('dateDelivrance')?.value,
       dateNaissance: this.firstFormGroup.get('dateNaissance')?.value,

       cinFront: this.cinFrontPreview ? (this.cinFrontPreview as string).split(',')[1] : '', // Base64 encoded string
       cinBack: this.cinBackPreview ? (this.cinBackPreview as string).split(',')[1] : '', // Base64 encoded string
      //statut: this.thirdFormGroup.get('statut')?.value,
      // documents: [
      //   {
      //     nom: 'Document1', // You can customize or dynamically generate this if needed
      //     cinFront: this.cinFrontPreview ? (this.cinFrontPreview as string).split(',')[1] : '', // Base64 encoded string
      //     cinBack: this.cinBackPreview ? (this.cinBackPreview as string).split(',')[1] : '', // Base64 encoded string
      //     numeroCin: this.fifthFormGroup.get('numeroCin')?.value,
      //     dateDelivrance: this.fifthFormGroup.get('cinDate')?.value
      //   }
      // ]
    };
    console.log(formValues);

    // Store the form data in the service
    this.formDataService.storeFormData(formValues);

    

    // Envoi des données au backend
    this.formDataService.submitFormData(formValues).subscribe({
      next: (response) => {
        console.log('Données envoyées avec succès', response);

        // Display success message
        this.snackBar.open('Formulaire soumis avec succès!', 'Fermer', {
          duration: 3000, // Duration in milliseconds
        });

        // Redirect to /signature
        this.router.navigate(['/felicitation']);
      },
      error: (error) => {
        console.error('Erreur lors de l\'envoi des données', error);

        this.snackBar.open('Erreur!!!', 'Fermer', {
          duration: 3000, // Duration in milliseconds
        });
      }
    });
  }

  private allFormGroupsValid(): boolean {
    return this.firstFormGroup.valid &&
           this.secondFormGroup.valid &&
           this.thirdFormGroup.valid &&
           this.fourthFormGroup.valid &&
           this.fifthFormGroup.valid &&
           this.sixthFormGroup.valid;
  }

  logFormValues(): void {
    console.log('First Form Group:', this.firstFormGroup.value);
    console.log('Second Form Group:', this.secondFormGroup.value);
    console.log('Third Form Group:', this.thirdFormGroup.value);
    console.log('Fourth Form Group:', this.fourthFormGroup.value);
    console.log('Fifth Form Group:', this.fifthFormGroup.value);
    console.log('Sixth Form Group:', this.sixthFormGroup.value);
  }
}