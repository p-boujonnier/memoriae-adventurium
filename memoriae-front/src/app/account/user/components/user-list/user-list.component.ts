import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ButtonComponent } from '../../../../common/components/button/button.component';
import { Router, RouterModule } from '@angular/router';
import { UserService } from '../../services/user.service';
import { UserResponse } from '../../models/dtos/user-response.dto';

@Component({
  selector: 'app-user-list',
  standalone: true,
  imports: [RouterModule, ButtonComponent],
  templateUrl: './user-list.component.html',
})
export class UserListComponent implements OnInit {
  users: UserResponse[] = [];

  constructor(
    private userService: UserService,
    private router: Router,
    private cdr: ChangeDetectorRef,
  ) {}

  ngOnInit(): void {
    this.userService.findAll().subscribe((data) => {
      this.users = data;
      this.cdr.detectChanges();
    });
  }

  onCreate(): void {
    this.router.navigate(['/users/new']);
  }

  onEdit(id: string): void {
    this.router.navigate(['/users', id, 'edit']);
  }

  onDelete(id: string): void {
    this.userService.delete(id).subscribe(() => {
      this.users = this.users.filter((user) => user.id !== id);
      this.cdr.detectChanges();
      this.router.navigate(['/users']);
    });
  }
}
