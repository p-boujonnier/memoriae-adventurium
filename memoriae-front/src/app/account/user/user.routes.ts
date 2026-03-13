import { Routes } from '@angular/router';
import { authGuard } from '../auth/guards/auth.guard';
import { UserListComponent } from './components/user-list/user-list.component';
import { UserFormComponent } from './components/user-form/user-form.component';
import { UserDetailComponent } from './components/user-detail/user-detail.component';

export const userRoutes: Routes = [
  { path: 'users', component: UserListComponent, canActivate: [authGuard] },
  { path: 'users/new', component: UserFormComponent, canActivate: [authGuard] },
  { path: 'users/:id', component: UserDetailComponent, canActivate: [authGuard] },
  { path: 'users/:id/edit', component: UserFormComponent, canActivate: [authGuard] },
];
