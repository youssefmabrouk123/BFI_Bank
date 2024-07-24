import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { MonCompteComponent } from './mon-compte/mon-compte.component';
import { NousContacterComponent } from './nous-contacter/nous-contacter.component';
import { OuvrirCompteComponent } from './ouvrir-compte/ouvrir-compte.component';
import { TestComponent } from './test/test.component';
import { SignatureComponent } from './signature/signature.component';

export const routes: Routes = [
    {path:'' , component:HomeComponent},
    {path:'mon-compte' , component:MonCompteComponent},
    {path:'nous-contacter' , component:NousContacterComponent},
    {path:'ouvrir-compte' , component:OuvrirCompteComponent},
    {path:'test' , component:TestComponent},
    {path:'signature' , component:SignatureComponent}



];
