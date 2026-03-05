import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { UserCreateRequest } from '../../models/dto/user-create-request.dto';
import { UserUpdateRequest } from '../../models/dto/user-update-request.dto';

@Component({
  selector: 'app-user-form',
  standalone: true,
  imports: [FormsModule],
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

  errorMessage: string | null = null;

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    protected router: Router,
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
    this.errorMessage = null;
    if (this.isEditMode) {
      this.userService.update(this.updateDTO).subscribe({
        next: () => this.router.navigate(['/users']),
        error: (err) => (this.errorMessage = err.message),
      });
    } else {
      this.userService.create(this.createDTO).subscribe({
        next: () => this.router.navigate(['/users']),
        error: (err) => (this.errorMessage = err.message),
      });
    }
  }

  cancel(): void {
    this.router.navigate(['/users']);
  }
}
