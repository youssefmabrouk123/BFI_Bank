import { Component, LOCALE_ID } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterOutlet } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { RecaptchaModule } from 'ng-recaptcha';
import { AuthService } from './auth.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { ToastModule } from 'primeng/toast';
import { CarteBancaireComponent } from "./carte-bancaire/carte-bancaire.component";
import { authGuard } from './auth.guard';
import { ReactiveFormsModule } from '@angular/forms';
import { FormDataService } from './form-data.service';
import { HttpClientModule } from '@angular/common/http';
import { SignatureEnLigneComponent } from './signature-en-ligne/signature-en-ligne.component';
import { CompteAdminComponent } from './compte-admin/compte-admin.component';
import { ClientService } from './client.service';
import { SignatureComponent } from './signature/signature.component';




@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    HeaderComponent,
    FooterComponent,
    RecaptchaModule,
    HttpClientModule,
    //BrowserAnimationsModule,
    ToastModule,
    CarteBancaireComponent,
    ReactiveFormsModule,
    SignatureEnLigneComponent,
    CompteAdminComponent,
    HttpClientModule,
    SignatureComponent,
    

  
     
   
    
],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [AuthService ,FormDataService,ClientService]
})
export class AppComponent {
  title = 'frontend';
}
