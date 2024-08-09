// login.component.ts
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { HttpClientModule } from '@angular/common/http';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, ToastModule, HttpClientModule],
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
  
          // Check status code and display appropriate messages
          if (response.statusCode === 200) {
            this.messageService.add({ 
              severity: 'success', 
              summary: 'Success', 
              detail: 'Welcome!' 
            });
            // Navigate to /mon-compte
            this.router.navigate(['/']);
          } else if (response.statusCode === 403) {
            this.messageService.add({ 
              severity: 'warn', 
              summary: 'Warning', 
              detail: response.message || 'Access denied.' 
            });
            // Optionally navigate or take other actions
            // this.router.navigate(['/some-other-page']);
          } else {
            this.messageService.add({ 
              severity: 'error', 
              summary: 'Error', 
              detail: response.error || 'An unexpected error occurred.' 
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
      console.log('Form is invalid');
      this.messageService.add({ 
        severity: 'warn', 
        summary: 'Warning', 
        detail: 'Form is invalid' 
      });
    }
  }
  
  
}
