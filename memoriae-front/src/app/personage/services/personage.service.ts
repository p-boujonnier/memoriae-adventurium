import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { PersonageResponse } from '../models/dtos/personage-response.dto';
import { PersonageCreateRequest } from '../models/dtos/personage-create-request.dto';
import { PersonageUpdateRequest } from '../models/dtos/personage-update-request.dto';

@Injectable({
  providedIn: 'root',
})
export class PersonageService {
  private mockData: PersonageResponse[] = [
    { id: '123', firstName: 'John', lastName: 'Doe' },
    { id: '789', firstName: 'Jane', lastName: 'Doe' },
    { id: '101112', firstName: 'Alice', lastName: 'Smith' },
  ];

  findAll(): Observable<PersonageResponse[]> {
    return of(this.mockData);
  }

  findById(id: string): Observable<PersonageResponse | null> {
    return of(this.mockData.find((p) => p.id === id) ?? null);
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
