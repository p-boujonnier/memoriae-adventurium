import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { UserCreateRequest } from '../../models/dto/user-create-request.dto';
import { UserUpdateRequest } from '../../models/dto/user-update-request.dto';
import { ButtonComponent } from '../../../../common/components/button/button.component';

@Component({
  selector: 'app-user-form',
  standalone: true,
  imports: [FormsModule, ButtonComponent],
  templateUrl: './user-form.component.html',
})
export class UserFormComponent implements OnInit {
  createDTO: UserCreateRequest = {
    pseudo: '',
    email: '',
    password: '',
  };

  updateDTO: UserUpdateRequest = {
    id: '',
    pseudo: '',
    email: '',
  };

  isEditMode = false;

  errorMessages: { field: string; message: string }[] = [];

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router,
    private cdr: ChangeDetectorRef,
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.userService.findById(id).subscribe((user) => {
        if (user) {
          this.updateDTO.id = user.id;
          this.updateDTO.pseudo = user.pseudo;
          this.updateDTO.email = user.email;
        }
      });
    }
  }

  submit(): void {
    if (this.isEditMode) {
      // Modification form
      this.userService.update(this.updateDTO).subscribe({
        next: () => this.router.navigate(['/users']),
        error: (err) => {
          this.errorMessages = err.data ?? [];
          this.cdr.detectChanges();
        },
      });
    } else {
      // Creation form
      this.userService.create(this.createDTO).subscribe({
        next: () => this.router.navigate(['/users']),
        error: (err) => {
          console.log(err.data);
          this.errorMessages = err.data ?? [];
          this.cdr.detectChanges();
        },
      });
    }
  }

  cancel(): void {
    this.router.navigate(['/users']);
  }
}
