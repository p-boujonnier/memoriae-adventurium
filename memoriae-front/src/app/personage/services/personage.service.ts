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
      .pipe(map((response) => response.data ?? null));
  }

  create(dto: PersonageCreateRequest): Observable<PersonageResponse> {
    const created = { id: crypto.randomUUID(), ...dto };
    this.mockData.push(created);
    return of(created);
  }

  update(id: string, dto: PersonageUpdateRequest): Observable<PersonageResponse> {
    const index = this.mockData.findIndex((p) => p.id === id);
    this.mockData[index] = { ...this.mockData[index], ...dto };
    return of(this.mockData[index]);
  }

  delete(id: string): Observable<void> {
    this.mockData = this.mockData.filter((p) => p.id !== id);
    return of(void 0);
  }
}
