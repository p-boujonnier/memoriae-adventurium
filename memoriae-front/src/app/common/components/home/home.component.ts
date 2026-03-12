import { ChangeDetectorRef, Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { UserService } from '../../../account/user/services/user.service';
import { ButtonComponent } from '../button/button.component';

@Component({
  selector: 'home',
  standalone: true,
  imports: [RouterModule, ButtonComponent],
  templateUrl: './home.component.html',
})
export class HomeComponent {
  constructor(private router: Router) {}

  onRegister(): void {
    this.router.navigate(['/register']);
  }
}
