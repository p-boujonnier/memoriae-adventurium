import {Component, inject, OnInit, signal} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PdfViewerModule} from 'ng2-pdf-viewer';
import {ActivatedRoute} from '@angular/router';
import {LibriaService} from '../../services/libria.service';
import {MagazineDetail} from '../../models/magazine.model';

@Component({
  selector: 'app-libria-detail',
  standalone: true,
  imports: [CommonModule, PdfViewerModule],
  templateUrl: './libria-detail.component.html',
})
export class LibriaDetailComponent implements OnInit {
  private readonly route = inject(ActivatedRoute);
  private readonly libriaService = inject(LibriaService);

  detail = signal<MagazineDetail | null>(null);

  ngOnInit() {
    const type = this.route.snapshot.url[1].path;
    const id = Number(this.route.snapshot.params['id']);

    const request$ = type === 'dragons'
      ? this.libriaService.getDragon(id)
      : this.libriaService.getDungeon(id);

    request$.subscribe(data => this.detail.set(data));
  }
}
