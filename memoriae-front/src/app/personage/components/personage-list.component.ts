import { Component } from '@angular/core';
import { Personage } from '../models/personage.model';
import { ButtonComponent } from '../../common/components/button/button.component';
import { Router } from '@angular/router';

@Component({
  standalone: true,
  imports: [ButtonComponent],
  selector: 'personage-list',
  templateUrl: './personage-list.component.html',
})
export class PersonageListComponent {
  personages: Personage[] = [
    { id: '123', firstName: 'John', lastName: 'Doe' },
    { id: '789', firstName: 'Jane', lastName: 'Doe' },
    { id: '101112', firstName: 'Alice', lastName: 'Smith' },
  ];

  constructor(private router: Router) {}


  onCreate(): void {}

  onEdit(id: string): void {}

  onDelete(id: string): void {}
}
