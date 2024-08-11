import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterOutlet } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { RecaptchaModule } from 'ng-recaptcha';
import { HttpClientModule } from '@angular/common/http';
import { AuthService } from './auth.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { ToastModule } from 'primeng/toast';
import { CarteBancaireComponent } from "./carte-bancaire/carte-bancaire.component";
import { authGuard } from './auth.guard';
import { ReactiveFormsModule } from '@angular/forms';



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
    ReactiveFormsModule
    
],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [AuthService ]
})
export class AppComponent {
  title = 'frontend';
}
