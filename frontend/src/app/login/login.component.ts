import { Component,OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../auth.service';
import { HttpClientModule } from '@angular/common/http';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { FooterComponent } from '../footer/footer.component';
import { HeaderComponent } from '../header/header.component';
import { CommonModule } from '@angular/common'; 

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, ToastModule, HttpClientModule, FooterComponent, HeaderComponent,CommonModule,RouterModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [MessageService]
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  signupText: string = "Vous n’avez pas de compte ?";
  signupLink: string = "/inscription";
  forgotPasswordText: string = "Mot de passe oublié ?";
  forgotPasswordLink: string = "/mot-de-passe-oublie";


  constructor(
    private router: Router,
    public authService: AuthService,
    private messageService: MessageService,
    private _formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.loginForm = this._formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  // Method to return error message for email
  getEmailErrorMessage(): string {
    if (this.loginForm.controls['email'].hasError('required')) {
      return 'L\'email est requis';
    }
    return this.loginForm.controls['email'].hasError('email') ? 'Veuillez fournir une adresse électronique valide' : '';
  }

  // Method to return error message for password
  getPasswordErrorMessage(): string {
    return this.loginForm.controls['password'].hasError('required') ? 'Ce champ est obligatoire' : '';
  }

onSubmit(): void {
  if (this.loginForm.valid) {
    const { email, password } = this.loginForm.value;
    this.authService.login(email, password).subscribe(
      response => {
        console.log('API response:', response);

        if (response.statusCode === 200) {
          this.messageService.add({
            severity: 'success',
            summary: 'Success',
            detail: 'Bienvenue!'
          });

          if (response.token) {
            this.authService.setToken(response.token);

            // Fetch and store user details
            this.authService.fetchUserDetails(response.token).subscribe(
              userDetails => {
                console.log('User details:', userDetails);
                localStorage.setItem('Id', userDetails.id);
                if(userDetails.role=="USER"){
                this.router.navigate(['/mon-compte']);}
                if(userDetails.role=="ADMIN"){
                  this.router.navigate(['/compte-admin']);}  // Redirect to /mon-compte after fetching user details
              },
              error => {
                console.error('Failed to fetch user details:', error);
                this.messageService.add({
                  severity: 'error',
                  summary: 'Error',
                  detail: 'Échec de la récupération des détails de l utilisateur.'
                });
              }
            );
          }
        } else {
          this.messageService.add({
            severity: 'warn',
            summary: 'Warning',
            detail: response.message || 'Accès refusé.'
          });
        }
      },
      error => {
        console.error('Login failed:', error);
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: error.error?.message || 'Une erreur inattendue s est produite.'
        });
      }
    );
  } else {
    this.messageService.add({
      severity: 'warn',
      summary: 'Warning',
      detail: 'Le formulaire n est pas valide'
    });
  }
}

}
