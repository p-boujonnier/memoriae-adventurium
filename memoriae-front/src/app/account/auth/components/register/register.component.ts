import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthRegisterRequestDto } from '../../models/dto/auth-register-request.dto';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './register.component.html',
})
export class RegisterComponent {

  dto : AuthRegisterRequestDto = {
    pseudo : '',
    email : '',
    password : ''
  }

  errorMessage : string |null = null;

  constructor(private authService : AuthService, private router : Router) {}

  submit(): void {
    this.errorMessage = null;
    this.authService.register(this.dto).subscribe({
      next: (response) => {
        if (response.data) {
          this.router.navigate(['/users']);
        } else {
          this.errorMessage = response.message;
        }
      },
      error: (err) => this.errorMessage = err.message
    })
  }
}
