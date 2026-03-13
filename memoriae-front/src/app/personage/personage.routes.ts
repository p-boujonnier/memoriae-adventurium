import { Routes } from '@angular/router';
import { authGuard } from '../account/auth/guards/auth.guard';
import { PersonageListComponent } from './components/personage-list/personage-list.component';
import { PersonageDetailComponent } from './components/personage-detail/personage-detail.component';
import { PersonageFormComponent } from './components/personage-form/personage-form.component';

export const personageRoutes: Routes = [
  { path: 'personages', component: PersonageListComponent, canActivate: [authGuard] },
  { path: 'personages/new', component: PersonageFormComponent, canActivate: [authGuard] },
  { path: 'personages/:id', component: PersonageDetailComponent, canActivate: [authGuard] },
  { path: 'personages/:id/edit', component: PersonageFormComponent, canActivate: [authGuard] },
];
