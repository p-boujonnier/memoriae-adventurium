import { Component } from '@angular/core';
import { ButtonComponent } from '../../../common/components/button/button.component';
import { FormsModule } from '@angular/forms';
import { UserCreateRequest } from '../../../account/user/models/dto/user-create-request.dto';
import { Personage } from '../../models/personage.model';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  standalone: true,
  imports: [ButtonComponent, FormsModule],
  selector: 'app-personage-form',
  templateUrl: './personage-form.component.html',
})
export class PersonageFormComponent {
  isEditMode = false;

  createDTO = { firstName: '', lastName: '' };
  updateDTO = { firstName: '', lastName: '' };

  constructor(
    private router: Router,
    private route: ActivatedRoute,
  ) {
    this.isEditMode = !!this.route.snapshot.paramMap.get('id');
  }

  submit(): void {}

  cancel(): void {
    this.router.navigate(['/personages']);
  }
}
