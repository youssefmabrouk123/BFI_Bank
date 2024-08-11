import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { HttpClientModule } from '@angular/common/http';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { FooterComponent } from '../footer/footer.component';
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, ToastModule, HttpClientModule, FooterComponent, HeaderComponent],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [MessageService]
})
export class LoginComponent {
  loginForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    public authService: AuthService,
    private messageService: MessageService
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
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
            detail: 'Welcome!'
          });

          if (response.token) {
            this.authService.setToken(response.token);

            // Fetch and store user details
            this.authService.fetchUserDetails(response.token).subscribe(
              userDetails => {
                console.log('User details:', userDetails);
                this.router.navigate(['/mon-compte']); // Redirect to /mon-compte after fetching user details
              },
              error => {
                console.error('Failed to fetch user details:', error);
                this.messageService.add({
                  severity: 'error',
                  summary: 'Error',
                  detail: 'Failed to fetch user details.'
                });
              }
            );
          }
        } else {
          this.messageService.add({
            severity: 'warn',
            summary: 'Warning',
            detail: response.message || 'Access denied.'
          });
        }
      },
      error => {
        console.error('Login failed:', error);
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: error.error?.message || 'An unexpected error occurred.'
        });
      }
    );
  } else {
    this.messageService.add({
      severity: 'warn',
      summary: 'Warning',
      detail: 'Form is invalid'
    });
  }
}

}
