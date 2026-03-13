import { ChangeDetectorRef, Component } from '@angular/core';
import { ButtonComponent } from '../../../common/components/button/button.component';
import { Router, RouterLink } from '@angular/router';
import { PersonageService } from '../../services/personage.service';
import { PersonageResponse } from '../../models/dtos/personage-response.dto';

@Component({
  standalone: true,
  imports: [ButtonComponent, RouterLink],
  selector: 'personage-list',
  templateUrl: './personage-list.component.html',
})
export class PersonageListComponent {
  personages: PersonageResponse[] = [];

  constructor(
    private personageService: PersonageService,
    private router: Router,
    private cdr: ChangeDetectorRef,
  ) {}

  ngOnInit(): void {
    this.personageService.findAll().subscribe((data) => {
      this.personages = data;
      this.cdr.detectChanges();
    });
  }

  onCreate(): void {
    this.router.navigate(['/personages/new']);
  }

  onEdit(id: string): void {
    this.router.navigate(['/personages', id, 'edit']);
  }

  onDelete(id: string): void {
    this.personageService.delete(id).subscribe(() => {
      this.personages = this.personages.filter((personage) => personage.id !== id);
      this.cdr.detectChanges();
      this.router.navigate(['/personages']);
    });
  }
}
