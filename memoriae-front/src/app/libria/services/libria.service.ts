import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {MagazineDetail, MagazineListItem} from '../models/magazine.model';

@Injectable({providedIn: 'root'})
export class LibriaService {
  private readonly http = inject(HttpClient);
  private readonly base = `${environment.apiUrl}/api/libria`;

  getDragons(): Observable<MagazineListItem[]> {
    return this.http.get<MagazineListItem[]>(`${this.base}/dragons`);
  }

  getDragon(id: number): Observable<MagazineDetail> {
    return this.http.get<MagazineDetail>(`${this.base}/dragons/${id}`);
  }

  getDungeons(): Observable<MagazineListItem[]> {
    return this.http.get<MagazineListItem[]>(`${this.base}/dungeons`);
  }

  getDungeon(id: number): Observable<MagazineDetail> {
    return this.http.get<MagazineDetail>(`${this.base}/dungeons/${id}`);
  }
}
