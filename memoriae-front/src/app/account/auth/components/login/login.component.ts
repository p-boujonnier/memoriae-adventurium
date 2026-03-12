import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AuthLoginRequestDto } from '../../models/dto/auth-login-request.dto';
import { AuthService } from '../../services/auth.service';
import { ButtonComponent } from '../../../../common/components/button/button.component';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, RouterLink, ButtonComponent],
  templateUrl: './login.component.html',
})
export class LoginComponent {
  dto: AuthLoginRequestDto = {
    identifier: '',
    password: '',
    rememberMe: false,
  };

  errorMessage: string | null = null;

  constructor(
    private authService: AuthService,
    private router: Router,
  ) {}

  submit(): void {
    this.errorMessage = null;
    this.authService.login(this.dto).subscribe({
      next: (response) => {
        if (response.data) {
          this.router.navigate(['/users']);
        } else {
          this.errorMessage = response.message;
        }
      },
      error: (err) => (this.errorMessage = err.message),
    });
  }
}
