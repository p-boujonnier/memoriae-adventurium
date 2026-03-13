import { Component } from '@angular/core';
import { Personage } from '../../models/personage.model';
import { ButtonComponent } from '../../../common/components/button/button.component';
import { Router, RouterLink } from '@angular/router';

@Component({
  standalone: true,
  imports: [ButtonComponent, RouterLink],
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

  onCreate(): void {
    this.router.navigate(['/personages/new']);
  }

  onEdit(id: string): void {
    this.router.navigate(['/personages', id, 'edit']);
  }

  onDelete(id: string): void {}
}
