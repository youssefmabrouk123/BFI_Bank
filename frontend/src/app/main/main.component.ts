import { Component } from '@angular/core';
import { AboutUsComponent } from '../about-us/about-us.component';
import { CtaComponent } from '../cta/cta.component';
import { FeaturesComponent } from '../features/features.component';
import { CountsComponent } from '../counts/counts.component';
import { FaqComponent } from '../faq/faq.component';
import { ContactComponent } from '../contact/contact.component';

@Component({
  selector: 'app-main',
  standalone: true,
  imports: [AboutUsComponent,CtaComponent,FeaturesComponent,CountsComponent,FaqComponent,ContactComponent],
  templateUrl: './main.component.html',
  styleUrl: './main.component.css'
})
export class MainComponent {

}
