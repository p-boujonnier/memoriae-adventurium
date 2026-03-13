import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ButtonComponent } from '../../../common/components/button/button.component';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { PersonageResponse } from '../../models/dtos/personage-response.dto';
import { PersonageService } from '../../services/personage.service';

@Component({
  selector: 'personage-detail',
  standalone: true,
  imports: [ButtonComponent],
  templateUrl: './personage-detail.component.html',
})
export class PersonageDetailComponent implements OnInit {
  personage : PersonageResponse | null = null;

  constructor(
    private personageService: PersonageService,
    private route: ActivatedRoute,
    private router: Router,
    private cdr: ChangeDetectorRef,) {}

  ngOnInit(): void {
    const personageId = this.route.snapshot.paramMap.get('id');
    if (personageId) {
      this.personageService.findById(personageId).subscribe((data) => {
        this.personage = data;
        this.cdr.detectChanges();
      });
    }
  }

  onEdit(): void {
    this.router.navigate(['/personages', this.personage?.id, 'edit']);
  }

  onBack(): void {
    this.router.navigate(['/personages']);
  }
}
