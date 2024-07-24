import { Component } from '@angular/core';
import { AboutUsComponent } from '../about-us/about-us.component';
import { ServicesComponent } from '../services/services.component';
import { CtaComponent } from '../cta/cta.component';
import { FeaturesComponent } from '../features/features.component';
import { ClientsComponent } from '../clients/clients.component';
import { CountsComponent } from '../counts/counts.component';
import { PortfolioComponent } from '../portfolio/portfolio.component';
import { PricingComponent } from '../pricing/pricing.component';
import { FaqComponent } from '../faq/faq.component';
import { ContactComponent } from '../contact/contact.component';

@Component({
  selector: 'app-main',
  standalone: true,
  imports: [AboutUsComponent,ServicesComponent,CtaComponent,FeaturesComponent,ClientsComponent,CountsComponent,PortfolioComponent,PricingComponent,FaqComponent,ContactComponent],
  templateUrl: './main.component.html',
  styleUrl: './main.component.css'
})
export class MainComponent {

}
