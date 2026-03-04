import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ServiceResponse } from '../../../common/service-response.model';
import { UserResponse } from '../models/dto/user-response.dto';
import { map, Observable } from 'rxjs';
import { UserCreateRequest } from '../models/dto/user-create-request.dto';
import { UserUpdateRequest } from '../models/dto/user-update-request.dto';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private apiUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) {}

  findAll() {
    console.log('CALL findAll ->', this.apiUrl);
    return this.http
      .get<ServiceResponse<UserResponse[]>>(this.apiUrl)
      .pipe(map((response) => response.data));
  }

  findById(id: string) {
    return this.http
      .get<ServiceResponse<UserResponse>>(`${this.apiUrl}/${id}`)
      .pipe(map((response) => response.data));
  }

  create(dto: UserCreateRequest): Observable<UserResponse> {
    return this.http
      .post<ServiceResponse<UserResponse>>(this.apiUrl, dto)
      .pipe(map((response) => response.data));
  }

  update(dto: UserUpdateRequest): Observable<UserResponse> {
    return this.http
      .put<ServiceResponse<UserResponse>>(this.apiUrl, dto)
      .pipe(map((response) => response.data));
  }

  delete(id: string): Observable<void> {
    return this.http
      .delete<ServiceResponse<void>>(`${this.apiUrl}/${id}`)
      .pipe(map((response) => response.data));
  }
}
