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
    MatFormFieldModule, MatDatepickerModule, MatIconModule
  ],
  providers: [provideNativeDateAdapter()],
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
  constructor(private _formBuilder: FormBuilder) {
    
   

    this.firstFormGroup = this._formBuilder.group({
      name: ['', Validators.required],
      phoneNumber: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      confirmPhoneNumber: ['', Validators.required],
      confirmEmail: ['', [Validators.required, Validators.email]],
      birthdate: ['', Validators.required]
    });

    this.secondFormGroup = this._formBuilder.group({
      street: ['', Validators.required],
    address: ['', Validators.required],
    country: ['', Validators.required],
    state: ['', Validators.required],
    postalCode: ['', Validators.required],
    });

    this.thirdFormGroup = this._formBuilder.group({

      nationality: ['', Validators.required],
      civilStatus: ['', Validators.required],
      childrenCount: ['', Validators.required]

    });

    this.fourthFormGroup = this._formBuilder.group({
      socioProfessionalCategory: ['', Validators.required],
      monthlyNetIncome: ['', Validators.required],
      activityNature: ['', Validators.required],
      activitySector: ['', Validators.required]
    });

    
    this.fifthFormGroup = this._formBuilder.group({
      cinFront: ['', Validators.required],
      cinBack: ['', Validators.required],
      cinNumber: ['', Validators.required],
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

 

}
