import { Component } from '@angular/core';
import { Personage } from '../../models/personage.model';
import { ButtonComponent } from '../../../common/components/button/button.component';
import { Router, RouterLink } from '@angular/router';

@Component({
  standalone: true,
  imports: [ButtonComponent],
  selector: 'personage-detail',
  templateUrl: './personage-detail.component.html',
})
export class PersonageDetailComponent {
  personage: Personage = {
    id: '123',
    firstName: 'Saria',
    lastName: 'Floraison des Cendres',
  };

  constructor(private router: Router) {}

  onEdit(): void {
  }

  onBack(): void {
    this.router.navigate(['/personages']);
  }
}
