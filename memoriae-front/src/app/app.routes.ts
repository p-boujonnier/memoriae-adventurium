import { Routes } from '@angular/router';
import { UserListComponent } from './account/user/components/user-list/user-list.component';
import { UserFormComponent } from './account/user/components/user-form/user-form.component';
import { UserDetailComponent } from './account/user/components/user-detail/user-detail.component';
import { LoginComponent } from './account/auth/components/login/login.component';
import { RegisterComponent } from './account/auth/components/register/register.component';
import { authGuard } from './account/auth/guards/auth.guard';
import { HomeComponent } from './common/components/home/home.component';
import { ErrorComponent } from './common/components/error/error.component';
import { PersonageListComponent } from './personage/components/personage-list.component';

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'users', component: UserListComponent, canActivate: [authGuard] },
  { path: 'users/new', component: UserFormComponent, canActivate: [authGuard] },
  { path: 'users/:id', component: UserDetailComponent, canActivate: [authGuard] },
  { path: 'users/:id/edit', component: UserFormComponent, canActivate: [authGuard] },
  { path: 'personages', component: PersonageListComponent, canActivate: [authGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {
    path: '**',
    component: ErrorComponent,
    data: {
      code: 404,
      title: 'Page introuvable',
      message: "Le parchemin que vous cherchez n'existe pas",
    },
  },
];
