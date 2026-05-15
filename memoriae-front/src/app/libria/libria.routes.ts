import { Routes } from '@angular/router';
import { authGuard } from '../account/auth/guards/auth.guard';
import {LibriaBrowserComponent} from './components/libria-browser/libria-browser.component';
import {LibriaDetailComponent} from './components/libria-detail/libria-detail.component';

export const libriaRoutes: Routes = [
  { path: 'libria', component: LibriaBrowserComponent, canActivate: [authGuard] },
  { path: 'libria/:type/:id', component: LibriaDetailComponent }

];
