import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ButtonComponent } from '../../../common/components/button/button.component';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PersonageCreateRequest } from '../../models/dtos/personage-create-request.dto';
import { PersonageUpdateRequest } from '../../models/dtos/personage-update-request.dto';
import { PersonageService } from '../../services/personage.service';

@Component({
  selector: 'app-personage-form',
  standalone: true,
  imports: [FormsModule, ButtonComponent],
  templateUrl: './personage-form.component.html',
})
export class PersonageFormComponent implements OnInit {
  id: string | null = null;

  createDTO: PersonageCreateRequest = {
    firstName: '',
    lastName: '',
  };

  updateDTO: PersonageUpdateRequest = {
    firstName: '',
    lastName: '',
  };

  isEditMode = false;

  errorMessages: { field: string; message: string }[] = [];

  constructor(
    private personageService: PersonageService,
    private route: ActivatedRoute,
    private router: Router,
    private cdr: ChangeDetectorRef,
  ) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    if (this.id) {
      this.isEditMode = true;
      this.personageService.findById(this.id).subscribe((personage) => {
        if (personage) {
          this.updateDTO.firstName = personage.firstName;
          this.updateDTO.lastName = personage.lastName;
        }
      });
    }
  }

  submit(): void {
    if (this.isEditMode) {
      // Modification form
      this.personageService.update(this.id!, this.updateDTO).subscribe({
        next: () => this.router.navigate(['/personages']),
        error: (err) => {
          this.errorMessages = err.data ?? [];
          this.cdr.detectChanges();
        },
      });
    } else {
      // Creation form
      this.personageService.create(this.createDTO).subscribe({
        next: () => this.router.navigate(['/personages']),
        error: (err) => {
          this.errorMessages = err.data ?? [];
          this.cdr.detectChanges();
        },
      });
    }
  }

  cancel(): void {
    this.router.navigate(['/personages']);
  }
}
