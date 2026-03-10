import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { httpErrorInterceptor } from './common/interceptors/http-error.interceptor';
import { authInterceptor } from './account/auth/interceptors/auth.interceptor';
import { routes } from './app.routes';

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideHttpClient(withInterceptors([httpErrorInterceptor, authInterceptor])),
  ],
};
