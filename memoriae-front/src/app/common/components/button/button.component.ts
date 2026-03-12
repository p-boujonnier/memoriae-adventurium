import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-button',
  standalone: true,
  template: `
    <button [class]="classes" (click)="onClick.emit()">
      <ng-content />
    </button>
  `,
})
export class ButtonComponent {
  @Input() variant: 'gold' | 'forest' | 'blood' = 'blood';
  @Output() onClick = new EventEmitter<void>();

  get classes(): string {
    const base =
      'text-parchment hover:text-white flex items-center tracking-wide gap-2 transition-colors font-enchanted text-2xl border px-3 py-0.5 rounded shadow-md';
    const variants = {
      gold: 'bg-gold-dark hover:bg-gold border-stone-dark hover:border-gold-dark',
      blood: 'bg-blood hover:bg-blood-light border-stone-dark hover:border-blood',
      forest: 'bg-forest hover:bg-forest-light border-stone-dark hover:border-forest',
    };
    return `${base} ${variants[this.variant]}`;
  }
}
