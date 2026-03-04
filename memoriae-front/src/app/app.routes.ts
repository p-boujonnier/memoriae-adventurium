import { Routes } from '@angular/router';
import { UserListComponent } from './account/user/components/user-list/user-list.component';
import { UserFormComponent } from './account/user/components/user-form/user-form.component';
import { UserDetailComponent } from './account/user/components/user-detail/user-detail.component';

export const routes: Routes = [
  { path: '', redirectTo: 'users', pathMatch: 'full' },
  { path: 'users', component: UserListComponent},
  { path: 'users/new', component: UserFormComponent},
  { path: 'users/:id', component: UserDetailComponent},
  { path: 'users/:id/edit', component: UserFormComponent}
];
