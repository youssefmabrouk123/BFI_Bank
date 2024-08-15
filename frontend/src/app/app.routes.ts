import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { MonCompteComponent } from './mon-compte/mon-compte.component';
import { NousContacterComponent } from './nous-contacter/nous-contacter.component';
import { OuvrirCompteComponent } from './ouvrir-compte/ouvrir-compte.component';

import { SignatureComponent } from './signature/signature.component';
import { LoginComponent } from './login/login.component';
import { authGuard } from './auth.guard';
import { EditComponent } from './edit/edit.component';
import { ListeDemandesComponent } from './liste-demandes/liste-demandes.component';
import { CompteAdminComponent } from './compte-admin/compte-admin.component';
import { SignatureEnLigneComponent } from './signature-en-ligne/signature-en-ligne.component';
import { FelicitationComponent } from './felicitation/felicitation.component';

export const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'nous-contacter', component: NousContacterComponent },
    { path: 'ouvrir-compte', component: OuvrirCompteComponent },
    { path: 'signature', component: SignatureComponent },
    { path: 'login', component: LoginComponent },
    { path: 'mon-compte', component: MonCompteComponent },
    { path: 'edit-profile', component: EditComponent },
    { path: 'liste-demandes', component: ListeDemandesComponent },
    { path: 'compte-admin', component: CompteAdminComponent },
    { path: 'signature-en-ligne', component: SignatureEnLigneComponent },
    { path: 'felicitation', component: FelicitationComponent },
    { path: '**', redirectTo: '/login' } // Wildcard route for a 404 page
];


