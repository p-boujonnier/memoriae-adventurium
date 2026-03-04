import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { catchError, throwError } from 'rxjs';
import { ServiceResponse } from '../models/service-response.model';

export const httpErrorInterceptor: HttpInterceptorFn = (req, next) => {
  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      const response: ServiceResponse<null> = error.error ??{
        code: error.status.toString(),
        message: 'une erreur est survenue',
        data: null
      };
      return throwError(() => new Error(JSON.stringify(response)));
    })
  );
};
