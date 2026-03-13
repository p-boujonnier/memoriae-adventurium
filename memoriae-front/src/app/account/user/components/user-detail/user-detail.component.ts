import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { UserResponse } from '../../models/dtos/user-response.dto';
import { UserService } from '../../services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ButtonComponent } from '../../../../common/components/button/button.component';

@Component({
  selector: 'app-user-detail',
  standalone: true,
  imports: [ButtonComponent],
  templateUrl: './user-detail.component.html',
})
export class UserDetailComponent implements OnInit {
  user: UserResponse | null = null;

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router,
    private cdr: ChangeDetectorRef,
  ) {}

  ngOnInit(): void {
    const userId = this.route.snapshot.paramMap.get('id');
    if (userId) {
      this.userService.findById(userId).subscribe((data) => {
        this.user = data;
        this.cdr.detectChanges();
      });
    }
  }

  onEdit(): void {
    this.router.navigate(['/users', this.user?.id, 'edit']);
  }

  onBack(): void {
    this.router.navigate(['/users']);
  }
}
