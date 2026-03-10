import { HttpInterceptorFn, HttpErrorResponse } from '@angular/common/http';
import { inject } from '@angular/core';
import { catchError, switchMap, throwError } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

export const authInterceptor: HttpInterceptorFn = (request, next) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  const token = authService.getAccessToken();

  const authRequest = token ? request.clone({ setHeaders: { Authorization: `Bearer ${token}` } }) : request;

  return next(authRequest).pipe(
    catchError((error: HttpErrorResponse) => {
      if (error.status === 401) {
        return authService.refresh().pipe(
          switchMap((response) => {
            if (response.data?.accessToken) {
              const retryRequest = request.clone({
                setHeaders: { Authorization: `Bearer ${response.data.accessToken}` },
              });
              return next(retryRequest);
            }
            authService.logout();
            return throwError(() => error);
          }),
          catchError(() => {
            authService.logout();
            return throwError(() => error);
          }),
        );
      }
      return throwError(() => error);
    }),
  );
};
