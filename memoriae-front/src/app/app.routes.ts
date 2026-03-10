import { Routes } from '@angular/router';
import { UserListComponent } from './account/user/components/user-list/user-list.component';
import { UserFormComponent } from './account/user/components/user-form/user-form.component';
import { UserDetailComponent } from './account/user/components/user-detail/user-detail.component';
import { LoginComponent } from './account/auth/components/login/login.component';
import { RegisterComponent } from './account/auth/components/register/register.component';
import { authGuard } from './account/auth/guards/auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: 'users', pathMatch: 'full' },
  { path: 'users', component: UserListComponent, canActivate: [authGuard]},
  { path: 'users/new', component: UserFormComponent, canActivate: [authGuard]},
  { path: 'users/:id', component: UserDetailComponent, canActivate: [authGuard]},
  { path: 'users/:id/edit', component: UserFormComponent, canActivate: [authGuard]},
  { path: 'login', component: LoginComponent},
  { path: 'register', component: RegisterComponent },
];
