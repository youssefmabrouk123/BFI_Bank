import { Component, OnInit } from '@angular/core';
import { RecaptchaModule } from 'ng-recaptcha';
import { FooterComponent } from '../footer/footer.component';
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-nous-contacter',
  standalone: true,
  imports: [RecaptchaModule,FooterComponent,HeaderComponent],
  templateUrl: './nous-contacter.component.html',
  styleUrl: './nous-contacter.component.css'
})
export class NousContacterComponent implements OnInit{

  captcha: string;
  email: string;

  constructor(){
    this.captcha = '';
    this.email = 'email@gmail.com';
  }

  ngOnInit(): void {
  }
  resolved(captchaResponse: string | null) {
    if (captchaResponse) {
      this.captcha = captchaResponse;
      console.log('resolved captcha with response: ' + this.captcha);
    }
  }
}


