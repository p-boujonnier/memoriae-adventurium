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
  @Input() variant: 'blood' | 'gold' = 'blood';
  @Output() onClick = new EventEmitter<void>();

  get classes(): string {
    const base =
      'flex items-center tracking-wide gap-2 transition-colors font-enchanted text-2xl border px-3 py-0.5 rounded shadow-md';
    const variants = {
      blood:
        'text-parchment/60 hover:text-parchment bg-blood hover:bg-blood-light border-stone-dark hover:border-blood',
      gold: 'text-parchment/60 hover:text-parchment bg-gold-dark hover:bg-gold border-stone-dark hover:border-gold-dark',
    };
    return `${base} ${variants[this.variant]}`;
  }
}
