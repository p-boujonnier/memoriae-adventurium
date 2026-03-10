import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ServiceResponse } from '../../../common/models/service-response.model';
import { UserResponse } from '../models/dto/user-response.dto';
import { map, Observable } from 'rxjs';
import { UserCreateRequest } from '../models/dto/user-create-request.dto';
import { UserUpdateRequest } from '../models/dto/user-update-request.dto';
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private readonly API = '/api/users';

  constructor(private http: HttpClient) {}

  findAll() {
    return this.http
      .get<ServiceResponse<UserResponse[]>>(this.API)
      .pipe(map((response) => response.data ?? []));
  }

  findById(id: string) {
    return this.http
      .get<ServiceResponse<UserResponse>>(`${this.API}/${id}`)
      .pipe(map((response) => response.data));
  }

  create(dto: UserCreateRequest): Observable<UserResponse | null> {
    return this.http
      .post<ServiceResponse<UserResponse>>(this.API, dto)
      .pipe(map((response) => response.data));
  }

  update(dto: UserUpdateRequest): Observable<UserResponse | null> {
    return this.http
      .put<ServiceResponse<UserResponse>>(this.API, dto)
      .pipe(map((response) => response.data));
  }

  delete(id: string): Observable<void | null> {
    return this.http
      .delete<ServiceResponse<void>>(`${this.API}/${id}`)
      .pipe(map((response) => response.data));
  }
}
