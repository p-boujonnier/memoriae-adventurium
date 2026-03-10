import { ChangeDetectorRef, Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { UserService } from '../../../account/user/services/user.service';

@Component({
  selector: 'home',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './home.component.html',
})
export class HomeComponent {
  constructor(
    private router: Router,
  ) {}

  onRegister(): void {
    this.router.navigate(['/register']);
  }
}
