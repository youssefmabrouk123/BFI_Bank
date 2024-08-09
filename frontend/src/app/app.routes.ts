import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { MonCompteComponent } from './mon-compte/mon-compte.component';
import { NousContacterComponent } from './nous-contacter/nous-contacter.component';
import { OuvrirCompteComponent } from './ouvrir-compte/ouvrir-compte.component';
import { TestComponent } from './test/test.component';
import { SignatureComponent } from './signature/signature.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './auth.guard'; // Import your AuthGuard

export const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'login', component: LoginComponent },
    { path: 'mon-compte', component: MonCompteComponent, canActivate: [AuthGuard] }, // Protect this route
    { path: 'nous-contacter', component: NousContacterComponent },
    { path: 'ouvrir-compte', component: OuvrirCompteComponent },
    { path: 'test', component: TestComponent },
    { path: 'signature', component: SignatureComponent }
];
