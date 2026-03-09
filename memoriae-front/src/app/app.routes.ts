import { Routes } from '@angular/router';
import { UserListComponent } from './account/user/components/user-list/user-list.component';
import { UserFormComponent } from './account/user/components/user-form/user-form.component';
import { UserDetailComponent } from './account/user/components/user-detail/user-detail.component';
import { LoginComponent } from './account/auth/components/login/login.component';
import { RegisterComponent } from './account/auth/components/register/register.component';

export const routes: Routes = [
  { path: '', redirectTo: 'users', pathMatch: 'full' },
  { path: 'users', component: UserListComponent},
  { path: 'users/new', component: UserFormComponent},
  { path: 'users/:id', component: UserDetailComponent},
  { path: 'users/:id/edit', component: UserFormComponent},
  { path: 'login', component: LoginComponent},
  { path: 'register', component: RegisterComponent },
];
