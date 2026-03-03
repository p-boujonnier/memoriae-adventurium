import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserCreateRequest } from '../../models/dto/user-create-request.dto';
import { UserService } from '../../services/user.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-user-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './user-form.component.html',
})
export class UserFormComponent implements OnInit {
  dto: UserCreateRequest = {
    pseudo: '',
    email: '',
    password: '',
  };

  isEditMode = false;
  userId: string | null = null;

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    protected router: Router,
  ) {}

  ngOnInit(): void {
    this.userId = this.route.snapshot.paramMap.get('id');
    if (this.userId) {
      this.isEditMode = true;
      this.userService.findById(this.userId).subscribe((user) => {
        this.dto.pseudo = user.pseudo;
        this.dto.email = user.email;
      });
    }
  }
  submit(): void {
    this.userService.save(this.dto).subscribe(() => {
      this.router.navigate(['/users']);
    })
  }
}
