import { Component } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { ButtonComponent } from '../button/button.component';

@Component({
  selector: 'app-error',
  standalone: true,
  imports: [ReactiveFormsModule, RouterLink, ButtonComponent],
  templateUrl: './error.component.html',
})
export class ErrorComponent {
  code: number = 404;
  title: string = 'Page introuvable';
  message: string = "Le parchemin que vous cherchez n'existe pas";

  constructor(private route: ActivatedRoute, private router: Router) {
    const data = this.route.snapshot.data;
    if (data['code']) {
      this.code = data['code'];
    }
    if (data['title']) {
      this.title = data['title'];
    }
    if (data['message']) {
      this.message = data['message'];
    }
  }

  home(): void {
          this.router.navigate(['/home']);
  }
}
