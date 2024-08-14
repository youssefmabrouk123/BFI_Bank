import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatStepperModule } from '@angular/material/stepper';
import { EmailVerificationService } from '../email-verification.service';
import { FormDataService } from '../form-data.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HttpResponse } from '@angular/common/http';
import { RdvService } from '../rdv.service';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-signature',
  standalone: true,
  imports: [
    MatStepperModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    FormsModule,
    ReactiveFormsModule,
    CommonModule
    
  ],
  providers: [EmailVerificationService, FormDataService,HttpResponse,RdvService],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './signature.component.html',
  styleUrls: ['./signature.component.css']
})
export class SignatureComponent implements OnInit {
  codeForm: FormGroup;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  storedData: any;

  slots: Date[] = [];
  selectedSlot: Date | null = null;

  constructor(
    private fb: FormBuilder,
    private emailVerificationService: EmailVerificationService,
    private formDataService: FormDataService,
    private snackBar: MatSnackBar ,
    private rdvService: RdvService

  ) {
    this.firstFormGroup = this.fb.group({
      firstCtrl: ['', Validators.required]
    });
    this.codeForm = this.fb.group({
      code: ['', [Validators.required]]
    });
    this.secondFormGroup = this.fb.group({
      secondCtrl: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.formDataService.getStoredFormData().subscribe(userDetails => {
      this.storedData = userDetails;
      console.log('Stored Data:', this.storedData);
    });

    this.rdvService.getAvailableSlots().subscribe(
      (data: Date[]) => {
        this.slots = data;
      },
      (error) => {
        console.error('Error fetching available slots', error);
      }
    );
  }

  sendVerificationEmail(email: string): void {
    this.emailVerificationService.sendVerificationToken(email).subscribe({
      next: (response) => {
        console.log('Full response:', response);
        if (response.status === 200) {
          console.log('Verification email sent successfully:', response.body);
          this.snackBar.open('Verification email sent successfully!', 'Close', {
            duration: 3000,
            panelClass: ['success-snackbar']
          });
        } else {
          console.error('Unexpected status code:', response.status);
          this.snackBar.open('Failed to send verification email. Please try again.', 'Close', {
            duration: 3000,
            panelClass: ['error-snackbar']
          });
        }
      },
      error: (error) => {
        console.error('Error sending verification email:', error);
        this.snackBar.open('Failed to send verification email. Please try again.', 'Close', {
          duration: 3000,
          panelClass: ['error-snackbar']
        });
      }
    });
  }

  sendOtp(): void {
    let phoneNumber = localStorage.getItem('phoneNumber');
    if (phoneNumber) {
      phoneNumber = '+216' + phoneNumber; // Add the +216 prefix
      const formValues = {
        phoneNumber:phoneNumber}
      this.sendVerificationPhoneNumber( formValues);
      console.log('Code sent');
    } else {
      console.error('Stored data is undefined or phone number is missing');
    }
  }
  
  sendVerificationPhoneNumber(phoneNumberObj: { phoneNumber: string }): void {
    this.emailVerificationService.sendOtp(phoneNumberObj).subscribe({
      next: (response) => {
        if (response.status === 200) {
          console.log('OTP sent successfully:', response.body);
          this.snackBar.open('OTP sent successfully!', 'Close', {
            duration: 3000,
            panelClass: ['success-snackbar']
          });
        } else {
          console.error('Unexpected status code:', response.status);
          this.snackBar.open('Failed to send OTP. Please try again.', 'Close', {
            duration: 3000,
            panelClass: ['error-snackbar']
          });
        }
      },
      error: (error) => {
        console.error('Error sending OTP:', error);
        console.error("aaaaaaaaaa");

        this.snackBar.open(error.error.text, 'Close', {
          duration: 3000,
          panelClass: ['error-snackbar']
        });
      }
    });
  }
  
  
  verifyToken(): void {
    const token = this.codeForm.get('code')?.value;
    if (token) {
      this.emailVerificationService.verifyToken(token).subscribe({
        next: (response) => {
          if (response.status === 200) {
          console.log('Token verified successfully:', response);
          this.snackBar.open('Token verified successfully!', 'Close', {
            duration: 3000,
            panelClass: ['success-snackbar']
          });}else{
            console.error('Unexpected status code:', response.status);
            this.snackBar.open('Failed to verifie token .', 'Close', {
              duration: 3000,
              panelClass: ['error-snackbar']
            });
          }
        },
        error: (error) => {
          console.error('Error verifying token:', error.status);
          if(error.status==200){
            this.snackBar.open('Token verified successfully!', 'Close', {
              duration: 3000,
              panelClass: ['success-snackbar']});
          }else{
          this.snackBar.open('Failed to verify token. Please try again.', 'Close', {
            duration: 3000,
            panelClass: ['error-snackbar']
          });
        }}
      });
    } else {
      console.error('Token is missing');
      this.snackBar.open('Please enter the verification code.', 'Close', {
        duration: 3000,
        panelClass: ['error-snackbar']
      });
    }
  }

  sendCode(): void {
    const email = localStorage.getItem('email');
    if (email) {
      this.sendVerificationEmail(email);
      console.log('Code sent');
    } else {
      console.error('Stored data is undefined or email is missing');
    }
  }



  verifyTokenOtp(): void {
    const otp = this.codeForm.get('code')?.value;
    const phoneNumber = localStorage.getItem('phoneNumber');
  
    if (otp && phoneNumber) {
      const prefixedPhoneNumber = `+216${phoneNumber}`;
  
      const body = {
        phoneNumber: prefixedPhoneNumber,
        otp: otp
      };
  
      this.emailVerificationService.verifyOtp(body).subscribe({
        next: (response) => {
          if (response === 200) {
            console.log('OTP verified successfully:', response);
            this.snackBar.open('OTP verified successfully!', 'Close', {
              duration: 3000,
              panelClass: ['success-snackbar']
            });
          } else {
            console.error('Unexpected status code:', response);
            this.snackBar.open('Failed to verify OTP.', 'Close', {
              duration: 3000,
              panelClass: ['error-snackbar']
            });
          }
        },
        error: (error) => {
          console.error('Error verifying OTP:', error);
          this.snackBar.open(error.error.text, 'Close', {
            duration: 3000,
            panelClass: ['error-snackbar']
          });
        }
      });
    } else {
      console.error('OTP or phone number is missing');
      this.snackBar.open('Please enter the OTP and ensure your phone number is available.', 'Close', {
        duration: 3000,
        panelClass: ['error-snackbar']
      });
    }
  }
  

  selectSlot(slot: Date): void {
    this.selectedSlot = slot;
  }

  confirmSelection(): void {
    if (this.selectedSlot) {
      const email = localStorage.getItem('email'); // Retrieve email from local storage
      if (email) {
        this.rdvService.bookSlot(this.selectedSlot, email).subscribe(
          response => {
            console.log('Booking confirmed:', response);
            alert('Slot booked successfully!');
            // Optionally, navigate to another page or reset the form
          },
          error => {
            console.error('Error booking slot', error);
            alert('There was an error booking the slot.');
          }
        );
      } else {
        alert('Email not found in local storage.');
      }
    } else {
      alert('Please select a slot.');
    }
  }

  // sendOtp(): void {
  //   const phoneNumber = localStorage.getItem('phoneNumber');
  //   if (phoneNumber) {
  //     this.sendOtpToPhoneNumber(phoneNumber);
  //     console.log('OTP sent to phone number');
  //   } else {
  //     console.error('Phone number is missing from local storage');
  //   }
  // }
}
