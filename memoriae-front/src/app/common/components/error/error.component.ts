import { Component } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-error',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    RouterLink
  ],
  templateUrl: './error.component.html',
})
export class ErrorComponent {
  code : number = 404;
  title : string = "Page introuvable";
  message : string = "Le parchemin que vous cherchez n'existe pas";

  constructor(private route: ActivatedRoute) {
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
}
