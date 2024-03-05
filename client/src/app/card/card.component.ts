import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrl: './card.component.scss'
})
export class CardComponent {
  @Input() title = '';
  @Input() subtitle = '';
  @Input() text = '';
  @Input() styleCard!: any;
  @Input() admin!: boolean;
  @Input() type!: boolean; /*true if bonnes pratiques */
}
