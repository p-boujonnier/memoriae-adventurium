import { Routes } from '@angular/router';
import { HomeComponent } from './common/components/home/home.component';
import { LoginComponent } from './account/auth/components/login/login.component';
import { RegisterComponent } from './account/auth/components/register/register.component';
import { ErrorComponent } from './common/components/error/error.component';
import { userRoutes } from './account/user/user.routes';
import { personageRoutes } from './personage/personage.routes';

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  ...userRoutes,
  ...personageRoutes,
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
