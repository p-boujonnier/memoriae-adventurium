import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { filter, map, take } from 'rxjs';

export const authGuard: CanActivateFn = () => {
  const authService = inject(AuthService);
  const router = inject(Router);

  return authService.isInitialized$.pipe(
    filter((initialized) => initialized),
    take(1),
    map(() => {
      if (authService.isLoggedIn()) {
        return true;
      }
      router.navigate(['/login']);
      return false;
    }),
  );
};
