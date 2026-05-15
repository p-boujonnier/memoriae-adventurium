import {Component, inject, OnInit, signal} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {LibriaService} from '../../services/libria.service';
import {MagazineListItem} from '../../models/magazine.model';
import {RouterLink} from '@angular/router';

@Component({
  selector:'app-libria-browser',
  standalone:true,
  imports: [CommonModule, RouterLink],
  templateUrl:'./libria-browser.component.html',
})
export class LibriaBrowserComponent implements OnInit {
  private readonly libriaService = inject(LibriaService);

  dragons = signal<MagazineListItem[]>([]);
  dungeons = signal<MagazineListItem[]>([]);

  ngOnInit(): void {
    console.log('ngOnInit');
    this.libriaService.getDragons().subscribe(data => {
      console.log(data);
      this.dragons.set(data);
    });
    this.libriaService.getDungeons().subscribe(data => this.dungeons.set(data));
  }
}
