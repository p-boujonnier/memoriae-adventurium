import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { ServiceResponse } from '../../../common/models/service-response.model';
import { AuthLoginRequestDto } from '../models/dto/auth-login-request.dto';
import { AuthRegisterRequestDto } from '../models/dto/auth-register-request.dto';
import { AuthResponseDto } from '../models/dto/auth-response.dto';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  // Constants
  private readonly API = `${environment.apiUrl}/api/auth`;

  // Injections
  private currentUserSubject = new BehaviorSubject<AuthResponseDto | null>(null);
  currentUser$ = this.currentUserSubject.asObservable();

  // Constuctor
  constructor(
    private http: HttpClient,
    private router: Router,
  ) {}

  // Methods
  login(request: AuthLoginRequestDto): Observable<ServiceResponse<AuthResponseDto>> {
    return this.http.post<ServiceResponse<AuthResponseDto>>(`${this.API}/login`, request).pipe(
      tap((response) => {
        if (response.data) this.currentUserSubject.next(response.data);
      }),
    );
  }

  register(request: AuthRegisterRequestDto): Observable<ServiceResponse<AuthResponseDto>> {
    return this.http
      .post<
        ServiceResponse<AuthResponseDto>
      >(`${this.API}/register`, request, { withCredentials: true })
      .pipe(
        tap((response) => {
          if (response.data) this.currentUserSubject.next(response.data);
        }),
      );
  }

  refresh(): Observable<ServiceResponse<AuthResponseDto>> {
    return this.http
      .post<ServiceResponse<AuthResponseDto>>(`${this.API}/refresh`, {}, { withCredentials: true })
      .pipe(
        tap((response) => {
          if (response.data) this.currentUserSubject.next(response.data);
        }),
      );
  }

  me(): Observable<ServiceResponse<AuthResponseDto>> {
    return this.http.get<ServiceResponse<AuthResponseDto>>(`${this.API}/me`, {
      withCredentials: true,
    });
  }

  logout(): void {
    this.http.post<void>(`${this.API}/logout}`, {}, { withCredentials: true });
  }

  getAccessToken(): string | null {
    return this.currentUserSubject.value?.accessToken ?? null;
  }

  isLoggedIn(): boolean {
    return this.currentUserSubject.value !== null;
  }
}
