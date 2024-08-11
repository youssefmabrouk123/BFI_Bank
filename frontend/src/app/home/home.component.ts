import { Component } from '@angular/core';
import { HeroComponent } from '../hero/hero.component';
import { MainComponent } from '../main/main.component';
import { FooterComponent } from '../footer/footer.component';
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [HeroComponent,MainComponent,FooterComponent,HeaderComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
