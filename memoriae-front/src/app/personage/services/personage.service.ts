import { Injectable } from '@angular/core';
import { map, Observable, of } from 'rxjs';
import { PersonageResponse } from '../models/dtos/personage-response.dto';
import { PersonageCreateRequest } from '../models/dtos/personage-create-request.dto';
import { PersonageUpdateRequest } from '../models/dtos/personage-update-request.dto';
import { HttpClient } from '@angular/common/http';
import { ServiceResponse } from '../../common/models/service-response.model';

@Injectable({
  providedIn: 'root',
})
export class PersonageService {
  private readonly API = '/api/personages';
  private mockData: PersonageResponse[] = [
    { id: '123', firstName: 'John', lastName: 'Doe' },
    { id: '789', firstName: 'Jane', lastName: 'Doe' },
    { id: '101112', firstName: 'Alice', lastName: 'Smith' },
  ];

  constructor(private http: HttpClient) {}

  findAll(): Observable<PersonageResponse[]> {
    return this.http
      .get<ServiceResponse<PersonageResponse[]>>(this.API)
      .pipe(map((response) => response.data ?? []));
  }

  findById(id: string): Observable<PersonageResponse | null> {
    return this.http
      .get<ServiceResponse<PersonageResponse>>(`${this.API}/${id}`)
      .pipe(map((response) => response.data));
  }

  create(dto: PersonageCreateRequest): Observable<PersonageResponse | null> {
    return this.http
      .post<ServiceResponse<PersonageResponse>>(this.API, dto)
      .pipe(map((response) => response.data));
  }

  update(id: string, dto: PersonageUpdateRequest): Observable<PersonageResponse | null> {
    return this.http
      .put<ServiceResponse<PersonageResponse>>(`${this.API}/${id}`, dto)
      .pipe(map((response) => response.data));
  }

  delete(id: string): Observable<void | null> {
    return this.http
      .delete<ServiceResponse<void>>(`${this.API}/${id}`)
      .pipe(map((response) => response.data));
  }
}
